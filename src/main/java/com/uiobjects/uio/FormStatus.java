package com.uiobjects.uio;

import java.util.HashMap;
import java.util.Map;

public class FormStatus {
	
	public static final FormStatus OK = new FormStatus();
	
	private boolean success;
	private Map<String, String> errors = new HashMap<String, String>();
	
	public FormStatus(FieldError fieldError) {
		this.success = fieldError == null;
		if (this.success)
		{
			return;
		}
		
		errors.put(fieldError.getField(), fieldError.getError());
	}
	
	public FormStatus(FieldError... fieldErrors) {
		this.success = fieldErrors == null || fieldErrors.length == 0;
		if (this.success)
		{
			return;
		}
		for (FieldError fieldError : fieldErrors) {
			errors.put(fieldError.getField(), fieldError.getError());
		}
	}
	
	public FormStatus(String field, String error) {
		this(new FieldError(field, error));
	}
	
	public FormStatus(String field1, String error1, String field2, String error2) {
		this(new FieldError(field1, error1), new FieldError(field2, error2));
	}

	public FormStatus(String field1, String error1, 
			String field2, String error2,
			String field3, String error3) {
		this(new FieldError(field1, error1), 
				new FieldError(field2, error2),
				new FieldError(field3, error3)
				);
		
	}

	

	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

}
