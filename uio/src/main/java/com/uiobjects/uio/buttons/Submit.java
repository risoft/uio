package com.uiobjects.uio.buttons;

import com.uiobjects.uio.Context;

public class Submit extends AbstractButton {

	private boolean reload = false;
	
	public Submit(Context context) {
		super(context);
	}
	
	public void setReload(boolean reload) {
		this.reload = reload;
	}
	
	public boolean isReload() {
		return reload;
	}
	

}
