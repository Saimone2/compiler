package kimple.ast;

public class CastNode extends ExpressionNode {
    private final IdentNode target;
    private final String toType;

    public CastNode(IdentNode target, String toType, int line) {
        super(line);
        this.target = target; this.toType = toType;
    }

    public IdentNode getTarget() { return target; }
    public String getToType() { return toType; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitCast(this);
    }
}
