package sls.transferenciaeletronica.manager.servico;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import sls.transferenciaeletronica.core.comum.ExcecaoGenerica;
import sls.transferenciaeletronica.manager.entidade.Erro;
import sls.transferenciaeletronica.manager.entidade.Parametro;
import sls.transferenciaeletronica.manager.entidade.Requisicao;
import sls.transferenciaeletronica.manager.repositorio.AspectManagerRepositorio;
import sls.transferenciaeletronica.manager.util.ConverteUtil;
import sls.transferenciaeletronica.manager.util.ExtratorUtil;

@Service
public class AspectManagerService {

	@Autowired
	private AspectManagerRepositorio aspectManagerRepositorio;

	@Autowired
	private IPBloqueadoService ipBloqueadoService;

	private Requisicao requisicao;
	private Parametro parametro;
	private Erro erro;

	public AspectManagerService() {
	}

	public void setParametrosDeEntrada(JoinPoint joinPoint, HttpServletRequest request) {
		try {
			requisicao = new Requisicao();
			parametro = new Parametro();
			requisicao.setData(Calendar.getInstance());
			requisicao.setIpOrigem(ConverteUtil.ipRequestToString(request));
			requisicao.setTipo(request.getMethod());
			requisicao.setContexto(ConverteUtil.contextoRequestToString(request));
			requisicao.setPath(ConverteUtil.uriRequestToString(request));
			requisicao.setUuid(ConverteUtil.uriRequestToUUID(request));
			parametro.setEntrada(ConverteUtil.entradaParaString(joinPoint, request));
			parametro.setMetodoInvocado(joinPoint.getSignature().getName());
			parametro.setClasseInvocada(joinPoint.getTarget().getClass().getName());

			parametro.setHeader(ConverteUtil.headerRequestToString(request));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setRetornoParaCliente(Object result) {
		parametro.setSaida(ConverteUtil.retornoParaString(result));
	}

	@SuppressWarnings("unchecked")
	public void setStatusCode(Object result) {
		ResponseEntity<Object> response = (ResponseEntity<Object>) result;
		requisicao.setCodigo(response.getStatusCode().value());
	}

	@Transactional
	public void persistirRequisicao() {
		try {
			if (!ipBloqueadoService.getIPBloqueado(requisicao.getIpOrigem())) {
				parametro.setRequisicao(requisicao);
				requisicao.setParametro(parametro);
				aspectManagerRepositorio.persistiRequisicao(requisicao);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public Object setTempoDeExecucao(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = null;
		try {
			result = joinPoint.proceed();
			long elapsedTime = System.currentTimeMillis() - start;
			requisicao.setTempoExecucao(elapsedTime);
		} catch (IllegalArgumentException e) {
			throw e;
		}
		return result;
	}

	@Transactional
	public void persistirRequisicaoComException(JoinPoint joinPoint, ExcecaoGenerica exception) {
		try {
			erro = new Erro();
			erro.setMetodo(joinPoint.getSignature().getName());
			erro.setMotivo(ExtratorUtil.extrairException(exception));
			erro.setClasse(joinPoint.getTarget().getClass().getName());
			erro.setStacktrace(ConverteUtil.stackTraceToString(exception));
			erro.setRequisicao(requisicao);
			requisicao.setCodigo(exception.getHttpCodigoStatus().value());
			parametro.setRequisicao(requisicao);
			requisicao.setParametro(parametro);
			requisicao.setErro(erro);
			aspectManagerRepositorio.persistiRequisicao(requisicao);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}