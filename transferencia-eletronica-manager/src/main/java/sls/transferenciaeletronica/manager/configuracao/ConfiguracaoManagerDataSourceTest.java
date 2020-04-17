/*
 * package sls.transferenciaeletronica.manager.configuracao;
 * 
 * import java.util.Properties;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.ComponentScan; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.context.annotation.Profile; import
 * org.springframework.jdbc.datasource.DriverManagerDataSource; import
 * org.springframework.orm.hibernate5.LocalSessionFactoryBean; import
 * org.springframework.transaction.annotation.EnableTransactionManagement;
 * 
 * @Configuration
 * 
 * @EnableTransactionManagement
 * 
 * @ComponentScan(basePackages = "sls.transferenciaeletronica" ) public class
 * ConfiguracaoManagerDataSourceTest {
 * 
 * @Bean
 * 
 * @Profile("test") public LocalSessionFactoryBean sessionFactory() {
 * LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
 * sessionFactory.setDataSource(dataSource());
 * sessionFactory.setPackagesToScan(new String[] { "sls.transferenciaeletronica"
 * }); sessionFactory.setHibernateProperties(additionalProperties()); return
 * sessionFactory; }
 * 
 * @Bean
 * 
 * @Profile("test") public DataSource dataSource() { DriverManagerDataSource
 * dataSource = new DriverManagerDataSource();
 * dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
 * dataSource.setUrl("jdbc:derby:memory:transferencia-eletronicaDB;create=true")
 * ; dataSource.setUsername("admin"); dataSource.setPassword("root"); return
 * dataSource; }
 * 
 * private Properties additionalProperties() { Properties properties = new
 * Properties(); properties.setProperty("hibernate.hbm2ddl.auto",
 * "create-drop"); properties.setProperty("hibernate.dialect",
 * "org.hibernate.dialect.DerbyTenSevenDialect");
 * properties.setProperty("hibernate.show_sql", "true");
 * properties.setProperty("hibernate.hbm2ddl.import_files", "import.sql");
 * return properties; }
 * 
 * 
 * 
 * }
 */