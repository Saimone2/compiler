package kimple.visitor;

import kimple.ast.*;

public interface ASTVisitor<T> {
    T visitProgram(ProgramNode n);
    T visitBlock(BlockNode n);
    T visitVarDecl(VarDeclNode n);
    T visitAssignment(AssignmentNode n);
    T visitRead(InputNode n);
    T visitPrint(OutputNode n);
    T visitReturn(ReturnNode n);
    T visitIf(IfNode n);
    T visitWhile(WhileNode n);
    T visitFor(ForNode n);
    T visitFuncDecl(FuncDeclNode n);
    T visitFuncCallExpr(FuncCallExprNode n);
    T visitFuncCallStmt(FuncCallStmtNode n);
    T visitBinaryOp(BinaryOpNode n);
    T visitUnaryOp(UnaryOpNode n);
    T visitLiteral(LiteralNode n);
    T visitIdent(IdentNode n);
    T visitCast(CastNode n);
}
