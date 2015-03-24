package com.skynet.app.workflow.expression;
import java.util.HashMap;
import java.util.Map;
public class VariableTable
{
	private Map typeMap = new HashMap();
	private Map valueMap = new HashMap();
	private static VariableTable instance = null;
	private VariableTable()
	{
	}
	public void putType(String varName, String type)
	{
		typeMap.put(varName, type);
	}
	public void putValue(String varName, String varValue)
	{
		valueMap.put(varName, varValue);
	}
	public String getType(String varName)
	{
		return (String) typeMap.get(varName);
	}
	public String getValue(String varName)
	{
		return (String) valueMap.get(varName);
	}
	public static VariableTable getInstance()
	{
		if (instance == null)
		{
			instance = new VariableTable();
		}
		return instance;
	};
	public void init()
	{
		typeMap.clear();
		valueMap.clear();
	}
	public boolean containsType(String varName)
	{
		return typeMap.containsKey(varName);
	}
	public boolean containsValue(String varName)
	{
		return valueMap.containsKey(varName);
	}
}
