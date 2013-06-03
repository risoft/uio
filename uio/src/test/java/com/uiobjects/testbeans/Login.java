package com.uiobjects.testbeans;

import com.uiobjects.uio.annotations.Form;
import com.uiobjects.uio.buttons.Submit;

@Form(buttons=Submit.class, buttonParams="reload=true")
public class Login {
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
