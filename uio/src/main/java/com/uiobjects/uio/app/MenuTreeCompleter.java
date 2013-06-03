package com.uiobjects.uio.app;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.uiobjects.uio.exceptions.UioException;

public class MenuTreeCompleter {
	
		
	public void complete(Submenu s, String ... attributes)
	{
		Map<String, Object> attributeValues = new HashMap<String, Object>();
		saveValues(s, attributes, attributeValues);
		complete(s, attributeValues, attributes);
	}

	private void complete(MenuItem mi, Map<String, Object> attributeValues,
			String[] attributes) {
			if (mi.getLeaf())
			{
				loadValues(mi, attributes, attributeValues);
				return;
			}
			attributeValues = saveValues(mi, attributes, attributeValues);

			Submenu submenu = (Submenu) mi;
			if (submenu.getItems() == null) return;
			for (MenuItem item : submenu.getItems()) {
				complete(item, attributeValues, attributes);
			}
		}

	private Map<String, Object> saveValues(MenuItem mi, String[] attributes,
			Map<String, Object> attributeValues) {
		Map<String, Object> result = new HashMap<String, Object>();

		for(int i=0; i < attributes.length; i++)
		{
			try {
				String name = attributes[i];
				Object value = PropertyUtils.getProperty(mi,  name);
				result.put(name, value != null ? value : attributeValues.get(name));
				 
			} catch (IllegalAccessException e) {
				throw new UioException(e);
			} catch (InvocationTargetException e) {
				throw new UioException(e);
			} catch (NoSuchMethodException e) {
				throw new UioException(e);
			}
		}
		return result;
	}
	
	private void loadValues(MenuItem mi, String[] attributes,
			Map<String, Object> attributeValues) {
		for(int i=0; i < attributes.length; i++)
		{
			try {
				String name = attributes[i];
				Object value = PropertyUtils.getProperty(mi,  name);
				if (value == null)
				{
					PropertyUtils.setProperty(mi, name, attributeValues.get(name));

				}
			} catch (IllegalAccessException e) {
				throw new UioException(e);
			} catch (InvocationTargetException e) {
				throw new UioException(e);
			} catch (NoSuchMethodException e) {
				throw new UioException(e);
			}
		}
	}

	
}
