package transferencia;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

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

import configuracao.RecursoBaseTest;
import sls.transferenciaeletronica.api.configuracao.ConfiguracaoAplicacao;
import sls.transferenciaeletronica.core.testes.configuracao.ConfiguracaoDataSourceTest;
import sls.transferenciaeletronica.core.transferencia.dto.TransferenciaDTO;
import util.ConstrutorDeRequisicaoUtil;
import util.ConverterUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguracaoAplicacao.class, ConfiguracaoDataSourceTest.class})
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
	public void testeTransferenciaBadRequestValor() throws Exception {
		TransferenciaDTO transferencia = new TransferenciaDTO();
		mockMvc.perform(post(getRecurso())
		.contentType(MediaType.APPLICATION_JSON)
		.content(ConverterUtil.ObjetoParaJsonBytes(transferencia)))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.status", is("BAD_REQUEST")))
		.andReturn();
	}
	
	@Test
	public void testeTransferenciaBadRequestData() throws Exception {
		TransferenciaDTO transferencia = new TransferenciaDTO(DEZ_REAIS, null,CONTA_ORIGEM, CONTA_DESTINO);
		mockMvc.perform(post(getRecurso())
		.contentType(MediaType.APPLICATION_JSON)
		.content(ConverterUtil.ObjetoParaJsonBytes(transferencia)))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.status", is("BAD_REQUEST")))
		.andReturn();
	}
	
	@Test
	public void testeTransferenciaTaxaACreated() throws Exception {
		TransferenciaDTO transferencia = new TransferenciaDTO(DEZ_REAIS, LocalDate.now(), CONTA_ORIGEM, CONTA_DESTINO);
		mockMvc.perform(post(getRecurso())
		.contentType(MediaType.APPLICATION_JSON)
		.content(ConverterUtil.ObjetoParaJsonBytes(transferencia)))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.status", is("CREATED")))
		.andReturn();
	}
	
	@Test
	public void testeTransferenciaTaxaBCreated() throws Exception {
		TransferenciaDTO transferencia = new TransferenciaDTO(DEZ_REAIS, adicionaDiasNaData(5L),CONTA_ORIGEM, CONTA_DESTINO);
		mockMvc.perform(post(getRecurso())
		.contentType(MediaType.APPLICATION_JSON)
		.content(ConverterUtil.ObjetoParaJsonBytes(transferencia)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.status", is("CREATED")))
		.andReturn();
	}
	
	@Test
	public void testeTransferenciaTaxaCCreated() throws Exception {
		TransferenciaDTO transferencia = new TransferenciaDTO(DEZ_REAIS, adicionaDiasNaData(15L), CONTA_ORIGEM, CONTA_DESTINO);
		mockMvc.perform(post(getRecurso())
		.contentType(MediaType.APPLICATION_JSON)
		.content(ConverterUtil.ObjetoParaJsonBytes(transferencia)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.status", is("CREATED")))
		.andReturn();
	}
	
	@Test
	public void testeTransferenciaTaxaCBadRequest() throws Exception {
		TransferenciaDTO transferencia = new TransferenciaDTO(QUARENTA_REAIS, adicionaDiasNaData(45L), CONTA_ORIGEM, CONTA_DESTINO);
		mockMvc.perform(post(getRecurso())
		.contentType(MediaType.APPLICATION_JSON)
		.content(ConverterUtil.ObjetoParaJsonBytes(transferencia)))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.status", is("BAD_REQUEST")))
		.andReturn();
	}
	
	@Test
	public void testConsultarAgendamento() throws Exception {
		mockMvc.perform(get(getRecurso()))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.httpStatus", is(200)))
		.andExpect(jsonPath("$.quantidadeTransferencias", notNullValue()))
		.andReturn();
	}

}