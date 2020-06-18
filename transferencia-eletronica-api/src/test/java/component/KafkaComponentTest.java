package component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sls.transferenciaeletronica.core.transferencia.entidade.Transferencia;
import sls.transferenciaeletronica.core.transferencia.messageria.KafkaComponent;

@SpringBootTest

@ActiveProfiles("test")
public class KafkaComponentTest {

    @Mock
    KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    KafkaComponent kafkaComponent;

    static final String TOPICO_NOME = "transfer-events";

    @Test
    public void enviaTransferenciaEventoComFalha() {
        Transferencia transferencia = new Transferencia().builder()
                .contaDestino("123")
                .contaOrigem("1234")
                .id(123L)
                .dataTransferencia(LocalDate.now())
                .dataAgendamento(LocalDate.now())
                .valor(BigDecimal.TEN)
                .build();

        SettableListenableFuture future = new SettableListenableFuture();
        future.setException(new RuntimeException("Exception Calling Kafka Server"));

        when(kafkaTemplate.send(isA(ProducerRecord.class))).thenReturn(future);

        kafkaComponent.enviaTransferenciaEvento(transferencia);

        assertThrows(Exception.class, () -> kafkaComponent.enviaTransferenciaEvento(transferencia).get());
    }

    @Test
    public void enviaTransferenciaEventoComSucesso()
            throws JsonProcessingException, ExecutionException, InterruptedException {
        //given
        Transferencia transferencia = new Transferencia().builder()
                .contaDestino("123")
                .contaOrigem("1234")
                .id(123L)
                .dataTransferencia(LocalDate.now())
                .dataAgendamento(LocalDate.now())
                .valor(BigDecimal.TEN)
                .build();

        String transferenciaJson = new ObjectMapper().writeValueAsString(transferencia);
        SettableListenableFuture future = new SettableListenableFuture();

        ProducerRecord<Integer, String> producerRecord = new ProducerRecord(TOPICO_NOME, transferencia.getId().toString(), transferenciaJson);
        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition(TOPICO_NOME, 1),
                1,
                1,
                342,
                System.currentTimeMillis(),
                1,
                2);
        SendResult<Integer, String> sendResult = new SendResult<Integer, String>(producerRecord, recordMetadata);

        future.set(sendResult);

        when(kafkaTemplate.send(isA(ProducerRecord.class))).thenReturn(future);

        //when
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaComponent.enviaTransferenciaEvento(transferencia);

        //then
        SendResult<String, String> sendResult1 = listenableFuture.get();
        assert sendResult1.getRecordMetadata().partition() == 1;
    }
}
