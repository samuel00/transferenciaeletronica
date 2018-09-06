package br.gov.pa.sefa.transferenciaeletronica.core.transferencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.User;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.UserJpaEntity;

@Service
public class JpaProxyUserRepository {

	@Autowired
	private UsuarioRepositorio jpaRepository;
	
	@Transactional
	public User save(User newUser) {
		UserJpaEntity newEntity = new UserJpaEntity(newUser);
		UserJpaEntity savedEntity = (UserJpaEntity) jpaRepository.salvar(newEntity);
		User savedUser = savedEntity.toUser();
		return savedUser;
	}

	public List<User> all() {
		List<UserJpaEntity> entities = jpaRepository.all();
		List<User> users = new ArrayList<User>(entities.size());
		for (UserJpaEntity entity : entities) {
			User user = entity.toUser();
			users.add(user);
		}
		return users;
	}
}