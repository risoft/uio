package com.uiobjects.uio;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ClassUtils;

import com.uiobjects.uio.annotations.interfaces.MapFieldEmitter;
import com.uiobjects.uio.annotations.meta.EmitterAnnotation;

public class FieldsEmitter implements Emitter {

	private final Context context;
	private final String FIELD_LABEL;

	public FieldsEmitter(Context context) {
		this.context = context;
		FIELD_LABEL = context.isMobile() ? "label" : "fieldLabel";
	}

	public void emit(Object form) throws IOException {
		Writer w = context.getWriter();
		PropertyDescriptor descriptors[] = context.getEmitterUtils()
				.getPropertyDescriptors(form);

		w.write('[');
		for (int i = 0; i < descriptors.length; i++) {
			PropertyDescriptor desc = descriptors[i];
			writeField(form, desc);
			if (i < descriptors.length - 1) {
				w.append(',');
			}
		}
		w.write(']');
	}

	private void writeField(Object form, PropertyDescriptor desc)
			throws IOException {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("xtype", "'textfield'");
		map.put(FIELD_LABEL, context.J(desc));
		map.put("name", "'" + desc.getName() + "'");

		if (context.getEmitterUtils().getDefaultValue(desc) != null) {
			String defaultValue = context.getEmitterUtils().getDefaultValue(
					desc);
			map.put("value", defaultValue);
		}
		if (desc.getName().endsWith("password")) {
			map.put("inputType", "'password'");
		}

		if (context.getEmitterUtils().hide(desc)) {
				map.put("hidden", "true");
		}
		modifyMapPerPropertyType(form, desc, map);
		modifyMapPerAnnotations(form, desc, map);
		write(map);
	}

	private void modifyMapPerPropertyType(Object form, PropertyDescriptor desc,
			Map<String, String> map) {
		if (Number.class.isAssignableFrom(ClassUtils.primitiveToWrapper(desc.getPropertyType()))) {
			modifyPerNumberType(form, desc, map);
		}
		if (Boolean.class.equals(ClassUtils.primitiveToWrapper(desc.getPropertyType())))
		{
			modifyPerBooleanType(form, desc, map);

		}
	}
	
	private void modifyPerBooleanType(Object form, PropertyDescriptor desc,
			Map<String, String> map) {
		map.put("xtype", "'checkboxfield'");
		if (!context.isMobile())
		{
			map.put("boxLabel", map.get(FIELD_LABEL));
			map.put(FIELD_LABEL, "''");
			map.put("labelSeparator", "''");
			map.put("hideEmptyLabel", "false");
		}
	}
		
	private void modifyPerNumberType(Object form, PropertyDescriptor desc,
			Map<String, String> map) {
		map.put("xtype", "'numberfield'");
		Field maxField;
		Class<?> propertyType = desc.getPropertyType();
		propertyType = ClassUtils.primitiveToWrapper(propertyType);
		try {
			maxField = propertyType.getField("MAX_VALUE");
			if (maxField != null) {
				Object max = maxField.get(null);
				if (max instanceof Number)
					map.put("maxValue", max.toString());
			}
		} catch (NoSuchFieldException e) {
		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}

		try {
			Field minField = propertyType.getField("MIN_VALUE");
			if (minField != null) {
				Object min = minField.get(null);
				if (min instanceof Number)
					map.put("minValue", min.toString());
			}
		} catch (NoSuchFieldException e) {
		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
	}

	private void write(Map<String, String> map) throws IOException {
		Writer w = context.getWriter();
		w.write("{\n\t");
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = map.get(key);
			w.append(key).append(": ").append(value);
			if (iterator.hasNext()) {
				w.append(",");
			}
		}
		w.append("\n}");
	}

	private void modifyMapPerAnnotations(Object form, PropertyDescriptor desc,
			Map<String, String> map) {
		List<EmitterAnnotation> annotations = context.getEmitterUtils()
				.getEmitterAnnotations(desc);
		for (int i = 0; i < annotations.size(); i++) {
			EmitterAnnotation annotation = annotations.get(i);
			Class<?> emitterClass = annotation.value();
			try {
				MapFieldEmitter emitter = (MapFieldEmitter) emitterClass
						.newInstance();
				emitter.setContext(context);
				emitter.set(desc);
				emitter.modify(map);
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}

		}
	}

	public void writeTranslation(Object form) throws IOException {
		Writer w = context.getWriter();
		PropertyDescriptor descriptors[] = PropertyUtils
				.getPropertyDescriptors(form);
		for (int i = 0; i < descriptors.length; i++) {
			PropertyDescriptor desc = descriptors[i];
			String name = desc.getName();
			if ("class".equals(name))
				continue;
			w.write(name + "=");
			w.write(System.lineSeparator());

		}
	}

}
