package sls.transferenciaeletronica.core.transferencia.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import sls.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import sls.transferenciaeletronica.core.transferencia.enuns.StatusTransferencia;
import sls.transferenciaeletronica.core.transferencia.util.FormatadorUtil;

@Entity
@Table(name="tab_transferencia")
@AllArgsConstructor
@Builder
public class Transferencia implements Serializable{
	
	private static final long serialVersionUID = 3083637479133171609L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="data_agendamento", columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataAgendamento;
	
	@Column(name="data_transferencia", columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataTransferencia;
	
	@Column(name="conta_origem")
	private String contaOrigem;
	
	@Column(name="conta_destino")
	private String contaDestino;
	
	private BigDecimal valor;
	
	private BigDecimal valorTaxa;
	
	private StatusTransferencia status;

	public Transferencia() {
	}

	public Transferencia(TransferenciaDTO transferenciaDTO, Double valorTaxa) {
		this.dataAgendamento = FormatadorUtil.localdatePadraoBrasil(LocalDate.now());
		this.dataTransferencia = transferenciaDTO.getDataTransferencia();
		this.status = StatusTransferencia.AGUARDANDO;
		this.valorTaxa = new BigDecimal(valorTaxa.toString());
		this.valor = new BigDecimal(transferenciaDTO.getValor().toString());
		this.contaDestino = transferenciaDTO.getContaDestino();
		this.contaOrigem = transferenciaDTO.getContaOrigem();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(LocalDate dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorTaxa() {
		return valorTaxa;
	}

	public void setValorTaxa(BigDecimal valorTaxa) {
		this.valorTaxa = valorTaxa;
	}

	public StatusTransferencia getStatus() {
		return status;
	}

	public void setStatus(StatusTransferencia status) {
		this.status = status;
	}

}
