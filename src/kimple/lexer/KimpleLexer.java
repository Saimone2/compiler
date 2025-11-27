package kimple.lexer;

import java.util.*;

public class KimpleLexer {
    private final String source;
    private int pos = 0;
    private int line = 1;
    private int column = 1;
    private char ch = 0;

    private static final Set<String> KEYWORDS = Set.of(
            "var", "val", "if", "else", "for", "while", "fun", "return", "print", "read", "in"
    );

    private static final Set<String> TYPES = Set.of(
            "Int", "Double", "Boolean", "String"
    );

    public KimpleLexer(String source) {
        this.source = source;
        advance();
    }

    private void advance() {
        if (ch == '\n') { line++; column = 1; }
        else if (ch != 0) column++;

        if (pos < source.length()) {
            ch = source.charAt(pos++);
        } else {
            ch = 0;
        }
    }

    private char peek() {
        return pos < source.length() ? source.charAt(pos) : 0;
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(ch)) advance();
    }

    private boolean isCyrillic(char c) {
        return c >= '\u0400' && c <= '\u04FF';
    }

    private boolean isIdentifierStart() {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_';
    }

    private boolean isIdentifierPart() {
        return (ch >= 'a' && ch <= 'z') ||
                (ch >= 'A' && ch <= 'Z') ||
                (ch >= '0' && ch <= '9') ||
                ch == '_' ||
                (ch > 127 && !isCyrillic(ch));
    }

    private Token identifier() {
        int startLine = line, startCol = column;

        if (!isIdentifierStart()) {
            throw new LexicalException(
                    "The identifier must start with a Latin letter (a-z, A-Z) or '_'",
                    line, column, String.valueOf(ch)
            );
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ch);
        advance();

        while (isIdentifierPart()) {
            if (isCyrillic(ch)) {
                throw new LexicalException(
                        "Cyrillic characters are not allowed in identifiers",
                        line, column, String.valueOf(ch)
                );
            }
            sb.append(ch);
            advance();
        }

        String text = sb.toString();
        if (text.equals("true")) {
            return new Token(TokenType.TRUE, text, startLine, startCol);
        } else if (text.equals("false")) {
            return new Token(TokenType.FALSE, text, startLine, startCol);
        } else if (text.equals("inf")) {
            return new Token(TokenType.INF, text, startLine, startCol);
        } else if (text.equals("as")) {
            return new Token(TokenType.CAST_OP, text, startLine, startCol);
        } else if (KEYWORDS.contains(text)) {
            return new Token(TokenType.KEYWORD, text, startLine, startCol);
        } else if (TYPES.contains(text)) {
            return new Token(TokenType.TYPE, text, startLine, startCol);
        }
        return new Token(TokenType.IDENT, text, startLine, startCol);
    }

    private Token number() {
        int startLine = line, startCol = column;
        StringBuilder sb = new StringBuilder();
        boolean dotSeen = false;
        boolean expSeen = false;

        while (Character.isDigit(ch)) {
            sb.append(ch);
            advance();
        }

        if (ch == '.' && Character.isDigit(peek())) {
            dotSeen = true;
            sb.append(ch);
            advance();
            while (Character.isDigit(ch)) {
                sb.append(ch);
                advance();
            }
        }

        if ((ch == 'e' || ch == 'E') && !sb.isEmpty()) {
            expSeen = true;
            sb.append(ch);
            advance();

            if (ch == '+' || ch == '-') {
                sb.append(ch);
                advance();
            }

            if (!Character.isDigit(ch)) {
                throw new LexicalException(
                        "Must contain at least one digit after 'e' or 'E'",
                        line, column,
                        sb.toString()
                );
            }

            while (Character.isDigit(ch)) {
                sb.append(ch);
                advance();
            }
        }

        if (dotSeen && sb.toString().endsWith(".")) {
            throw new LexicalException("Invalid number: trailing dot without digits", startLine, startCol, sb.toString());
        }

        String text = sb.toString();
        if (dotSeen || expSeen) {
            return new Token(TokenType.REAL, text, startLine, startCol);
        } else {
            return new Token(TokenType.INT, text, startLine, startCol);
        }
    }

    private Token string() {
        int startLine = line, startCol = column;
        StringBuilder sb = new StringBuilder();
        advance(); // пропустити "

        while (ch != 0 && ch != '"') {
            sb.append(ch);
            advance();
        }

        if (ch != '"') {
            throw new LexicalException("Unterminated string", startLine, startCol, sb.toString());
        }
        advance();
        return new Token(TokenType.STRING, sb.toString(), startLine, startCol);
    }

    private Token skipSingleLineComment() {
        while (ch != 0 && ch != '\n') advance();
        return nextToken(); // продовжити
    }

    private Token operatorOrDelimiter() {
        int startLine = line, startCol = column;
        char first = ch;
        advance();

        return switch (first) {
            case '+' -> new Token(TokenType.ADD_OP, "+", startLine, startCol);
            case '-' -> new Token(TokenType.ADD_OP, "-", startLine, startCol);
            case '*' -> new Token(TokenType.MULT_OP, "*", startLine, startCol);
            case '/' -> {
                if (ch == '/') {
                    advance();
                    yield skipSingleLineComment();
                }
                yield new Token(TokenType.MULT_OP, "/", startLine, startCol);
            }
            case '%' -> new Token(TokenType.MULT_OP, "%", startLine, startCol);
            case '^' -> new Token(TokenType.POW_OP, "^", startLine, startCol);
            case '!' -> {
                if (ch == '=') {
                    advance();
                    yield new Token(TokenType.NEQ_OP, "!=", startLine, startCol);
                }
                yield new Token(TokenType.NOT_OP, "!", startLine, startCol);
            }
            case '<' -> {
                if (ch == '=') {
                    advance();
                    yield new Token(TokenType.REL_OP, "<=", startLine, startCol);
                }
                yield new Token(TokenType.REL_OP, "<", startLine, startCol);
            }
            case '>' -> {
                if (ch == '=') {
                    advance();
                    yield new Token(TokenType.REL_OP, ">=", startLine, startCol);
                }
                yield new Token(TokenType.REL_OP, ">", startLine, startCol);
            }
            case '=' -> {
                if (ch == '=') {
                    advance();
                    yield new Token(TokenType.EQ_OP, "==", startLine, startCol);
                }
                yield new Token(TokenType.ASSIGN_OP, "=", startLine, startCol);
            }
            case '&' -> {
                if (ch == '&') {
                    advance();
                    yield new Token(TokenType.AND_OP, "&&", startLine, startCol);
                }
                throw new LexicalException("Invalid operator", startLine, startCol, "&");
            }
            case '|' -> {
                if (ch == '|') {
                    advance();
                    yield new Token(TokenType.OR_OP, "||", startLine, startCol);
                }
                throw new LexicalException("Invalid operator", startLine, startCol, "|");
            }
            case '(' -> new Token(TokenType.LPAREN, "(", startLine, startCol);
            case ')' -> new Token(TokenType.RPAREN, ")", startLine, startCol);
            case '{' -> new Token(TokenType.LBRACE, "{", startLine, startCol);
            case '}' -> new Token(TokenType.RBRACE, "}", startLine, startCol);
            case ';' -> new Token(TokenType.SEMICOLON, ";", startLine, startCol);
            case ',' -> new Token(TokenType.COMMA, ",", startLine, startCol);
            case ':' -> new Token(TokenType.COLON, ":", startLine, startCol);
            case '.' -> {
                if (ch == '.') {
                    advance();
                    yield new Token(TokenType.RANGE_OP, "..", startLine, startCol);
                }
                throw new LexicalException("Invalid dot", startLine, startCol, ".");
            }
            default -> throw new LexicalException("Unknown character", startLine, startCol, String.valueOf(first));
        };
    }

    public Token nextToken() {
        while (ch != 0) {
            skipWhitespace();

            if (ch == 0) break;

            if (isIdentifierStart()) return identifier();
            if (Character.isDigit(ch)) return number();
            if (ch == '"') return string();
            if ("+-*/%^!<>=&|(){};,:.".indexOf(ch) != -1) return operatorOrDelimiter();

            throw new LexicalException("Unknown character", line, column, String.valueOf(ch));
        }
        return new Token(TokenType.EOF, null, line, column);
    }

    public List<Token> tokenize() throws LexicalException {
        List<Token> tokens = new ArrayList<>();
        Token token;
        while ((token = nextToken()).type() != TokenType.EOF) {
            tokens.add(token);
        }
        tokens.add(token);
        return tokens;
    }
}
