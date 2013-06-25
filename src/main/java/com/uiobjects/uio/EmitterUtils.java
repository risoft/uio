package com.uiobjects.uio;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.uiobjects.uio.annotations.IdField;
import com.uiobjects.uio.annotations.Skip;
import com.uiobjects.uio.annotations.interfaces.MapFieldEmitter;
import com.uiobjects.uio.annotations.meta.EmitterAnnotation;

public class EmitterUtils {
	
	private static final Log log = LogFactory.getLog(EmitterUtils.class);
	
	public PropertyDescriptor[] getPropertyDescriptors(Object form) {
		log.info("Getting properties of "+form.getClass());
		Member members[] = form.getClass().getDeclaredFields();
		PropertyDescriptor all[] = PropertyUtils.getPropertyDescriptors(form);
		List<PropertyDescriptor> descs = new ArrayList<PropertyDescriptor>();
		for(int i=0; i < members.length; i++)
		{
			for(int j=0; j < all.length; j++)
			{
				if (mustSkip(all[j])) continue;
				
				if (members[i].getName().equals(all[j].getName()))
				{
					descs.add(all[j]);
				}
			}
		}
		
		return descs.toArray(new PropertyDescriptor[descs.size()]);
	}

	public boolean mustSkip(PropertyDescriptor desc)
	{
		return "class".equals(desc.getName()) || hasAnnotation(desc, Skip.class);
	}

	public boolean hide(PropertyDescriptor desc)
	{
		return hasAnnotation(desc, IdField.class);
	}
	
	public String getDefaultValue(PropertyDescriptor desc)
	{
		IdField idField = (IdField)getAnnotation(desc, IdField.class);
		if (idField == null) return null;
		if (idField.defaultValue().equals("")) return null;
		return idField.defaultValue();
	}


	
	public boolean hasAnnotation(PropertyDescriptor desc, Class<? extends Annotation> annotation)
	{
		return getAnnotation(desc, annotation) != null;
	}
	
	public PropertyDescriptor getAnnotatedProperty(Object form, Class<? extends Annotation> annotation)
	{
		PropertyDescriptor descs[] = getPropertyDescriptors(form);
		for(int i=0; i <descs.length; i++)
		{
			if (hasAnnotation(descs[i], annotation))
			{
				return descs[i];
			}
		}
		return null;
	}

	
	public Annotation getAnnotation(PropertyDescriptor desc, Class<? extends Annotation> annotation)
	{
		return 
			getAnnotationByReadMethod(desc, annotation) != null ?
			getAnnotationByReadMethod(desc, annotation) :
			getAnnotationByField(desc, annotation);
	}
	
	private Annotation getAnnotationByReadMethod(PropertyDescriptor desc, Class<? extends Annotation> annotation)
	{
		Method m = desc.getReadMethod();
		if (m == null)
		{
			return null;
		}
		Annotation actual = m.getAnnotation(annotation);
		return actual;
	}

	
	private Annotation getAnnotationByField(PropertyDescriptor desc, Class<? extends Annotation> annotation)
	{
		return getField(desc) == null ? null : getField(desc).getAnnotation(annotation);
	}

	private Annotation getAnnotationByMethod(Method m, Class<? extends Annotation> annotation)
	{
		return m == null ? null : m.getAnnotation(annotation);
	}
	
	private Field getField(PropertyDescriptor desc)
	{
		Field field;
		try {
			log.debug("Getting field for "+desc);
			Method method = desc.getReadMethod();
			if (method == null) method = desc.getWriteMethod();
			field = method.getDeclaringClass().getDeclaredField(desc.getName());
			log.debug("Got field: "+field);
		} catch (NoSuchFieldException e) {
			log.debug("No such field");
			return null;
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		return field;
	}

	private Annotation[] getAnnotations(PropertyDescriptor desc)
	{
		List<Annotation> annotations = new ArrayList<Annotation>();
		Field f = getField(desc);
		if (f != null)
		{
			Annotation annotation[] = f.getAnnotations();
			annotations.addAll(Arrays.asList(annotation));
		}
		Annotation methodAnnotations[] = desc.getReadMethod().getAnnotations();
		annotations.addAll(Arrays.asList(methodAnnotations));
		return annotations.toArray(new Annotation[annotations.size()]);
	}
	
	public List<EmitterAnnotation> getEmitterAnnotations(PropertyDescriptor desc)
	{
		List<EmitterAnnotation> result = new ArrayList<EmitterAnnotation>();
		
		Annotation annotations[] = getAnnotations(desc);
		for(int i=0; i < annotations.length; i++)
		{
			Annotation annotation = annotations[i];
			EmitterAnnotation emitter = annotation.annotationType().getAnnotation(EmitterAnnotation.class);
			if (emitter == null) continue;
			if (MapFieldEmitter.class.isAssignableFrom(emitter.value()))
			{
				result.add(emitter);
			}
		}
		
		return result;
	}
	

}
