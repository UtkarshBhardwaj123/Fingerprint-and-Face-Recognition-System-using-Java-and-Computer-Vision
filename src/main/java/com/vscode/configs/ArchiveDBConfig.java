package com.vscode.configs;

import java.util.Collections;

/**
 * @author Utkarsh Bhardwaj 
 */

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vscode.common.repo.CustomIntegrator;
import com.vscode.common.repo.DefaultRepoImpl;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "archiveEntityManagerFactory", basePackages = {
		"com.vscode.archive.service" }, repositoryBaseClass = DefaultRepoImpl.class, transactionManagerRef = "archiveTransactionManager", considerNestedRepositories = true)
public class ArchiveDBConfig {

	private static final String DATABASE = "ARCHIVE";

	@Bean(name = "archiveDatasource")
	@ConfigurationProperties(prefix = "pring.archive.datasource")
	public DataSource createDataSource() {
		return DataSourceBuilder.create().build();
	}

//	//temproary changes
//	@SuppressWarnings("deprecation")
//	public HibernateJpaSessionFactoryBean hibernateJpaSessionFactoryBean() {
//		
//	}

	@Bean(name = "archiveEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("archiveDatasource") DataSource dataSource) {

		Map<String, Object> properties = new HashMap<>();

		// statement to fine tune our queries
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

		CustomIntegrator integrator = new CustomIntegrator();
		integrator.put(DATABASE, integrator);

		// statement to configure hibernate.integrator_provider in Spring and JPA
		properties.put("hibernate.integrator_provider",
				(IntegratorProvider) () -> Collections.singletonList(integrator));

		// JDBC Batching
		properties.put("hibernate.jdbc.batch_size", 4);
		properties.put("hibernate.order_updates", true);
		properties.put("hibernate.order_inserts", true);
		properties.put("hibernate.generate_statistics", true);

		return builder.dataSource(dataSource).packages("com.vscode.entity.archive").properties(properties)
				.persistenceUnit("Archive").build();
	}

	@Bean(name = "archiveTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("archiveEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

//	private static class ProxyDataSourceImplementor implements MethodInterceptor{
//
//		private final DataSource dataSource;
//		public ProxyDataSourceImplementor(final DataSource dataSource) {
//			this.dataSource = ProxyDataSourceBuilder.create(dataSource);
//		}
//		@Override
//		public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//	}

}
