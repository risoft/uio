package com.uiobjects.uio.app;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class MobileTransformer {
	
	private Submenu deepcopy(Submenu s)
	{
		XStream xstream = new XStream();
		return (Submenu)xstream.fromXML(xstream.toXML(s));
	}
	
	public Submenu filter(Submenu s)
	{
		MenuItem copy = deepcopy(s);
		Platform platform = copy.getPlatform();
		return (Submenu)filter(copy, platform);
	}

	private MenuItem filter(MenuItem menuItem, Platform platform) {
		
		Platform p = menuItem.getPlatform();
		Platform newPlatform = p == null ? platform : p;
		
		if (menuItem.getLeaf())
		{
			return newPlatform == Platform.desktop ? null : menuItem;
		}
		else if (menuItem instanceof Submenu)
		{
			Submenu sm = (Submenu)menuItem;
			
			List<MenuItem> filtered = filter(sm.getItems(), newPlatform);
			sm.setItems(filtered);
			return filtered == null || filtered.isEmpty() ? null : sm;
		}
		else
		{
			return 
					newPlatform == Platform.all || newPlatform == Platform.mobile ?
					menuItem :
					null;
		}
	}
	
	private List<MenuItem> filter(List<MenuItem> menuItems, Platform platform) {
		List<MenuItem> result = new ArrayList<MenuItem>();
		for (MenuItem menuItem : menuItems) {
			MenuItem after = filter(menuItem, platform);
			if (after != null)
			{
				result.add(after);
			}
		}
		
		return result;
	}

}
