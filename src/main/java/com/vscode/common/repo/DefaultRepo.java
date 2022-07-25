package com.vscode.common.repo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vscode.common.Filter;

/**
 * @author Utkarsh Bhardwaj 
 */
public interface DefaultRepo<T, ID extends Serializable> extends JpaRepository<T, ID> {

	public List<Map<String, Object>> loadAll(Filter filter);

	public String createSpecialSelectQuery(Filter filter);

	public String filterQuery(Filter filter);
	
}
