package com.uiobjects.uio;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class GridColumnsEmitter implements Emitter {
	
	private final Context context;
	
	public GridColumnsEmitter(Context context)
	{
		this.context = context;
	}
	
	public void emit(Object form) throws IOException
	{
		PropertyDescriptor descriptors[] = context.getEmitterUtils().getPropertyDescriptors(form);
		Template t =context.getTemplateFactory().getTemplate(GridColumnsEmitter.class);
		
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("fields", descriptors);
		velocityContext.put("ctx", context);
		velocityContext.put("className", ClassUtils.getShortClassName(form.getClass()));
		
		t.merge(velocityContext, context.getWriter());
	}
	

	
	
	
	public void writeTranslation(Object form) throws IOException
	{
		Writer w = context.getWriter();
		PropertyDescriptor descriptors[] = PropertyUtils.getPropertyDescriptors(form);
		for(int i=0; i < descriptors.length; i++)
		{
			PropertyDescriptor desc = descriptors[i];
			String name = desc.getName();
			if ("class".equals(name)) continue;
			w.write(name+"=");
			w.write(System.lineSeparator());

		}
	}
	
	
	

}
