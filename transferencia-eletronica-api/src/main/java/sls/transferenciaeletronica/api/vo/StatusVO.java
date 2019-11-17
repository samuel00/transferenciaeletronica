package sls.transferenciaeletronica.api.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import sls.transferenciaeletronica.core.status.StatusDTO;

public class StatusVO implements Serializable{
	
	private static final long serialVersionUID = -5520555844760498311L;
    public final String HTTP_STATUS_OK;
    private String status;

	@JsonFormat(pattern="dd/MM/yyyy")
    private Date data = new Date();    

	public StatusVO(StatusDTO statusDTO) {
		this.HTTP_STATUS_OK = StatusDTO.HTTP_STATUS_OK;
		this.status = statusDTO.getStatus();
		this.data = statusDTO.getData();
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
