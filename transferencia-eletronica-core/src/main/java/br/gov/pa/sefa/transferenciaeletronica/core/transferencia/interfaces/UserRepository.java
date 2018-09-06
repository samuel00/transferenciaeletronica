package br.gov.pa.sefa.transferenciaeletronica.core.transferencia.interfaces;

import java.util.List;

import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.User;

public interface UserRepository {

	public User save(User user);

	public List<User> all();
}
