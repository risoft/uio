package com.uiobjects.uio.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.uiobjects.uio.annotations.implementations.DefaultEmitter;
import com.uiobjects.uio.annotations.meta.EmitterAnnotation;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@EmitterAnnotation(DefaultEmitter.class)
public @interface Default {

	public String value(); 
}
