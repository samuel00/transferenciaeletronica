package sls.transferenciaeletronica.manager.repositorio;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class IPBloqueadoRepositorio extends AbstractRepositorio<Integer, Object>{
	
	
	public boolean getIPBloqueado(String ipBloqueado){
		Query query = getSession().createQuery("from IPBloqueado ip where ip.ipBloqueado = :ip");
		query.setParameter("ip", ipBloqueado);
		if(query.list().size() > 0)
			return true;
		else
			return false;
	}
}
