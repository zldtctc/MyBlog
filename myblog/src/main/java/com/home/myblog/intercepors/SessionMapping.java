package com.home.myblog.intercepors;

import java.util.Hashtable;
import java.util.Map;

public class SessionMapping {
	
	/**
	 * 存放session.setAttribute(A,B)方法中的两个参数.
	 * B为key，A为value
	 */
	public static Map<Integer, String> idMaps = new Hashtable<Integer, String>();
	
	public static String getSessionAttributeId(Integer key) {
		return idMaps.get(key);
	}
	public static void setSessionAttributeId(Integer key ,String value) {
		idMaps.put(key, value);
	}
}
