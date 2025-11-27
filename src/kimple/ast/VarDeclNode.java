package kimple.ast;

public class VarDeclNode extends StatementNode {
    private final String name;
    private final String type; // "Int","Double","Boolean","String"
    private final ExpressionNode init; // may be null
    private final boolean isConst;

    public VarDeclNode(String name, String type, ExpressionNode init, boolean isConst, int line) {
        super(line);
        this.name = name; this.type = type; this.init = init; this.isConst = isConst;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public ExpressionNode getInit() { return init; }
    public boolean isConst() { return isConst; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitVarDecl(this);
    }
}
