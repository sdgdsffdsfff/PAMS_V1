package com.skynet.framework.common.generator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;

public class UUIDGenerator
{
	private static UUIDGenerator me = null;

	private static String hexServerIP = null;

	private static final SecureRandom seeder = new SecureRandom();

	private UUIDGenerator()
	{
	}

	public static UUIDGenerator getInstance()
	{
		if (me == null)
			me = new UUIDGenerator();
		return me;
	}

	public final String getNextValue(String tbName)
	{
		StringBuffer tmpBuffer = new StringBuffer(16);
		if (hexServerIP == null)
		{
			InetAddress localInetAddress = null;
			try
			{
				localInetAddress = InetAddress.getLocalHost();
			}
			catch (UnknownHostException uhe)
			{
				uhe.printStackTrace();
				return null;
			}
			byte serverIP[] = localInetAddress.getAddress();
			hexServerIP = hexFormat(getInt(serverIP), 8);
		}
		String hashcode = hexFormat(System.identityHashCode(tbName), 8);
		tmpBuffer.append(hexServerIP);
		tmpBuffer.append(hashcode);
		long timeNow = System.currentTimeMillis();
		int timeLow = (int) timeNow & 0xffffffff;
		int node = seeder.nextInt();
		StringBuffer guid = new StringBuffer(32);
		guid.append(hexFormat(timeLow, 8));
		guid.append(tmpBuffer.toString());
		guid.append(hexFormat(node, 8));
		return guid.toString();
	}

	public final String getNextValue()
	{
		StringBuffer tmpBuffer = new StringBuffer(16);
		if (hexServerIP == null)
		{
			InetAddress localInetAddress = null;
			try
			{
				localInetAddress = InetAddress.getLocalHost();
			}
			catch (UnknownHostException uhe)
			{
				uhe.printStackTrace();
				return null;
			}
			byte serverIP[] = localInetAddress.getAddress();
			hexServerIP = hexFormat(getInt(serverIP), 8);
		}
		String hashcode = hexFormat(System.identityHashCode(this), 8);
		tmpBuffer.append(hexServerIP);
		tmpBuffer.append(hashcode);
		long timeNow = System.currentTimeMillis();
		int timeLow = (int) timeNow & 0xffffffff;
		int node = seeder.nextInt();
		StringBuffer guid = new StringBuffer(32);
		guid.append(hexFormat(timeLow, 8));
		guid.append(tmpBuffer.toString());
		guid.append(hexFormat(node, 8));
		return guid.toString();
	}

	private static int getInt(byte bytes[])
	{
		int i = 0;
		int j = 24;
		for (int k = 0; j >= 0; k++)
		{
			int l = bytes[k] & 0xff;
			i += l << j;
			j -= 8;
		}

		return i;
	}

	private static String hexFormat(int i, int j)
	{
		String s = Integer.toHexString(i);
		return padHex(s, j) + s;
	}

	private static String padHex(String s, int i)
	{
		StringBuffer tmpBuffer = new StringBuffer();
		if (s.length() < i)
		{
			for (int j = 0; j < i - s.length(); j++)
				tmpBuffer.append('0');

		}
		return tmpBuffer.toString();
	}

	public final String getNextValue16(String tbName)
	{
		long timeNow = System.currentTimeMillis();
		int timeLow = (int) timeNow & 0xffffffff;
		int node = seeder.nextInt();
		StringBuffer guid = new StringBuffer(16);
		guid.append(hexFormat(timeLow, 8));
		guid.append(hexFormat(node, 8));
		return guid.toString();
	}
	

}
