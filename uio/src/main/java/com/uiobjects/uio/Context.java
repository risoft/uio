package com.uiobjects.uio;

import java.beans.PropertyDescriptor;
import java.io.Writer;

import org.apache.commons.lang.StringEscapeUtils;

public class Context {
	
	private Translator translator = new Translator();
	private String language = "he";
	private Writer writer;
	private boolean mobile = false;
	private TemplateFactory templateFactory = new TemplateFactory();
	private EmitterUtils emitterUtils = new EmitterUtils();
	
	public void setWriter(Writer writer) {
		this.writer = writer;
	}
	
	public Writer getWriter() {
		return writer;
	}
	
	public EmitterUtils getEmitterUtils() {
		return emitterUtils;
	}

	public Translator getTranslator() {
		return translator;
	}
	
	public TemplateFactory getTemplateFactory() {
		return templateFactory;
	}
	
	public String J(Class<?> c)
	{
		return J(c, null);
	}
	
	public String J(Class<?> c, String category)
	{
		return jswrap(translator.translate(c, category, language));
	}

	public String J(PropertyDescriptor desc)
	{
		return J(desc, null);
	}
	
	public String H(PropertyDescriptor desc)
	{
		return H(desc, null);
	}

	public String J(PropertyDescriptor desc, String category)
	{
		return jswrap(translator.translate(desc, category, language));
	}
	
	public String H(PropertyDescriptor desc, String category)
	{
		return htmlwrap(translator.translate(desc, category, language));
	}
	
	public String J(String original)
	{
		return jswrap(translator.translate(original, language));
	}
	
	/** wraps in single quotes and js escapes */
	private String jswrap(String original)
	{
		if (original == null || original.trim().length() == 0) return "''";
		return '\'' + StringEscapeUtils.escapeJavaScript(original) +'\'';
	}
	
	/** Escapes double quotes **/
	public String htmlwrap(String original)
	{
		return original.replace("\"", "\\\"");
	}
	
	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}
	
	public boolean isMobile() {
		return mobile;
	}
	
	public boolean getMobile() {
		return isMobile();
	}
	
}
