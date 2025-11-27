package kimple.ast;

import java.util.ArrayList;
import java.util.List;

public class OutputNode extends StatementNode {
    private final List<ExpressionNode> args = new ArrayList<>();

    public OutputNode(int line) { super(line); }
    public void addArg(ExpressionNode e) { args.add(e); }
    public List<ExpressionNode> getArgs() { return args; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitPrint(this);
    }
}
