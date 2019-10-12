package sls.transferenciaeletronica.api.vo;

import java.io.Serializable;

public class PadraoVO implements Serializable {

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

	private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
		throw new java.io.NotSerializableException(getClass().getName());
	}

	private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
		throw new java.io.NotSerializableException(getClass().getName());
	}

}
