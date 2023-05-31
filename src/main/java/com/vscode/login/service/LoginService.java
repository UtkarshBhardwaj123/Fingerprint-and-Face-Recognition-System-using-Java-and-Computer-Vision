package com.vscode.login.service;

import java.util.List;
import java.util.Map;

import com.vscode.common.Filter;

/**
 * @author Utkarsh Bhardwaj
 */
public interface LoginService {

	public boolean checkLoginID(List<Map<String, Object>> dynaBean, boolean isAdmin) throws IllegalArgumentException;

	public List<Map<String, Object>> loadAll(Filter filter);

	public void saveDynaBean(List<Map<String, Object>> dynaBean, boolean isAdmin);

	public boolean isAdmin();

	public void setAdmin(boolean isAdmin);

	public void setSaveAdmin(boolean saveAdmin);

	public boolean isSaveAdmin();
}
