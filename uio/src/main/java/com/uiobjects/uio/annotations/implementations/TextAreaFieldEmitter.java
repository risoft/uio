package com.uiobjects.uio.annotations.implementations;

import java.util.Map;

import com.uiobjects.uio.annotations.TextArea;
import com.uiobjects.uio.annotations.interfaces.MapFieldEmitter;

public class TextAreaFieldEmitter extends AbstractFieldEmitter implements MapFieldEmitter {
	
	public void modify(Map<String, String> map) {
		TextArea textArea= (TextArea)getAnnotation(TextArea.class);
		map.put("xtype", "'textareafield'");
		map.put("cols", ""+textArea.cols());
		map.put("rows", ""+textArea.rows());		
	}

}
