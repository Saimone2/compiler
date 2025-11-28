package kimple.ast;

public class LiteralNode extends ExpressionNode {
    private final String value; // text form (e.g. "123", "3.14", "\"hi\"", "true")
    private final String type;  // "Int","Double","Boolean","String"

    public LiteralNode(String value, String type, int line) {
        super(line);
        this.value = value; this.type = type;
    }

    public String getValue() { return value; }
    public String getType() { return type; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitLiteral(this);
    }
}
