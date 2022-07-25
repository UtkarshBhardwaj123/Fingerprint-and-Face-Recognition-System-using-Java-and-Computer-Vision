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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vscode.common.repo.DefaultRepoImpl;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "archiveEntityManagerFactory", basePackages = {
		"com.vscode.archive.service" }, repositoryBaseClass = DefaultRepoImpl.class, transactionManagerRef = "archiveTransactionManager")
public class ArchiveDBConfig {

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
		// JDBC Batching
		properties.put("hibernate.jdbc.batch_size", 40);
		properties.put("hibernate.order_updates", true);
		properties.put("hibernate.order_inserts", true);
		return builder.dataSource(dataSource).packages("com.vscode.entity.archive").properties(properties)
				.persistenceUnit("Archive").build();
	}

	@Bean(name = "archiveTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("archiveEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

//	@Bean(name = "defaultRepoImpl")
//	public DefaultRepo getDefaultRepo(
//			@Qualifier("archiveEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//		DefaultRepo defaultRepo = new DefaultRepoImpl(jpaRepository.get, entityManagerFactory.createEntityManager());
//	}
}
