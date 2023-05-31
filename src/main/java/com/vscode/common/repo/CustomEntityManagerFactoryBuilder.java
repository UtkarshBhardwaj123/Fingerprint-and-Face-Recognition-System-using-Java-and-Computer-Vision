package com.vscode.common.repo;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

public interface CustomEntityManagerFactoryBuilder extends EntityManagerFactoryBuilder {
	
public ArrayList<HashMap<String, Object>> createNativeQueryForMap(String sqlString);

public HashMap<String, Object> getDynaBean(String entityName) ;
}
