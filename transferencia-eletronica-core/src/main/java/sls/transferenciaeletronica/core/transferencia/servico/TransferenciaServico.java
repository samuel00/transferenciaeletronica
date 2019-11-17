package sls.transferenciaeletronica.core.transferencia.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sls.transferenciaeletronica.core.comum.ExcecaoGenerica;
import sls.transferenciaeletronica.core.comum.MensagemUtils;
import sls.transferenciaeletronica.core.comum.OcorrenciaExcecao;
import sls.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import sls.transferenciaeletronica.core.transferencia.entidade.Transferencia;
import sls.transferenciaeletronica.core.transferencia.repositorio.TransferenciaRepositorio;

@Service
public class TransferenciaServico {

	@Autowired
	private TransferenciaRepositorio transferenciaRepositorio;

	@Transactional
	public TransferenciaDTO criarTransferencia(TransferenciaDTO transferenciaDTO) throws OcorrenciaExcecao {
		
			Transferencia transferencia = transferenciaRepositorio.salvar(new Transferencia(transferenciaDTO));
			return new TransferenciaDTO(transferencia);
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
