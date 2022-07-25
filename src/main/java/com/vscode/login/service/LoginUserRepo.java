package com.vscode.login.service;

import org.springframework.stereotype.Repository;

import com.vscode.common.repo.DefaultRepo;
import com.vscode.entity.login.LoginUserBean;

/**
 * @author Utkarsh Bhardwaj
 */
@Repository
public interface LoginUserRepo extends DefaultRepo<LoginUserBean, String> {

}
