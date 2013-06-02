package com.uiobjects.uio.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.uiobjects.uio.buttons.Button;
import com.uiobjects.uio.buttons.Reset;
import com.uiobjects.uio.buttons.Submit;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE, ElementType.PACKAGE})
public @interface Form {

	Class<? extends Button>[] buttons() default {Submit.class, Reset.class}; 
	String[] buttonParams() default {};
	
}
