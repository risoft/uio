package com.uiobjects.uio.app;

import java.util.Properties;

import com.uiobjects.uio.Translator;


public class Main {

	
	public static void main(String[] args) 
	{
		MenuParser mp = new MenuParser();
		Submenu sm = mp.parse();
		
		
		
		Translator t = new Translator();
		Properties p = t.getDictionary("he" );
		
		sm.translate(p);
		
		System.out.println(mp.unparse(sm));
		
		System.out.println("============================================");
		
		MobileTransformer mt = new MobileTransformer();
		Submenu filtered = mt.filter(sm);
		
		System.out.println(mp.unparse(filtered));


	}
}
