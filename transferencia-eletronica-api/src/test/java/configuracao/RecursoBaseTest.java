package configuracao;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RecursoBaseTest {
	
	protected MockMvc mockMvc;
	
	protected final static Double DEZ_REAIS = 10.0;
	protected final static Double QUARENTA_REAIS = 40.0;
	
	protected final static String CONTA_ORIGEM = "104736";
	protected final static String CONTA_DESTINO = "204789";

	protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	protected String json(Object objeto) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		return mapper.writeValueAsString(objeto);
	}
	
	protected Date adicionaDiasNaData(Long numeroDeDias){
		LocalDateTime localDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		localDateTime = localDateTime.plusDays(numeroDeDias);
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

}
