package com.skynet.framework.services.db;

import java.sql.Statement;

public class DBSessionUtil
{
		public static final ThreadLocal session = new ThreadLocal();
		public static void setSession(Statement stmt) throws Exception
		{
			// System.out.println("stmt:" + stmt.hashCode());			
			session.set(stmt);
		}
		public static Statement getSession() throws Exception
		{
			Statement stmt = (Statement)session.get();
			// System.out.println("stmt:" + stmt.hashCode());			
			return stmt;
		}
		public static void removeSession() throws Exception
		{
			session.set(null);
		}
		
}
