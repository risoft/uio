package com.uiobjects.uio;

import java.io.IOException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.uiobjects.uio.annotations.Form;
import com.uiobjects.uio.annotations.FormButton;

public class ButtonsEmitter implements Emitter {
	
	private final Context context;
	
	public ButtonsEmitter(Context context)
	{
		this.context = context;
	}

	public void emit(Object form) throws IOException {
		Template t = context.getTemplateFactory().getTemplate(ButtonsEmitter.class);
		VelocityContext velocityCtx = new VelocityContext();
		
		Form formAnnotation = (Form)form.getClass().getAnnotation(Form.class);
		if (formAnnotation == null)
		{
			formAnnotation = (Form)DefaultForm.class.getAnnotation(Form.class);
		}
		FormButton buttons[] = formAnnotation.buttons();

		velocityCtx.put("buttons", buttons);
		velocityCtx.put("ctx", context);
		t.merge(velocityCtx, context.getWriter());
	}


}
