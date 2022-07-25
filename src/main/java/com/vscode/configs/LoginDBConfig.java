package com.vscode.configs;

/**
 * @author Utkarsh Bhardwaj 
 */

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vscode.common.repo.DefaultRepoImpl;
import com.vscode.entity.login.LoginUserBean;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "loginEntityManagerFactory", basePackages = {
		"com.vscode.login.service"}, repositoryBaseClass = DefaultRepoImpl.class, 
				transactionManagerRef = "loginTransactionManager")

public class LoginDBConfig {

	@Primary
	@Bean(name = "loginDatasource")
	@ConfigurationProperties(prefix = "pring.login.datasource")
	public DataSource createDataSource() {
		return DataSourceBuilder.create().build();
	}
	@Primary
	@Bean(name = "loginEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("loginDatasource") DataSource dataSource) {
		Map<String, Object> properties = new HashMap<>();
		
		//statement to fine tune our queries
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		
		//JDBC Batching 
		properties.put("hibernate.jdbc.batch_size", 40);
		properties.put("hibernate.order_updates", true);
		properties.put("hibernate.order_inserts", true);
		return builder.dataSource(dataSource).packages("com.vscode.entity.login").properties(properties)
				.persistenceUnit("User").build();
	}

	@Primary
	@Bean(name = "loginTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("loginEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
}
