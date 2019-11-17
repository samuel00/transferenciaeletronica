package sls.transferenciaeletronica.core.comum;

import org.springframework.http.HttpStatus;

import sls.transferenciaeletronica.core.transferencia.enuns.MotivoErro;

public abstract class OcorrenciaExcecao extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public OcorrenciaExcecao(String mensagem) {
		super(mensagem);
	}

	public abstract MotivoErro getMotivoErro();
	
	public abstract HttpStatus getHttpStatus();

}
