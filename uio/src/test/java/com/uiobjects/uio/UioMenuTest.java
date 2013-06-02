package com.uiobjects.uio;

import junit.framework.TestCase;

import com.uiobjects.uio.app.UioMenu;

public class UioMenuTest extends TestCase {

		
	@Override
	protected void setUp() throws Exception {
		System.out.println(this.getName());
	}
	
	
	
	public void testDesktopMenuAuth()
	{
		UioMenu uioMenu = new UioMenu();
		System.out.println(uioMenu.getMenu(false, true));
	}

	
	public void testDesktopMenuNotAuth()
	{
		UioMenu uioMenu = new UioMenu();
		System.out.println(uioMenu.getMenu(false, false));
	}
	
	public void testMobileMenuAuth()
	{
		UioMenu uioMenu = new UioMenu();
		System.out.println(uioMenu.getMenu(true, true));
	}

	
	public void testMobileMenuNotAuth()
	{
		UioMenu uioMenu = new UioMenu();
		System.out.println(uioMenu.getMenu(true, false));
	}
	
}
