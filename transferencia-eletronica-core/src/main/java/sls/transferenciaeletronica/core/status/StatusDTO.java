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
    public static final String HTTP_STATUS_OK = "OK";
    private String status;

	@JsonFormat(pattern="dd/MM/yyyy")
    private Date data = new Date();    
    
    public StatusDTO() {}
           
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public Date getData() {
		return new Date(this.data.getTime());
	}

	public void setData(Date data) {
		this.data = new Date(data.getTime());
	}
}
