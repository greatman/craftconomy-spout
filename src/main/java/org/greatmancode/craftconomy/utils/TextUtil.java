package org.greatmancode.craftconomy.utils;

import org.spout.api.ChatColor;

public class TextUtil {

	
	public static String generateWithPrefix(String msg)
	{
		return ChatColor.DARK_GREEN + "[" + ChatColor.WHITE + "Money" + ChatColor.DARK_GREEN + "]" + ChatColor.WHITE + msg; 
	}
}
