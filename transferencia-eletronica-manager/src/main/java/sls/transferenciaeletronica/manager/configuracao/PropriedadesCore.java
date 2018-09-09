package sls.transferenciaeletronica.manager.configuracao;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropriedadesCore {

    private static final Properties propriedades;
    static final Logger logger = LoggerFactory.getLogger(PropriedadesCore.class);

    private PropriedadesCore() {
    }

    static {
        propriedades = new Properties();
        final String nomeDoArquivo = "core.properties";
        try {
            final InputStream inputStream = PropriedadesCore.class.getClassLoader()
                    .getResourceAsStream(nomeDoArquivo);
            if (inputStream != null)
                propriedades.load(inputStream);
        } catch (Exception e) {
            logger.error("Erro ao carregar arquivo de configuracoes iniciais!", e);
        }
    }

    public static String obterPropriedade(String nomeDaPropriedade) {
        return propriedades.getProperty(nomeDaPropriedade);
    }

    public static String getNomeSistema() {
        return obterPropriedade("sistema.nome");
    }

    public static String getVersaoSistema() {
        return obterPropriedade("sistema.versao");
    }
}
