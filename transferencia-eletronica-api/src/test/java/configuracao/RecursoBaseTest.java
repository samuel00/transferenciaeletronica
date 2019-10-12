package configuracao;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RecursoBaseTest {
	
	protected MockMvc mockMvc;
	
	protected final static Double DEZ_REAIS = 10.0;
	protected final static Double QUARENTA_REAIS = 40.0;
	
	protected final static String CONTA_ORIGEM = "104736";
	protected final static String CONTA_DESTINO = "204789";
	
	protected final static String UTF_8 = "utf8";
	protected final static String PADRAO_DATA = "yyyy-MM-dd";

	protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName(UTF_8));

	protected String json(Object objeto) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat(PADRAO_DATA));
		return mapper.writeValueAsString(objeto);
	}
	
	protected LocalDate adicionaDiasNaData(Long numeroDeDias){
		return LocalDate.now().plusDays(numeroDeDias);
	}

}
