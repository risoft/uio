package com.uiobjects.uio;

import java.io.IOException;
import java.io.PrintWriter;

import junit.framework.TestCase;

public class JavascriptFileGenerator extends TestCase {
	
	public void testGenerate() throws IOException
	{
		
		PrintWriter w = new PrintWriter(System.out);
		new UioFactory().getInstance().emit(w, true);
		w.close();
	}
	
	

}
