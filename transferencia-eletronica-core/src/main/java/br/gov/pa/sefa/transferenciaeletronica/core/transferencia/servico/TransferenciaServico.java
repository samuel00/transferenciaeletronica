package br.gov.pa.sefa.transferenciaeletronica.core.transferencia.servico;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.sefa.transferenciaeletronica.core.comum.HTTPResponse;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.Transferencia;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.repositorio.TransferenciaRepositorio;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.util.TransferenciaUtil;

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
		return new HTTPResponse("Não foi encontrado uma taxa para os parametros informados", HttpStatus.BAD_REQUEST,
				HttpStatus.BAD_REQUEST.value());

	}
	
	public List<Transferencia> getAgendamentos() {
			return transferenciaRepositorio.getAgendamentos();
	}

}
