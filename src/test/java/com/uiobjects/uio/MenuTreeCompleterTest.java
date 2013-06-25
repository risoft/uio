package com.uiobjects.uio;

import junit.framework.TestCase;

import com.uiobjects.uio.app.MenuParser;
import com.uiobjects.uio.app.MenuTreeCompleter;
import com.uiobjects.uio.app.Submenu;
import com.uiobjects.uio.app.UioMenu;

public class MenuTreeCompleterTest extends TestCase {
	
	public void testMenuCompleter()
	{
		MenuParser mp = new MenuParser();
		UioMenu uioMenu = new UioMenu();
		Submenu sm = mp.parseraw();
		System.out.println("BEFORE");
		System.out.println(uioMenu.toJson(sm));
		System.out.println("AFTER");
		MenuTreeCompleter mtc = new MenuTreeCompleter();
		mtc.complete(sm, "platform", "authentication");
		System.out.println(uioMenu.toJson(sm));

	}

}
