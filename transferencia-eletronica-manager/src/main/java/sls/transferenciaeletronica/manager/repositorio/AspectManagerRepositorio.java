package sls.transferenciaeletronica.manager.repositorio;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class AspectManagerRepositorio extends AbstractRepositorio<Integer, Object>{
	
	public void persistiRequisicao(Object object) throws SQLException{
		persist(object);
	}

}
