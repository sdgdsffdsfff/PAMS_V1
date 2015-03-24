package com.skynet.app.workflow.expression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import antlr.Token;

import com.skynet.app.workflow.exception.WfException;
public class CompOpNode extends OperatorAST
{
	public CompOpNode(Token tok)
	{
		super(tok);
	}
	public String value() throws WfException
	{
		WfCondAST left = left();
		WfCondAST right = right();
		String value = null;
		Logger logger = LoggerFactory.getLogger(CompOpNode.class);
		if (logger.isDebugEnabled())
		{
			logger.debug("from value() ttype:" + String.valueOf(getType()) + " token text:" + getText());
			logger.debug("from value() left tree:" + left.toStringTree());
			if (right != null)
			{
				logger.debug("from value() right tree:" + right.toStringTree());
			}
		}
		if (left.VType().equals(right.VType()))
		{
			if (left.VType().equals("NUMBER"))
			{
				double lv = Double.valueOf(left.value()).doubleValue();
				double rv = Double.valueOf(right.value()).doubleValue();
				value = String.valueOf(false);
				switch (getType())
				{
					case EQUAL :
						{
							// value = String.valueOf(left.value().equals(right.value()));
							if (lv == rv)
							{
								value = String.valueOf(true);
							}
							break;
						}
					case NOT_EQUAL :
						{
							//value = String.valueOf(!left.value().equals(right.value()));
							if (lv != rv)
							{
								value = String.valueOf(true);
							}
							break;
						}
					case GT :
						{
							if (left.VType().equals("BOOLEAN"))
							{
								throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
							}
							else
							{
								//int compResult = left.value().compareTo(right.value());
								//value = String.valueOf(compResult > 0);
								if (lv > rv)
								{
									value = String.valueOf(true);
								}
							}
							break;
						}
					case LT :
						{
							if (left.VType().equals("BOOLEAN"))
							{
								throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
							}
							else
							{
								//int compResult = left.value().compareTo(right.value());
								//value = String.valueOf(compResult < 0);
								if (lv < rv)
								{
									value = String.valueOf(true);
								}
							}
							break;
						}
					case GE :
						{
							if (left.VType().equals("BOOLEAN"))
							{
								throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
							}
							else
							{
								//int compResult = left.value().compareTo(right.value());
								//value = String.valueOf(compResult >= 0);
								if (lv >= rv)
								{
									value = String.valueOf(true);
								}
							}
							break;
						}
					case LE :
						{
							if (left.VType().equals("BOOLEAN"))
							{
								throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
							}
							else
							{
								//int compResult = left.value().compareTo(right.value());
								//value = String.valueOf(compResult <= 0);
								if (lv <= rv)
								{
									value = String.valueOf(true);
								}
							}
							break;
						}
				}
			}
			else
			{
				switch (getType())
				{
					case EQUAL :
						{
							value = String.valueOf(left.value().equals(right.value()));
							break;
						}
					case NOT_EQUAL :
						{
							value = String.valueOf(!left.value().equals(right.value()));
							break;
						}
					case GT :
						{
							if (left.VType().equals("BOOLEAN"))
							{
								throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
							}
							else
							{
								int compResult = left.value().compareTo(right.value());
								value = String.valueOf(compResult > 0);
							}
							break;
						}
					case LT :
						{
							if (left.VType().equals("BOOLEAN"))
							{
								throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
							}
							else
							{
								int compResult = left.value().compareTo(right.value());
								value = String.valueOf(compResult < 0);
							}
							break;
						}
					case GE :
						{
							if (left.VType().equals("BOOLEAN"))
							{
								throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
							}
							else
							{
								int compResult = left.value().compareTo(right.value());
								value = String.valueOf(compResult >= 0);
							}
							break;
						}
					case LE :
						{
							if (left.VType().equals("BOOLEAN"))
							{
								throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
							}
							else
							{
								int compResult = left.value().compareTo(right.value());
								value = String.valueOf(compResult <= 0);
							}
							break;
						}
				}
			}
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
	public String VType() throws WfException
	{
		WfCondAST left = left();
		WfCondAST right = right();
		String vtype = null;
		Logger logger = LoggerFactory.getLogger(CompOpNode.class);
		if (logger.isDebugEnabled())
		{
			logger.debug("from vtype() ttype:" + String.valueOf(getType()) + " token text:" + getText());
			logger.debug("from vtype() left tree:" + left.toStringTree());
			if (right != null)
			{
				logger.debug("from vtype() right tree:" + right.toStringTree());
			}
		}
		if (left.VType().equals(right.VType()))
		{
			switch (getType())
			{
				case EQUAL :
				case NOT_EQUAL :
					{
						vtype = "BOOLEAN";
						break;
					}
				case GT :
				case LT :
				case LE :
				case GE :
					{
						if (left.VType().equals("BOOLEAN"))
						{
							throw new WfException("类型不匹配:" + left.VType() + " " + getText() + " " + right.VType());
						}
						else
						{
							vtype = "BOOLEAN";
						}
						break;
					}
			}
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
