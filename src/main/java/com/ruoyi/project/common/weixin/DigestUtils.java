package com.ruoyi.project.common.weixin;

import java.security.MessageDigest;

public class DigestUtils {
	public static String SHA1(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes("utf-8"));
			byte messageDigest[] = digest.digest();
			StringBuffer hexStr = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexStr.append(0);
				}
				hexStr.append(shaHex);
			}
			return hexStr.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
