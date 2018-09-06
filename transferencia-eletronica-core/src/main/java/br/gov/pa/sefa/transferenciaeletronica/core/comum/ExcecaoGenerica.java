package br.gov.pa.sefa.transferenciaeletronica.core.comum;

import org.springframework.http.HttpStatus;

/**
 * Classe para formatar em JSON um excecao, com o codigo e a mensagem.
 *
 */
public class ExcecaoGenerica extends Exception {

    private static final long serialVersionUID = 1L;
    protected final HttpStatus httpCodigoStatus;

    public ExcecaoGenerica(HttpStatus httpCodigoStatus, String mensagem) {
        super(mensagem);
        this.httpCodigoStatus = httpCodigoStatus;
    }

    public HttpStatus getHttpCodigoStatus() {
        return httpCodigoStatus;
    }
}
