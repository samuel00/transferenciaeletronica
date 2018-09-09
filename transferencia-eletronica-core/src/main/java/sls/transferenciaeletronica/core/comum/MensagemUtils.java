package sls.transferenciaeletronica.core.comum;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sls.transferenciaeletronica.core.configuracao.PropriedadesCore;

public class MensagemUtils {
	private static final Properties propriedades;
	static final Logger logger = LoggerFactory.getLogger(MensagemUtils.class);

	private MensagemUtils() {
	}

	static {
		propriedades = new Properties();
		final String nomeDoArquivo = "ValidationMessages.properties";
		try {
			final InputStream inputStream = PropriedadesCore.class.getClassLoader().getResourceAsStream(nomeDoArquivo);
			if (inputStream != null)
				propriedades.load(inputStream);
		} catch (Exception e) {
			logger.error("Erro ao carregar arquivo de mensagens (ValidationMessages.properties)!", e);
		}
	}

	public static String getMensagen(String chaveMensagem) {
		return propriedades.getProperty(chaveMensagem);
	}

	public static String getMensagenSemTaxa(){
		return propriedades.getProperty("info.sem.taxa");
	}

	public static String getMensagenSemAgendamento() {
		return propriedades.getProperty("info.sem.agendamento");
	}
}
