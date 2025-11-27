package kimple.ast;

public class ReturnNode extends StatementNode {
    private final ExpressionNode expr;

    public ReturnNode(ExpressionNode expr, int line) { super(line); this.expr = expr; }
    public ExpressionNode getExpr() { return expr; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitReturn(this);
    }
}
