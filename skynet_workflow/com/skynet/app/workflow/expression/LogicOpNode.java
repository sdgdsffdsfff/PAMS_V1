package com.skynet.app.workflow.expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import antlr.Token;

import com.skynet.app.workflow.exception.WfException;
public class LogicOpNode extends OperatorAST
{
	public LogicOpNode(Token tok)
	{
		super(tok);
	}
	public String value() throws WfException
	{
		WfCondAST left = left();
		WfCondAST right = right();
		String value = null;
		Logger logger = LoggerFactory.getLogger(LogicOpNode.class);
		if (logger.isDebugEnabled())
		{
			logger.debug("from value() ttype:" + String.valueOf(getType()) + " token text:" + getText());
			logger.debug("from value() left tree:" + left.toStringTree());
			if (right != null)
			{
				logger.debug("from value() right tree:" + right.toStringTree());
			}
		}
		if (getType() != NOT_OP && left.VType().equals("BOOLEAN") && right.VType().equals("BOOLEAN"))
		{
			switch (getType())
			{
				case AND_OP :
					{
						value = String.valueOf(Boolean.valueOf(left.value()).booleanValue() && Boolean.valueOf(right.value()).booleanValue());
						break;
					}
				case OR_OP :
					{
						value = String.valueOf(Boolean.valueOf(left.value()).booleanValue() || Boolean.valueOf(right.value()).booleanValue());
						break;
					}
			}
		}
		else if (getType() == NOT_OP && left.VType().equals("BOOLEAN"))
		{
			value = String.valueOf(!Boolean.valueOf(left.value()).booleanValue());
		}
		else if (getType() == NOT_OP)
		{
			throw new WfException("类型不匹配:" + getText() + " " + left.VType());
		}
		else
		{
			throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
		}
		if (logger.isDebugEnabled())
		{
			logger.debug("value:" + value);
		}
		return value;
	}
	/**
	 * @return String
	 * @throws WfException
	 * @roseuid 3ED71223018E
	 */
	public String VType() throws WfException
	{
		Logger logger = LoggerFactory.getLogger(LogicOpNode.class);
		WfCondAST left = left();
		WfCondAST right = right();
		String vtype = null;
		if (logger.isDebugEnabled())
		{
			logger.debug("from VType() ttype:" + String.valueOf(getType()) + " token text:" + getText());
			logger.debug("from VType() left tree:" + left.toStringTree());
			if (right != null)
			{
				logger.debug("from VType() right tree:" + right.toStringTree());
			}
		}
		if (getType() != NOT_OP && left.VType().equals("BOOLEAN") && right.VType().equals("BOOLEAN"))
		{
			vtype = "BOOLEAN";
		}
		else if (getType() == NOT_OP && left.VType().equals("BOOLEAN"))
		{
			vtype = "BOOLEAN";
		}
		else if (getType() == NOT_OP)
		{
			throw new WfException("类型不匹配:" + getText() + " " + left.VType());
		}
		else
		{
			throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
		}
		if (logger.isDebugEnabled())
		{
			logger.debug("vtype:" + vtype);
		}
		return vtype;
	}
}
