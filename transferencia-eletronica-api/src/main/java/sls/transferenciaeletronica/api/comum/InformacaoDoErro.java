package sls.transferenciaeletronica.api.comum;

import java.io.Serializable;

/**
 * Classe empacotando todas as informacoes de um erro para ser reportado ao
 * cliente da API.
 *
 */
public class InformacaoDoErro implements Serializable {

    private static final long serialVersionUID = -2587351090678336453L;
    public final int httpStatus;
    public final String url;
    public final String mensagemDeErro;

    public InformacaoDoErro(int httpStatus, String url, String mensagemDeErro) {
        this.httpStatus = httpStatus;
        this.url = url;
        this.mensagemDeErro = mensagemDeErro;
    }
}
