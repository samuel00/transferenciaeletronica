package sls.transferenciaeletronica.manager.entidade;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TAB_REQUISICAO_IP_BLOQUEADO", schema="MONITORAMENTO_SERVICE")
public class IPBloqueado implements Serializable{
	
	private static final long serialVersionUID = 2969270434992581763L;

	@Id
	@Column(name="tarib_id")
	private Long id;
	
	@Column(name="tarib_data_insercao", nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar dataInsercao;
	
	@Column(name="tarib_ip_bloqueado", nullable=false, length = 15)
	private String ipBloqueado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(Calendar dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public String getIpBloqueado() {
		return ipBloqueado;
	}

	public void setIpBloqueado(String ipBloqueado) {
		this.ipBloqueado = ipBloqueado;
	}
	
	
}
