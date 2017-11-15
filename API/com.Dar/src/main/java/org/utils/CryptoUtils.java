package org.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class CryptoUtils {
	public static String toSHA256(byte[] convertme) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		byte[] mdbytes =  md.digest(convertme);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
	
	public static String randomHash() {
		String hexAlphabet = "0123456789abcdef";
		StringBuilder out = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < 64; i++) {
			out.append(hexAlphabet.charAt(rand.nextInt(16)));
		}
		return out.toString();
	}
}
