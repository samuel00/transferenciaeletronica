package br.gov.pa.sefa.transferenciaeletronica.core.transferencia.servico;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.gov.pa.sefa.transferenciaeletronica.core.comum.HTTPResponse;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;

@Service
public class TransferenciaServico {
	
	public HTTPResponse criarTransferencia(TransferenciaDTO transferenciaDTO){
		
        return new HTTPResponse(HttpStatus.OK);
	}

}
