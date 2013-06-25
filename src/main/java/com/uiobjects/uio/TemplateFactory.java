package com.uiobjects.uio;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class TemplateFactory {
	
	public Template getTemplate(String name)
	{
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		Template t = ve.getTemplate("uio-templates/"+name+".vm", "utf-8");
		return t;
	}
	
	public Template getTemplate(Class<?> clazz)
	{
		return getTemplate(clazz.getName().replace('.' , '/'));
	}
	
	
}
