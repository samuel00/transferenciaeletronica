package sls.transferenciaeletronica.core.status;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe para representar o status do sistema buscando no banco de dados a data
 * atual.
 *
 */
public class StatusDTO implements Serializable {

    private static final long serialVersionUID = -5520555844760498311L;
    private String status;

	@JsonFormat(pattern="dd/MM/yyyy")
    private Date data;
    
    
    public StatusDTO() {}
    
    public StatusDTO(String status, Date data) {
		this.status = status;
		this.data = data;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
