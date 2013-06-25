package com.uiobjects.testbeans;

import com.uiobjects.uio.annotations.IdField;
import com.uiobjects.uio.annotations.TextArea;


public class MailTemplate {
	
	
	@IdField(defaultValue="0")
	private long id;
	private String name;
	private String subject;
	@TextArea(cols=40, rows=10)
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
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	
	

}
