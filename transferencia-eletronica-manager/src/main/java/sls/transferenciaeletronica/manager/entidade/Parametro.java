package sls.transferenciaeletronica.manager.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TAB_REQUISICAO_PARAMETRO")
public class Parametro implements Serializable{
	
	private static final long serialVersionUID = -221708863259338535L;
	
	@Id
	@Column(name="tap_id")
	@GeneratedValue
	@JsonIgnore
	private long id;
	
	@OneToOne
	@JoinColumn(name="tap_requisicao_id")
	@JsonIgnore
	private Requisicao requisicao;
	
	@Column(name= "tap_header", nullable=false, updatable = false, columnDefinition="clob")
	@Lob
	private String header;
	
	@Column(name= "tap_entrada", nullable=false, updatable = false, columnDefinition="clob")
	@Lob
	private String entrada;
	
	@Column(name= "tap_saida", nullable=true, updatable = false, columnDefinition="clob")
	@Lob
	private String saida;
	
	@Column(name= "tap_metodo_invocado", nullable=false, updatable = false)
	private String metodoInvocado;
	
	@Column(name= "tap_classe_invocada", nullable=false, updatable = false)
	private String classeInvocada;
	
	public Parametro(){}
	
	public Parametro(ParametroDTO dto){
		setHeader(dto.getHeader());
		setEntrada(dto.getEntrada());
		setSaida(dto.getSaida());
		setMetodoInvocado(dto.getMetodoInvocado());
		setClasseInvocada(dto.getClasseInvocada());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada){
		this.entrada = entrada;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public String getMetodoInvocado() {
		return metodoInvocado;
	}

	public void setMetodoInvocado(String metodoInvocado) {
		this.metodoInvocado = metodoInvocado;
	}

	public String getClasseInvocada() {
		return classeInvocada;
	}

	public void setClasseInvocada(String classeInvocada) {
		this.classeInvocada = classeInvocada;
	}
}