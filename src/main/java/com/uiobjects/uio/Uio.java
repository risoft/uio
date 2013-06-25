package com.uiobjects.uio;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.uiobjects.uio.annotations.IdField;

public class Uio {
	
	private static final Log log = LogFactory.getLog(Uio.class);
	
	private List<Class<?>> classes;
	

	public Uio(List<Class<?>> classes)
	{
		this.classes = classes;
	}

	public void emitDesktop(Writer writer) throws IOException
	{
		emit(writer, false);
	}
	
	public void emitMobile(Writer writer) throws IOException
	{
		emit(writer, true);
	}
	
	public void emit(Writer writer, boolean mobile) throws IOException
	{
		Context ctx = new Context();
		ctx.setMobile(mobile);
		ctx.setWriter(writer);
		writer.write("uio = {formdefs: {}};\n");
		for(int i=0; i < classes.size(); i++)
		{
			Class<?> cl = classes.get(i);
			String className = ClassUtils.getShortClassName(cl);
			writer.append("uio.formdefs.").append(className).append("={};\n");

			emitFormTitle(ctx, cl);
			emitGridTitle(ctx, cl);
			emitIdField(ctx, cl);
			emitFields(ctx, cl);
			emitTpl(ctx, cl);
			emitButtons(ctx, cl);
			emitStoreFields(ctx, cl);
			emitGridColumns(ctx, cl);

		}
	}

	private void emitTpl(Context ctx, Class<?> cl) throws IOException {
		if (!ctx.isMobile()) return;
		
		new TplEmitter(ctx).emit(newInstance(cl));
		
	}

	private void emitFields(Context ctx, Class<?> cl) throws IOException {
		Writer w = ctx.getWriter();
		String className = ClassUtils.getShortClassName(cl);
		
		Object o = newInstance(cl);
		
		FieldsEmitter e = new FieldsEmitter(ctx);
		w.append("uio.formdefs.").append(className).append(".items=");
		e.emit(o);
		w.append(";\n");		
	}

	private Object newInstance(Class<?> cl) {
		try {
			return cl.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private void emitFormTitle(Context ctx, Class<?> cl) throws IOException {
		Writer w = ctx.getWriter();
		String className = ClassUtils.getShortClassName(cl);
		w.append("uio.formdefs.").append(className).append(".title=");
		w.append(ctx.J(cl, "form")).append(";\n");
	}
	
	private void emitIdField(Context ctx, Class<?> cl) throws IOException {
		log.info("Emitting IdField of "+ClassUtils.getShortClassName(cl));
		Writer w = ctx.getWriter();
		String className = ClassUtils.getShortClassName(cl);
		w.append("uio.formdefs.").append(className).append(".idField=");
		PropertyDescriptor idProperty = ctx.getEmitterUtils().getAnnotatedProperty(newInstance(cl), IdField.class);
		w.append("'").append(idProperty == null ? "id" : idProperty.getName()).append("';\n");
	}
	
	
	private void emitGridTitle(Context ctx, Class<?> cl) throws IOException {
		Writer w = ctx.getWriter();
		String className = ClassUtils.getShortClassName(cl);
		w.append("uio.formdefs.").append(className).append(".gridTitle=");
		w.append(ctx.J(cl, "grid")).append(";\n");
	}
	

	private void emitStoreFields(Context ctx, Class<?> cl) throws IOException {
		new JsonStoreEmitter(ctx).emit(newInstance(cl));
	}
	
	private void emitGridColumns(Context ctx, Class<?> cl) throws IOException {
		new GridColumnsEmitter(ctx).emit(newInstance(cl));
	}
	
	
	private void emitButtons(Context ctx, Class<?> cl) throws IOException {
		Writer w = ctx.getWriter();
		String className = ClassUtils.getShortClassName(cl);
		Object o = newInstance(cl);

		w.append("uio.formdefs.").append(className).append(".buttons=");
		new ButtonsEmitter(ctx).emit(o);
	}
	

	public void emitTranslationFile(Writer w) throws IOException
	{
		Context ctx = new Context();
		ctx.setWriter(w);
		for(int i=0; i < classes.size(); i++)
		{
			Class<?> cl = classes.get(i);
			w.write(ClassUtils.getShortClassName(cl)+"="+System.lineSeparator());
			try {
				Object o = cl.newInstance();
				String className = ClassUtils.getShortClassName(cl);
				FieldsEmitter e = new FieldsEmitter(ctx);
				e.writeTranslation(o);
				w.write(System.lineSeparator());
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
