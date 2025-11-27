package kimple.ast;

public class IfNode extends StatementNode {
    private final ExpressionNode cond;
    private final BlockNode thenBlock;
    private final BlockNode elseBlock;

    public IfNode(ExpressionNode cond, BlockNode thenBlock, BlockNode elseBlock, int line) {
        super(line);
        this.cond = cond; this.thenBlock = thenBlock; this.elseBlock = elseBlock;
    }

    public ExpressionNode getCond() { return cond; }
    public BlockNode getThenBlock() { return thenBlock; }
    public BlockNode getElseBlock() { return elseBlock; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitIf(this);
    }
}
