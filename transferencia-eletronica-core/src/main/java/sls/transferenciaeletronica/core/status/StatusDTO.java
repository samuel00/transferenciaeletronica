package sls.transferenciaeletronica.core.status;

import java.io.Serializable;

/**
 * Classe para representar o status do sistema buscando no banco de dados a data
 * atual.
 *
 */
public class StatusDTO implements Serializable {

    private static final long serialVersionUID = -5520555844760498311L;
    private String status;
    private String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
