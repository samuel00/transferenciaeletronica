package sls.transferenciaeletronica.api.rest.seguranca;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import sls.transferenciaeletronica.core.comum.ExcecaoGenerica;
import sls.transferenciaeletronica.core.seguranca.dto.CredenciaisDTO;
import sls.transferenciaeletronica.core.seguranca.dto.TokenDTO;
import sls.transferenciaeletronica.core.seguranca.entidade.Usuario;
import sls.transferenciaeletronica.core.seguranca.exception.SenhaInvalidaException;
import sls.transferenciaeletronica.core.seguranca.servico.JwtServico;
import sls.transferenciaeletronica.core.seguranca.servico.UsuarioServico;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioRecurso {

	@Autowired
	private UsuarioServico usuarioServico;
	
	@Autowired JwtServico jwtService;

	@RequestMapping(value = "/autenticar", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<?> autenticar(@Valid @RequestBody CredenciaisDTO credenciaisDTO,
			HttpServletRequest httpServletRequest) throws ExcecaoGenerica {
		try {
			log.debug("Transferencia Recebida: {},!", credenciaisDTO);
			Usuario usuario = Usuario.builder()
                    .login(credenciaisDTO.getLogin())
                    .senha(credenciaisDTO.getSenha()).build();
			this.usuarioServico.autenticar(usuario);
            String token = this.jwtService.gerarToken(usuario);
			return new ResponseEntity<>(new TokenDTO(usuario.getLogin(), token), HttpStatus.OK);
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			System.out.println(e);
			throw new ExcecaoGenerica(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

}
