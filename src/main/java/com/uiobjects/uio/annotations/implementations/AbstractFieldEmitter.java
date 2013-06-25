package com.uiobjects.uio.annotations.implementations;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;

import com.uiobjects.uio.Context;
import com.uiobjects.uio.annotations.interfaces.FieldEmitter;

public abstract class AbstractFieldEmitter implements FieldEmitter{
	
	protected Context context;
	protected PropertyDescriptor desc;
	
	public void set(PropertyDescriptor desc) {
		this.desc = desc;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	
	protected Annotation getAnnotation(Class<? extends Annotation> annotation)
	{
		return context.getEmitterUtils().getAnnotation(this.desc, annotation);
	}
	
	
}
