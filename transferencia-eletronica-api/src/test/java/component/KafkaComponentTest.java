package component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.SettableListenableFuture;

import sls.transferenciaeletronica.core.transferencia.entidade.Transferencia;
import sls.transferenciaeletronica.core.transferencia.messageria.KafkaComponent;

@ExtendWith(MockitoExtension.class) public class KafkaComponentTest {

    @Mock KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks KafkaComponent kafkaComponent;

    @Test public void enviaTransferenciaEvento() {
	Transferencia transferencia =	new Transferencia().builder()
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

}
