package sls.transferenciaeletronica.core.configuracao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile("docker")
public class ConfiguracaoDataSourceDocker {
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://mysqldb/transferenciaeletronica");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

}
