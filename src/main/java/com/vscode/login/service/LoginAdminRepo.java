package com.vscode.login.service;

import org.springframework.stereotype.Repository;

import com.vscode.common.repo.DefaultRepo;
import com.vscode.entity.login.LoginAdminBean;

/**
 * @author Utkarsh Bhardwaj 
 */

@Repository("loginAdminRepo")
public interface LoginAdminRepo extends DefaultRepo<LoginAdminBean, String>{
}
