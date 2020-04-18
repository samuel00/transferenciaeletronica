package sls.transferenciaeletronica.api.rest.transferencia.advicer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import sls.transferenciaeletronica.core.comum.HTTPResponse;

@ControllerAdvice
@Slf4j
public class RecursoAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerError(MethodArgumentNotValidException ex) {
		List<FieldError> listaErros = ex.getBindingResult().getFieldErrors();
		String mensagemDeErro = listaErros.stream().map(FieldError::getDefaultMessage).sorted()
				.collect(Collectors.joining(","));
		log.info("mensagem de erro: {}", mensagemDeErro);
		return new ResponseEntity<HTTPResponse>(new HTTPResponse(mensagemDeErro, 
				HttpStatus.BAD_REQUEST, 
				HttpStatus.BAD_REQUEST.value()),
				HttpStatus.BAD_REQUEST);
	}

}
