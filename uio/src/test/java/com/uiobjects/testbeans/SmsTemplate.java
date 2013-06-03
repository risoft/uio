package com.uiobjects.testbeans;

import com.uiobjects.uio.annotations.IdField;
import com.uiobjects.uio.annotations.TextArea;


public class SmsTemplate {
	
	
	@IdField(defaultValue="0")
	private long id;
	private String name;
	@TextArea(cols=20, rows=5)
	private String template;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	
	

}
