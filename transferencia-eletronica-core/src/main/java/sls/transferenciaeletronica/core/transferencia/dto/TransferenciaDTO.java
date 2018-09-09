package sls.transferenciaeletronica.core.transferencia.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TransferenciaDTO implements Serializable{

	private static final long serialVersionUID = 8863335127038176340L;
	
	@NotNull(message = "{error.data.transeferencia.not.null}")
	@JsonFormat(pattern="dd/MM/yyyy", timezone = "GMT-3")
	private Date dataTransferencia;
	
	@Null(message = "{error.data.agendamento.null}")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataAgendamento;
	
	@NotNull(message = "{error.valor.not.null}")
	private Double valor;
	
	@NotNull(message = "{error.conta.origem.not.null}")
	private String contaOrigem;
	
	@NotNull(message = "{error.conta.destino.not.null}")
	private String contaDestino;
	
	@Null(message = "{error.valor.taxa.null}")
	private Double valorTaxa;
	
	public TransferenciaDTO() {
	}

	public TransferenciaDTO(Double valor, Date dataTransferencia, String contaOrigem, String contaDestino) {
		this.valor = valor;
		this.dataTransferencia = dataTransferencia;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
	}	

	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Double getValorTaxa() {
		return valorTaxa;
	}

	public void setValorTaxa(Double valorTaxa) {
		this.valorTaxa = valorTaxa;
	}
	
	
	

}
