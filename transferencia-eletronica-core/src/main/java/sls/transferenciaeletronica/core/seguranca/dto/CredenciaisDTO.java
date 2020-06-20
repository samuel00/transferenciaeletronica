package sls.transferenciaeletronica.core.seguranca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO {
	
    private String login;
    private String senha;
}
