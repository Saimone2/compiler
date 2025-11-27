package kimple.ast;

public class FuncCallStmtNode extends StatementNode {
    private final FuncCallExprNode call;

    public FuncCallStmtNode(FuncCallExprNode call, int line) { super(line); this.call = call; }
    public FuncCallExprNode getCall() { return call; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitFuncCallStmt(this);
    }
}
