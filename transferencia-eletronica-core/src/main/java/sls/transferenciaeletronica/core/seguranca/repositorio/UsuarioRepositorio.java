package sls.transferenciaeletronica.core.seguranca.repositorio;

import org.springframework.stereotype.Repository;

import sls.transferenciaeletronica.core.comum.BaseRepositorio;
import sls.transferenciaeletronica.core.seguranca.entidade.Usuario;

@Repository
public class UsuarioRepositorio extends BaseRepositorio <Object, Long>{
	
	public Usuario loadUserByUsername(String login) {
		return (Usuario) getEntityManager()
				.createQuery("SELECT U FROM Usuario U WHERE U.login = :login")
				.setParameter("login", login)
				.getSingleResult();		
	}

}
