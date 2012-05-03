package org.greatmancode.craftconomy.utils;

import java.util.logging.Logger;

public class CCLogger {

	public static Logger log;
	public static String prefix;
	public CCLogger(Logger paramLog, String paramPrefix)
	{
		log = paramLog;
		prefix = "[" + paramPrefix + "]";
	}
	
	public static void info(String msg)
	{
		log.info(prefix + msg);
	}
	
	public static void severe(String msg)
	{
		log.severe(prefix + msg);
	}
}
