package com.uiobjects.uio.app;

import org.apache.commons.lang.ClassUtils;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public abstract class AbstractMenuItem implements MenuItem {
	
	@XStreamAsAttribute
	private Platform platform;
	
	public String getType() {
		return ClassUtils.getShortClassName(getClass()).toLowerCase();
	}
	
	public String getText()
	{
		return getName();
	}
	
	public boolean getLeaf() {
		return true;
	}
	
	public Platform getPlatform()
	{
		return platform;
	}

}
