package com.vscode.entity.login;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Utkarsh Bhardwaj 
 */

@MappedSuperclass
public class Login {

	// Primary Key
	@Id
	@Column(name ="LOGIN_ID")
	private String loginID;

	private String password;

	public Login() {
		super();
	}

	public Login(String loginID, String password) {
		super();
		this.loginID = loginID;
		this.password = password;
	}

	public String getLOGINID() {
		return loginID;
	}

	public void setLOGINID(String lOGIN_ID) {
		this.loginID = lOGIN_ID;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}
}
