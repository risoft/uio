package com.uiobjects.uio.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE, ElementType.PACKAGE})
public @interface Form {

	FormButton[] buttons() default {FormButton.SUBMIT, FormButton.RESET}; 
	
	
}
