package com.uiobjects.uio;

import javax.servlet.http.HttpServletRequest;


public class MobileOrDesktop {
	
	public boolean isMobile(HttpServletRequest req)
	{
		String ua = req.getHeader("User-Agent");
		return ua.indexOf("Mobile") >= 0;
	}
}
