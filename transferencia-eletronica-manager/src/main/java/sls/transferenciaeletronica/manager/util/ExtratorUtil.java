package sls.transferenciaeletronica.manager.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import sls.transferenciaeletronica.core.comum.ExcecaoDeValidacao;
import sls.transferenciaeletronica.manager.entidade.MotivoExcecao;

public class ExtratorUtil {

	private static final String QUEBRA_LINHA = "\n";

	public static String extrairException(Throwable exception) throws JsonProcessingException {
		if (exception instanceof ExcecaoDeValidacao) {
			return listaDeErros(exception);
		} else {
			return exception.getMessage();
		}
	}

	private static String listaDeErros(Throwable exception) throws JsonProcessingException {
		ExcecaoDeValidacao excecaoDeValidacao = (ExcecaoDeValidacao) exception;
		List<FieldError> erros = excecaoDeValidacao.getBindingResult().getFieldErrors();
		List<MotivoExcecao> motivosExcecao = new ArrayList<>();
		String retorno = "";
		for (FieldError error : erros) {
			MotivoExcecao motivoExcecao = new MotivoExcecao();
			motivoExcecao.setCampo(error.getField());
			motivoExcecao.setMenssagem(error.getDefaultMessage());
			motivoExcecao
					.setValorInvalido(error.getRejectedValue() != null ? error.getRejectedValue().toString() : null);
			motivosExcecao.add(motivoExcecao);
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		retorno = ow.writeValueAsString(motivosExcecao);
		return retorno;
	}

	public static String extraiParametros(JoinPoint joinPoint) {
		StringBuffer paranetrosDeEntrada = new StringBuffer(" - ");
		Object[] args = joinPoint.getArgs();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
		Method method = methodSignature.getMethod();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		assert args.length == parameterAnnotations.length;
		for (int argIndex = 0; argIndex < args.length; argIndex++) {
			for (Annotation annotation : parameterAnnotations[argIndex]) {
				if (!(annotation instanceof RequestParam))
					continue;
				RequestParam requestParam = (RequestParam) annotation;
				paranetrosDeEntrada.append(requestParam.value() + " = " + args[argIndex] + QUEBRA_LINHA);
			}
		}
		return paranetrosDeEntrada.toString();
	}

}
