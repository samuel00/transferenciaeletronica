package br.gov.pa.sefa.transferenciaeletronica.core.transferencia.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.User;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.UserJpaEntity;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Transactional
	public User save(User newUser) {
		UserJpaEntity newEntity = new UserJpaEntity(newUser);
		User savedUser = usuarioRepositorio.salvar(newEntity).toUser();
		return savedUser;
	}

	public List<User> all() {
		List<UserJpaEntity> entities = usuarioRepositorio.all();
		List<User> users = new ArrayList<User>(entities.size());
		for (UserJpaEntity entity : entities) {
			User user = entity.toUser();
			users.add(user);
		}
		return users;
	}

}
