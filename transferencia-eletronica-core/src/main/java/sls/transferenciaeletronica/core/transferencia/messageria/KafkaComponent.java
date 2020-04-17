package sls.transferenciaeletronica.core.transferencia.messageria;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import sls.transferenciaeletronica.core.transferencia.entidade.Transferencia;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class KafkaComponent {

	/*
	 * @Autowired private KafkaTemplate<String, String> kafkaTemplate;
	 * 
	 * private final String TOPIC = "transfer-events";
	 * 
	 * public void enviaTransferenciaEvento(Transferencia transferencia) { try {
	 * String key = transferencia.getId().toString(); String value = new
	 * ObjectMapper().writeValueAsString(transferencia); ProducerRecord<String,
	 * String> producerRecord = buildProducerRecord(key, value, TOPIC);
	 * ListenableFuture<SendResult<String, String>> listenableFuture =
	 * this.kafkaTemplate.send(producerRecord); listenableFuture.addCallback(new
	 * PostEnvioMensagem(key, value)); } catch (JsonProcessingException e) {
	 * log.error("Erro conversao de json {} ", e.getCause()); } }
	 * 
	 * private ProducerRecord<String, String> buildProducerRecord(String key, String
	 * value, String topic) { List<Header> recordHeaders = Arrays.asList(new
	 * RecordHeader("event-source", "scanner".getBytes())); return new
	 * ProducerRecord<>(topic, null, key, value, recordHeaders); }
	 */

}
