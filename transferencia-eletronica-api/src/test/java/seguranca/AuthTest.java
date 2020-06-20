package seguranca;

import configuracao.RecursoBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sls.transferenciaeletronica.api.configuracao.ConfiguracaoAplicacao;
import sls.transferenciaeletronica.api.configuracao.ConfiguracaoSeguranca;
import sls.transferenciaeletronica.core.seguranca.dto.CredenciaisDTO;
import sls.transferenciaeletronica.core.testes.configuracao.ConfiguracaoDataSourceTest;
import util.ConstrutorDeRequisicaoUtil;
import util.ConverterUtil;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ConfiguracaoAplicacao.class, ConfiguracaoDataSourceTest.class,
		ConfiguracaoSeguranca.class })
@WebAppConfiguration
@ActiveProfiles("test")
public class AuthTest extends RecursoBaseTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private String getRecurso() {
		return new ConstrutorDeRequisicaoUtil().getUrlAutenticacao().build();
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/** TESTE AUTH */
	@Test
	public void auth() throws Exception {
		mockMvc.perform(post(getRecurso())
				.contentType(MediaType.APPLICATION_JSON)
				.content(ConverterUtil.ObjetoParaJsonBytes(new CredenciaisDTO("adm", "paysandu"))))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.login", is("adm"))).andReturn();
	}

}
