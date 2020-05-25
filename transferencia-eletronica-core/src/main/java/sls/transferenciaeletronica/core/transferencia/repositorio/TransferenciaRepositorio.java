package sls.transferenciaeletronica.core.transferencia.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;

import sls.transferenciaeletronica.core.comum.BaseRepositorio;
import sls.transferenciaeletronica.core.transferencia.entidade.Transferencia;

@Repository
public class TransferenciaRepositorio extends BaseRepositorio <Object, Long>{

	@SuppressWarnings("unchecked")
	public List<Transferencia> getAgendamentos() {
		return getEntityManager().createQuery("SELECT T FROM Transferencia T").getResultList();		
	}

}
