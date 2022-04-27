package ar.com.eventsocial.backend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ar.com.eventsocial.backend",
entityManagerFactoryRef = "sqlEntityManagerApp", 
transactionManagerRef = "sqlTransactionManagerApp")
public class HibernateUtils {

	@Value("${db.driver}")
	private String DRIVER;

	@Value("${db.password}")
	private String PASSWORD;

	@Value("${db.url}")
	private String URL;

	@Value("${db.username}")
	private String USERNAME;

	@Value("${hibernate.dialect}")
	private String DIALECT;

	@Value("${hibernate.show_sql}")
	private String SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HBM2DDL_AUTO;

	@Value("${entitymanager.packagestoScan}")
	private String PACKAGES_TO_SCAN;


	@Bean("sqlDataSourceApp")
	public DataSource sqlDataSource() throws ClassNotFoundException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}

	@Bean("sqlEntityManagerApp")
	public LocalContainerEntityManagerFactoryBean sqlEntityManager() throws ClassNotFoundException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(sqlDataSource());
		em.setPackagesToScan(PACKAGES_TO_SCAN);
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setPersistenceUnitName("sqlUnitName");
		em.setJpaProperties(hibernateProperties());
		return em;
	}

	@Bean("sqlTransactionManagerApp")
	public PlatformTransactionManager sqlTransactionManagerDataMart() throws ClassNotFoundException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(sqlEntityManager().getObject());
		return transactionManager;
	}


	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.temp.use_jdbc_metadata_defaults", "true");
		properties.put("hibernate.dialect", DIALECT);
		properties.put("hibernate.show_sql", SHOW_SQL);
		properties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		properties.put("hibernate.auto_quote_keyword", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("connection.release_mode", "auto");
		properties.put("entitymanager.packages.to.scan", PACKAGES_TO_SCAN);
		properties.put("hibernate.proc.param_null_passing", "true");
		return properties;
	}


}
