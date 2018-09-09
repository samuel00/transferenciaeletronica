package sls.transferenciaeletronica.manager.entidade;

import java.util.Calendar;

public class RequisicaoDTO {
	
	private Long id;
	private Calendar data;
	private long tempoExecucao;
	private String ipOrigem;
	private String tipo;
	private Parametro parametro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data){
		this.data = data;
	}

	public long getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(long tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public String getIpOrigem() {
		return ipOrigem;
	}

	public void setIpOrigem(String ipOrigem) {
		this.ipOrigem = ipOrigem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}
}
