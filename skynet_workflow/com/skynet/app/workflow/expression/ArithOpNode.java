package com.skynet.app.workflow.expression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import antlr.Token;

import com.skynet.app.workflow.exception.WfException;
public class ArithOpNode extends OperatorAST
{
	public ArithOpNode(Token tok)
	{
		super(tok);
	}
	public String value() throws WfException
	{
		WfCondAST left = left();
		WfCondAST right = right();
		String value = null;
		Logger logger = LoggerFactory.getLogger(ArithOpNode.class);
		if (logger.isDebugEnabled())
		{
			logger.debug("from value() ttype:" + String.valueOf(getType()) + " token text:" + getText());
			logger.debug("from value() left tree:" + left.toStringTree());
			if (right != null)
			{
				logger.debug("from value() right tree:" + right.toStringTree());
			}
		}
		switch (getType())
		{
			case PLUS :
				{
					if (left.VType().equals("NUMBER") && right.VType().equals("NUMBER"))
					{
						value = String.valueOf(new Double(left.value()).doubleValue() + new Double(right.value()).doubleValue());
					}
					else if (left.VType().equals("STRING") && right.VType().equals("STRING"))
					{
						value = left.value() + right.value();
					}
					else
					{
						throw new WfException("类型不匹配:" + left.VType() + " + " + right.VType());
					}
					break;
				}
			case MINUS :
				{
					if (left.VType().equals("NUMBER") && right.VType().equals("NUMBER"))
					{
						value = String.valueOf(new Double(left.value()).doubleValue() - new Double(right.value()).doubleValue());
					}
					else
					{
						throw new WfException("类型不匹配:" + left.VType() + " - " + right.VType());
					}
					break;
				}
			case STAR :
				{
					if (left.VType().equals("NUMBER") && right.VType().equals("NUMBER"))
					{
						value = String.valueOf(new Double(left.value()).doubleValue() * new Double(right.value()).doubleValue());
					}
					else
					{
						throw new WfException("类型不匹配:" + left.VType() + " * " + right.VType());
					}
					break;
				}
			case DIV :
				{
					if (left.VType().equals("NUMBER") && right.VType().equals("NUMBER"))
					{
						value = String.valueOf(new Double(left.value()).doubleValue() / new Double(right.value()).doubleValue());
					}
					else
					{
						throw new WfException("类型不匹配:" + left.VType() + " / " + right.VType());
					}
					break;
				}
			case UNARY_PLUS :
				{
					if (left.VType().equals("NUMBER"))
					{
						value = left.value();
					}
					else
					{
						throw new WfException("类型不匹配:" + " + " + left.VType());
					}
					break;
				}
			case UNARY_MINUS :
				{
					if (left.VType().equals("NUMBER"))
					{
						value = String.valueOf(-1 * new Double(left.value()).doubleValue());
					}
					else
					{
						throw new WfException("类型不匹配:" + " - " + left.VType());
					}
					break;
				}
		}
		if (logger.isDebugEnabled())
		{
			logger.debug("value:" + value);
		}
		return value;
	}
	public String VType() throws WfException
	{
		WfCondAST left = left();
		WfCondAST right = right();
		String vtype = null;
		Logger logger = LoggerFactory.getLogger(ArithOpNode.class);
		if (logger.isDebugEnabled())
		{
			logger.debug("from vtype() ttype:" + String.valueOf(getType()) + " token text:" + getText());
			logger.debug("from vtype() left tree:" + left.toStringTree());
			if (right != null)
			{
				logger.debug("from vtype() right tree:" + right.toStringTree());
			}
		}
		switch (getType())
		{
			case PLUS :
				{
					if (left.VType().equals("NUMBER") && right.VType().equals("NUMBER"))
					{
						vtype = "NUMBER";
					}
					else if (left.VType().equals("STRING") && right.VType().equals("STRING"))
					{
						vtype = "STRING";
					}
					else
					{
						throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
					}
					break;
				}
			case MINUS :
			case STAR :
			case DIV :
				{
					if (left.VType().equals("NUMBER") && right.VType().equals("NUMBER"))
					{
						vtype = "NUMBER";
					}
					else
					{
						throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
					}
					break;
				}
			case UNARY_PLUS :
			case UNARY_MINUS :
				{
					if (left.VType().equals("NUMBER"))
					{
						vtype = "NUMBER";
					}
					else
					{
						throw new WfException("类型不匹配:" + " " + getText() + " " + left.VType());
					}
					break;
				}
		}
		if (logger.isDebugEnabled())
		{
			logger.debug("vtype:" + vtype);
		}
		return vtype;
	}
}
