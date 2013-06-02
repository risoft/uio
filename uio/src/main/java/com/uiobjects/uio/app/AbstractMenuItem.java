package com.uiobjects.uio.app;

import org.apache.commons.lang.ClassUtils;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public abstract class AbstractMenuItem implements MenuItem {
	
	@XStreamAsAttribute
	private Platform platform;
	
	@XStreamAsAttribute
	private Authentication authentication;
	
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
	
	public Authentication getAuthentication()
	{
		return authentication;
	}
	
	@Override
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	@Override
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	
	@Override
	public String toString() {
		return "["+getText()+":"+platform+"]";
	}
}
