package kimple.ast;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends StatementNode {
    private final List<StatementNode> statements = new ArrayList<>();

    public BlockNode(int line) { super(line); }

    public void addStatement(StatementNode s) { statements.add(s); }
    public List<StatementNode> getStatements() { return statements; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitBlock(this);
    }
}
