// Generated from src/kimple/antlr/Kimple.g4 by ANTLR 4.13.1
package kimple.gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class KimpleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, VAL=2, IF=3, ELSE=4, FOR=5, WHILE=6, FUN=7, RETURN=8, PRINT=9, 
		READ=10, INT_TYPE=11, DOUBLE_TYPE=12, BOOLEAN_TYPE=13, STRING_TYPE=14, 
		AS=15, TRUE=16, FALSE=17, INF=18, IN=19, RANGE=20, PLUS=21, MINUS=22, 
		MUL=23, DIV=24, MOD=25, POW=26, NOT=27, AND=28, OR=29, LT=30, GT=31, LE=32, 
		GE=33, EQ=34, NEQ=35, ASSIGN=36, LPAREN=37, RPAREN=38, LBRACE=39, RBRACE=40, 
		SEMI=41, COMMA=42, COLON=43, IDENT=44, INT=45, REAL=46, STRING=47, WS=48, 
		COMMENT=49;
	public static final int
		RULE_program = 0, RULE_topLevel = 1, RULE_statement = 2, RULE_varDecl = 3, 
		RULE_valDecl = 4, RULE_funDecl = 5, RULE_paramList = 6, RULE_param = 7, 
		RULE_printStatement = 8, RULE_readStatement = 9, RULE_ifStatement = 10, 
		RULE_forStatement = 11, RULE_forVarDecl = 12, RULE_whileStatement = 13, 
		RULE_returnStatement = 14, RULE_assignStatement = 15, RULE_exprStatement = 16, 
		RULE_block = 17, RULE_exprList = 18, RULE_expr = 19, RULE_logicExpr = 20, 
		RULE_compareExpr = 21, RULE_rangeExpr = 22, RULE_addSubExpr = 23, RULE_mulDivExpr = 24, 
		RULE_powExpr = 25, RULE_unaryExpr = 26, RULE_castExpr = 27, RULE_primaryExpr = 28, 
		RULE_funcCall = 29, RULE_literal = 30, RULE_type = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "topLevel", "statement", "varDecl", "valDecl", "funDecl", 
			"paramList", "param", "printStatement", "readStatement", "ifStatement", 
			"forStatement", "forVarDecl", "whileStatement", "returnStatement", "assignStatement", 
			"exprStatement", "block", "exprList", "expr", "logicExpr", "compareExpr", 
			"rangeExpr", "addSubExpr", "mulDivExpr", "powExpr", "unaryExpr", "castExpr", 
			"primaryExpr", "funcCall", "literal", "type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'var'", "'val'", "'if'", "'else'", "'for'", "'while'", "'fun'", 
			"'return'", "'print'", "'read'", "'Int'", "'Double'", "'Boolean'", "'String'", 
			"'as'", "'true'", "'false'", "'inf'", "'in'", "'..'", "'+'", "'-'", "'*'", 
			"'/'", "'%'", "'^'", "'!'", "'&&'", "'||'", "'<'", "'>'", "'<='", "'>='", 
			"'=='", "'!='", "'='", "'('", "')'", "'{'", "'}'", "';'", "','", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "VAR", "VAL", "IF", "ELSE", "FOR", "WHILE", "FUN", "RETURN", "PRINT", 
			"READ", "INT_TYPE", "DOUBLE_TYPE", "BOOLEAN_TYPE", "STRING_TYPE", "AS", 
			"TRUE", "FALSE", "INF", "IN", "RANGE", "PLUS", "MINUS", "MUL", "DIV", 
			"MOD", "POW", "NOT", "AND", "OR", "LT", "GT", "LE", "GE", "EQ", "NEQ", 
			"ASSIGN", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA", "COLON", 
			"IDENT", "INT", "REAL", "STRING", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Kimple.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public KimpleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(KimpleParser.EOF, 0); }
		public List<TopLevelContext> topLevel() {
			return getRuleContexts(TopLevelContext.class);
		}
		public TopLevelContext topLevel(int i) {
			return getRuleContext(TopLevelContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 264570124306414L) != 0)) {
				{
				{
				setState(64);
				topLevel();
				}
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(70);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TopLevelContext extends ParserRuleContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public ValDeclContext valDecl() {
			return getRuleContext(ValDeclContext.class,0);
		}
		public FunDeclContext funDecl() {
			return getRuleContext(FunDeclContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TopLevelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topLevel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterTopLevel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitTopLevel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitTopLevel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopLevelContext topLevel() throws RecognitionException {
		TopLevelContext _localctx = new TopLevelContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_topLevel);
		try {
			setState(76);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				varDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				valDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				funDecl();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(75);
				statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public ValDeclContext valDecl() {
			return getRuleContext(ValDeclContext.class,0);
		}
		public FunDeclContext funDecl() {
			return getRuleContext(FunDeclContext.class,0);
		}
		public ExprStatementContext exprStatement() {
			return getRuleContext(ExprStatementContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public ReadStatementContext readStatement() {
			return getRuleContext(ReadStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public AssignStatementContext assignStatement() {
			return getRuleContext(AssignStatementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				varDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				valDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(80);
				funDecl();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(81);
				exprStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(82);
				printStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(83);
				readStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(84);
				ifStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(85);
				forStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(86);
				whileStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(87);
				returnStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(88);
				assignStatement();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(89);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(KimpleParser.VAR, 0); }
		public TerminalNode IDENT() { return getToken(KimpleParser.IDENT, 0); }
		public TerminalNode COLON() { return getToken(KimpleParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(KimpleParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(VAR);
			setState(93);
			match(IDENT);
			setState(94);
			match(COLON);
			setState(95);
			type();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(96);
				match(ASSIGN);
				setState(97);
				expr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValDeclContext extends ParserRuleContext {
		public TerminalNode VAL() { return getToken(KimpleParser.VAL, 0); }
		public TerminalNode IDENT() { return getToken(KimpleParser.IDENT, 0); }
		public TerminalNode COLON() { return getToken(KimpleParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(KimpleParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ValDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterValDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitValDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitValDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValDeclContext valDecl() throws RecognitionException {
		ValDeclContext _localctx = new ValDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_valDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(VAL);
			setState(101);
			match(IDENT);
			setState(102);
			match(COLON);
			setState(103);
			type();
			setState(104);
			match(ASSIGN);
			setState(105);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunDeclContext extends ParserRuleContext {
		public TerminalNode FUN() { return getToken(KimpleParser.FUN, 0); }
		public TerminalNode IDENT() { return getToken(KimpleParser.IDENT, 0); }
		public TerminalNode LPAREN() { return getToken(KimpleParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(KimpleParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(KimpleParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FunDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterFunDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitFunDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitFunDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunDeclContext funDecl() throws RecognitionException {
		FunDeclContext _localctx = new FunDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_funDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(FUN);
			setState(108);
			match(IDENT);
			setState(109);
			match(LPAREN);
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENT) {
				{
				setState(110);
				paramList();
				}
			}

			setState(113);
			match(RPAREN);
			setState(114);
			match(COLON);
			setState(115);
			type();
			setState(116);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KimpleParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KimpleParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			param();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(119);
				match(COMMA);
				setState(120);
				param();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(KimpleParser.IDENT, 0); }
		public TerminalNode COLON() { return getToken(KimpleParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(IDENT);
			setState(127);
			match(COLON);
			setState(128);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrintStatementContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(KimpleParser.PRINT, 0); }
		public TerminalNode LPAREN() { return getToken(KimpleParser.LPAREN, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(KimpleParser.RPAREN, 0); }
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterPrintStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitPrintStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitPrintStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_printStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(PRINT);
			setState(131);
			match(LPAREN);
			setState(132);
			exprList();
			setState(133);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReadStatementContext extends ParserRuleContext {
		public TerminalNode READ() { return getToken(KimpleParser.READ, 0); }
		public TerminalNode LPAREN() { return getToken(KimpleParser.LPAREN, 0); }
		public TerminalNode IDENT() { return getToken(KimpleParser.IDENT, 0); }
		public TerminalNode RPAREN() { return getToken(KimpleParser.RPAREN, 0); }
		public ReadStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterReadStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitReadStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitReadStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReadStatementContext readStatement() throws RecognitionException {
		ReadStatementContext _localctx = new ReadStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_readStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(READ);
			setState(136);
			match(LPAREN);
			setState(137);
			match(IDENT);
			setState(138);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(KimpleParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(KimpleParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(KimpleParser.RPAREN, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(KimpleParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(IF);
			setState(141);
			match(LPAREN);
			setState(142);
			expr();
			setState(143);
			match(RPAREN);
			setState(144);
			block();
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(145);
				match(ELSE);
				setState(146);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(KimpleParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(KimpleParser.LPAREN, 0); }
		public ForVarDeclContext forVarDecl() {
			return getRuleContext(ForVarDeclContext.class,0);
		}
		public TerminalNode IN() { return getToken(KimpleParser.IN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(KimpleParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(FOR);
			setState(150);
			match(LPAREN);
			setState(151);
			forVarDecl();
			setState(152);
			match(IN);
			setState(153);
			expr();
			setState(154);
			match(RPAREN);
			setState(155);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForVarDeclContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(KimpleParser.IDENT, 0); }
		public TerminalNode COLON() { return getToken(KimpleParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ForVarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forVarDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterForVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitForVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitForVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForVarDeclContext forVarDecl() throws RecognitionException {
		ForVarDeclContext _localctx = new ForVarDeclContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_forVarDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(IDENT);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(158);
				match(COLON);
				setState(159);
				type();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(KimpleParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(KimpleParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(KimpleParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(WHILE);
			setState(163);
			match(LPAREN);
			setState(164);
			expr();
			setState(165);
			match(RPAREN);
			setState(166);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(KimpleParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(RETURN);
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(169);
				expr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignStatementContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(KimpleParser.IDENT, 0); }
		public TerminalNode ASSIGN() { return getToken(KimpleParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterAssignStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitAssignStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitAssignStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignStatementContext assignStatement() throws RecognitionException {
		AssignStatementContext _localctx = new AssignStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_assignStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(IDENT);
			setState(173);
			match(ASSIGN);
			setState(174);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprStatementContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterExprStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitExprStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitExprStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprStatementContext exprStatement() throws RecognitionException {
		ExprStatementContext _localctx = new ExprStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_exprStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(KimpleParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(KimpleParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(LBRACE);
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 264570124306414L) != 0)) {
				{
				{
				setState(179);
				statement();
				}
				}
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(185);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KimpleParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KimpleParser.COMMA, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitExprList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			expr();
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(188);
				match(COMMA);
				setState(189);
				expr();
				}
				}
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public LogicExprContext logicExpr() {
			return getRuleContext(LogicExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			logicExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicExprContext extends ParserRuleContext {
		public List<CompareExprContext> compareExpr() {
			return getRuleContexts(CompareExprContext.class);
		}
		public CompareExprContext compareExpr(int i) {
			return getRuleContext(CompareExprContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(KimpleParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(KimpleParser.AND, i);
		}
		public List<TerminalNode> OR() { return getTokens(KimpleParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(KimpleParser.OR, i);
		}
		public LogicExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterLogicExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitLogicExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitLogicExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicExprContext logicExpr() throws RecognitionException {
		LogicExprContext _localctx = new LogicExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_logicExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			compareExpr();
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==OR) {
				{
				{
				setState(198);
				_la = _input.LA(1);
				if ( !(_la==AND || _la==OR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(199);
				compareExpr();
				}
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompareExprContext extends ParserRuleContext {
		public List<RangeExprContext> rangeExpr() {
			return getRuleContexts(RangeExprContext.class);
		}
		public RangeExprContext rangeExpr(int i) {
			return getRuleContext(RangeExprContext.class,i);
		}
		public List<TerminalNode> LT() { return getTokens(KimpleParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(KimpleParser.LT, i);
		}
		public List<TerminalNode> LE() { return getTokens(KimpleParser.LE); }
		public TerminalNode LE(int i) {
			return getToken(KimpleParser.LE, i);
		}
		public List<TerminalNode> GT() { return getTokens(KimpleParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(KimpleParser.GT, i);
		}
		public List<TerminalNode> GE() { return getTokens(KimpleParser.GE); }
		public TerminalNode GE(int i) {
			return getToken(KimpleParser.GE, i);
		}
		public List<TerminalNode> EQ() { return getTokens(KimpleParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(KimpleParser.EQ, i);
		}
		public List<TerminalNode> NEQ() { return getTokens(KimpleParser.NEQ); }
		public TerminalNode NEQ(int i) {
			return getToken(KimpleParser.NEQ, i);
		}
		public CompareExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compareExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterCompareExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitCompareExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitCompareExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompareExprContext compareExpr() throws RecognitionException {
		CompareExprContext _localctx = new CompareExprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_compareExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			rangeExpr();
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 67645734912L) != 0)) {
				{
				{
				setState(206);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 67645734912L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(207);
				rangeExpr();
				}
				}
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RangeExprContext extends ParserRuleContext {
		public List<AddSubExprContext> addSubExpr() {
			return getRuleContexts(AddSubExprContext.class);
		}
		public AddSubExprContext addSubExpr(int i) {
			return getRuleContext(AddSubExprContext.class,i);
		}
		public TerminalNode RANGE() { return getToken(KimpleParser.RANGE, 0); }
		public RangeExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterRangeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitRangeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitRangeExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeExprContext rangeExpr() throws RecognitionException {
		RangeExprContext _localctx = new RangeExprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_rangeExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			addSubExpr();
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RANGE) {
				{
				setState(214);
				match(RANGE);
				setState(215);
				addSubExpr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AddSubExprContext extends ParserRuleContext {
		public List<MulDivExprContext> mulDivExpr() {
			return getRuleContexts(MulDivExprContext.class);
		}
		public MulDivExprContext mulDivExpr(int i) {
			return getRuleContext(MulDivExprContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(KimpleParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(KimpleParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(KimpleParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(KimpleParser.MINUS, i);
		}
		public AddSubExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addSubExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterAddSubExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitAddSubExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitAddSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddSubExprContext addSubExpr() throws RecognitionException {
		AddSubExprContext _localctx = new AddSubExprContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_addSubExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			mulDivExpr();
			setState(223);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(219);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(220);
					mulDivExpr();
					}
					} 
				}
				setState(225);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MulDivExprContext extends ParserRuleContext {
		public List<PowExprContext> powExpr() {
			return getRuleContexts(PowExprContext.class);
		}
		public PowExprContext powExpr(int i) {
			return getRuleContext(PowExprContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(KimpleParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(KimpleParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(KimpleParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(KimpleParser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(KimpleParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(KimpleParser.MOD, i);
		}
		public MulDivExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulDivExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterMulDivExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitMulDivExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitMulDivExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulDivExprContext mulDivExpr() throws RecognitionException {
		MulDivExprContext _localctx = new MulDivExprContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_mulDivExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			powExpr();
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 58720256L) != 0)) {
				{
				{
				setState(227);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 58720256L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(228);
				powExpr();
				}
				}
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PowExprContext extends ParserRuleContext {
		public UnaryExprContext unaryExpr() {
			return getRuleContext(UnaryExprContext.class,0);
		}
		public TerminalNode POW() { return getToken(KimpleParser.POW, 0); }
		public PowExprContext powExpr() {
			return getRuleContext(PowExprContext.class,0);
		}
		public PowExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_powExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterPowExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitPowExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitPowExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowExprContext powExpr() throws RecognitionException {
		PowExprContext _localctx = new PowExprContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_powExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			unaryExpr();
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==POW) {
				{
				setState(235);
				match(POW);
				setState(236);
				powExpr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExprContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(KimpleParser.MINUS, 0); }
		public UnaryExprContext unaryExpr() {
			return getRuleContext(UnaryExprContext.class,0);
		}
		public TerminalNode NOT() { return getToken(KimpleParser.NOT, 0); }
		public CastExprContext castExpr() {
			return getRuleContext(CastExprContext.class,0);
		}
		public UnaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_unaryExpr);
		try {
			setState(244);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(MINUS);
				setState(240);
				unaryExpr();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				match(NOT);
				setState(242);
				unaryExpr();
				}
				break;
			case TRUE:
			case FALSE:
			case INF:
			case LPAREN:
			case IDENT:
			case INT:
			case REAL:
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(243);
				castExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CastExprContext extends ParserRuleContext {
		public PrimaryExprContext primaryExpr() {
			return getRuleContext(PrimaryExprContext.class,0);
		}
		public TerminalNode AS() { return getToken(KimpleParser.AS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public CastExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_castExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterCastExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitCastExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitCastExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CastExprContext castExpr() throws RecognitionException {
		CastExprContext _localctx = new CastExprContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_castExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			primaryExpr();
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(247);
				match(AS);
				setState(248);
				type();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryExprContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(KimpleParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(KimpleParser.RPAREN, 0); }
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(KimpleParser.IDENT, 0); }
		public PrimaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterPrimaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitPrimaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitPrimaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExprContext primaryExpr() throws RecognitionException {
		PrimaryExprContext _localctx = new PrimaryExprContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_primaryExpr);
		try {
			setState(258);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(251);
				match(LPAREN);
				setState(252);
				expr();
				setState(253);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				funcCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(256);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(257);
				match(IDENT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncCallContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(KimpleParser.IDENT, 0); }
		public TerminalNode LPAREN() { return getToken(KimpleParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(KimpleParser.RPAREN, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public FuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitFuncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_funcCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(IDENT);
			setState(261);
			match(LPAREN);
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 264020368490496L) != 0)) {
				{
				setState(262);
				exprList();
				}
			}

			setState(265);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(KimpleParser.INT, 0); }
		public TerminalNode REAL() { return getToken(KimpleParser.REAL, 0); }
		public TerminalNode STRING() { return getToken(KimpleParser.STRING, 0); }
		public TerminalNode TRUE() { return getToken(KimpleParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(KimpleParser.FALSE, 0); }
		public TerminalNode INF() { return getToken(KimpleParser.INF, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290605080576L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT_TYPE() { return getToken(KimpleParser.INT_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(KimpleParser.DOUBLE_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(KimpleParser.BOOLEAN_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(KimpleParser.STRING_TYPE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KimpleListener ) ((KimpleListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KimpleVisitor ) return ((KimpleVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 30720L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u00011\u0110\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0001\u0000\u0005\u0000B\b\u0000\n\u0000\f\u0000"+
		"E\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001M\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002[\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"c\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0003\u0005p\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006z\b\u0006"+
		"\n\u0006\f\u0006}\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u0094\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0003\f\u00a1"+
		"\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u00ab\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0005\u0011\u00b5"+
		"\b\u0011\n\u0011\f\u0011\u00b8\t\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0005\u0012\u00bf\b\u0012\n\u0012\f\u0012\u00c2"+
		"\t\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0005"+
		"\u0014\u00c9\b\u0014\n\u0014\f\u0014\u00cc\t\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0005\u0015\u00d1\b\u0015\n\u0015\f\u0015\u00d4\t\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00d9\b\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0005\u0017\u00de\b\u0017\n\u0017\f\u0017\u00e1\t\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u00e6\b\u0018\n\u0018"+
		"\f\u0018\u00e9\t\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u00ee\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0003\u001a\u00f5\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b"+
		"\u00fa\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0003\u001c\u0103\b\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0003\u001d\u0108\b\u001d\u0001\u001d\u0001\u001d\u0001\u001e"+
		"\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0000\u0000 \u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02468:<>\u0000\u0006\u0001\u0000\u001c\u001d\u0001\u0000\u001e"+
		"#\u0001\u0000\u0015\u0016\u0001\u0000\u0017\u0019\u0002\u0000\u0010\u0012"+
		"-/\u0001\u0000\u000b\u000e\u0113\u0000C\u0001\u0000\u0000\u0000\u0002"+
		"L\u0001\u0000\u0000\u0000\u0004Z\u0001\u0000\u0000\u0000\u0006\\\u0001"+
		"\u0000\u0000\u0000\bd\u0001\u0000\u0000\u0000\nk\u0001\u0000\u0000\u0000"+
		"\fv\u0001\u0000\u0000\u0000\u000e~\u0001\u0000\u0000\u0000\u0010\u0082"+
		"\u0001\u0000\u0000\u0000\u0012\u0087\u0001\u0000\u0000\u0000\u0014\u008c"+
		"\u0001\u0000\u0000\u0000\u0016\u0095\u0001\u0000\u0000\u0000\u0018\u009d"+
		"\u0001\u0000\u0000\u0000\u001a\u00a2\u0001\u0000\u0000\u0000\u001c\u00a8"+
		"\u0001\u0000\u0000\u0000\u001e\u00ac\u0001\u0000\u0000\u0000 \u00b0\u0001"+
		"\u0000\u0000\u0000\"\u00b2\u0001\u0000\u0000\u0000$\u00bb\u0001\u0000"+
		"\u0000\u0000&\u00c3\u0001\u0000\u0000\u0000(\u00c5\u0001\u0000\u0000\u0000"+
		"*\u00cd\u0001\u0000\u0000\u0000,\u00d5\u0001\u0000\u0000\u0000.\u00da"+
		"\u0001\u0000\u0000\u00000\u00e2\u0001\u0000\u0000\u00002\u00ea\u0001\u0000"+
		"\u0000\u00004\u00f4\u0001\u0000\u0000\u00006\u00f6\u0001\u0000\u0000\u0000"+
		"8\u0102\u0001\u0000\u0000\u0000:\u0104\u0001\u0000\u0000\u0000<\u010b"+
		"\u0001\u0000\u0000\u0000>\u010d\u0001\u0000\u0000\u0000@B\u0003\u0002"+
		"\u0001\u0000A@\u0001\u0000\u0000\u0000BE\u0001\u0000\u0000\u0000CA\u0001"+
		"\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DF\u0001\u0000\u0000\u0000"+
		"EC\u0001\u0000\u0000\u0000FG\u0005\u0000\u0000\u0001G\u0001\u0001\u0000"+
		"\u0000\u0000HM\u0003\u0006\u0003\u0000IM\u0003\b\u0004\u0000JM\u0003\n"+
		"\u0005\u0000KM\u0003\u0004\u0002\u0000LH\u0001\u0000\u0000\u0000LI\u0001"+
		"\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LK\u0001\u0000\u0000\u0000"+
		"M\u0003\u0001\u0000\u0000\u0000N[\u0003\u0006\u0003\u0000O[\u0003\b\u0004"+
		"\u0000P[\u0003\n\u0005\u0000Q[\u0003 \u0010\u0000R[\u0003\u0010\b\u0000"+
		"S[\u0003\u0012\t\u0000T[\u0003\u0014\n\u0000U[\u0003\u0016\u000b\u0000"+
		"V[\u0003\u001a\r\u0000W[\u0003\u001c\u000e\u0000X[\u0003\u001e\u000f\u0000"+
		"Y[\u0003\"\u0011\u0000ZN\u0001\u0000\u0000\u0000ZO\u0001\u0000\u0000\u0000"+
		"ZP\u0001\u0000\u0000\u0000ZQ\u0001\u0000\u0000\u0000ZR\u0001\u0000\u0000"+
		"\u0000ZS\u0001\u0000\u0000\u0000ZT\u0001\u0000\u0000\u0000ZU\u0001\u0000"+
		"\u0000\u0000ZV\u0001\u0000\u0000\u0000ZW\u0001\u0000\u0000\u0000ZX\u0001"+
		"\u0000\u0000\u0000ZY\u0001\u0000\u0000\u0000[\u0005\u0001\u0000\u0000"+
		"\u0000\\]\u0005\u0001\u0000\u0000]^\u0005,\u0000\u0000^_\u0005+\u0000"+
		"\u0000_b\u0003>\u001f\u0000`a\u0005$\u0000\u0000ac\u0003&\u0013\u0000"+
		"b`\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000c\u0007\u0001\u0000"+
		"\u0000\u0000de\u0005\u0002\u0000\u0000ef\u0005,\u0000\u0000fg\u0005+\u0000"+
		"\u0000gh\u0003>\u001f\u0000hi\u0005$\u0000\u0000ij\u0003&\u0013\u0000"+
		"j\t\u0001\u0000\u0000\u0000kl\u0005\u0007\u0000\u0000lm\u0005,\u0000\u0000"+
		"mo\u0005%\u0000\u0000np\u0003\f\u0006\u0000on\u0001\u0000\u0000\u0000"+
		"op\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qr\u0005&\u0000\u0000"+
		"rs\u0005+\u0000\u0000st\u0003>\u001f\u0000tu\u0003\"\u0011\u0000u\u000b"+
		"\u0001\u0000\u0000\u0000v{\u0003\u000e\u0007\u0000wx\u0005*\u0000\u0000"+
		"xz\u0003\u000e\u0007\u0000yw\u0001\u0000\u0000\u0000z}\u0001\u0000\u0000"+
		"\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|\r\u0001\u0000"+
		"\u0000\u0000}{\u0001\u0000\u0000\u0000~\u007f\u0005,\u0000\u0000\u007f"+
		"\u0080\u0005+\u0000\u0000\u0080\u0081\u0003>\u001f\u0000\u0081\u000f\u0001"+
		"\u0000\u0000\u0000\u0082\u0083\u0005\t\u0000\u0000\u0083\u0084\u0005%"+
		"\u0000\u0000\u0084\u0085\u0003$\u0012\u0000\u0085\u0086\u0005&\u0000\u0000"+
		"\u0086\u0011\u0001\u0000\u0000\u0000\u0087\u0088\u0005\n\u0000\u0000\u0088"+
		"\u0089\u0005%\u0000\u0000\u0089\u008a\u0005,\u0000\u0000\u008a\u008b\u0005"+
		"&\u0000\u0000\u008b\u0013\u0001\u0000\u0000\u0000\u008c\u008d\u0005\u0003"+
		"\u0000\u0000\u008d\u008e\u0005%\u0000\u0000\u008e\u008f\u0003&\u0013\u0000"+
		"\u008f\u0090\u0005&\u0000\u0000\u0090\u0093\u0003\"\u0011\u0000\u0091"+
		"\u0092\u0005\u0004\u0000\u0000\u0092\u0094\u0003\"\u0011\u0000\u0093\u0091"+
		"\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u0015"+
		"\u0001\u0000\u0000\u0000\u0095\u0096\u0005\u0005\u0000\u0000\u0096\u0097"+
		"\u0005%\u0000\u0000\u0097\u0098\u0003\u0018\f\u0000\u0098\u0099\u0005"+
		"\u0013\u0000\u0000\u0099\u009a\u0003&\u0013\u0000\u009a\u009b\u0005&\u0000"+
		"\u0000\u009b\u009c\u0003\"\u0011\u0000\u009c\u0017\u0001\u0000\u0000\u0000"+
		"\u009d\u00a0\u0005,\u0000\u0000\u009e\u009f\u0005+\u0000\u0000\u009f\u00a1"+
		"\u0003>\u001f\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001"+
		"\u0000\u0000\u0000\u00a1\u0019\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005"+
		"\u0006\u0000\u0000\u00a3\u00a4\u0005%\u0000\u0000\u00a4\u00a5\u0003&\u0013"+
		"\u0000\u00a5\u00a6\u0005&\u0000\u0000\u00a6\u00a7\u0003\"\u0011\u0000"+
		"\u00a7\u001b\u0001\u0000\u0000\u0000\u00a8\u00aa\u0005\b\u0000\u0000\u00a9"+
		"\u00ab\u0003&\u0013\u0000\u00aa\u00a9\u0001\u0000\u0000\u0000\u00aa\u00ab"+
		"\u0001\u0000\u0000\u0000\u00ab\u001d\u0001\u0000\u0000\u0000\u00ac\u00ad"+
		"\u0005,\u0000\u0000\u00ad\u00ae\u0005$\u0000\u0000\u00ae\u00af\u0003&"+
		"\u0013\u0000\u00af\u001f\u0001\u0000\u0000\u0000\u00b0\u00b1\u0003&\u0013"+
		"\u0000\u00b1!\u0001\u0000\u0000\u0000\u00b2\u00b6\u0005\'\u0000\u0000"+
		"\u00b3\u00b5\u0003\u0004\u0002\u0000\u00b4\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b8\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b9\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b9\u00ba\u0005(\u0000\u0000\u00ba"+
		"#\u0001\u0000\u0000\u0000\u00bb\u00c0\u0003&\u0013\u0000\u00bc\u00bd\u0005"+
		"*\u0000\u0000\u00bd\u00bf\u0003&\u0013\u0000\u00be\u00bc\u0001\u0000\u0000"+
		"\u0000\u00bf\u00c2\u0001\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1%\u0001\u0000\u0000\u0000"+
		"\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u00c4\u0003(\u0014\u0000\u00c4"+
		"\'\u0001\u0000\u0000\u0000\u00c5\u00ca\u0003*\u0015\u0000\u00c6\u00c7"+
		"\u0007\u0000\u0000\u0000\u00c7\u00c9\u0003*\u0015\u0000\u00c8\u00c6\u0001"+
		"\u0000\u0000\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001"+
		"\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb)\u0001\u0000"+
		"\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cd\u00d2\u0003,\u0016"+
		"\u0000\u00ce\u00cf\u0007\u0001\u0000\u0000\u00cf\u00d1\u0003,\u0016\u0000"+
		"\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d1\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000"+
		"\u00d3+\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d5"+
		"\u00d8\u0003.\u0017\u0000\u00d6\u00d7\u0005\u0014\u0000\u0000\u00d7\u00d9"+
		"\u0003.\u0017\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001"+
		"\u0000\u0000\u0000\u00d9-\u0001\u0000\u0000\u0000\u00da\u00df\u00030\u0018"+
		"\u0000\u00db\u00dc\u0007\u0002\u0000\u0000\u00dc\u00de\u00030\u0018\u0000"+
		"\u00dd\u00db\u0001\u0000\u0000\u0000\u00de\u00e1\u0001\u0000\u0000\u0000"+
		"\u00df\u00dd\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000"+
		"\u00e0/\u0001\u0000\u0000\u0000\u00e1\u00df\u0001\u0000\u0000\u0000\u00e2"+
		"\u00e7\u00032\u0019\u0000\u00e3\u00e4\u0007\u0003\u0000\u0000\u00e4\u00e6"+
		"\u00032\u0019\u0000\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e6\u00e9\u0001"+
		"\u0000\u0000\u0000\u00e7\u00e5\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001"+
		"\u0000\u0000\u0000\u00e81\u0001\u0000\u0000\u0000\u00e9\u00e7\u0001\u0000"+
		"\u0000\u0000\u00ea\u00ed\u00034\u001a\u0000\u00eb\u00ec\u0005\u001a\u0000"+
		"\u0000\u00ec\u00ee\u00032\u0019\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000"+
		"\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee3\u0001\u0000\u0000\u0000\u00ef"+
		"\u00f0\u0005\u0016\u0000\u0000\u00f0\u00f5\u00034\u001a\u0000\u00f1\u00f2"+
		"\u0005\u001b\u0000\u0000\u00f2\u00f5\u00034\u001a\u0000\u00f3\u00f5\u0003"+
		"6\u001b\u0000\u00f4\u00ef\u0001\u0000\u0000\u0000\u00f4\u00f1\u0001\u0000"+
		"\u0000\u0000\u00f4\u00f3\u0001\u0000\u0000\u0000\u00f55\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f9\u00038\u001c\u0000\u00f7\u00f8\u0005\u000f\u0000\u0000"+
		"\u00f8\u00fa\u0003>\u001f\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00f9"+
		"\u00fa\u0001\u0000\u0000\u0000\u00fa7\u0001\u0000\u0000\u0000\u00fb\u00fc"+
		"\u0005%\u0000\u0000\u00fc\u00fd\u0003&\u0013\u0000\u00fd\u00fe\u0005&"+
		"\u0000\u0000\u00fe\u0103\u0001\u0000\u0000\u0000\u00ff\u0103\u0003:\u001d"+
		"\u0000\u0100\u0103\u0003<\u001e\u0000\u0101\u0103\u0005,\u0000\u0000\u0102"+
		"\u00fb\u0001\u0000\u0000\u0000\u0102\u00ff\u0001\u0000\u0000\u0000\u0102"+
		"\u0100\u0001\u0000\u0000\u0000\u0102\u0101\u0001\u0000\u0000\u0000\u0103"+
		"9\u0001\u0000\u0000\u0000\u0104\u0105\u0005,\u0000\u0000\u0105\u0107\u0005"+
		"%\u0000\u0000\u0106\u0108\u0003$\u0012\u0000\u0107\u0106\u0001\u0000\u0000"+
		"\u0000\u0107\u0108\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000"+
		"\u0000\u0109\u010a\u0005&\u0000\u0000\u010a;\u0001\u0000\u0000\u0000\u010b"+
		"\u010c\u0007\u0004\u0000\u0000\u010c=\u0001\u0000\u0000\u0000\u010d\u010e"+
		"\u0007\u0005\u0000\u0000\u010e?\u0001\u0000\u0000\u0000\u0015CLZbo{\u0093"+
		"\u00a0\u00aa\u00b6\u00c0\u00ca\u00d2\u00d8\u00df\u00e7\u00ed\u00f4\u00f9"+
		"\u0102\u0107";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}