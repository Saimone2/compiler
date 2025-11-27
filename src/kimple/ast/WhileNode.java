package kimple.ast;

public class WhileNode extends StatementNode {
    private final ExpressionNode cond;
    private final BlockNode body;

    public WhileNode(ExpressionNode cond, BlockNode body, int line) { super(line); this.cond = cond; this.body = body; }
    public ExpressionNode getCond() { return cond; }
    public BlockNode getBody() { return body; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitWhile(this);
    }
}
