package com.uiobjects.uio.app;

import java.util.List;
import java.util.Properties;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.uiobjects.uio.buttons.GridButton;

@XStreamAlias("grid")
public class Grid extends AbstractMenuItem {

	@XStreamAsAttribute
	private String token;
	@XStreamAsAttribute
	@XStreamOmitField
	private String name;

	@XStreamAsAttribute
	@XStreamAlias("class")
	private String className;
	private boolean loadObject = false;
	@XStreamImplicit
	private List<GridButton> buttons;
	
	@XStreamAsAttribute
	private String url;

	public String getName() {
		return name;
	}

	public String getToken() {
		return token;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void translate(Properties properties) {
		name = properties.getProperty(token, token);
		
		if (buttons != null)
		{
			for (GridButton button : buttons) {
				String buttonName = properties.getProperty(button.getToken(), button.getToken());
				button.setName(buttonName);
			}
		}
	}

	public boolean isLoadObject() {
		return loadObject;
	}

	public void setLoadObject(boolean loadObject) {
		this.loadObject = loadObject;
	}

	public void setButtons(List<GridButton> buttons) {
		this.buttons = buttons;
	}

	public List<GridButton> getButtons() {
		return buttons;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}

}
