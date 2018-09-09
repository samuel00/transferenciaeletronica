package sls.transferenciaeletronica.manager.entidade;

public class MotivoExcecao {
	private String campo;
	
	private String menssagem;
	
	private String valorInvalido;

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMenssagem() {
		return menssagem;
	}

	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}

	public String getValorInvalido() {
		return valorInvalido;
	}

	public void setValorInvalido(String valorInvalido) {
		this.valorInvalido = valorInvalido;
	}
	
}
