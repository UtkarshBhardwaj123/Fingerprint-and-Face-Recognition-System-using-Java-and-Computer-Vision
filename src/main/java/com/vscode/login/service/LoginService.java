package com.vscode.login.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vscode.common.Filter;
import com.vscode.entity.login.Login;
import com.vscode.entity.login.LoginUserBean;

/**
 * @author Utkarsh Bhardwaj
 */
public interface LoginService {

	public boolean checkLoginID(List<Map<String, Object>> dynaBean, boolean isAdmin) throws IllegalArgumentException;

	public List<Map<String, Object>> loadAll(Filter filter);

	public void saveDynaBean(List<Map<String, Object>> dynaBean, boolean isAdmin);

	public boolean isAdmin();

	public void setAdmin(boolean isAdmin);
}
