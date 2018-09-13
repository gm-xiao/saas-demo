package com.michael.saas.tenant.util;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 
 * 生成32位UUID
 */
@Component
public class GUIDHelper implements Serializable{
	
	private static final long serialVersionUID = 8250546871127709222L;
	private static Random myRand;
	private static SecureRandom mySecureRand;

	private static String s_id;

	static {
		mySecureRand = new SecureRandom();

		long secureInitializer = mySecureRand.nextLong();
		myRand = new Random(secureInitializer);

		try {

			s_id = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public GUIDHelper() {
	}

	/**
	 * 生成uuid
	 * @return
	 */
	public static String genRandomGUID() {
		return genRandomGUID(true);
	}

	private static String genRandomGUID(boolean secure) {
		String valueBeforeMD5 = "";
		String valueAfterMD5 = "";

		MessageDigest md5 = null;
		StringBuffer sbValueBeforeMD5 = new StringBuffer();

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: " + e);
			return valueBeforeMD5;
		}

		long time = System.currentTimeMillis();
		long rand = 0;
		if (secure) {
			rand = mySecureRand.nextLong();
		} else {
			rand = myRand.nextLong();
		}
		sbValueBeforeMD5.append(s_id);
		sbValueBeforeMD5.append(":");
		sbValueBeforeMD5.append(Long.toString(time));
		sbValueBeforeMD5.append(":");
		sbValueBeforeMD5.append(Long.toString(rand));
		valueBeforeMD5 = sbValueBeforeMD5.toString();
		md5.update(valueBeforeMD5.getBytes());
		byte[] array = md5.digest();
		String strTemp = "";
		for (int i = 0; i < array.length; i++) {
			strTemp = (Integer.toHexString(array[i] & 0XFF));
			if (strTemp.length() == 1) {
				valueAfterMD5 = valueAfterMD5 + "0" + strTemp;
			} else {
				valueAfterMD5 = valueAfterMD5 + strTemp;
			}
		}

		return valueAfterMD5;
	}

}
