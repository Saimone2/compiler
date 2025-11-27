package kimple.ast;

import kimple.visitor.ASTVisitor;

/**
 * Базовий інтерфейс для AST-вузлів.
 */
public interface ASTNode {
    <T> T accept(ASTVisitor<T> visitor);
    int getLine(); // номер рядка для повідомлень про помилки
}
