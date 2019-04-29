package com.sap.test;


public interface ILogWriter {

	public void path(String msg);
	
	public void debug(String msg);
	
	public void info(String msg);
	
	public void warning(String msg);
	
	public void error(String msg);
	
	public void fatal(String msg);
}