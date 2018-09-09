package sls.transferenciaeletronica.api.vo;

import java.io.Serializable;

public class PadraoVO implements Serializable{

	private static final long serialVersionUID = -6678558048058104346L;
	private int httpStatus;
	private String url;

	public PadraoVO() {
    }

	public PadraoVO(String url, int codigoHttpResposta) {
        this.url = url;
        this.httpStatus = codigoHttpResposta;
    }

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int codigoHttpResposta) {
		this.httpStatus = codigoHttpResposta;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
