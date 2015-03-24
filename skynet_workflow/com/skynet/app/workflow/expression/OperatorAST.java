package com.skynet.app.workflow.expression;
import antlr.Token;
public abstract class OperatorAST extends WfCondAST
{
	public OperatorAST(Token tok)
	{
		super(tok);
	}
	public WfCondAST left()
	{
		return (WfCondAST) getFirstChild();
	}
	public WfCondAST right()
	{
		WfCondAST left = left();
		if (left == null)
			return null;
		return (WfCondAST) left.getNextSibling();
	}
}
