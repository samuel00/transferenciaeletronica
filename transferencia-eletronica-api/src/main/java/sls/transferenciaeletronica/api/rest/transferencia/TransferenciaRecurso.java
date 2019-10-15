package sls.transferenciaeletronica.api.rest.transferencia;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sls.transferenciaeletronica.api.vo.TransferenciaVO;
import sls.transferenciaeletronica.core.comum.ExcecaoDeValidacao;
import sls.transferenciaeletronica.core.comum.ExcecaoGenerica;
import sls.transferenciaeletronica.core.comum.HTTPResponse;
import sls.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import sls.transferenciaeletronica.core.transferencia.servico.TransferenciaServico;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaRecurso {

	private static final Logger logger = LoggerFactory.getLogger(TransferenciaRecurso.class);

	@Autowired
	private TransferenciaServico transferenciaServico;

	private HTTPResponse httpResponse;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<?> criarAgendamento(@Valid @RequestBody TransferenciaDTO transferenciaDTO,
			BindingResult bindingResult, HttpServletRequest httpServletRequest) throws ExcecaoGenerica {
		try {
			logger.debug("Transferencia Recebida: {},!", transferenciaDTO);
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<HTTPResponse>(
						new HTTPResponse(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST,
								HttpStatus.BAD_REQUEST.value()),
						HttpStatus.BAD_REQUEST);
			}
			httpResponse = transferenciaServico.criarTransferencia(transferenciaDTO);
			return new ResponseEntity<HTTPResponse>(httpResponse, httpResponse.getStatus());
		} catch (Exception e) {
			throw new ExcecaoGenerica(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<?> recuperarAgendamentos(HttpServletRequest request)
			throws ExcecaoDeValidacao, ExcecaoGenerica {
		logger.debug("Consulta Agendamento");
		try {
			TransferenciaVO resposta = new TransferenciaVO(transferenciaServico.getAgendamentos());
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
