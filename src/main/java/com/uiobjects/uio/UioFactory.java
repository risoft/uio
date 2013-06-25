package com.uiobjects.uio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import com.uiobjects.web.UioServlet;



public class UioFactory {
	
	public Uio getInstance() throws IOException {
		List<String> classNames = getClassNames();
		List<Class<?>> classes = getClasses(classNames);
		return new Uio(classes);
	}

	private List<Class<?>> getClasses(List<String> classNames) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (int i = 0; i < classNames.size(); i++) {
			try {
				classes.add(Class.forName(classNames.get(i)));
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		return classes;
	}

	private List<String> getClassNames() throws IOException {
		InputStream inp = UioServlet.class.getClassLoader().getResourceAsStream(
				"uio.txt");
		if (inp == null) {
			throw new RuntimeException("file not found");
		}

		List<String> lines = new ArrayList<String>();
		
		LineNumberReader lr = new LineNumberReader(new InputStreamReader(inp, "utf-8"));
		while (true) {
			String line = lr.readLine();
			if (line == null)
				break;
			if (line.trim().length() == 0 || line.trim().startsWith("#")) continue;
			lines.add(line);
		}

		return lines;

	}
}
