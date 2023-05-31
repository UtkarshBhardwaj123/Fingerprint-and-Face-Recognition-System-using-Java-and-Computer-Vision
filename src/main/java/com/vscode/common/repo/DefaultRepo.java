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

	/**
	 * Specifically created for executing custom Select Query so that we can have
	 * dynamic where clause.
	 **/
	public List<Map<String, Object>> loadAll(Filter filter);

	public Map<String, Object> callProcedure();

}
