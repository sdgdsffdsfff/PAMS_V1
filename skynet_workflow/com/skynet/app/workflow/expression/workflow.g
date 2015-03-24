header
{
	package com.butone.framework.workflow.expression;
}

class P extends Parser;
options
{
    buildAST = true;
    k=2;
}

tokens
{
  UNARY_PLUS<AST=com.butone.framework.workflow.expression.ArithOpNode>;
  UNARY_MINUS<AST=com.butone.framework.workflow.expression.ArithOpNode>;
  
  PLUS<AST=com.butone.framework.workflow.expression.ArithOpNode>;
  MINUS<AST=com.butone.framework.workflow.expression.ArithOpNode>;
  STAR<AST=com.butone.framework.workflow.expression.ArithOpNode>;
  DIV<AST=com.butone.framework.workflow.expression.ArithOpNode>;
  
  AND_OP<AST=com.butone.framework.workflow.expression.LogicOpNode>;
  OR_OP<AST=com.butone.framework.workflow.expression.LogicOpNode>;
  NOT_OP<AST=com.butone.framework.workflow.expression.LogicOpNode>;
  
  EQUAL<AST=com.butone.framework.workflow.expression.CompOpNode>;
  NOT_EQUAL<AST=com.butone.framework.workflow.expression.CompOpNode>;
  LT<AST=com.butone.framework.workflow.expression.CompOpNode>;
  GT<AST=com.butone.framework.workflow.expression.CompOpNode>;
  LE<AST=com.butone.framework.workflow.expression.CompOpNode>;
  GE<AST=com.butone.framework.workflow.expression.CompOpNode>;
  
  VARIABLE<AST=com.butone.framework.workflow.expression.VariableNode>;
  BOOL_VAL<AST=com.butone.framework.workflow.expression.ConstNode>;
  STRING<AST=com.butone.framework.workflow.expression.ConstNode>;
  NUMBER<AST=com.butone.framework.workflow.expression.ConstNode>;
  
  FIELD<AST=com.butone.framework.workflow.expression.FieldNode>;
  JOB<AST=com.butone.framework.workflow.expression.JobNode>;
}

condition : logical_term ( OR_OP^ logical_term )* ;

logical_term : logical_factor ( AND_OP^ logical_factor )*;

logical_factor  : 
      (exp_simple comparison_op) =>
               (exp_simple (EQUAL^|LT^|GT^|NOT_EQUAL^|LE^|GE^) exp_simple)
      | NOT_OP^ logical_factor
      | BOOL_VAL
      | OPEN_PAREN! condition CLOSE_PAREN!
      | VARIABLE
      | wfexpr
    ;

exp_simple : term ((PLUS^|MINUS^) term)* ;
     
term : factor (( STAR^|DIV^) factor)* ;
	
factor : OPEN_PAREN! exp_simple CLOSE_PAREN!
	|MINUS^ {#MINUS.setType(UNARY_MINUS);} factor
	|PLUS^ {#PLUS.setType(UNARY_MINUS);} factor
	|NUMBER
	|STRING
	|VARIABLE
	|wfexpr
	;

comparison_op : EQUAL | LT | GT | NOT_EQUAL | LE | GE ;

wfexpr: FIELD | JOB ;
    
class L extends Lexer;
options
{
	k = 2;
    charVocabulary = '\3' .. '\127';
}

protected
LETTER : ('a'..'z' | 'A'..'Z' ) ;

protected
DIGIT :	'0'..'9' ;

protected
COLON: ':' ;

protected
DOT : '.' ;

protected
SEMI : ';' ;

OPEN_PAREN : '('  ;

CLOSE_PAREN : ')' ;

PLUS : '+' ;

MINUS : '-' ;

DIV : '/' ;

STAR : '*' ;

AND_OP : "and" ;

OR_OP : "or" ;

BOOL_VAL : ("true"|"false") ;

EQUAL : '=' ;

NOT_EQUAL : "<>" ;

LT : '<' ;

GT : '>' ;

LE : "<=" ;

GE : ">=" ;

NOT_OP : "not" ;

VARIABLE : COLON ( LETTER | DIGIT | '_' )+ ;

WS	:	(' '
		|'\n'
		|'\t'
		|'\r')+	
			{ $setType(Token.SKIP); };

STRING : '"' ( ~'"' )* '"' ;

protected
N : ( DIGIT )+ ;

NUMBER :( N DOT )=>N DOT N 
       | N {$append(".0");} ;
    
FIELD: "@field" '(' (LETTER)+ ')' '[' ("STRING"|"NUMBER")+ ']' ;

JOB: "@job" '(' (LETTER)+ ')' '[' ("STRING"|"NUMBER")+ ']' ;