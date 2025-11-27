package kimple.ast;

import java.util.ArrayList;
import java.util.List;

public class ProgramNode implements ASTNode {
    private final List<ASTNode> topLevel = new ArrayList<>();
    public void addTopLevel(ASTNode n) { topLevel.add(n); }
    public List<ASTNode> getTopLevel() { return topLevel; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitProgram(this);
    }

    @Override
    public int getLine() { return -1; }
}
