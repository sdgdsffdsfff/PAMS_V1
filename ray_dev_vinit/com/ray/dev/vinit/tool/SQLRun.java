package com.ray.dev.vinit.tool;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class SQLRun
{
	public static void main(String[] args) throws Exception
	{
		DataInputStream in = new DataInputStream(new FileInputStream(new File("c:\\temp.txt")));
		
		StringBuffer sb = new StringBuffer();
		
		String s = null;
		while((s=in.readLine())!=null)
		{
			System.out.println("sql.append(\" " + new String(s.getBytes("ISO-8859-1")) + " \").append(\"\\n\");");
		}
	}

}
