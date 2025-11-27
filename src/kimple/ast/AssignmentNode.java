package kimple.ast;

public class AssignmentNode extends StatementNode {
    private final String name;
    private final ExpressionNode expr;

    public AssignmentNode(String name, ExpressionNode expr, int line) {
        super(line);
        this.name = name; this.expr = expr;
    }

    public String getName() { return name; }
    public ExpressionNode getExpr() { return expr; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitAssignment(this);
    }
}
