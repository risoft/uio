package com.uiobjects.uio;

import java.io.IOException;
import java.io.PrintWriter;

public class JavascriptFileGenerator {
	
	public void generate() throws IOException
	{
		PrintWriter w = new PrintWriter(System.out);
		new UioFactory().getInstance().emit(w, true);
		w.close();
	}
	
	public static void main(String[] args) throws IOException {
		//Logger.getRootLogger().removeAllAppenders();
		new JavascriptFileGenerator().generate();
		
	}

}
