package sls.transferenciaeletronica.api.vo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import sls.transferenciaeletronica.manager.entidade.Requisicao;
import sls.transferenciaeletronica.manager.entidade.RequisicaoDTO;

@JsonPropertyOrder({ "url", "httpStatus", "quantidadeRequisicoes", "requisicoes" })
public class RequisicaoVO extends PadraoVO {

	private static final long serialVersionUID = -46991773045826365L;

	private transient Set<RequisicaoDTO> requisicoes;

	public RequisicaoVO() {
		this.requisicoes = new HashSet<RequisicaoDTO>();
	}

	public RequisicaoVO(List<Requisicao> requisicoes) {
		this();
		for (Requisicao requisicao : requisicoes) {
			RequisicaoDTO requisicaoDTO = new RequisicaoDTO();
			requisicaoDTO.setData(requisicao.getData());
			requisicaoDTO.setIpOrigem(requisicao.getIpOrigem());
			requisicaoDTO.setTempoExecucao(requisicao.getTempoExecucao());
			requisicaoDTO.setTipo(requisicao.getTipo());
			requisicaoDTO.setParametro(requisicao.getParametro());
			requisicaoDTO.setErro(requisicao.getErro());
			this.requisicoes.add(requisicaoDTO);
		}
	}

	public Integer getQuantidadeRequisicoes() {
		return this.requisicoes.size();
	}

	public Set<RequisicaoDTO> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(Set<RequisicaoDTO> requisicoes) {
		this.requisicoes = requisicoes;
	}

}
