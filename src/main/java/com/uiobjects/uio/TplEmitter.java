package com.uiobjects.uio;

import java.beans.PropertyDescriptor;
import java.io.IOException;

import org.apache.commons.lang.ClassUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class TplEmitter implements Emitter {
	
	private final Context context;
	
	public TplEmitter(Context context)
	{
		this.context = context;
	}

	public void emit(Object form) throws IOException {
		PropertyDescriptor descriptors[] = context.getEmitterUtils().getPropertyDescriptors(form);
		Template t =context.getTemplateFactory().getTemplate(TplEmitter.class);
		
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("fields", descriptors);
		velocityContext.put("ctx", context);
		velocityContext.put("className", ClassUtils.getShortClassName(form.getClass()));
		
		t.merge(velocityContext, context.getWriter());

	}


}
