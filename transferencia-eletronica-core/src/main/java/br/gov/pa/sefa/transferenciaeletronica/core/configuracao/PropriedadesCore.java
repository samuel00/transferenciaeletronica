package br.gov.pa.sefa.transferenciaeletronica.core.configuracao;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropriedadesCore {

    private static Properties propriedades;
    static final Logger logger = LoggerFactory.getLogger(PropriedadesCore.class);
    static final String AMBIENTE = "configuracao.ambiente";
    static final String VERSAO = "configuracao.versao";
    static final String PROJETO = "configuracao.projeto";

    private PropriedadesCore() {
    }

    static {
        propriedades = new Properties();
        final String nomeDoArquivo = "core.properties";
        try {
            final InputStream inputStream = PropriedadesCore.class.getClassLoader().getResourceAsStream(nomeDoArquivo);
            if (inputStream != null)
                propriedades.load(inputStream);
        } catch (Exception e) {
            logger.error("Erro ao carregar arquivo de configuracoes iniciais!", e);
        }
    }

    public static String obterPropriedade(String nomeDaPropriedade) {
        return propriedades.getProperty(nomeDaPropriedade);
    }

    public static String getAmbiente() {
        return obterPropriedade(AMBIENTE);
    }

    public static String getVersao() {
        return obterPropriedade(VERSAO);
    }

    public static String getProjeto() {
        return obterPropriedade(PROJETO).toUpperCase();
    }

    public static String getRelease() {
        return String.format("%s.%s.%s", getProjeto(), getVersao(), getAmbienteSigla());
    }

    public static boolean isProducao() {
        return "producao".equals(getAmbiente());
    }

    public static boolean isDesenvolvimento() {
        return "desenvolvimento".equalsIgnoreCase(getAmbiente());
    }

    public static boolean isHomologacao() {
        return "homologacao".equalsIgnoreCase(getAmbiente());
    }

    public static String getAmbienteSigla() {
        String ambiente = "L";
        switch (getAmbiente()) {
            case "desenvolvimento":
                ambiente = "D";
                break;
            case "homologacao":
                ambiente = "H";
                break;
            case "producao":
                ambiente = "P";
                break;
            default:
                break;
        }

        return ambiente;
    }
}
