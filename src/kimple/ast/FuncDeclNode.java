package kimple.ast;

import java.util.ArrayList;
import java.util.List;

public class FuncDeclNode implements ASTNode {
    private final String name;
    private final List<String> paramNames = new ArrayList<>();
    private final List<String> paramTypes = new ArrayList<>();
    private final String returnType;
    private final BlockNode body;
    private final int line;

    public FuncDeclNode(String name, List<String> paramNames, List<String> paramTypes, String returnType, BlockNode body, int line) {
        this.name = name;
        this.paramNames.addAll(paramNames);
        this.paramTypes.addAll(paramTypes);
        this.returnType = returnType;
        this.body = body;
        this.line = line;
    }

    public String getName() { return name; }
    public List<String> getParamNames() { return paramNames; }
    public List<String> getParamTypes() { return paramTypes; }
    public String getReturnType() { return returnType; }
    public BlockNode getBody() { return body; }
    public int getLine() { return line; }

    @Override
    public <T> T accept(kimple.visitor.ASTVisitor<T> visitor) {
        return visitor.visitFuncDecl(this);
    }
}
