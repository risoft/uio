package com.uiobjects.uio.app;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uiobjects.uio.Translator;
import com.uiobjects.uio.exceptions.UioException;


public class UioMenu {
	
	private Translator translator = new Translator();
	private MenuParser menuParser = new MenuParser();
	private MenuFilter menuFilter = new MenuFilter();

	public String getMenu(boolean mobile, boolean authenticated)
	{
		Submenu submenu = menuParser.parse();
		submenu.translate(translator.getDictionary("he"));
		Map<String, Set<Object>> legalvalues = new HashMap<String, Set<Object>>();
		legalvalues.put("platform",  new HashSet<Object>(Arrays.asList(new Object[]{Platform.all, mobile ? Platform.mobile : Platform.desktop})));
		legalvalues.put("authentication",  new HashSet<Object>(Arrays.asList(new Object[]{Authentication.all, authenticated ? Authentication.loggedin : Authentication.anonymous})));

		submenu = (Submenu) menuFilter.filter(submenu, legalvalues);
		return toJson(submenu);
	}
	
	
	public String toJson(Object o)
	{
		
		try {
			
			ObjectMapper om = new ObjectMapper();
			return om.writeValueAsString(o);
			
		} catch (JsonProcessingException e) {
			throw new UioException(e);
		} catch (IOException e) {
			throw new UioException(e);
		}
	}
	
	
	

}
