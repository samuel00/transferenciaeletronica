package util;

public class ConstrutorDeRequisicaoUtil {
	
	private String url;
	
	private static final String URL_TRANSFERENCIA = "/transferencia";
	private static final String URL_ADMINISTRATIVO = "/administrativo";
	private static final String URL_AUTENTICACAO = "/usuario/autenticar";
	
	public ConstrutorDeRequisicaoUtil getUrlCriarTrasnferencia() {
		this.url = URL_TRANSFERENCIA;
		return this;
	}
	
	public ConstrutorDeRequisicaoUtil getUrlAdministrativo() {
		this.url = URL_ADMINISTRATIVO;
		return this;
	}

	public ConstrutorDeRequisicaoUtil getUrlAutenticacao() {
		this.url = URL_AUTENTICACAO;
		return this;
	}
	
	public String build(){
		return this.url;
	}

	

}
