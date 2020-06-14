package sls.transferenciaeletronica.core.transferencia.messageria;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import sls.transferenciaeletronica.core.transferencia.entidade.Transferencia;

@Component
public class KafkaComponent {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private final String TOPIC = "transfer-events";

	public ListenableFuture<SendResult<String, String>> enviaTransferenciaEvento(Transferencia transferencia) {
		ParametroEnvioKafka parametroEnvioKafka = criarParametroKafka(transferencia);
		ProducerRecord<String, String> producerRecord = buildProducerRecord(parametroEnvioKafka.getKey(),
				parametroEnvioKafka.getValue(), TOPIC);
		ListenableFuture<SendResult<String, String>> listenableFuture = this.kafkaTemplate.send(producerRecord);
		listenableFuture
				.addCallback(new PostEnvioMensagem(parametroEnvioKafka.getKey(), parametroEnvioKafka.getValue()));
		return listenableFuture;
	}

	private ParametroEnvioKafka criarParametroKafka(Transferencia transferencia) {
		return new ParametroEnvioKafka().comKey(transferencia.getId().toString()).comValue(transferencia);
	}

	private ProducerRecord<String, String> buildProducerRecord(String key, String value, String topic) {
		List<Header> recordHeaders = Arrays.asList(new RecordHeader("event-source", "scanner".getBytes()));
		return new ProducerRecord<>(topic, null, key, value, recordHeaders);
	}

}
