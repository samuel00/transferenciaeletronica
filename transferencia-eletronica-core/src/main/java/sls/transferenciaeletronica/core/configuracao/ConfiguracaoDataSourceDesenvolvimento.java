package sls.transferenciaeletronica.core.configuracao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile("desenvolvimento")
public class ConfiguracaoDataSourceDesenvolvimento {
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/transferenciaeletronica");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
}
