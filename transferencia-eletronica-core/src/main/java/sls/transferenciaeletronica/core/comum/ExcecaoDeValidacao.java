package sls.transferenciaeletronica.core.comum;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

/**
 * Classe para formatar erros de validacao de um objeto em formato JSON
 *
 */
public class ExcecaoDeValidacao extends ExcecaoGenerica {

    private static final long serialVersionUID = 1L;
    private transient final BindingResult bindingResult;

    public ExcecaoDeValidacao(BindingResult bindingResult, String mensagem) {
        super(HttpStatus.BAD_REQUEST, mensagem);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return this.bindingResult;
    }
}
