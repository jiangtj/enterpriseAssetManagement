package com.jtj.web.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5String {

	/** 
	 * MD5 加密 
	 */  
	public static String getMD5Str(String str) {

		MessageDigest messageDigest = null;  

		try {  
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));  
		} catch (NoSuchAlgorithmException e) {  
			System.out.println("NoSuchAlgorithmException caught!");  
			System.exit(-1);  
		} catch (UnsupportedEncodingException e) {  
			e.printStackTrace();  
		}  

		byte[] byteArray = messageDigest.digest();  

		StringBuilder md5StrBuff = new StringBuilder();

		for (byte aByteArray : byteArray) {
			if (Integer.toHexString(0xFF & aByteArray).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & aByteArray));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
		}  

		return md5StrBuff.toString();  
	} 
}
