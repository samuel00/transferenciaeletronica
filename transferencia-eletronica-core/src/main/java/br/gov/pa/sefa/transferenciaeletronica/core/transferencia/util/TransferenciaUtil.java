package br.gov.pa.sefa.transferenciaeletronica.core.transferencia.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.Agendamento;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.enuns.Taxa;

public class TransferenciaUtil {
	
	public static final Long ZERO = 0L;
	
	public static Taxa identificarTaxa(TransferenciaDTO transferenciaDTO, Agendamento agendamento){
		Long diferencaEntreData = agendamento != null ? calculaDiferencaEntreData(agendamento.getData()) : ZERO;
		return null;
		
	}
	
	public static Long calculaDiferencaEntreData(Date data){
			LocalDate dataInicio = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate dataFim = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return ChronoUnit.DAYS.between(dataInicio, dataFim);
	}

}
