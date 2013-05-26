package com.uiobjects.uio;

import java.io.IOException;
import java.io.PrintWriter;

public class StringsFileGenerator {
	
	public void generate() throws IOException
	{
		PrintWriter w = new PrintWriter(System.out);
		new UioFactory().getInstance().emitTranslationFile(w);
		w.close();
	}
	
	public static void main(String[] args) throws IOException {
		new StringsFileGenerator().generate();
		
	}

}
