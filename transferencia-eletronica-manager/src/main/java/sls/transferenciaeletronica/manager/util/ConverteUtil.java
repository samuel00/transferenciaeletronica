package sls.transferenciaeletronica.manager.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import sls.transferenciaeletronica.manager.entidade.Header;

public class ConverteUtil {

	private static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";
	private static final String STRING_VAZIA = "";
	private static final String PADRAO_DATA = "yyyy-MM-dd";
	private static final String GET = "GET";
	private static final String ARROBA = "@";
	public static Calendar getDataFormatadaYYYYMMDD(Calendar instance) {
		SimpleDateFormat format1 = new SimpleDateFormat(PADRAO_DATA);
		String formatted = format1.format(instance.getTime());
		try {
			instance.setTime(format1.parse(formatted));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public static String entradaParaString(JoinPoint joinPoint, HttpServletRequest request) {
		String entrada = null;
		try {
			if (request.getMethod().equals(GET)) {
				entrada = ExtratorUtil.extraiParametros(joinPoint);
			} else {// Converte Para Json
				ObjectWriter ow = new ObjectMapper()
					.setDateFormat(new SimpleDateFormat(PADRAO_DATA))
					.writer()
					.withDefaultPrettyPrinter();
				entrada = ow.writeValueAsString(joinPoint.getArgs()[0]);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return entrada;
	}

	public static String retornoParaString(Object result) {
		String valorRetorno = null;
		if (null != result) {
			if (result.toString().endsWith(ARROBA + Integer.toHexString(result.hashCode()))) {
				valorRetorno = ReflectionToStringBuilder.toString(result);
			} else {
				valorRetorno = result.toString();
				ObjectWriter ow = new ObjectMapper()
					.setDateFormat(new SimpleDateFormat(PADRAO_DATA))
					.writer().withDefaultPrettyPrinter();
				try {
					valorRetorno = ow.writeValueAsString(result);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
		return valorRetorno;
	}

	public static String ipRequestToString(HttpServletRequest request) {
		String remoteAddr = STRING_VAZIA;
		if (request != null) {
			remoteAddr = request.getHeader(X_FORWARDED_FOR);
			if (remoteAddr == null || STRING_VAZIA.equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	}

	public static String contextoRequestToString(HttpServletRequest request) {
		if(request != null)
			if(!request.getContextPath().equals(STRING_VAZIA)){
				return request.getContextPath().toString();
			}
		return STRING_VAZIA;
	}

	public static String uriRequestToString(HttpServletRequest request) {
		if(request != null)
			if(!request.getServletPath().equals(STRING_VAZIA)){
				return request.getServletPath() + request.getPathInfo();
			}
		return STRING_VAZIA;
	}

	public static String headerRequestToString(HttpServletRequest request) throws JsonProcessingException {
		String retorno = "";
		List<Header> headers = new ArrayList<>();
		if (null != request) {
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				Header header = new Header();
				String headerName = headerNames.nextElement();
				String headerValue = request.getHeader(headerName);
				header.setDescricao(headerName);
				header.setValor(headerValue);
				headers.add(header);
			}
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		retorno = ow.writeValueAsString(headers);
		return retorno;
	}

	public static String stackTraceToString(Throwable exception) {
		StringWriter sw = new StringWriter();
		exception.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	public static String uriRequestToUUID(HttpServletRequest request) {
		String uuid = "";
		if (null != request) {
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String headerValue = request.getHeader(headerName);
				if(headerName.equalsIgnoreCase("uuid")){
					uuid = headerValue;
					break;
				}
			}
		}
		return uuid;
	}
}
