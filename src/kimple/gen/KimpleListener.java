// Generated from src/kimple/antlr/Kimple.g4 by ANTLR 4.13.1
package kimple.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KimpleParser}.
 */
public interface KimpleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KimpleParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(KimpleParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(KimpleParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#topLevel}.
	 * @param ctx the parse tree
	 */
	void enterTopLevel(KimpleParser.TopLevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#topLevel}.
	 * @param ctx the parse tree
	 */
	void exitTopLevel(KimpleParser.TopLevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(KimpleParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(KimpleParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(KimpleParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(KimpleParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#valDecl}.
	 * @param ctx the parse tree
	 */
	void enterValDecl(KimpleParser.ValDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#valDecl}.
	 * @param ctx the parse tree
	 */
	void exitValDecl(KimpleParser.ValDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#funDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunDecl(KimpleParser.FunDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#funDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunDecl(KimpleParser.FunDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(KimpleParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(KimpleParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(KimpleParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(KimpleParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(KimpleParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(KimpleParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#readStatement}.
	 * @param ctx the parse tree
	 */
	void enterReadStatement(KimpleParser.ReadStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#readStatement}.
	 * @param ctx the parse tree
	 */
	void exitReadStatement(KimpleParser.ReadStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(KimpleParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(KimpleParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(KimpleParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(KimpleParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#forVarDecl}.
	 * @param ctx the parse tree
	 */
	void enterForVarDecl(KimpleParser.ForVarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#forVarDecl}.
	 * @param ctx the parse tree
	 */
	void exitForVarDecl(KimpleParser.ForVarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(KimpleParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(KimpleParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(KimpleParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(KimpleParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStatement(KimpleParser.AssignStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStatement(KimpleParser.AssignStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#exprStatement}.
	 * @param ctx the parse tree
	 */
	void enterExprStatement(KimpleParser.ExprStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#exprStatement}.
	 * @param ctx the parse tree
	 */
	void exitExprStatement(KimpleParser.ExprStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(KimpleParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(KimpleParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(KimpleParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(KimpleParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(KimpleParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(KimpleParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#logicExpr}.
	 * @param ctx the parse tree
	 */
	void enterLogicExpr(KimpleParser.LogicExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#logicExpr}.
	 * @param ctx the parse tree
	 */
	void exitLogicExpr(KimpleParser.LogicExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#compareExpr}.
	 * @param ctx the parse tree
	 */
	void enterCompareExpr(KimpleParser.CompareExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#compareExpr}.
	 * @param ctx the parse tree
	 */
	void exitCompareExpr(KimpleParser.CompareExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#rangeExpr}.
	 * @param ctx the parse tree
	 */
	void enterRangeExpr(KimpleParser.RangeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#rangeExpr}.
	 * @param ctx the parse tree
	 */
	void exitRangeExpr(KimpleParser.RangeExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#addSubExpr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(KimpleParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#addSubExpr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(KimpleParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#mulDivExpr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(KimpleParser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#mulDivExpr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(KimpleParser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#powExpr}.
	 * @param ctx the parse tree
	 */
	void enterPowExpr(KimpleParser.PowExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#powExpr}.
	 * @param ctx the parse tree
	 */
	void exitPowExpr(KimpleParser.PowExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(KimpleParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(KimpleParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#castExpr}.
	 * @param ctx the parse tree
	 */
	void enterCastExpr(KimpleParser.CastExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#castExpr}.
	 * @param ctx the parse tree
	 */
	void exitCastExpr(KimpleParser.CastExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(KimpleParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(KimpleParser.PrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(KimpleParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(KimpleParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(KimpleParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(KimpleParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link KimpleParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(KimpleParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link KimpleParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(KimpleParser.TypeContext ctx);
}