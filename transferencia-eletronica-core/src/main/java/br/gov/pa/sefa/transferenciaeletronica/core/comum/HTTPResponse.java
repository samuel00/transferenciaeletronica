package br.gov.pa.sefa.transferenciaeletronica.core.comum;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class HTTPResponse implements Serializable {

    private static final long serialVersionUID = 8795652857990309912L;

    private static final String MENSAGEM_OK = "Requisicao realizada com sucesso!";
    private static final String MENSAGEM_CREATED = "Recurso criado com sucesso!";
    private static final String MENSAGEM_BAD_REQUEST = "Requisicao com má formatação!";
    private static final String MENSAGEM_INTERNAL_SERVER_ERROR = "Erro interno na aplicação!";

    private String mensagem;

    private HttpStatus status;

    private Integer statusCode;

    public HTTPResponse() {
    }

    public HTTPResponse(String mensagem, HttpStatus status, Integer statusCode) {
        setAtributos(mensagem, status, statusCode);
    }

    public HTTPResponse(HttpStatus status) {
        switch (status.value()) {
            case 200:
                setAtributos(MENSAGEM_OK, status, status.value());
                break;
            case 201:
                setAtributos(MENSAGEM_CREATED, status, status.value());
                break;
            case 400:
                setAtributos(MENSAGEM_BAD_REQUEST, status, status.value());
                break;
            case 500:
                setAtributos(MENSAGEM_INTERNAL_SERVER_ERROR, status, status.value());
                break;
        }
    }

    private void setAtributos(String mensagem, HttpStatus status, Integer statusCode) {
        this.mensagem = mensagem;
        this.status = status;
        this.statusCode = statusCode;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
