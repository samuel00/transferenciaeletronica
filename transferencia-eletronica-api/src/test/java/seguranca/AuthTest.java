package seguranca;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import sls.transferenciaeletronica.api.configuracao.ConfiguracaoAplicacao;
import sls.transferenciaeletronica.api.configuracao.ConfiguracaoSeguranca;
import sls.transferenciaeletronica.core.testes.configuracao.ConfiguracaoDataSourceTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfiguracaoAplicacao.class, ConfiguracaoDataSourceTest.class,
		ConfiguracaoSeguranca.class })
@WebAppConfiguration
@ActiveProfiles("test")
public class AuthTest {

}
