package kimple.ast;

import java.util.ArrayList;
import java.util.List;

public class FuncCallExprNode extends ExpressionNode {
    private final String name;
    private final List<ExpressionNode> args = new ArrayList<>();

    public FuncCallExprNode(String name, List<ExpressionNode> args, int line) {
        super(line);
        this.name = name;
        if (args != null) this.args.addAll(args);
    }

    public String getName() { return name; }
    public List<ExpressionNode> getArgs() { return args; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitFuncCallExpr(this);
    }
}
