package com.uiobjects.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.uiobjects.uio.MobileOrDesktop;
import com.uiobjects.uio.Uio;
import com.uiobjects.uio.UioFactory;
import com.uiobjects.uio.app.UioMenu;
import com.uiobjects.uio.exceptions.UioException;

public class InternalUioServlet {

	private Uio uio;
	private UioMenu uioMenu;
	private String desktopDefinitions;
	private String mobileDefinitions;
	
	public void init()  {
		try {
			uio = new UioFactory().getInstance();
			uioMenu = new UioMenu();
		} catch (IOException e) {
			throw new UioException(e);
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String path = req.getPathInfo();
		
		if (path == null || "/".equals(path)) redirectByClientType(req, resp);
		else if ("/mobile/definitions.js".equals(path)) serveMobileDefinitions(req, resp);
		else if ("/desktop/definitions.js".equals(path)) serveDesktopDefinitions(req, resp);
		else if ("/desktop/menu.js".equals(path)) serveDesktopMenu(req, resp);
		else if ("/mobile/menu.js".equals(path)) serveMobileMenu(req, resp);
		else serveStatic(req, resp);
	}
	
	private void redirectByClientType(HttpServletRequest req,
			HttpServletResponse resp) {
		MobileOrDesktop mod = new MobileOrDesktop();
		String path = req.getPathInfo();
		path = path == null ? "uio/" : path;
		try {
			resp.sendRedirect(mod.isMobile(req) ? path+"mobile/main.html" : path+"desktop/main.html");
		} catch (IOException e) {
			throw new UioException(e);
		}
	}

	private void serveStatic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String path = req.getPathInfo();
		InputStream inp = InternalUioServlet.class.getClassLoader().getResourceAsStream("static"+path);
		if (inp == null)
		{
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "static: "+path);
			return;
		}
		
		if (path.endsWith(".js")) resp.setContentType("application/javascript");
		else if (path.endsWith(".json")) resp.setContentType("application/json");
		else if (path.endsWith(".css")) resp.setContentType("text/css");

		IOUtils.copy(inp, resp.getOutputStream());
		inp.close();
		return;
		
	}

	private void serveMobileMenu(HttpServletRequest req,
			HttpServletResponse resp) {
		resp.setContentType("application/javascript");
		resp.setCharacterEncoding("utf-8");		
		String mobileMenu = uioMenu.getMenu(true, isAuth(req));
		try {
			resp.getWriter().write(mobileMenu);
		} catch (IOException e) {
			throw new UioException(e);
		}		
	}

	private boolean isAuth(HttpServletRequest req) {
		return req.getSession().getAttribute("home") != null;
	}

	private void serveDesktopMenu(HttpServletRequest req,
			HttpServletResponse resp) {
		resp.setContentType("application/javascript");
		resp.setCharacterEncoding("utf-8");		
		String desktopMenu = uioMenu.getMenu(false, isAuth(req));
		try {
			resp.getWriter().write(desktopMenu);
		} catch (IOException e) {
			throw new UioException(e);
		}	
		}

	private void serveDesktopDefinitions(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.setContentType("application/javascript");
			resp.setCharacterEncoding("utf-8");		
			if (desktopDefinitions == null)
			{
				StringWriter sw = new StringWriter();
				uio.emitDesktop(sw);
				sw.flush();
				sw.close();
				desktopDefinitions = sw.toString();
			}
			resp.getWriter().write(desktopDefinitions);
		} catch (IOException e) {
			throw new UioException(e);
		}	
	}

	private void serveMobileDefinitions(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.setContentType("application/javascript");
			resp.setCharacterEncoding("utf-8");		
			if (mobileDefinitions ==null)
			{
				StringWriter sw = new StringWriter();
				uio.emitMobile(sw);
				sw.flush();
				sw.close();
				mobileDefinitions = sw.toString();
			}
			resp.getWriter().write(mobileDefinitions);
		} catch (IOException e) {
			throw new UioException(e);
		}	
	}

}
