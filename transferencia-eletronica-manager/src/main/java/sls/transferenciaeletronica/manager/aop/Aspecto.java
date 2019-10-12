package sls.transferenciaeletronica.manager.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sls.transferenciaeletronica.core.comum.ExcecaoGenerica;
import sls.transferenciaeletronica.manager.servico.AspectManagerService;

@Aspect
@Component
public class Aspecto {

	@Autowired
	private AspectManagerService service;

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void restController() {
	}

	@Pointcut("execution(* *.*(..))")
	protected void allMethod() {
	}

	@Before("restController() && allMethod() && args(..,request)")
	public void logBefore(JoinPoint joinPoint, HttpServletRequest request){
		service.setParametrosDeEntrada(joinPoint, request);
	}
	
	@AfterReturning(pointcut = "restController() && allMethod() && args(..,request)", returning = "result")
	public void logAfter(JoinPoint joinPoint, HttpServletRequest request, Object result){
		service.setStatusCode(result);
		service.setRetornoParaCliente(result);
		service.persistirRequisicao();
	}
	
	@Around("restController() && allMethod() && args(..,request)")
	public Object logAround(ProceedingJoinPoint joinPoint, HttpServletRequest request) throws Throwable {
		return service.setTempoDeExecucao(joinPoint);
	}	
	
	@AfterThrowing(pointcut = "restController() && allMethod() && args(..,request)", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, ExcecaoGenerica exception, HttpServletRequest request) {
		service.persistirRequisicaoComException(joinPoint, exception);
	}
}