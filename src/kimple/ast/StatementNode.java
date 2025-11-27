package kimple.ast;

public abstract class StatementNode implements ASTNode {
    private final int line;
    protected StatementNode(int line) { this.line = line; }
    @Override public int getLine() { return line; }
}
