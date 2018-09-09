package sls.transferenciaeletronica.core.status;

import java.util.Date;

import org.springframework.stereotype.Repository;

import sls.transferenciaeletronica.core.comum.BaseRepositorio;

/**
 * Classe para retornar a data/hora do banco de dados, com o objetivo de servir
 * como status de conexao/funcionado do banco de dados.
 *
 */
@Repository
public class StatusRepositorio extends BaseRepositorio<StatusDTO, Long> {
    public StatusDTO buscarStatus() {
        
        StatusDTO statusDTO = new StatusDTO("OK", new Date());
        return statusDTO;
    }
}
