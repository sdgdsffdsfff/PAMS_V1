package com.skynet.framework.common.encrypt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Util
{

	static
	{
		new Util();
	}

	public Util()
	{
	}

	/**
	 * 读取源文件内容
	 * 
	 * @param filename String 文件路径
	 * @throws IOException
	 * @return byte[] 文件内容
	 */
	public static byte[] readFile(String filename) throws IOException
	{

		File file = new File(filename);
		if (filename == null || filename.equals(""))
		{
			throw new NullPointerException("无效的文件路径");
		}
		long len = file.length();
		byte[] bytes = new byte[(int) len];

		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
		int r = bufferedInputStream.read(bytes);
		if (r != len)
			throw new IOException("读取文件不正确");
		bufferedInputStream.close();

		return bytes;

	}

	/**
	 * 将加密的数据写入文件
	 * 
	 * @param data byte[]
	 * @throws IOException
	 */
	public static void writeFile(byte[] data, String filename) throws IOException
	{
		File file = new File(filename);
		file.getParentFile().mkdirs();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
		bufferedOutputStream.write(data);
		bufferedOutputStream.close();
	}

	/**
	 * 从jar文件里读取class
	 * 
	 * @param filename
	 * @throws IOException
	 * @return byte[]
	 */
	public byte[] readFileJar(String filename) throws IOException
	{
		BufferedInputStream bufferedInputStream = new BufferedInputStream(getClass().getResource(filename).openStream());
		int len = bufferedInputStream.available();
		byte[] bytes = new byte[len];
		int r = bufferedInputStream.read(bytes);
		if (len != r)
		{
			bytes = null;
			throw new IOException("读取文件不正确");
		}
		bufferedInputStream.close();
		return bytes;
	}
}
