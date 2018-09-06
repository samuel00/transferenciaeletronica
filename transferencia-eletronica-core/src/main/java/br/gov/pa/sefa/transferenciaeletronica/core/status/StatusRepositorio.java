package br.gov.pa.sefa.transferenciaeletronica.core.status;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import br.gov.pa.sefa.transferenciaeletronica.core.comum.BaseRepositorio;

/**
 * Classe para retornar a data/hora do banco de dados, com o objetivo de servir
 * como status de conexao/funcionado do banco de dados.
 *
 */
@Repository
public class StatusRepositorio extends BaseRepositorio<StatusDTO, Long> {
    public StatusDTO buscarStatus() {
        
        StringBuilder SQL = new StringBuilder(
                "SELECT \'OK\' AS \"status\", TO_CHAR (SYSDATE, 'DD/MM/YY HH:MI:SS') AS \"data\" FROM DUAL");

        SQLQuery query = ((Session) getEntityManager().unwrap(Session.class)).createSQLQuery(SQL.toString());
        query.setResultTransformer(new AliasToBeanResultTransformer(StatusDTO.class));
        StatusDTO statusDTO = (StatusDTO) query.uniqueResult();

        return statusDTO;
    }
}
