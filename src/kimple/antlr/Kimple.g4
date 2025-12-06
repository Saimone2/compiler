grammar Kimple;

program
    : topLevel* EOF
    ;

topLevel
    : varDecl
    | valDecl
    | funDecl
    | statement
    ;

statement
    : varDecl
    | valDecl
    | funDecl
    | exprStatement
    | printStatement
    | readStatement
    | ifStatement
    | forStatement
    | whileStatement
    | returnStatement
    | assignStatement
    | block
    ;

varDecl : VAR IDENT COLON type (ASSIGN expr)? ;
valDecl : VAL IDENT COLON type ASSIGN expr ;

funDecl : FUN IDENT LPAREN paramList? RPAREN COLON type block ;
paramList : param (COMMA param)* ;
param : IDENT COLON type ;

printStatement : PRINT LPAREN exprList RPAREN ;
readStatement  : READ  LPAREN IDENT   RPAREN ;

ifStatement : IF LPAREN expr RPAREN block (ELSE block)? ;
forStatement : FOR LPAREN forVarDecl IN expr RPAREN block ;
forVarDecl   : IDENT (COLON type)? ;

whileStatement : WHILE LPAREN expr RPAREN block ;
returnStatement: RETURN expr? ;

assignStatement: IDENT ASSIGN expr ;
exprStatement  : expr ;

block : LBRACE statement* RBRACE ;

exprList : expr (COMMA expr)* ;

expr          : logicExpr ;
logicExpr     : compareExpr ( (AND | OR) compareExpr )* ;
compareExpr   : rangeExpr ( (LT | LE | GT | GE | EQ | NEQ) rangeExpr )* ;
rangeExpr     : addSubExpr (RANGE addSubExpr)? ;
addSubExpr    : mulDivExpr ( (PLUS | MINUS) mulDivExpr )* ;
mulDivExpr    : powExpr    ( (MUL | DIV | MOD) powExpr )* ;
powExpr       : unaryExpr (POW powExpr)? ;
unaryExpr     : MINUS unaryExpr
              | NOT unaryExpr
              | castExpr
              ;
castExpr      : primaryExpr (AS type)? ;
primaryExpr   : LPAREN expr RPAREN
              | funcCall
              | literal
              | IDENT
              ;

funcCall      : IDENT LPAREN exprList? RPAREN ;

literal       : INT
              | REAL
              | STRING
              | TRUE
              | FALSE
              | INF
              ;

type : INT_TYPE | DOUBLE_TYPE | BOOLEAN_TYPE | STRING_TYPE ;


// ====================
// Lexer Rules
// ====================

// Keywords
VAR : 'var';
VAL : 'val';
IF : 'if';
ELSE : 'else';
FOR : 'for';
WHILE : 'while';
FUN : 'fun';
RETURN : 'return';
PRINT : 'print';
READ : 'read';
INT_TYPE : 'Int';
DOUBLE_TYPE : 'Double';
BOOLEAN_TYPE : 'Boolean';
STRING_TYPE : 'String';
AS : 'as';
TRUE : 'true';
FALSE : 'false';
INF : 'inf';
IN : 'in';

// Operators
RANGE : '..';
PLUS  : '+';
MINUS : '-';
MUL   : '*';
DIV   : '/';
MOD   : '%';
POW   : '^';
NOT   : '!';
AND   : '&&';
OR    : '||';
LT    : '<';
GT    : '>';
LE    : '<=';
GE    : '>=';
EQ    : '==';
NEQ   : '!=';
ASSIGN: '=';

// Delimiters
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
SEMI  : ';';
COMMA : ',';
COLON : ':';

// Identifiers
IDENT : [a-zA-Z_] [a-zA-Z_0-9]* ;

// Literals
INT  : [0-9]+ ;
REAL
  : [0-9]+ '.' [0-9]+ ([eE] [+-]? [0-9]+)?
  | '.' [0-9]+ ([eE] [+-]? [0-9]+)?
  | [0-9]+ [eE] [+-]? [0-9]+
  ;

STRING : '"' ( ~["\\\r\n] | '\\' . )* '"' ;

WS      : [ \t\r\n]+ -> skip ;
COMMENT : '//' ~[\r\n]* -> skip ;