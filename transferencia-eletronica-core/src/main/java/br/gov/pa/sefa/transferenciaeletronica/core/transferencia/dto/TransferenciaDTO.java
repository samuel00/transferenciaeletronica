package br.gov.pa.sefa.transferenciaeletronica.core.transferencia.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.Agendamento;

public class TransferenciaDTO implements Serializable{

	private static final long serialVersionUID = 8863335127038176340L;
	
	private Date data;
	
	@NotNull(message = "{error.valor.not.null}")
	private Double valor;
	
	private Agendamento agendamento;
	
	public TransferenciaDTO() {
		data = new Date();
	}

	public TransferenciaDTO(Double valor) {
		this.valor = valor;
		this.data = new Date();
		this.agendamento = new Agendamento(new Date());
	}

	public Date getData() {
		return data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
	
	

}
