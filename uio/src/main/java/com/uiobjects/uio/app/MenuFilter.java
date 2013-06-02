package com.uiobjects.uio.app;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import com.uiobjects.uio.exceptions.UioException;

public class MenuFilter {

	public MenuItem filter(MenuItem menuItem,
			Map<String, Set<Object>> legalValues) {

		if (menuItem.getLeaf()) {
			return complies(menuItem, legalValues) ? menuItem : null;
		} else if (menuItem instanceof Submenu) {
			Submenu sm = (Submenu) menuItem;

			List<MenuItem> filtered = filter(sm.getItems(), legalValues);
			sm.setItems(filtered);
			return filtered == null || filtered.isEmpty() ? null : sm;
		} else {
			if(true) throw new RuntimeException();
			return complies(menuItem, legalValues) ? menuItem : null;
		}
	}

	private List<MenuItem> filter(List<MenuItem> menuItems,
			Map<String, Set<Object>> legalValues) {
		List<MenuItem> result = new ArrayList<MenuItem>();
		for (MenuItem menuItem : menuItems) {
			MenuItem after = filter(menuItem, legalValues);
			if (after != null) {
				result.add(after);
			}
		}

		return result;
	}

	/**
	 * Are all the attributes in one of the legal values
	 */
	private boolean complies(MenuItem menuItem,
			Map<String, Set<Object>> legalValues) {
		{
			Set<String> attributeNames = legalValues.keySet();
			for (String name : attributeNames) {
				Set<Object> ok = legalValues.get(name);
				Object currentValue;
				try {
					currentValue = PropertyUtils.getProperty(menuItem, name);
				} catch (IllegalAccessException e) {
					throw new UioException(e);
				} catch (InvocationTargetException e) {
					throw new UioException(e);
				} catch (NoSuchMethodException e) {
					throw new UioException(e);
				}
				if (!ok.contains(currentValue))
				{
					return false;
				}
			}
			return true;
		}
	}
}