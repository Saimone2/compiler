package kimple.antlr;

import java.util.*;
import kimple.ast.*;
import kimple.gen.*;


import kimple.antlr.*;
import kimple.ast.*;
import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends KimpleBaseVisitor<ASTNode> {

    // program : topLevel* EOF
    @Override
    public ProgramNode visitProgram(KimpleParser.ProgramContext ctx) {
        List<ASTNode> topLevelNodes = new ArrayList<>();
        for (KimpleParser.TopLevelContext tl : ctx.topLevel()) {
            ASTNode node = visit(tl);
            if (node != null) topLevelNodes.add(node);
        }
        return new ProgramNode();
    }

    // topLevel : varDecl | valDecl | funDecl | statement
    @Override
    public ASTNode visitTopLevel(KimpleParser.TopLevelContext ctx) {
        if (ctx.varDecl() != null) return visitVarDecl(ctx.varDecl());
        if (ctx.valDecl() != null) return visitValDecl(ctx.valDecl());
        if (ctx.funDecl() != null) return visitFunDecl(ctx.funDecl());
        if (ctx.statement() != null) return visitStatement(ctx.statement());
        return null;
    }

    // statement : ... (всі види)
    @Override
    public StatementNode visitStatement(KimpleParser.StatementContext ctx) {
        if (ctx.varDecl() != null) return visitVarDecl(ctx.varDecl());
        if (ctx.valDecl() != null) return visitValDecl(ctx.valDecl());
        if (ctx.funDecl() != null) return visitFunDecl(ctx.funDecl());
        if (ctx.printStatement() != null) return visitPrintStatement(ctx.printStatement());
        if (ctx.readStatement() != null) return visitReadStatement(ctx.readStatement());
        if (ctx.ifStatement() != null) return visitIfStatement(ctx.ifStatement());
        if (ctx.forStatement() != null) return visitForStatement(ctx.forStatement());
        if (ctx.whileStatement() != null) return visitWhileStatement(ctx.whileStatement());
        if (ctx.returnStatement() != null) return visitReturnStatement(ctx.returnStatement());
        if (ctx.assignStatement() != null) return visitAssignStatement(ctx.assignStatement());
        if (ctx.exprStatement() != null) return visitExprStatement(ctx.exprStatement());
        throw new RuntimeException("Unknown statement at line " + ctx.start.getLine());
    }

    // var x: Int = 5;
    @Override
    public VarDeclNode visitVarDecl(KimpleParser.VarDeclContext ctx) {
        String name = ctx.IDENT().getText();
        String type = ctx.type().getText();
        ExpressionNode init = ctx.expr() != null ? (ExpressionNode) visit(ctx.expr()) : null;
        return new VarDeclNode(name, type, init, false, ctx.start.getLine());
    }

    @Override
    public ForNode visitForStatement(KimpleParser.ForStatementContext ctx) {
        String varName = ctx.forVarDecl().IDENT().getText();
        String varType = ctx.forVarDecl().type() != null ? ctx.forVarDecl().type().getText() : "Int";
        ExpressionNode start = (ExpressionNode) visit(ctx.expr());
        ExpressionNode end = (ExpressionNode) visit(ctx.expr());
        BlockNode body = (BlockNode) visit(ctx.block());
        return new ForNode(varName, varType, start, end, body, ctx.start.getLine());
    }

    // val x: Int = 5;
    @Override
    public VarDeclNode visitValDecl(KimpleParser.ValDeclContext ctx) {
        String name = ctx.IDENT().getText();
        String type = ctx.type().getText();
        ExpressionNode init = (ExpressionNode) visit(ctx.expr());
        return new VarDeclNode(name, type, init, true, ctx.start.getLine());
    }

    // fun name(p1: Int): Int { ... }
    @Override
    public FuncDeclNode visitFunDecl(KimpleParser.FunDeclContext ctx) {
        String name = ctx.IDENT().getText();
        List<String> paramNames = new ArrayList<>();
        List<String> paramTypes = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (KimpleParser.ParamContext p : ctx.paramList().param()) {
                paramNames.add(p.IDENT().getText());
                paramTypes.add(p.type().getText());
            }
        }
        String returnType = ctx.type() != null ? ctx.type().getText() : "Unit";
        BlockNode body = (BlockNode) visit(ctx.block());
        return new FuncDeclNode(name, paramNames, paramTypes, returnType, body, ctx.start.getLine());
    }

    // print(...)
    @Override
    public OutputNode visitPrintStatement(KimpleParser.PrintStatementContext ctx) {
        OutputNode node = new OutputNode(ctx.start.getLine());
        if (ctx.exprList() != null) {
            for (KimpleParser.ExprContext e : ctx.exprList().expr()) {
                node.addArg((ExpressionNode) visit(e));
            }
        }
        return node;
    }

    // read(x)
    @Override
    public InputNode visitReadStatement(KimpleParser.ReadStatementContext ctx) {
        return new InputNode(ctx.IDENT().getText(), ctx.start.getLine());
    }

    // if (...) ... else ...
    @Override
    public IfNode visitIfStatement(KimpleParser.IfStatementContext ctx) {
        ExpressionNode cond = (ExpressionNode) visit(ctx.expr());
        BlockNode thenBlock = (BlockNode) visit(ctx.block(0));
        BlockNode elseBlock = ctx.block().size() > 1 ? (BlockNode) visit(ctx.block(1)) : null;
        return new IfNode(cond, thenBlock, elseBlock, ctx.start.getLine());
    }

    // while (...) ...
    @Override
    public WhileNode visitWhileStatement(KimpleParser.WhileStatementContext ctx) {
        ExpressionNode cond = (ExpressionNode) visit(ctx.expr());
        BlockNode body = (BlockNode) visit(ctx.block());
        return new WhileNode(cond, body, ctx.start.getLine());
    }

    // return expr?
    @Override
    public ReturnNode visitReturnStatement(KimpleParser.ReturnStatementContext ctx) {
        ExpressionNode expr = ctx.expr() != null ? (ExpressionNode) visit(ctx.expr()) : null;
        return new ReturnNode(expr, ctx.start.getLine());
    }

    // x = expr
    @Override
    public AssignmentNode visitAssignStatement(KimpleParser.AssignStatementContext ctx) {
        String name = ctx.IDENT().getText();
        ExpressionNode expr = (ExpressionNode) visit(ctx.expr());
        return new AssignmentNode(name, expr, ctx.start.getLine());
    }

    // expr;
    @Override
    public StatementNode visitExprStatement(KimpleParser.ExprStatementContext ctx) {
        ExpressionNode expr = (ExpressionNode) visit(ctx.expr());
        // Якщо це виклик функції — робимо FuncCallStmtNode
        if (expr instanceof FuncCallExprNode) {
            return new FuncCallStmtNode((FuncCallExprNode) expr, ctx.start.getLine());
        }
        throw new RuntimeException("Expr statement must be function call at line " + ctx.start.getLine());
    }

    @Override
    public ExpressionNode visitFuncCall(KimpleParser.FuncCallContext ctx) {
        String name = ctx.IDENT().getText();
        List<ExpressionNode> args = new ArrayList<>();
        if (ctx.exprList() != null) {
            for (KimpleParser.ExprContext e : ctx.exprList().expr()) {
                args.add((ExpressionNode) visit(e));
            }
        }
        return new FuncCallExprNode(name, args, ctx.start.getLine());
    }
}