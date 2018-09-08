package br.gov.pa.sefa.transferenciaeletronica.core.transferencia.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.pa.sefa.transferenciaeletronica.core.comum.BaseRepositorio;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.Transferencia;

@Repository
public class TransferenciaRepositorio extends BaseRepositorio<Object, Long>{

	@SuppressWarnings("unchecked")
	public List<Transferencia> getAgendamentos() {
		return getEntityManager().createQuery("SELECT T FROM Transferencia T").getResultList();		
	}

}
