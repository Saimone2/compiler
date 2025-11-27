package kimple.ast;

public class ForNode extends StatementNode {
    private final String varName;
    private final String varType;
    private final ExpressionNode startExpr;
    private final ExpressionNode endExpr;
    private final BlockNode body;

    public ForNode(String varName, String varType, ExpressionNode startExpr, ExpressionNode endExpr, BlockNode body, int line) {
        super(line);
        this.varName = varName; this.varType = varType; this.startExpr = startExpr; this.endExpr = endExpr; this.body = body;
    }

    public String getVarName() { return varName; }
    public String getVarType() { return varType; }
    public ExpressionNode getStartExpr() { return startExpr; }
    public ExpressionNode getEndExpr() { return endExpr; }
    public BlockNode getBody() { return body; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitFor(this);
    }
}
