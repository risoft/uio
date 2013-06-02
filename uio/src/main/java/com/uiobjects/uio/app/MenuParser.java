package com.uiobjects.uio.app;

import java.io.IOException;
import java.io.InputStream;

import com.thoughtworks.xstream.XStream;
import com.uiobjects.uio.exceptions.UioException;

public class MenuParser {

	private XStream getXStream()
	{
		XStream xstream = new XStream();
		xstream.processAnnotations(Submenu.class);
		xstream.processAnnotations(Link.class);
		xstream.processAnnotations(Grid.class);
		xstream.processAnnotations(Form.class);
		xstream.alias("submenu", Submenu.class);
		
		return xstream;
	}
	
	public String unparse(Submenu submenu)
	{
		return getXStream().toXML(submenu);
	}
	
	public Submenu parseraw()
	{
		InputStream inp = getInputStream();
		XStream xstream = getXStream();
		Submenu submenu = (Submenu) xstream.fromXML(inp);
		
		try {
			inp.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
		return submenu;
	}
	
	public Submenu parse()
	{
		Submenu sm = parseraw();
		MenuTreeCompleter mtc = new MenuTreeCompleter();
		mtc.complete(sm, "platform", "authentication");
		return sm;
	}
	
	
	private InputStream getInputStream() {
		InputStream inp =
				MenuParser.class.getClassLoader().getResourceAsStream("uio-app/menus.xml");
		if (inp == null)
		{
			throw new UioException("menus.xml not found");
		}
		return inp;
	}
}
