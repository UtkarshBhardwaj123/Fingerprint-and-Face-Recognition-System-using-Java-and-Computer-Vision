package com.vscode.login.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vscode.common.Filter;
import com.vscode.common.repo.DefaultRepoImpl;
import com.vscode.entity.login.Login;
import com.vscode.entity.login.LoginAdminBean;
import com.vscode.entity.login.LoginUserBean;

/**
 * @author Utkarsh Bhardwaj
 */

@Component
public class LoginServiceImpl implements LoginService {
	private boolean isAdmin = false;
	@Autowired
	private LoginAdminRepo loginAdminRepo;

	@Autowired
	private LoginUserRepo loginUserRepo ;

	@Override
	public boolean checkLoginID(List<Map<String, Object>> dynaBean, boolean isAdmin) throws IllegalArgumentException {
		Login loginEntity = null;
		if (dynaBean != null && !dynaBean.isEmpty()) {
			if (!isAdmin) {
				loginEntity = new LoginUserBean();
				return loginUserRepo.existsById(convertDynaBeanToEntity(dynaBean, loginEntity).get(0).getLOGINID());
			} else {
				loginEntity = new LoginAdminBean();
				return loginAdminRepo.existsById(convertDynaBeanToEntity(dynaBean, loginEntity).get(0).getLOGINID());
			}
		}

		return false;
	}

	public List<Map<String, Object>> loadAll(Filter filter) {
		return loginUserRepo.loadAll(filter);
	}

	@Override
	public void saveDynaBean(List<Map<String, Object>> dynaBean, boolean isAdmin) {
		Login loginEntity = null;
		if (dynaBean != null) {
			if (!isAdmin) {
				loginEntity = new LoginUserBean();
				loginUserRepo
						.saveAll((Iterable<LoginUserBean>) convertDynaBeanToEntity(dynaBean, loginEntity).iterator());
			} else {
				loginEntity = new LoginAdminBean();
				loginAdminRepo
						.saveAll((Iterable<LoginAdminBean>) convertDynaBeanToEntity(dynaBean, loginEntity).iterator());
			}

		}
	}

	private List<Login> convertDynaBeanToEntity(List<Map<String, Object>> dynaBean, Login entity) {
		List<Login> entityList = new ArrayList<Login>();
		for (Map<String, Object> map : dynaBean) {
			entity.setLOGINID(map.get("LOGIN_ID").toString());
			entity.setpassword(map.get("PASSWORD").toString());
			entityList.add(entity);
		}
		return entityList;
	}

	@Override
	public boolean isAdmin() {
		return isAdmin;
	}
	
	@Override 
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
