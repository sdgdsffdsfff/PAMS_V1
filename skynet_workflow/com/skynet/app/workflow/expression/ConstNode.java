package com.skynet.app.workflow.expression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import antlr.Token;

import com.skynet.app.workflow.exception.WfException;
public class ConstNode extends WfCondAST
{
	private String value;
	private String vType;
	public ConstNode(Token tok)
	{
		super(tok);
		value = tok.getText();
		switch (tok.getType())
		{
			case BOOL_VAL :
				{
					vType = "BOOLEAN";
					value = value.toLowerCase();
					break;
				}
			case STRING :
				{
					vType = "STRING";
					// value = value.substring(1, value.length() - 2);
					value = value.substring(1, value.length() - 1);
					break;
				}
			case NUMBER :
				{
					vType = "NUMBER";
				}
		}
	}
	public String value() throws WfException
	{
		Logger logger = LoggerFactory.getLogger(ConstNode.class);
		if (logger.isDebugEnabled())
		{
			logger.debug("value:" + value);
		}
		return value;
	}
	public String VType() throws WfException
	{
		Logger logger = LoggerFactory.getLogger(ConstNode.class);
		if (logger.isDebugEnabled())
		{
			logger.debug("vtype:" + vType);
		}
		return vType;
	}
}
