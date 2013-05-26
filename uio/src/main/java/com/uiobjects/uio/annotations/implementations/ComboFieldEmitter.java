package com.uiobjects.uio.annotations.implementations;

import java.util.Map;

import org.apache.commons.lang.ClassUtils;

import com.uiobjects.uio.annotations.Combo;
import com.uiobjects.uio.annotations.interfaces.MapFieldEmitter;

public class ComboFieldEmitter extends AbstractFieldEmitter implements MapFieldEmitter {
	
	public void modify(Map<String, String> map) {
		Combo combo= (Combo)getAnnotation(Combo.class);
		map.put("xtype", "'uiocombo'");
		map.put("jclass", "'"+ClassUtils.getShortClassName(combo.type())+"'");
		map.put("forceSelection", ""+combo.forceSelection());

	}

}
