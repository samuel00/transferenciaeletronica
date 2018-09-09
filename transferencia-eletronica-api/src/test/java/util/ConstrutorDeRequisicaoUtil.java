package util;

public class ConstrutorDeRequisicaoUtil {
	
	private String url;
	
	private static final String URL_TRANSFERENCIA = "/transferencia";
	private static final String URL_ADMINISTRATIVO = "/administrativo";
	
	public ConstrutorDeRequisicaoUtil getUrlCriarTrasnferencia() {
		this.url = URL_TRANSFERENCIA;
		return this;
	}
	
	public ConstrutorDeRequisicaoUtil getUrlAdministrativo() {
		this.url = URL_ADMINISTRATIVO;
		return this;
	}
	
	public String build(){
		return this.url;
	}

	

}
