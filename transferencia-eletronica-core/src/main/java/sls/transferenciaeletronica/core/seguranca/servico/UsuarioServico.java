package sls.transferenciaeletronica.core.seguranca.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sls.transferenciaeletronica.core.seguranca.entidade.Usuario;
import sls.transferenciaeletronica.core.seguranca.exception.SenhaInvalidaException;
import sls.transferenciaeletronica.core.seguranca.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServico implements UserDetailsService {

	private UsuarioRepositorio usuarioRepositorio;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UsuarioServico(UsuarioRepositorio usuarioRepositorio, PasswordEncoder passwordEncoder) {
		this.usuarioRepositorio = usuarioRepositorio;
		this.passwordEncoder = passwordEncoder;
	}

	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getLogin());

		if (passwordEncoder.matches(usuario.getSenha(), user.getPassword())) {
			return user;
		}

		throw new SenhaInvalidaException();
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepositorio.loadUserByUsername(login);

		return User.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(getRoles(usuario))
				.build();
	}

	private String[] getRoles(Usuario usuario) {
		return usuario.isAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };
	}
	
	public static void main(String [] args) {
		String senhaCriptografada = new BCryptPasswordEncoder().encode("paysandu");
        System.out.print(senhaCriptografada);
	}

}
