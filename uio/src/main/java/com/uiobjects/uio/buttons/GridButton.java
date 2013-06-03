package com.uiobjects.uio.buttons;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("button")
public class GridButton {
	
	@XStreamAsAttribute private String token;
	@XStreamAsAttribute private String name;
	@XStreamAsAttribute private int max = 9999;
	@XStreamAsAttribute private int min = 0;
	@XStreamAsAttribute private String ajaxUrl;
	@XStreamAsAttribute private String form;
	@XStreamAsAttribute private boolean loadObject;

	
	public int getMax() {
		return max == 0 ? 9999 : max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min == 0 ? 1 : min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public String getAjaxUrl() {
		return ajaxUrl;
	}
	public void setAjaxUrl(String ajaxUrl) {
		this.ajaxUrl = ajaxUrl;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLoadObject(boolean loadObject) {
		this.loadObject = loadObject;
	}
	
	public boolean isLoadObject() {
		return loadObject;
	}

}
