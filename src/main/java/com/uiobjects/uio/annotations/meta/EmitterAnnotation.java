package com.uiobjects.uio.annotations.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.uiobjects.uio.annotations.interfaces.FieldEmitter;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface EmitterAnnotation {

	public Class<? extends FieldEmitter> value();
	
}
