package com.uiobjects.uio.annotations.implementations;

import java.util.Map;

import com.uiobjects.uio.annotations.Html;
import com.uiobjects.uio.annotations.interfaces.MapFieldEmitter;

public class HtmlFieldEmitter extends AbstractFieldEmitter implements MapFieldEmitter {
	
	public void modify(Map<String, String> map) {
		Html html= (Html)getAnnotation(Html.class);
		map.put("xtype", "'htmleditor'");
		map.put("width", ""+html.width());
		map.put("height", ""+html.height());	
	}

}
