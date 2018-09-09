package sls.transferenciaeletronica.manager.entidade;

public class ParametroDTO {
	
	private long id;
	private Requisicao requisicao;
	private String header;
	private String entrada;
	private String saida;
	private String metodoInvocado;
	private String classeInvocada;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada){
		this.entrada = entrada;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public String getMetodoInvocado() {
		return metodoInvocado;
	}

	public void setMetodoInvocado(String metodoInvocado) {
		this.metodoInvocado = metodoInvocado;
	}

	public String getClasseInvocada() {
		return classeInvocada;
	}

	public void setClasseInvocada(String classeInvocada) {
		this.classeInvocada = classeInvocada;
	}
}
