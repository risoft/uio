package com.uiobjects.uio.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.uiobjects.uio.annotations.implementations.ComboFieldEmitter;
import com.uiobjects.uio.annotations.meta.EmitterAnnotation;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@EmitterAnnotation(ComboFieldEmitter.class)
public @interface Combo {
	
	public Class<?> type();
	public boolean forceSelection() default false;


}
