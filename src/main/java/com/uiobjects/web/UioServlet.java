package com.uiobjects.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UioServlet extends HttpServlet{

	private InternalUioServlet internalUioServlet = new InternalUioServlet();
	
	@Override
	public void init() throws ServletException {
		internalUioServlet.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		internalUioServlet.doGet(req, resp);
	}
}
