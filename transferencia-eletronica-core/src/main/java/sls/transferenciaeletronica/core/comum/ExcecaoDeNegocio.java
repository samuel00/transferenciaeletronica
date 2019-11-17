package sls.transferenciaeletronica.core.comum;

import org.springframework.http.HttpStatus;

import sls.transferenciaeletronica.core.transferencia.enuns.MotivoErro;

public class ExcecaoDeNegocio extends OcorrenciaExcecao{

    private static final long serialVersionUID = 1L;
    private final MotivoErro motivoErro;
    private final HttpStatus httpStatus;
    
    public ExcecaoDeNegocio(MotivoErro motivoErro, String mensagem) {
        super(mensagem);
        this.motivoErro = motivoErro;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

	@Override
	public MotivoErro getMotivoErro() {
		return this.motivoErro;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

}
