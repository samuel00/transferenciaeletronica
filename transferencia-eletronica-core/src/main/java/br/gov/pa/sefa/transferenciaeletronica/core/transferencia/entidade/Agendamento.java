package br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade;

import java.util.Date;

public class Agendamento {
	
	private Date data;
	
	public Agendamento() {
		// TODO Auto-generated constructor stub
	}

	public Agendamento(Date date) {
		this.data = date;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	

}
