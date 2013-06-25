package com.uiobjects.testbeans;

import com.uiobjects.uio.annotations.IdField;


public class SmsOrigin {
	
	
	@IdField(defaultValue="0")
	private long id;
	private String number;
	private String name;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
