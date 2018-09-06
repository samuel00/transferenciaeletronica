package transferencia;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.gov.pa.sefa.transferenciaeletronica.api.configuracao.ConfiguracaoAplicacao;
import br.gov.pa.sefa.transferenciaeletronica.core.testes.configuracao.ConfiguracaoJPATeste;
import br.gov.pa.sefa.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import configuracao.RecursoBaseTest;
import util.ConstrutorDeRequisicaoUtil;
import util.ConverterUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguracaoAplicacao.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class TransferenciaTest extends RecursoBaseTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	private String getRecurso() {
		return new ConstrutorDeRequisicaoUtil().getUrlCriarTrasnferencia().build();
	}

	/** TESTE TRASNFERÊNCIA */
	@Test
	public void testeTransferenciaBadRequest() throws Exception {
		TransferenciaDTO transferencia = new TransferenciaDTO();
		mockMvc.perform(post(getRecurso())
				.contentType(MediaType.APPLICATION_JSON)
				.content(ConverterUtil.ObjetoParaJsonBytes(transferencia)))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testeTransferenciaCreated() throws Exception {
		TransferenciaDTO transferencia = new TransferenciaDTO(VALOR_OK);
		mockMvc.perform(post(getRecurso())
				.contentType(MediaType.APPLICATION_JSON)
				.content(ConverterUtil.ObjetoParaJsonBytes(transferencia)))
				.andExpect(status().isCreated());
	}

}