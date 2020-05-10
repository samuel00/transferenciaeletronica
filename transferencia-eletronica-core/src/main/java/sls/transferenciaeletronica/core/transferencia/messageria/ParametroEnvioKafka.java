package sls.transferenciaeletronica.core.transferencia.messageria;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sls.transferenciaeletronica.core.transferencia.entidade.Transferencia;

@NoArgsConstructor
@Data
@Slf4j
public class ParametroEnvioKafka {
	
	private String key;
	private String value;
	
	public ParametroEnvioKafka comKey(String key) {
		this.key = key;
		return this;
	}
	
	public ParametroEnvioKafka comValue(Transferencia value) {
		try {
			this.value = new ObjectMapper().registerModule(new ParameterNamesModule())
		            .registerModule(new Jdk8Module())
		            .registerModule(new JavaTimeModule())
		            .writeValueAsString(value);
		} catch (JsonProcessingException e) {
			log.error("Erro conversao de json {} ", e.getCause());
		}
		return this;
	}

}
