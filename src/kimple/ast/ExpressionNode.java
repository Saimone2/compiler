package kimple.ast;

public abstract class ExpressionNode implements ASTNode {
    private final int line;
    protected ExpressionNode(int line) { this.line = line; }
    @Override public int getLine() { return line; }
}
