package sls.transferenciaeletronica.api.rest.administrativo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sls.transferenciaeletronica.api.vo.RequisicaoVO;
import sls.transferenciaeletronica.core.comum.ExcecaoDeValidacao;
import sls.transferenciaeletronica.core.comum.ExcecaoGenerica;
import sls.transferenciaeletronica.manager.servico.AspectManagerService;

@RestController
@RequestMapping("/administrativo")
public class AdministrativoRecurso {
	
	private static final Logger logger = LoggerFactory.getLogger(AdministrativoRecurso.class);
	
	@Autowired
	private AspectManagerService aspectManagerService;
	
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    public ResponseEntity<?> recuperarAgendamentos(HttpServletRequest request) throws ExcecaoDeValidacao, ExcecaoGenerica {
       logger.debug("Consulta Agendamento");
       try {
    	   RequisicaoVO resposta =
                   new RequisicaoVO(aspectManagerService.getRequisicoes());
           resposta.setHttpStatus(HttpStatus.OK.value());
           resposta.setUrl(request.getRequestURI());
           return new ResponseEntity<>(resposta, HttpStatus.OK);
       } catch (Exception e) {
       	logger.error(e.getMessage(), e);
           throw new ExcecaoGenerica(HttpStatus.INTERNAL_SERVER_ERROR,
                   "Erro ao consultar informacoes. Tente novamente mais tarde.");
       }
	}

}
