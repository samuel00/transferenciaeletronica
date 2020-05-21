package sls.transferenciaeletronica.core.testes.integracao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sls.transferenciaeletronica.core.configuracao.ConfiguracaoDataSourceGeneral;
import sls.transferenciaeletronica.core.testes.configuracao.ConfiguracaoDataSourceIntegrationTest;
import sls.transferenciaeletronica.core.transferencia.entidade.Transferencia;
import sls.transferenciaeletronica.core.transferencia.repositorio.TransferenciaRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfiguracaoDataSourceGeneral.class, 
		ConfiguracaoDataSourceIntegrationTest.class,
		TransferenciaRepositorio.class })
@ActiveProfiles("IT")
public class MySQLIntegrationIT {

	@Autowired
	private TransferenciaRepositorio transferenciaRepositorio;

	@Test
	public void someFakeTest() throws Exception {
		List<Transferencia> transferencias = transferenciaRepositorio.getAgendamentos();
		Assert.assertEquals(transferencias.size(), 0);
	}

}
