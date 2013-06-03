package com.uiobjects.testbeans;

import com.uiobjects.uio.annotations.IdField;


public class OutgoingEmail {
	
	
	@IdField(defaultValue="0")
	private long id;
	private String host;
	private int port;
	private String username;
	private String password;
	private boolean secure;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
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
	public boolean isSecure() {
		return secure;
	}
	public void setSecure(boolean secure) {
		this.secure = secure;
	}
	
	
	
	

}
