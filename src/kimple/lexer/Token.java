package kimple.lexer;

public record Token(TokenType type, String value, int line, int column) {
    @Override
    public String toString() {
        String val = value == null ? "null" : ("'" + value + "'");
        return String.format("%-12s %-15s line=%-3d col=%-3d",
                type, val, line, column);
    }
}
