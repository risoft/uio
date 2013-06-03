package com.uiobjects.uio.buttons;

import java.io.IOException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.uiobjects.uio.Context;

public abstract class AbstractButton implements Button {

	protected final Context context;
	
	public AbstractButton(Context context)
	{
		this.context = context;
	}
	
	public void emit(Object form) throws IOException {
		Template t = context.getTemplateFactory().getTemplate(this.getClass());
		VelocityContext velocityCtx = new VelocityContext();
		
		velocityCtx.put("ctx", context);
		velocityCtx.put("form", form);
		velocityCtx.put("button", this);

		t.merge(velocityCtx, context.getWriter());
	}

}
