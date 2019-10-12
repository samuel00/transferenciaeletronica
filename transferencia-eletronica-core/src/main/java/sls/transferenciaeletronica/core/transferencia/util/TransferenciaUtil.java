package sls.transferenciaeletronica.core.transferencia.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import sls.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import sls.transferenciaeletronica.core.transferencia.enuns.Taxa;

public class TransferenciaUtil {

	public static final Long ZERO_DIAS = 0L;
	public static final Long DEZ_DIAS = 10L;
	public static final Object OBJETO_NULO = null;

	public static Double calcularTaxa(TransferenciaDTO transferenciaDTO) {
		Long diferencaEntreData = calculaDiferencaEntreData(transferenciaDTO.getDataTransferencia());
		if (diferencaEntreData.compareTo(ZERO_DIAS) < 0)
			return Taxa.VAZIA.calcular(transferenciaDTO.getValor(), diferencaEntreData);
		if (diferencaEntreData.compareTo(DEZ_DIAS) == 0)
			return Taxa.A.calcular(transferenciaDTO.getValor(), diferencaEntreData);
		if (diferencaEntreData <= DEZ_DIAS)
			return Taxa.B.calcular(transferenciaDTO.getValor(), diferencaEntreData);
		else
			return Taxa.C.calcular(transferenciaDTO.getValor(), diferencaEntreData);
	}

	public static Long calculaDiferencaEntreData(LocalDate data) {
		LocalDate hoje = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();		
		Long diferencaEntreData = Duration.between(hoje.atStartOfDay(),data.atStartOfDay()).toDays();
	    return diferencaEntreData;
	}

}
