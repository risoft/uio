package com.uiobjects.uio;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Translator {
	
	private static final String MISSING = "!missing";
	
	private Map<String, Properties> dictionaries = new HashMap<String, Properties>();

	public String translate(PropertyDescriptor desc, String category, String language)
	{
		String className = desc.getReadMethod().getDeclaringClass().getName();
		String fullName = className+ '.' + desc.getName();
		return translate(fullName, category, language);
	}
	
	public Properties getDictionary(String language)
	{
		Properties dictionary = dictionaries.get(language);
		if (dictionary == null)
		{
			dictionary = new Properties();
			InputStream inp = 
					Translator.class.getClassLoader().getResourceAsStream("uiostrings_"+language+".properties");
			try {
				dictionary.load(new InputStreamReader(inp, "utf-8"));
				inp.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			dictionaries.put(language, dictionary);
		}
		return dictionary;
			
	}
	
	public String translate(String qualified, String category, String language)
	{
		String translated = translate(qualified+"|"+category, language);
		return MISSING.equals(translated)
				? translate(qualified, language) :
				translated;
	}

	
	public String translate(String qualified, String language)
	{
		Properties dict = getDictionary(language);
		while(!qualified.isEmpty())
		{
			String translated = dict.getProperty(qualified);
			if (translated != null) return translated;
			int nextDot = qualified.indexOf('.');
			if (nextDot == -1) break;
			qualified = qualified.substring(nextDot+1);
		}
		return MISSING;
	}

	public String translate(Class<?> c, String category, String language) {
		return translate(c.getName(), category, language);
	}
}
