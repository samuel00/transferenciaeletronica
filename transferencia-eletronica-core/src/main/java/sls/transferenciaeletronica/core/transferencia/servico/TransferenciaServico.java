package sls.transferenciaeletronica.core.transferencia.servico;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sls.transferenciaeletronica.core.comum.ExcecaoGenerica;
import sls.transferenciaeletronica.core.comum.HTTPResponse;
import sls.transferenciaeletronica.core.comum.MensagemUtils;
import sls.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import sls.transferenciaeletronica.core.transferencia.entidade.Transferencia;
import sls.transferenciaeletronica.core.transferencia.repositorio.TransferenciaRepositorio;
import sls.transferenciaeletronica.core.transferencia.util.TransferenciaUtil;

@Service
public class TransferenciaServico {

	@Autowired
	private TransferenciaRepositorio transferenciaRepositorio;

	@Transactional
	public HTTPResponse criarTransferencia(TransferenciaDTO transferenciaDTO) {
		Double valorTaxa = TransferenciaUtil.calcularTaxa(transferenciaDTO);
		if (Objects.nonNull(valorTaxa)) {
			Transferencia transferencia = new Transferencia(transferenciaDTO, valorTaxa);
			transferenciaRepositorio.salvar(transferencia);
			return new HTTPResponse(HttpStatus.CREATED);
		}
		return new HTTPResponse(MensagemUtils.getMensagenSemTaxa(), HttpStatus.BAD_REQUEST,
				HttpStatus.BAD_REQUEST.value());

	}

	public List<Transferencia> getAgendamentos() throws ExcecaoGenerica {
		try {
			List<Transferencia> transferencias = transferenciaRepositorio.getAgendamentos();
			if (transferencias == null) {
				throw new ExcecaoGenerica(HttpStatus.NOT_FOUND, MensagemUtils.getMensagenSemAgendamento());
			}
			return transferencias;
		} catch (ExcecaoGenerica e) {
			throw e;
		}

	}

}
