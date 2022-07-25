package com.vscode.entity.login;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Utkarsh Bhardwaj 
 */

@Entity
@Table(name = "USER")
public class LoginUserBean extends Login {

	public LoginUserBean() {
		super();
	}

	public LoginUserBean(String login_ID, String password) {
		super(login_ID, password);
	}

}
