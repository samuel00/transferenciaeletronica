package sls.transferenciaeletronica.manager.entidade;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TAB_REQUISICAO")
public class Requisicao implements Serializable{

	private static final long serialVersionUID = -7700060984818848442L;
	
	@Id
	@Column(name="tar_id")
	@GeneratedValue
	private Long id;
	
	@Column(name="tar_data", nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	@Column(name="tar_tempo_execucao", nullable=false)
	private long tempoExecucao;
	
	@Column(name="tar_ip_origem", nullable=false, length = 15)
	private String ipOrigem;
	
	@Column(name="tar_tipo", nullable=false, length = 6)
	private String tipo;
	
	@Column(name="tar_contexto", nullable=false, length = 125)
	private String contexto;
	
	@Column(name="tar_path", nullable=false, length = 125)
	private String path;
	
	@Column(name="tar_codigo", nullable=false, length = 125)
	private Integer codigo;
	
	@Column(name="tar_uuid", nullable=false, length = 125)
	private String uuid;
	
	@OneToOne(mappedBy="requisicao", cascade = CascadeType.ALL)
	private Parametro parametro;
	
	@OneToOne(mappedBy="requisicao", cascade = CascadeType.ALL)
	private Erro erro;
	
	public Requisicao(){}
	
	public Requisicao(RequisicaoDTO dto){
		setData(dto.getData());
		setTempoExecucao(dto.getTempoExecucao());
		setIpOrigem(dto.getIpOrigem());
		setTipo(dto.getTipo());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data){
		this.data = data;
	}

	public long getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(long tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public String getIpOrigem() {
		return ipOrigem;
	}

	public void setIpOrigem(String ipOrigem) {
		this.ipOrigem = ipOrigem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public Erro getErro() {
		return erro;
	}

	public void setErro(Erro erro) {
		this.erro = erro;
	}

	public String getContexto() {
		return contexto;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}

	
	