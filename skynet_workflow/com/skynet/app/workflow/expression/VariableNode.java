package com.skynet.app.workflow.expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import antlr.Token;

import com.skynet.app.workflow.exception.WfException;
public class VariableNode extends WfCondAST
{
	private String value = null;
	private String vType = null;
	public VariableNode(Token tok)
	{
		super(tok);
		String varName = tok.getText().substring(1);
		VariableTable vt = VariableTable.getInstance();
		if (vt.containsType(varName))
			vType = vt.getType(varName);
		if (vt.containsValue(varName))
		{
			value = vt.getValue(varName);
			if (vType.equals("NUMBER") && value.indexOf('.') < 0)
			{
				value = value + ".0";
			}
		}
	}
	public String value() throws WfException
	{
		Logger logger = LoggerFactory.getLogger(CompOpNode.class);
		if (logger.isDebugEnabled())
		{
			logger.debug("variable value:" + value);
		}
		if (value == null)
		{
			throw new WfException("变量不存在！");
		}
		return value;
	}
	public String VType() throws WfException
	{
		Logger logger = LoggerFactory.getLogger(CompOpNode.class);
		if (logger.isDebugEnabled())
		{
			logger.debug("variable type:" + vType);
		}
		if (vType == null)
		{
			throw new WfException("变量不存在！");
		}
		return vType;
	}
}
