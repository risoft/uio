package com.uiobjects.uio;

import java.beans.PropertyEditor;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.uiobjects.uio.annotations.Form;
import com.uiobjects.uio.buttons.Button;
import com.uiobjects.uio.exceptions.UioException;

public class ButtonsEmitter implements Emitter {
	
	private final Context context;
	
	public ButtonsEmitter(Context context)
	{
		this.context = context;
	}

	public void emit(Object form) throws IOException {
		Template t = context.getTemplateFactory().getTemplate(ButtonsEmitter.class);
		VelocityContext velocityCtx = new VelocityContext();
		Class cl = form.getClass();
		
		Form formAnnotation = (Form)cl.getAnnotation(Form.class);
		if (formAnnotation == null)
		{
			formAnnotation = (Form)DefaultForm.class.getAnnotation(Form.class);
		}
		Class<? extends Button> buttonClasses[] = formAnnotation.buttons();
		String buttonParams[] = formAnnotation.buttonParams();

		if (buttonParams.length > 0 && buttonParams.length != buttonClasses.length)
		{
			throw new UioException("The length of buttonParams must be 0 or equal to the length of buttonClasses in the Form annotation in class "+cl);
		}
		
		List<Button> buttons = new ArrayList<Button>();

		for(int i=0; i < buttonClasses.length; i++)
		{
			try {
				Constructor ctor = buttonClasses[i].getConstructor(Context.class);
				Button b = (Button)ctor.newInstance(context);
				if (buttonParams.length != 0)
				{
					String params = buttonParams[i];
					populateParams(b, params);
				}
				buttons.add(b);
			} catch (NoSuchMethodException e) {
				throw new UioException(e);
			} catch (SecurityException e) {
				throw new UioException(e);
			} catch (InstantiationException e) {
				throw new UioException(e);
			} catch (IllegalAccessException e) {
				throw new UioException(e);
			} catch (IllegalArgumentException e) {
				throw new UioException(e);
			} catch (InvocationTargetException e) {
				throw new UioException(e);

			}
			
		}
		velocityCtx.put("buttons", buttons);
		velocityCtx.put("ctx", context);
		velocityCtx.put("form", cl);
		t.merge(velocityCtx, context.getWriter());
	}

	private void populateParams(Button b, String params) {
		String pairs[] = params.split(",");		
		for(int i=0; i < pairs.length; i++)
		{
			String pair = pairs[i];
			String parts[] = pair.split("[=]");
			String name = parts[0];
			String value = parts[1];
			Class c;
			PropertyEditor p;
			try {
				Class cl = PropertyUtils.getPropertyType(b, name);
				cl = ClassUtils.primitiveToWrapper(cl);
				Constructor ctor = cl.getConstructor(String.class);
				Object valueObj = ctor.newInstance(value);
				PropertyUtils.setProperty(b, name, valueObj);
			} catch (IllegalAccessException e) {
				throw new UioException(e);
			} catch (InvocationTargetException e) {
				throw new UioException(e);
			} catch (NoSuchMethodException e) {
				throw new UioException(e);
			} catch (InstantiationException e) {
				throw new UioException(e);

			}
		}
	}


}
