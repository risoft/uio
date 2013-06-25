
package com.uiobjects.uio.app;

import java.util.Properties;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("link")
public class Link extends AbstractMenuItem {
	
	@XStreamOmitField
	private String name;
	@XStreamAsAttribute
	private String token;
	@XStreamAsAttribute
	private String url;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void translate(Properties properties) {
		name = properties.getProperty(token, token);
		
	}

}
