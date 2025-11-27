package kimple.ast;

public class IdentNode extends ExpressionNode {
    private final String name;
    public IdentNode(String name, int line) { super(line); this.name = name; }
    public String getName() { return name; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitIdent(this);
    }
}
