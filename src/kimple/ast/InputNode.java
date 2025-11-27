package kimple.ast;

public class InputNode extends StatementNode {
    private final String targetName;

    public InputNode(String targetName, int line) { super(line); this.targetName = targetName; }
    public String getTargetName() { return targetName; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitRead(this);
    }
}
