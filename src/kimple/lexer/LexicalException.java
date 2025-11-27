package kimple.lexer;

public class LexicalException extends RuntimeException {
    public LexicalException(String message, int line, int column, String details) {
        super(message + " at line " + line + ", column " + column + ": " + details);
    }
}