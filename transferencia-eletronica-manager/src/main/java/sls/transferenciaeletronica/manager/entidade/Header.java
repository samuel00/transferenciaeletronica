package sls.transferenciaeletronica.manager.entidade;

import java.io.Serializable;

public class Header implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8192030432590523692L;

	private String descricao;
	
	private String valor;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
