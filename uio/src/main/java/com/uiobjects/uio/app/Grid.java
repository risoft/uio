package com.uiobjects.uio.app;

import java.util.Properties;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("grid")
public class Grid extends AbstractMenuItem {
	
	@XStreamAsAttribute private String token;
	@XStreamAsAttribute @XStreamOmitField private String name;
	
	@XStreamAsAttribute @XStreamAlias("class") private String className;
	private boolean loadObject = false; 

	public String getName() {
		return name;
	}
	
	public String getToken() {
		return token;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public void translate(Properties properties) {
		name = properties.getProperty(token, token);
	}

	public boolean isLoadObject() {
		return loadObject;
	}
	
	public void setLoadObject(boolean loadObject) {
		this.loadObject = loadObject;
	}
	
}
