package br.gov.pa.sefa.transferenciaeletronica.core.transferencia.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.pa.sefa.transferenciaeletronica.core.comum.BaseRepositorio;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.UserJpaEntity;

@Repository
public class UsuarioRepositorio extends BaseRepositorio<Object, Long>{
	
	
	public UserJpaEntity salvar(UserJpaEntity usuario) {
        return (UserJpaEntity) super.salvar(usuario);
    }
	
	
	@SuppressWarnings("unchecked")
	public List<UserJpaEntity> all() {
		return getEntityManager().createQuery("SELECT T FROM UserJpaEntity T").getResultList();
}

}
