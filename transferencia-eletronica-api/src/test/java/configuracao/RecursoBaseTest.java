package configuracao;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RecursoBaseTest {
	
	protected MockMvc mockMvc;
	
	protected final static Double VALOR_OK = 5.5;

	protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	protected String json(Object objeto) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		return mapper.writeValueAsString(objeto);
	}

}
