package br.gov.pa.sefa.transferenciaeletronica.core.testes.transacao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.pa.sefa.transferenciaeletronica.core.testes.configuracao.ConfiguracaoJPATeste;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.entidade.User;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.repositorio.UsuarioRepositorio;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.servico.UsuarioServico;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfiguracaoJPATeste.class, UsuarioServico.class, UsuarioRepositorio.class })
@ActiveProfiles("test")
public class UsuarioTeste {

	@Autowired
	private UsuarioServico usuarioServico;
	
	@Before
	public void setup() {
	}
	
	@Test
	public void testeListagemDeDeUsuario() {
		List<User> users = usuarioServico.all();
		assertThat(users.size(), is(2));
	}

	@Test
	public void testeCadastroDeDeUsuario() {
		User dave = new User("Dave", "Mathews");
		dave = usuarioServico.save(dave);

		User carter = new User("Carter", "Beauford");
		carter = usuarioServico.save(carter);
	}
	
	

}