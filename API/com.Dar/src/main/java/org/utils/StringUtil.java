package org.utils;

public class StringUtil {
	
	/** 
	 * Returns the i-th last element of an url, with useless '/' removed
	 * @param url
	 * @param i
	 * @return url[-i] (with pythonic syntax)
	 */
	public static String getLastParam(String url, int i) {
		String[] urlToks = url.split("/");
		int shift = urlToks[urlToks.length - 1].equals("") ? 2 : 1;
		String lp = urlToks[urlToks.length - shift - i];
		return lp;
	}
}
