package com.uiobjects.uio;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uiobjects.uio.app.MenuParser;
import com.uiobjects.uio.app.MobileTransformer;
import com.uiobjects.uio.app.Submenu;
import com.uiobjects.uio.exceptions.UioException;


public class UioMenu {
	
	private Translator translator = new Translator();
	private MenuParser menuParser = new MenuParser();
	private MobileTransformer mobileTransformer = new MobileTransformer();

	
	public String getMobileMenu()
	{
		Submenu submenu = menuParser.parse();
		submenu.translate(translator.getDictionary("he"));
		mobileTransformer.filter(submenu);
		return toJson(submenu);
	}
	
	public String getDesktopMenu()
	{
		Submenu submenu = menuParser.parse();
		submenu.translate(translator.getDictionary("he"));
		return toJson(submenu);		
	}

	private String toJson(Submenu s)
	{
		
		try {
			
			ObjectMapper om = new ObjectMapper();
			return om.writeValueAsString(s);
			
		} catch (JsonProcessingException e) {
			throw new UioException(e);
		} catch (IOException e) {
			throw new UioException(e);
		}
	}
	
	
	

}
