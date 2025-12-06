// Generated from src/kimple/antlr/Kimple.g4 by ANTLR 4.13.1
package kimple.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link KimpleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface KimpleVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link KimpleParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(KimpleParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#topLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopLevel(KimpleParser.TopLevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(KimpleParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(KimpleParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#valDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValDecl(KimpleParser.ValDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#funDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDecl(KimpleParser.FunDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(KimpleParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(KimpleParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(KimpleParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#readStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStatement(KimpleParser.ReadStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(KimpleParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(KimpleParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#forVarDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForVarDecl(KimpleParser.ForVarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(KimpleParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(KimpleParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#assignStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStatement(KimpleParser.AssignStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#exprStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStatement(KimpleParser.ExprStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(KimpleParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(KimpleParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(KimpleParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#logicExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicExpr(KimpleParser.LogicExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#compareExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareExpr(KimpleParser.CompareExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#rangeExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeExpr(KimpleParser.RangeExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#addSubExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(KimpleParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#mulDivExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpr(KimpleParser.MulDivExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#powExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpr(KimpleParser.PowExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#unaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(KimpleParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#castExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpr(KimpleParser.CastExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#primaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(KimpleParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(KimpleParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(KimpleParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link KimpleParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(KimpleParser.TypeContext ctx);
}