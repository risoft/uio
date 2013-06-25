package com.uiobjects.uio.annotations.interfaces;

import java.util.Map;

public interface MapFieldEmitter extends FieldEmitter {

	public void modify(Map<String, String> map);
	
}
