package com.uiobjects.uio.annotations.implementations;

import java.util.Map;

import org.apache.commons.lang.ClassUtils;

import com.uiobjects.uio.annotations.Default;
import com.uiobjects.uio.annotations.interfaces.MapFieldEmitter;

public class DefaultEmitter extends AbstractFieldEmitter implements MapFieldEmitter {
	
	public void modify(Map<String, String> map) {
		Default default_= (Default)getAnnotation(Default.class);
		String value = default_.value();
		if (ClassUtils.primitiveToWrapper(desc.getPropertyType()).equals(Boolean.class))
		{
			map.put("checked", "true");
		}
		else
		{
			map.put("value", "'"+value+"'");
		}
	}

}
