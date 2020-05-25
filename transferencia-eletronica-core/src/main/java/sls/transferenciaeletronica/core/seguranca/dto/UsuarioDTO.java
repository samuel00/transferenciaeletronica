package sls.transferenciaeletronica.core.seguranca.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO implements Serializable{
		
	private static final long serialVersionUID = -2089019310394288900L;
	
	private Integer id;
    
	@NotEmpty(message = "{error.login.not.null}")
    private String login;
    
	@NotEmpty(message = "{error.senha.not.null}")
    private String senha;
    
	private boolean admin;

}
