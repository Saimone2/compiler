package kimple.ast;

import java.util.ArrayList;
import java.util.List;

public class FuncDeclNode extends StatementNode {
    private final String name;
    private final List<String> paramNames = new ArrayList<>();
    private final List<String> paramTypes = new ArrayList<>();
    private final String returnType;
    private BlockNode body;
    private final List<FuncDeclNode> nested = new ArrayList<>();

    public FuncDeclNode(
            String name,
            List<String> paramNames,
            List<String> paramTypes,
            String returnType,
            BlockNode body,
            int line
    ) {
        super(line);
        this.name = name;
        this.paramNames.addAll(paramNames);
        this.paramTypes.addAll(paramTypes);
        this.returnType = returnType;
        this.body = body;
    }

    public String getName() { return name; }
    public List<String> getParamNames() { return paramNames; }
    public List<String> getParamTypes() { return paramTypes; }
    public String getReturnType() { return returnType; }
    public BlockNode getBody() { return body; }
    public void setBody(BlockNode body) { this.body = body; }

    // вкладені функції
    public void addNested(FuncDeclNode f) { nested.add(f); }
    public List<FuncDeclNode> getNested() { return nested; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitFuncDecl(this);
    }
}
