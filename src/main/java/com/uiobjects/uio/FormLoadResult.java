package com.uiobjects.uio;

public class FormLoadResult {
	
	private boolean success;
	private Object data;
	
	public FormLoadResult(Object data) {
		this.success = data != null;
		this.data = data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean getSuccess() {
		return success;
	}
	
	
	
	

}
