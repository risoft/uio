package com.uiobjects.uio;

public class ComboEntry {

	private String value;
	private String display;
	
	public ComboEntry(Object value, Object display)
	{
		this.value = value.toString();
		this.display = display.toString();
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	
	
}
