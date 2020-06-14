package sls.transferenciaeletronica.manager.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TAB_REQUISICAO_ERRO")
public class Erro implements Serializable{
	
	private static final long serialVersionUID = -4609689208810205727L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tae_id")
	@JsonIgnore
	private long id;
	
	@OneToOne
	@JoinColumn(name="tae_requisicao_id", unique=true, nullable=false, updatable=false)
	@JsonIgnore
	private Requisicao requisicao;
	
	@Column(name= "tae_motivo_ocorrencia", nullable=true, updatable = false, columnDefinition = "LONGTEXT")
	@Lob
	private String motivo;
	
	@Column(name= "tae_classe_ocorrencia", nullable=true, updatable = false)
	private String classe;
	
	@Column(name= "tae_metodo_ocorrencia", nullable=true, updatable = false)
	private String metodo;
	
	@Column(name= "tae_stacktrace",  updatable = false, columnDefinition = "LONGTEXT")
	@Lob
	private String stacktrace;

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}
	
	

}
