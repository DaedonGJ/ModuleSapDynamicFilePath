package com.sap.test;

import com.sap.tc.logging.*;


public class LogWriterCategory implements ILogWriter {
	
	private Category cat = null;
	private Location logger = null;
	
	@SuppressWarnings("unchecked")
	public LogWriterCategory(Category category, Class clazz) {
		
		this.cat = category;
		this.logger = Location.getLocation(clazz);
		
	}
	
	@SuppressWarnings("unchecked")
	public LogWriterCategory(String applicationCategory, Class clazz) throws Exception {
		
		this.cat = Category.getCategory(Category.APPLICATIONS, applicationCategory);
		if (this.cat == null) 
			throw new Exception("Category no encontrada: " + Category.APPLICATIONS.getName() + "/" + applicationCategory);
		this.logger = Location.getLocation(clazz);
		
	}
	
	public void debug(String msg) {
		logger.debugT(msg);
	}

	public void error(String msg) {
		cat.errorT(logger, msg);
	}

	public void fatal(String msg) {
		cat.fatalT(logger, msg);
	}

	public void info(String msg) {
		cat.infoT(logger, msg);
	}

	public void path(String msg) {
		logger.pathT(msg);
	}

	public void warning(String msg) {
		cat.warningT(logger, msg);
	}

	public void error(String string, Throwable cause) {
		// TODO Auto-generated method stub
		
	}

}