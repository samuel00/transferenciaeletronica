package sls.transferenciaeletronica.api.comum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Classe que encapsula informacoes de erros de validacao a serem reportados ao
 * cliente da API.
 *
 */
public class InformacaoDeErroDeValidacao extends InformacaoDoErro {

    private static final long serialVersionUID = 8483363174439098434L;
    private Map<String, String> erros;

    public InformacaoDeErroDeValidacao(int httpStatus, String url, String mensagemDeErro, BindingResult bindingResult) {
        super(httpStatus, url, mensagemDeErro);
        this.erros = new HashMap<>();
        preencherErrosDeValidacao(bindingResult);
    }

    private void preencherErrosDeValidacao(BindingResult bindingResult) {
        List<FieldError> errosDeValidacao = bindingResult.getFieldErrors();
        for (FieldError erro : errosDeValidacao) {
            this.erros.put(erro.getField(), erro.getDefaultMessage());
        }
    }

    public Map<String, String> getErros() {
        return erros;
    }
}
