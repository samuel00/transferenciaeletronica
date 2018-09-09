package sls.transferenciaeletronica.core.transferencia.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import sls.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import sls.transferenciaeletronica.core.transferencia.enuns.StatusTransferencia;

@Entity
@Table(name="tab_transferencia")
public class Transferencia {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="data_agendamento")
	private Date dataAgendamento;
	
	@Column(name="data_transferencia")
	private Date dataTransferencia;
	
	@Column(name="conta_origem")
	private String contaOrigem;
	
	@Column(name="conta_destino")
	private String contaDestino;
	
	private Double valor;
	
	private Double valorTaxa;
	
	private StatusTransferencia status;

	public Transferencia() {
	}

	public Transferencia(TransferenciaDTO transferenciaDTO, Double valorTaxa) {
		this.dataAgendamento = new Date();
		this.dataTransferencia = transferenciaDTO.getDataTransferencia();
		this.status = StatusTransferencia.AGUARDANDO;
		this.valorTaxa = valorTaxa;
		this.valor = transferenciaDTO.getValor();
		this.contaDestino = transferenciaDTO.getContaDestino();
		this.contaOrigem = transferenciaDTO.getContaOrigem();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorTaxa() {
		return valorTaxa;
	}

	public void setValorTaxa(Double valorTaxa) {
		this.valorTaxa = valorTaxa;
	}

	public StatusTransferencia getStatus() {
		return status;
	}

	public void setStatus(StatusTransferencia status) {
		this.status = status;
	}

}
