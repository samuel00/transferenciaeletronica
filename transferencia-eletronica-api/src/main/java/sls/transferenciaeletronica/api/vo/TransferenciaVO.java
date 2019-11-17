package sls.transferenciaeletronica.api.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import sls.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import sls.transferenciaeletronica.core.transferencia.entidade.Transferencia;

@JsonPropertyOrder({ "url", "httpStatus", "quantidadeTransferencias", "transferencias" })
public class TransferenciaVO extends PadraoVO{
	
	private static final long serialVersionUID = -2835456641013033351L;
	
	private List<TransferenciaDTO>  transferencias = new ArrayList<>();
	
	private TransferenciaDTO transferencia = new TransferenciaDTO();
	
	public TransferenciaVO() {}
	
	public TransferenciaVO(TransferenciaDTO transferenciaDTO) {
		this.transferencia.setContaDestino(transferenciaDTO.getContaDestino());
		this.transferencia.setContaOrigem(transferenciaDTO.getContaOrigem());
		this.transferencia.setValor(transferenciaDTO.getValor().doubleValue());
		this.transferencia.setValorTaxa(transferenciaDTO.getValorTaxa().doubleValue());
		this.transferencia.setDataTransferencia(transferenciaDTO.getDataTransferencia());
		this.transferencia.setDataAgendamento(transferenciaDTO.getDataAgendamento());
	}
	
	public TransferenciaVO(List<Transferencia> transferencias) {
		for(Transferencia transferencia : transferencias){
			TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
			transferenciaDTO.setContaDestino(transferencia.getContaDestino());
			transferenciaDTO.setContaOrigem(transferencia.getContaOrigem());
			transferenciaDTO.setValor(transferencia.getValor().doubleValue());
			transferenciaDTO.setValorTaxa(transferencia.getValorTaxa().doubleValue());
			transferenciaDTO.setDataTransferencia(transferencia.getDataTransferencia());
			transferenciaDTO.setDataAgendamento(transferencia.getDataAgendamento());
			this.transferencias.add(transferenciaDTO);
		}
	}
	
	public Integer getQuantidadeTransferencias(){
		return this.transferencias.size();
	}

	public List<TransferenciaDTO> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(List<TransferenciaDTO> transferencias) {
		this.transferencias = transferencias;
	}

}
