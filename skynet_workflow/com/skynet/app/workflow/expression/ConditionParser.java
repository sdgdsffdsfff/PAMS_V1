// $ANTLR 2.7.2: "simple.g" -> "P.java"$
package com.skynet.app.workflow.expression;
import java.util.Hashtable;

import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.NoViableAltException;
import antlr.ParserSharedInputState;
import antlr.RecognitionException;
import antlr.TokenBuffer;
import antlr.TokenStream;
import antlr.TokenStreamException;
import antlr.collections.AST;
import antlr.collections.impl.BitSet;

import com.skynet.framework.services.db.dybeans.DynamicObject;
public class ConditionParser extends antlr.LLkParser implements PTokenTypes
{
	DynamicObject swapFlow = new DynamicObject();
	public void setFlowSwap(DynamicObject obj)
	{
		swapFlow = obj;
	}	
	
	protected ConditionParser(TokenBuffer tokenBuf, int k)
	{
		super(tokenBuf, k);
		tokenNames = _tokenNames;
		buildTokenTypeASTClassMap();
		astFactory = new ASTFactory(getTokenTypeToASTClassMap());
	}
	public ConditionParser(TokenBuffer tokenBuf)
	{
		this(tokenBuf, 2);
	}
	protected ConditionParser(TokenStream lexer, int k)
	{
		super(lexer, k);
		tokenNames = _tokenNames;
		buildTokenTypeASTClassMap();
		astFactory = new ASTFactory(getTokenTypeToASTClassMap());
	}
	public ConditionParser(TokenStream lexer)
	{
		this(lexer, 2);
	}
	public ConditionParser(ParserSharedInputState state)
	{
		super(state, 2);
		tokenNames = _tokenNames;
		buildTokenTypeASTClassMap();
		astFactory = new ASTFactory(getTokenTypeToASTClassMap());
	}
	public final void condition() throws RecognitionException, TokenStreamException
	{
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST condition_AST = null;
		try
		{ // for error handling
			logical_term();
			astFactory.addASTChild(currentAST, returnAST);
			{
				_loop3 : do
				{
					if ((LA(1) == OR_OP))
					{
						com.skynet.app.workflow.expression.LogicOpNode tmp1_AST = null;
						tmp1_AST = (com.skynet.app.workflow.expression.LogicOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.LogicOpNode");
						astFactory.makeASTRoot(currentAST, tmp1_AST);
						match(OR_OP);
						logical_term();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else
					{
						break _loop3;
					}
				}
				while (true);
			}
			condition_AST = (AST) currentAST.root;
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_0);
			}
			else
			{
				throw ex;
			}
		}
		returnAST = condition_AST;
	}
	public final void logical_term() throws RecognitionException, TokenStreamException
	{
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST logical_term_AST = null;
		try
		{ // for error handling
			logical_factor();
			astFactory.addASTChild(currentAST, returnAST);
			{
				_loop6 : do
				{
					if ((LA(1) == AND_OP))
					{
						com.skynet.app.workflow.expression.LogicOpNode tmp2_AST = null;
						tmp2_AST = (com.skynet.app.workflow.expression.LogicOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.LogicOpNode");
						astFactory.makeASTRoot(currentAST, tmp2_AST);
						match(AND_OP);
						logical_factor();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else
					{
						break _loop6;
					}
				}
				while (true);
			}
			logical_term_AST = (AST) currentAST.root;
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_1);
			}
			else
			{
				throw ex;
			}
		}
		returnAST = logical_term_AST;
	}
	public final void logical_factor() throws RecognitionException, TokenStreamException
	{
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST logical_factor_AST = null;
		try
		{ // for error handling
			switch (LA(1))
			{
				case NOT_OP :
					{
						com.skynet.app.workflow.expression.LogicOpNode tmp3_AST = null;
						tmp3_AST = (com.skynet.app.workflow.expression.LogicOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.LogicOpNode");
						astFactory.makeASTRoot(currentAST, tmp3_AST);
						match(NOT_OP);
						logical_factor();
						astFactory.addASTChild(currentAST, returnAST);
						logical_factor_AST = (AST) currentAST.root;
						break;
					}
				case BOOL_VAL :
					{
						com.skynet.app.workflow.expression.ConstNode tmp4_AST = null;
						tmp4_AST = (com.skynet.app.workflow.expression.ConstNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.ConstNode");
						astFactory.addASTChild(currentAST, tmp4_AST);
						match(BOOL_VAL);
						logical_factor_AST = (AST) currentAST.root;
						break;
					}
				default :
					boolean synPredMatched9 = false;
					if (((_tokenSet_2.member(LA(1))) && (_tokenSet_3.member(LA(2)))))
					{
						int _m9 = mark();
						synPredMatched9 = true;
						inputState.guessing++;
						try
						{
							{
								exp_simple();
								comparison_op();
							}
						}
						catch (RecognitionException pe)
						{
							synPredMatched9 = false;
						}
						rewind(_m9);
						inputState.guessing--;
					}
					if (synPredMatched9)
					{
						{
							exp_simple();
							astFactory.addASTChild(currentAST, returnAST);
							{
								switch (LA(1))
								{
									case EQUAL :
										{
											com.skynet.app.workflow.expression.CompOpNode tmp5_AST = null;
											tmp5_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
											astFactory.makeASTRoot(currentAST, tmp5_AST);
											match(EQUAL);
											break;
										}
									case LT :
										{
											com.skynet.app.workflow.expression.CompOpNode tmp6_AST = null;
											tmp6_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
											astFactory.makeASTRoot(currentAST, tmp6_AST);
											match(LT);
											break;
										}
									case GT :
										{
											com.skynet.app.workflow.expression.CompOpNode tmp7_AST = null;
											tmp7_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
											astFactory.makeASTRoot(currentAST, tmp7_AST);
											match(GT);
											break;
										}
									case NOT_EQUAL :
										{
											com.skynet.app.workflow.expression.CompOpNode tmp8_AST = null;
											tmp8_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
											astFactory.makeASTRoot(currentAST, tmp8_AST);
											match(NOT_EQUAL);
											break;
										}
									case LE :
										{
											com.skynet.app.workflow.expression.CompOpNode tmp9_AST = null;
											tmp9_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
											astFactory.makeASTRoot(currentAST, tmp9_AST);
											match(LE);
											break;
										}
									case GE :
										{
											com.skynet.app.workflow.expression.CompOpNode tmp10_AST = null;
											tmp10_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
											astFactory.makeASTRoot(currentAST, tmp10_AST);
											match(GE);
											break;
										}
									default :
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
								}
							}
							exp_simple();
							astFactory.addASTChild(currentAST, returnAST);
						}
						logical_factor_AST = (AST) currentAST.root;
					}
					else if ((LA(1) == OPEN_PAREN) && (_tokenSet_4.member(LA(2))))
					{
						match(OPEN_PAREN);
						condition();
						astFactory.addASTChild(currentAST, returnAST);
						match(CLOSE_PAREN);
						logical_factor_AST = (AST) currentAST.root;
					}
					else if ((LA(1) == VARIABLE) && (LA(2) == AND_OP || LA(2) == OR_OP || LA(2) == CLOSE_PAREN))
					{
						com.skynet.app.workflow.expression.VariableNode tmp13_AST = null;
						tmp13_AST = (com.skynet.app.workflow.expression.VariableNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.VariableNode");
						astFactory.addASTChild(currentAST, tmp13_AST);
						match(VARIABLE);
						logical_factor_AST = (AST) currentAST.root;
					}
					else if ((LA(1) == FIELD || LA(1) == JOB) && (LA(2) == AND_OP || LA(2) == OR_OP || LA(2) == CLOSE_PAREN))
					{
						wfexpr();
						astFactory.addASTChild(currentAST, returnAST);
						logical_factor_AST = (AST) currentAST.root;
					}
					else
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_5);
			}
			else
			{
				throw ex;
			}
		}
		returnAST = logical_factor_AST;
	}
	public final void exp_simple() throws RecognitionException, TokenStreamException
	{
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exp_simple_AST = null;
		try
		{ // for error handling
			term();
			astFactory.addASTChild(currentAST, returnAST);
			{
				_loop15 : do
				{
					if ((LA(1) == PLUS || LA(1) == MINUS))
					{
						{
							switch (LA(1))
							{
								case PLUS :
									{
										com.skynet.app.workflow.expression.ArithOpNode tmp14_AST = null;
										tmp14_AST = (com.skynet.app.workflow.expression.ArithOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.ArithOpNode");
										astFactory.makeASTRoot(currentAST, tmp14_AST);
										match(PLUS);
										break;
									}
								case MINUS :
									{
										com.skynet.app.workflow.expression.ArithOpNode tmp15_AST = null;
										tmp15_AST = (com.skynet.app.workflow.expression.ArithOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.ArithOpNode");
										astFactory.makeASTRoot(currentAST, tmp15_AST);
										match(MINUS);
										break;
									}
								default :
									{
										throw new NoViableAltException(LT(1), getFilename());
									}
							}
						}
						term();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else
					{
						break _loop15;
					}
				}
				while (true);
			}
			exp_simple_AST = (AST) currentAST.root;
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_6);
			}
			else
			{
				throw ex;
			}
		}
		returnAST = exp_simple_AST;
	}
	public final void comparison_op() throws RecognitionException, TokenStreamException
	{
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST comparison_op_AST = null;
		try
		{ // for error handling
			switch (LA(1))
			{
				case EQUAL :
					{
						com.skynet.app.workflow.expression.CompOpNode tmp16_AST = null;
						tmp16_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
						astFactory.addASTChild(currentAST, tmp16_AST);
						match(EQUAL);
						comparison_op_AST = (AST) currentAST.root;
						break;
					}
				case LT :
					{
						com.skynet.app.workflow.expression.CompOpNode tmp17_AST = null;
						tmp17_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
						astFactory.addASTChild(currentAST, tmp17_AST);
						match(LT);
						comparison_op_AST = (AST) currentAST.root;
						break;
					}
				case GT :
					{
						com.skynet.app.workflow.expression.CompOpNode tmp18_AST = null;
						tmp18_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
						astFactory.addASTChild(currentAST, tmp18_AST);
						match(GT);
						comparison_op_AST = (AST) currentAST.root;
						break;
					}
				case NOT_EQUAL :
					{
						com.skynet.app.workflow.expression.CompOpNode tmp19_AST = null;
						tmp19_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
						astFactory.addASTChild(currentAST, tmp19_AST);
						match(NOT_EQUAL);
						comparison_op_AST = (AST) currentAST.root;
						break;
					}
				case LE :
					{
						com.skynet.app.workflow.expression.CompOpNode tmp20_AST = null;
						tmp20_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
						astFactory.addASTChild(currentAST, tmp20_AST);
						match(LE);
						comparison_op_AST = (AST) currentAST.root;
						break;
					}
				case GE :
					{
						com.skynet.app.workflow.expression.CompOpNode tmp21_AST = null;
						tmp21_AST = (com.skynet.app.workflow.expression.CompOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.CompOpNode");
						astFactory.addASTChild(currentAST, tmp21_AST);
						match(GE);
						comparison_op_AST = (AST) currentAST.root;
						break;
					}
				default :
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_7);
			}
			else
			{
				throw ex;
			}
		}
		returnAST = comparison_op_AST;
	}
	public final void wfexpr() throws RecognitionException, TokenStreamException
	{
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST wfexpr_AST = null;
		try
		{ // for error handling
			switch (LA(1))
			{
				case FIELD :
					{
						com.skynet.app.workflow.expression.FieldNode tmp22_AST = null;
						tmp22_AST = (com.skynet.app.workflow.expression.FieldNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.FieldNode");
						tmp22_AST.setFlowSwap(swapFlow);
						astFactory.addASTChild(currentAST, tmp22_AST);
						match(FIELD);
						wfexpr_AST = (AST) currentAST.root;
						break;
					}
				case JOB :
					{
						com.skynet.app.workflow.expression.JobNode tmp23_AST = null;
						tmp23_AST = (com.skynet.app.workflow.expression.JobNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.JobNode");
						tmp23_AST.setFlowSwap(swapFlow);
						astFactory.addASTChild(currentAST, tmp23_AST);
						match(JOB);
						wfexpr_AST = (AST) currentAST.root;
						break;
					}
				default :
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_8);
			}
			else
			{
				throw ex;
			}
		}
		returnAST = wfexpr_AST;
	}
	public final void term() throws RecognitionException, TokenStreamException
	{
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST term_AST = null;
		try
		{ // for error handling
			factor();
			astFactory.addASTChild(currentAST, returnAST);
			{
				_loop19 : do
				{
					if ((LA(1) == STAR || LA(1) == DIV))
					{
						{
							switch (LA(1))
							{
								case STAR :
									{
										com.skynet.app.workflow.expression.ArithOpNode tmp24_AST = null;
										tmp24_AST = (com.skynet.app.workflow.expression.ArithOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.ArithOpNode");
										astFactory.makeASTRoot(currentAST, tmp24_AST);
										match(STAR);
										break;
									}
								case DIV :
									{
										com.skynet.app.workflow.expression.ArithOpNode tmp25_AST = null;
										tmp25_AST = (com.skynet.app.workflow.expression.ArithOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.ArithOpNode");
										astFactory.makeASTRoot(currentAST, tmp25_AST);
										match(DIV);
										break;
									}
								default :
									{
										throw new NoViableAltException(LT(1), getFilename());
									}
							}
						}
						factor();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else
					{
						break _loop19;
					}
				}
				while (true);
			}
			term_AST = (AST) currentAST.root;
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_9);
			}
			else
			{
				throw ex;
			}
		}
		returnAST = term_AST;
	}
	public final void factor() throws RecognitionException, TokenStreamException
	{
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST factor_AST = null;
		try
		{ // for error handling
			switch (LA(1))
			{
				case OPEN_PAREN :
					{
						match(OPEN_PAREN);
						exp_simple();
						astFactory.addASTChild(currentAST, returnAST);
						match(CLOSE_PAREN);
						factor_AST = (AST) currentAST.root;
						break;
					}
				case MINUS :
					{
						com.skynet.app.workflow.expression.ArithOpNode tmp28_AST = null;
						tmp28_AST = (com.skynet.app.workflow.expression.ArithOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.ArithOpNode");
						astFactory.makeASTRoot(currentAST, tmp28_AST);
						match(MINUS);
						if (inputState.guessing == 0)
						{
							tmp28_AST.setType(UNARY_MINUS);
						}
						factor();
						astFactory.addASTChild(currentAST, returnAST);
						factor_AST = (AST) currentAST.root;
						break;
					}
				case PLUS :
					{
						com.skynet.app.workflow.expression.ArithOpNode tmp29_AST = null;
						tmp29_AST = (com.skynet.app.workflow.expression.ArithOpNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.ArithOpNode");
						astFactory.makeASTRoot(currentAST, tmp29_AST);
						match(PLUS);
						if (inputState.guessing == 0)
						{
							tmp29_AST.setType(UNARY_MINUS);
						}
						factor();
						astFactory.addASTChild(currentAST, returnAST);
						factor_AST = (AST) currentAST.root;
						break;
					}
				case NUMBER :
					{
						com.skynet.app.workflow.expression.ConstNode tmp30_AST = null;
						tmp30_AST = (com.skynet.app.workflow.expression.ConstNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.ConstNode");
						astFactory.addASTChild(currentAST, tmp30_AST);
						match(NUMBER);
						factor_AST = (AST) currentAST.root;
						break;
					}
				case STRING :
					{
						com.skynet.app.workflow.expression.ConstNode tmp31_AST = null;
						tmp31_AST = (com.skynet.app.workflow.expression.ConstNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.ConstNode");
						astFactory.addASTChild(currentAST, tmp31_AST);
						match(STRING);
						factor_AST = (AST) currentAST.root;
						break;
					}
				case VARIABLE :
					{
						com.skynet.app.workflow.expression.VariableNode tmp32_AST = null;
						tmp32_AST = (com.skynet.app.workflow.expression.VariableNode) astFactory.create(LT(1), "com.skynet.app.workflow.expression.VariableNode");
						astFactory.addASTChild(currentAST, tmp32_AST);
						match(VARIABLE);
						factor_AST = (AST) currentAST.root;
						break;
					}
				case FIELD :
				case JOB :
					{
						wfexpr();
						astFactory.addASTChild(currentAST, returnAST);
						factor_AST = (AST) currentAST.root;
						break;
					}
				default :
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_8);
			}
			else
			{
				throw ex;
			}
		}
		returnAST = factor_AST;
	}
	public static final String[] _tokenNames =
		{
			"<0>",
			"EOF",
			"<2>",
			"NULL_TREE_LOOKAHEAD",
			"UNARY_PLUS",
			"UNARY_MINUS",
			"PLUS",
			"MINUS",
			"STAR",
			"DIV",
			"AND_OP",
			"OR_OP",
			"NOT_OP",
			"EQUAL",
			"NOT_EQUAL",
			"LT",
			"GT",
			"LE",
			"GE",
			"VARIABLE",
			"BOOL_VAL",
			"STRING",
			"NUMBER",
			"FIELD",
			"JOB",
			"OPEN_PAREN",
			"CLOSE_PAREN",
			"LETTER",
			"DIGIT",
			"COLON",
			"DOT",
			"SEMI",
			"WS",
			"N" };
	protected void buildTokenTypeASTClassMap()
	{
		tokenTypeToASTClassMap = new Hashtable();
		tokenTypeToASTClassMap.put(new Integer(UNARY_PLUS), com.skynet.app.workflow.expression.ArithOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(UNARY_MINUS), com.skynet.app.workflow.expression.ArithOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(PLUS), com.skynet.app.workflow.expression.ArithOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(MINUS), com.skynet.app.workflow.expression.ArithOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(STAR), com.skynet.app.workflow.expression.ArithOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(DIV), com.skynet.app.workflow.expression.ArithOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(AND_OP), com.skynet.app.workflow.expression.LogicOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(OR_OP), com.skynet.app.workflow.expression.LogicOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(NOT_OP), com.skynet.app.workflow.expression.LogicOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(EQUAL), com.skynet.app.workflow.expression.CompOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(NOT_EQUAL), com.skynet.app.workflow.expression.CompOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(LT), com.skynet.app.workflow.expression.CompOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(GT), com.skynet.app.workflow.expression.CompOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(LE), com.skynet.app.workflow.expression.CompOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(GE), com.skynet.app.workflow.expression.CompOpNode.class);
		tokenTypeToASTClassMap.put(new Integer(VARIABLE), com.skynet.app.workflow.expression.VariableNode.class);
		tokenTypeToASTClassMap.put(new Integer(BOOL_VAL), com.skynet.app.workflow.expression.ConstNode.class);
		tokenTypeToASTClassMap.put(new Integer(STRING), com.skynet.app.workflow.expression.ConstNode.class);
		tokenTypeToASTClassMap.put(new Integer(NUMBER), com.skynet.app.workflow.expression.ConstNode.class);
		tokenTypeToASTClassMap.put(new Integer(FIELD), com.skynet.app.workflow.expression.FieldNode.class);
		tokenTypeToASTClassMap.put(new Integer(JOB), com.skynet.app.workflow.expression.JobNode.class);
	};
	private static final long[] mk_tokenSet_0()
	{
		long[] data = { 67108864L, 0L };
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1()
	{
		long[] data = { 67110912L, 0L };
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2()
	{
		long[] data = { 65536192L, 0L };
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3()
	{
		long[] data = { 66053056L, 0L };
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4()
	{
		long[] data = { 66588864L, 0L };
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5()
	{
		long[] data = { 67111936L, 0L };
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6()
	{
		long[] data = { 67628032L, 0L };
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7()
	{
		long[] data = { 2L, 0L };
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8()
	{
		long[] data = { 67628992L, 0L };
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9()
	{
		long[] data = { 67628224L, 0L };
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
}
