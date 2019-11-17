package sls.transferenciaeletronica.core.transferencia.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import sls.transferenciaeletronica.core.comum.ExcecaoDeNegocio;
import sls.transferenciaeletronica.core.comum.MensagemUtils;
import sls.transferenciaeletronica.core.comum.OcorrenciaExcecao;
import sls.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import sls.transferenciaeletronica.core.transferencia.enuns.MotivoErro;
import sls.transferenciaeletronica.core.transferencia.enuns.Taxa;

public class TransferenciaUtil {

	public static final Long ZERO_DIAS = 0L;
	public static final Long DEZ_DIAS = 10L;
	public static final Object OBJETO_NULO = null;

	public static Optional<Double> calcularTaxa(TransferenciaDTO transferenciaDTO) throws OcorrenciaExcecao {
		Long diferencaEntreData = calculaDiferencaEntreData(transferenciaDTO.getDataTransferencia());
		if (diferencaEntreData.compareTo(ZERO_DIAS) < 0)
			throw new ExcecaoDeNegocio(MotivoErro.SEM_TAXA, MensagemUtils.getMensagenSemTaxa());
		if (diferencaEntreData.compareTo(DEZ_DIAS) == 0)
			return Taxa.A.calcularValor(transferenciaDTO.getValor(), diferencaEntreData);
		if (diferencaEntreData <= DEZ_DIAS)
			return Taxa.B.calcularValor(transferenciaDTO.getValor(), diferencaEntreData);
		else
			return Taxa.C.calcularValor(transferenciaDTO.getValor(), diferencaEntreData);
	}

	public static Long calculaDiferencaEntreData(LocalDate data) {
		LocalDate hoje = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();		
		Long diferencaEntreData = Duration.between(hoje.atStartOfDay(),data.atStartOfDay()).toDays();
	    return diferencaEntreData;
	}

}
