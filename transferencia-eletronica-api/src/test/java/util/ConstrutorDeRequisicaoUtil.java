package util;

public class ConstrutorDeRequisicaoUtil {
	
	private String url;
	
	private static final String URL_TRANSFERENCIA = "/transferencia";
	
	public ConstrutorDeRequisicaoUtil getUrlCriarTrasnferencia() {
		this.url = URL_TRANSFERENCIA;
		return this;
	}
	
	public String build(){
		return this.url;
	}

	

}
