package com.uiobjects.uio.app;

import java.util.Properties;

public interface MenuItem {
	
	public String getToken();
	
	public String getName();
	
	public String getType();
	
	public void translate(Properties properties);
	
	public Platform getPlatform();
	
	public void setPlatform(Platform platform);

	public Authentication getAuthentication();
	
	public void setAuthentication(Authentication authentication);

	
	public boolean getLeaf();

}
