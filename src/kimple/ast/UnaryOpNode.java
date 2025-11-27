package kimple.ast;

public class UnaryOpNode extends ExpressionNode {
    private final String op;
    private final ExpressionNode expr;

    public UnaryOpNode(String op, ExpressionNode expr, int line) {
        super(line); this.op = op; this.expr = expr;
    }

    public String getOp() { return op; }
    public ExpressionNode getExpr() { return expr; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitUnaryOp(this);
    }
}
