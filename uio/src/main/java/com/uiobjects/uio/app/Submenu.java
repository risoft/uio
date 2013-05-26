package com.uiobjects.uio.app;

import java.util.List;
import java.util.Properties;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("submenu")
public class Submenu extends AbstractMenuItem {
	
	@XStreamOmitField
	private String name;
	@XStreamAsAttribute
	private String token;
	@XStreamImplicit
	private List<MenuItem> items;

	public String getName() {
		return name;
	}
	
	public List<MenuItem> getItems()
	{		
		return items;
	}
	
	public String getToken() {
		return token;
	}
	
	public void translate(Properties properties)
	{
		name = properties.getProperty(token, token);
		if (items != null)
		{
			for(int i=0; i < items.size(); i++)
			{
				((MenuItem)items.get(i)).translate(properties);
			}
		}
	}	
	
	public void setItems(List<MenuItem> items) {
		this.items = items;
	}
	
	@Override
	public boolean getLeaf() {
		return false;
	}
}
