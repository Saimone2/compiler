package kimple.ast;

public class BinaryOpNode extends ExpressionNode {
    private final String op;
    private final ExpressionNode left;
    private final ExpressionNode right;

    public BinaryOpNode(String op, ExpressionNode left, ExpressionNode right, int line) {
        super(line); this.op = op; this.left = left; this.right = right;
    }

    public String getOp() { return op; }
    public ExpressionNode getLeft() { return left; }
    public ExpressionNode getRight() { return right; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitBinaryOp(this);
    }
}
