package br.gov.pa.sefa.transferenciaeletronica.api.comum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.gov.pa.sefa.transferenciaeletronica.core.comum.ExcecaoDeValidacao;
import br.gov.pa.sefa.transferenciaeletronica.core.comum.ExcecaoGenerica;

@ControllerAdvice
public class ControladorAvisoExcecao {

    final Logger logger = LoggerFactory.getLogger(ControladorAvisoExcecao.class);

    @ExceptionHandler(ExcecaoGenerica.class)
    @ResponseBody
    InformacaoDoErro ouvinteExcecaoGenerica(ExcecaoGenerica excecao, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(excecao.getHttpCodigoStatus().value());
        return new InformacaoDoErro(excecao.getHttpCodigoStatus().value(), request.getRequestURI(), excecao.getMessage());
    }

    @ExceptionHandler(ExcecaoDeValidacao.class)
    @ResponseBody
    InformacaoDoErro ouvinteErrosDeValidacao(ExcecaoDeValidacao excecaoDeValidacao, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(excecaoDeValidacao.getHttpCodigoStatus().value());
        return new InformacaoDeErroDeValidacao(excecaoDeValidacao.getHttpCodigoStatus().value(), request.getRequestURI(), excecaoDeValidacao.getMessage(),
                excecaoDeValidacao.getBindingResult());
    }
}
