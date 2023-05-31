package com.vscode.entity.login;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Utkarsh Bhardwaj 
 */

@Entity
@Table(name = "ADMIN", schema = "LOGIN")
public class LoginAdminBean extends Login {

	public LoginAdminBean() {
		super();
	}

	public LoginAdminBean(String login_ID, String password) {
		super(login_ID, password);
	}

}
