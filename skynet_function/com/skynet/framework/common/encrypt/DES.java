package com.skynet.framework.common.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.skynet.framework.services.function.StringToolKit;

public class DES
{

	public DES()
	{
	}

	public static byte[] encode(byte text[], String keytext) throws Exception
	{
		DESKeySpec dks = new DESKeySpec(keytext.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		javax.crypto.SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(1, key);
		return cipher.doFinal(text);
	}

	public static byte[] decode(byte text[], String keytext) throws Exception
	{
		DESKeySpec dks = new DESKeySpec(keytext.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		javax.crypto.SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(2, key);
		return cipher.doFinal(text);
	}

	public static String tostring(byte bytes[])
	{
		String text = new String();
		int length = bytes.length;
		for (int i = 0; i < length; i++)
		{
			text = (new StringBuilder(String.valueOf(text))).append(bytes[i]).toString();
			if (i < bytes.length - 1)
				text = (new StringBuilder(String.valueOf(text))).append("#").toString();
		}

		return text.toString();
	}

	public static byte[] tobytes(String text)
	{
		String stext[] = StringToolKit.split(text, "#");
		int length = stext.length;
		byte bytes[] = new byte[length];
		for (int i = 0; i < length; i++)
			bytes[i] = (byte) Integer.parseInt(stext[i]);

		return bytes;
	}

	public static void main(String args[]) throws Exception
	{
		String key = "head.ray";
		String date = "2010-12-1";
		System.out.println(date);
		byte date_a[] = date.getBytes();
		System.out.println(date_a);
		byte date_b[] = encode(date_a, key);
		System.out.println(date_b);
		String date_c = tostring(date_b);
		System.out.println(date_c);
		byte date_d[] = tobytes(date_c);
		System.out.println(date_d);
		byte date_e[] = decode(date_d, key);
		System.out.println(date_e);
		String date_f = new String(date_e);
		System.out.println(date_f);
	}
}
