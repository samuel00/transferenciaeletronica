package sls.transferenciaeletronica.manager.repositorio;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import sls.transferenciaeletronica.manager.entidade.Requisicao;

@Repository
public class AspectManagerRepositorio extends AbstractRepositorio<Integer, Object>{
	
	public void persistiRequisicao(Object object) throws SQLException{
		persist(object);
	}

	@SuppressWarnings("unchecked")
	public List<Requisicao> getRequisicoes() {
		return getSession().createQuery("SELECT R FROM Requisicao R").list();
	}

}
