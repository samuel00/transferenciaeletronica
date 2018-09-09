package sls.transferenciaeletronica.api.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;;

@Component
public class Scheduler {

    final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void exibirLogACadaCincoMinutos() {
        logger.debug("Exemplo de mensagem Debug: Executar a cada {} minutos", 5);
        logger.info("Exemplo de mensagem Info: Executar a cada 5 {}", "minutos");
    }

    @Scheduled(fixedRate = 2 * 60 * 1000)
    public void exibirLogACadaDoisMinutos() {
        logger.warn("Exemplo de mensagem Warn: Executar a cada {} {}", 2, "minutos");
        logger.error("Exemplo de mensagem Erro: Executar a cada 2 minutos");
    }
}
