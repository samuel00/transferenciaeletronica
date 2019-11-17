package sls.transferenciaeletronica.core.transferencia.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import sls.transferenciaeletronica.core.comum.ExcecaoDeNegocio;
import sls.transferenciaeletronica.core.comum.MensagemUtils;
import sls.transferenciaeletronica.core.comum.OcorrenciaExcecao;
import sls.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import sls.transferenciaeletronica.core.transferencia.enuns.MotivoErro;
import sls.transferenciaeletronica.core.transferencia.enuns.StatusTransferencia;
import sls.transferenciaeletronica.core.transferencia.util.FormatadorUtil;
import sls.transferenciaeletronica.core.transferencia.util.TransferenciaUtil;

@Entity
@Table(name="tab_transferencia")
public class Transferencia {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="data_agendamento")
	private LocalDate dataAgendamento;
	
	@Column(name="data_transferencia")
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

	public Transferencia(TransferenciaDTO transferenciaDTO) throws OcorrenciaExcecao {
		setDataAgendamento(FormatadorUtil.localdatePadraoBrasil(LocalDate.now()));
		setDataTransferencia(transferenciaDTO.getDataTransferencia());
		setStatus(StatusTransferencia.AGUARDANDO);
		setValorTaxa(transferenciaDTO);
		setValor(new BigDecimal(transferenciaDTO.getValor().toString()));
		setContaDestino(transferenciaDTO.getContaDestino());
		setContaOrigem(transferenciaDTO.getContaOrigem());
	}

	private void setValorTaxa(TransferenciaDTO transferenciaDTO) throws OcorrenciaExcecao {
		this.valorTaxa = BigDecimal.valueOf(
				TransferenciaUtil.calcularTaxa(transferenciaDTO)
				.orElseThrow(()-> new ExcecaoDeNegocio(MotivoErro.SEM_TAXA, MensagemUtils.getMensagenSemTaxa())));		
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

	public StatusTransferencia getStatus() {
		return status;
	}

	public void setStatus(StatusTransferencia status) {
		this.status = status;
	}

}
