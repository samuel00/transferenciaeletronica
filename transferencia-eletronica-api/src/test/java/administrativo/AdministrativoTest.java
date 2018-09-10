package administrativo;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import configuracao.RecursoBaseTest;
import sls.transferenciaeletronica.api.configuracao.ConfiguracaoAplicacao;
import sls.transferenciaeletronica.manager.configuracao.ConfiguracaoManagerDataSourceTest;
import util.ConstrutorDeRequisicaoUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguracaoAplicacao.class, ConfiguracaoManagerDataSourceTest.class})
@WebAppConfiguration
public class AdministrativoTest  extends RecursoBaseTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	private String getRecurso() {
		return new ConstrutorDeRequisicaoUtil().getUrlAdministrativo().build();		
	}
	
	@Test
	public void testConsultarAdministrativo() throws Exception {
		mockMvc.perform(get(getRecurso()))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.httpStatus", is(200)))
		.andExpect(jsonPath("$.quantidadeRequisicoes", is(1)))
		.andReturn();
	}

}
