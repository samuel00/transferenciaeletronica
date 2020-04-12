package sls.transferenciaeletronica.core.testes.configuracao;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration @EnableTransactionManagement public class ConfiguracaoDataSourceTest {

    @Bean @Profile("test") public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
	entityManagerFactory.setDataSource(dataSource());
	entityManagerFactory.setPersistenceUnitName("transferencia-eletronica-PU");
	entityManagerFactory.setPackagesToScan(new String[] {"sls.transferenciaeletronica"});
	JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
	entityManagerFactory.setJpaProperties(additionalProperties());
	return entityManagerFactory;
    }

    @Bean @Profile("test") public DataSource dataSource() {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
	dataSource.setUrl("jdbc:derby:memory:transferencia-eletronicaDB;create=true");
	dataSource.setUsername("admin");
	dataSource.setPassword("");
	return dataSource;
    }

    @Bean @Profile("test") public LocalSessionFactoryBean sessionFactory() {
	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	sessionFactory.setDataSource(dataSource());
	sessionFactory.setPackagesToScan(new String[] {"sls.transferenciaeletronica"});
	sessionFactory.setHibernateProperties(additionalProperties());
	return sessionFactory;
    }

    private Properties additionalProperties() {
	Properties properties = new Properties();
	properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	properties.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyTenSevenDialect");
	properties.setProperty("hibernate.show_sql", "true");
	properties.setProperty("hibernate.hbm2ddl.import_files", "import.sql");
	return properties;
    }

    @Bean @Profile("test") public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(emf);
	return transactionManager;
    }
}
