package com.uiobjects.uio.annotations.interfaces;

import java.beans.PropertyDescriptor;

import com.uiobjects.uio.Context;

public interface FieldEmitter {
	
	public void setContext(Context context);
	
	public void set(PropertyDescriptor desc);

}
