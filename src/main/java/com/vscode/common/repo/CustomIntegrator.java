package com.vscode.common.repo;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class CustomIntegrator implements Integrator {

	private static final Map<String,CustomIntegrator> INTEGRATORDBMAP=new HashMap<>();
	private Metadata metadata;
	private Database database;

	HashMap<String, Object> data = new HashMap<String, Object>();

	@Override
	public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
		this.database = metadata.getDatabase();
		this.metadata = metadata;
	}

	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {

	}
	
	public Metadata getMetadata() {
		return metadata;
	}
	
	public Database getDatabase() {
		return database;
	}
	
	public void put(String database, CustomIntegrator integrator) {
		INTEGRATORDBMAP.put(database, integrator);
	}
	
	public static CustomIntegrator getMap(String database) {
		return INTEGRATORDBMAP.get(database);
	}
}
