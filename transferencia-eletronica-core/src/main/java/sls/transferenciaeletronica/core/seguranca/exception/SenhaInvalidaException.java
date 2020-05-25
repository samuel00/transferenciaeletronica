package sls.transferenciaeletronica.core.seguranca.exception;

public class SenhaInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 7894773048401532981L;

	public SenhaInvalidaException() {
		super("Senha inválida");
	}
}
