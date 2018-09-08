package br.gov.pa.sefa.transferenciaeletronica.api.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.Transferencia;

@JsonPropertyOrder({ "url", "httpStatus", "quantidadeTransferencias", "transferencias" })
public class TransferenciaVO extends PadraoVO{
	
	private static final long serialVersionUID = -2835456641013033351L;
	
	private List<TransferenciaDTO>  transferencias = new ArrayList<>();
	
	public TransferenciaVO() {
	}
	
	public TransferenciaVO(List<Transferencia> transferencias) {
		for(Transferencia transferencia : transferencias){
			TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
			transferenciaDTO.setContaDestino(transferencia.getContaDestino());
			transferenciaDTO.setContaOrigem(transferencia.getContaOrigem());
			transferenciaDTO.setValor(transferencia.getValor());
			transferenciaDTO.setValorTaxa(transferencia.getValorTaxa());
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
