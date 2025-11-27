package kimple.ast;

import kimple.visitor.ASTVisitor;


public interface ASTNode {
    <T> T accept(ASTVisitor<T> visitor);
    int getLine();
}
