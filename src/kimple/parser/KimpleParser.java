package kimple.parser;

import kimple.lexer.*;

import java.util.ArrayList;
import java.util.List;

public class KimpleParser {
    private final List<Token> tokens;
    private int pos = 0;
    private Scope currentScope;

    public KimpleParser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentScope = new Scope(null, "global");
    }

    private Token current() {
        return pos < tokens.size() ? tokens.get(pos) : new Token(TokenType.EOF, null, -1, -1);
    }

    private Token peek() {
        return pos + 1 < tokens.size() ? tokens.get(pos + 1) : new Token(TokenType.EOF, null, -1, -1);
    }

    private void advance() { pos++; }

    private void log(String message) {
        Token curr = current();
        String tokenInfo = curr.type() == TokenType.EOF
                ? "EOF"
                : String.format("('%s', '%s')", curr.value(), curr.type().name().toLowerCase());
        System.out.printf("parseToken: В рядку %d токен %s %s%n",
                curr.line(), tokenInfo, message);
    }

    private Token expect(TokenType type) {
        log("очікується " + type);
        Token curr = current();
        if (curr.type() != type) {
            throw new SyntaxException("Expected " + type + ", got " + curr.type() +
                    " at line " + curr.line());
        }
        advance();
        return curr;
    }

    private boolean match(TokenType type) {
        if (current().type() == type) {
            log("знайдено " + type);
            advance();
            return true;
        }
        return false;
    }

    private boolean matchKeyword(String keyword) {
        Token curr = current();
        if (curr.type() == TokenType.KEYWORD && curr.value().equals(keyword)) {
            log("знайдено KEYWORD '" + keyword + "'");
            advance();
            return true;
        }
        return false;
    }

    private void expectKeyword() {
        Token curr = current();
        if (curr.type() != TokenType.KEYWORD || !curr.value().equals("in")) {
            throw new SyntaxException("Expected keyword '" + "in" + "'");
        }
        advance();
    }

    public void parse() {
        log("початок аналізу програми → викликано parseProgram()");
        parseProgram();
        log("досягнуто кінця файлу");
        expect(TokenType.EOF);
    }

    private void parseProgram() {
        log("parseProgram() → обробка глобальних оголошень та функцій");
        while (current().type() != TokenType.EOF) {
            if (matchKeyword("fun")) {
                parseFunctionDecl();
            } else if (matchKeyword("val")) {
                parseGlobalConstDecl();
            } else if (matchKeyword("var")) {
                parseGlobalVarDecl();
            } else {
                parseStatement();
            }
        }
    }

    private void parseGlobalConstDecl() {
        Token name = expect(TokenType.IDENT);
        expect(TokenType.COLON);
        Token type = expect(TokenType.TYPE);
        expect(TokenType.ASSIGN_OP);
        String exprType = parseExpression();

        if (isCompatible(type.value(), exprType)) {
            throw new SemanticException(
                    "Incompatible initialization types const: " + exprType + " -> " + type.value());
        }

        currentScope.declare(new Symbol(name.value(), type.value(), true, name.line()));
        match(TokenType.SEMICOLON);
    }

    private void parseGlobalVarDecl() {
        Token name = expect(TokenType.IDENT);
        expect(TokenType.COLON);
        Token type = expect(TokenType.TYPE);

        if (match(TokenType.ASSIGN_OP)) {
            String exprType = parseExpression();
            if (isCompatible(type.value(), exprType)) {
                throw new SemanticException("Incompatible types var: " + exprType + " -> " + type.value());
            }
        }
        currentScope.declare(new Symbol(name.value(), type.value(), false, name.line()));
        match(TokenType.SEMICOLON);
    }

    private void parseFunctionDecl() {
        log("parseFunctionDecl() → оголошення функції");
        Token nameTok = expect(TokenType.IDENT);
        String funcName = nameTok.value();

        expect(TokenType.LPAREN);

        List<String> paramNames = new ArrayList<>();
        List<String> paramTypes = new ArrayList<>();

        if (current().type() != TokenType.RPAREN) {
            do {
                Token pName = expect(TokenType.IDENT);
                expect(TokenType.COLON);
                Token pType = expect(TokenType.TYPE);
                paramNames.add(pName.value());
                paramTypes.add(pType.value());
            } while (match(TokenType.COMMA));
        }
        expect(TokenType.RPAREN);

        String returnType = "Unit";
        if (match(TokenType.COLON)) {
            returnType = expect(TokenType.TYPE).value();
        }

        currentScope.declare(
                new Symbol(funcName, "Function", false, nameTok.line(), paramTypes, returnType)
        );

        Scope old = currentScope;
        currentScope = new Scope(old, funcName);

        for (int i = 0; i < paramNames.size(); i++) {
            currentScope.declare(new Symbol(paramNames.get(i), paramTypes.get(i), false, nameTok.line()));
        }

        parseBlock();

        currentScope = old;
    }

    private void parseBlock() {
        log("parseBlock() → вхід у блок {");
        expect(TokenType.LBRACE);

        Scope old = currentScope;
        currentScope = new Scope(old, "block");

        while (current().type() != TokenType.RBRACE && current().type() != TokenType.EOF) {
            parseStatement();
        }

        expect(TokenType.RBRACE);
        currentScope = old;

        log("parseBlock() → вихід з блоку }");
    }

    private void parseStatement() {
        if (matchKeyword("val")) {
            parseConstDecl();
        } else if (matchKeyword("var")) {
            parseVarDecl();
        } else if (matchKeyword("fun")) {
            parseFunctionDecl();
        } else if (matchKeyword("read")) {
            parseInputStmt();
        } else if (matchKeyword("print")) {
            parseOutputStmt();
        } else if (matchKeyword("return")) {
            parseReturnStmt();
        } else if (matchKeyword("if")) {
            parseIfStmt();
        } else if (matchKeyword("for")) {
            parseForStmt();
        } else if (matchKeyword("while")) {
            parseWhileStmt();
        } else if (current().type() == TokenType.IDENT) {

            if (peek().type() == TokenType.ASSIGN_OP) {
                log("parseStatement() → присвоювання");
                parseAssignment();
            } else {
                log("parseStatement() → виклик функції");
                parseFuncCall();
                match(TokenType.SEMICOLON);
            }

        } else {
            throw new SyntaxException("Unexpected token: " + current());
        }
    }

    private void parseVarDecl() {
        Token nameTok = expect(TokenType.IDENT);
        expect(TokenType.COLON);
        Token typeTok = expect(TokenType.TYPE);

        if (match(TokenType.ASSIGN_OP)) {
            String exprType = parseExpression();
            if (isCompatible(typeTok.value(), exprType))
                throw new SemanticException("Incompatible types: " + exprType + " -> " + typeTok.value());
        }
        currentScope.declare(new Symbol(nameTok.value(), typeTok.value(), false, nameTok.line()));
        match(TokenType.SEMICOLON);
    }

    private void parseConstDecl() {
        Token nameTok = expect(TokenType.IDENT);
        expect(TokenType.COLON);
        Token typeTok = expect(TokenType.TYPE);
        expect(TokenType.ASSIGN_OP);

        String exprType = parseExpression();
        if (isCompatible(typeTok.value(), exprType))
            throw new SemanticException("Const init type mismatch: " + exprType);

        currentScope.declare(new Symbol(nameTok.value(), typeTok.value(), true, nameTok.line()));
        match(TokenType.SEMICOLON);
    }

    private void parseAssignment() {
        Token nameTok = expect(TokenType.IDENT);
        Symbol sym = currentScope.lookup(nameTok.value(), nameTok.line());
        if (sym.isConst()) throw new SemanticException("Cannot reassign const: " + sym.name());

        expect(TokenType.ASSIGN_OP);
        String exprType = parseExpression();

        if (isCompatible(sym.type(), exprType))
            throw new SemanticException("Type mismatch: " + exprType + " -> " + sym.type());

        match(TokenType.SEMICOLON);
    }

    private void parseInputStmt() {
        expect(TokenType.LPAREN);
        Token var = expect(TokenType.IDENT);
        Symbol sym = currentScope.lookup(var.value(), var.line());

        if (isCompatible(sym.type(), "String"))
            throw new SemanticException("read() expects String target");

        expect(TokenType.RPAREN);
        match(TokenType.SEMICOLON);
    }

    private void parseOutputStmt() {
        expect(TokenType.LPAREN);
        if (current().type() != TokenType.RPAREN) {
            parseArgList();
        }
        expect(TokenType.RPAREN);
        match(TokenType.SEMICOLON);
    }

    private void parseReturnStmt() {
        if (current().type() != TokenType.SEMICOLON)
            parseExpression();
        match(TokenType.SEMICOLON);
    }

    private void parseIfStmt() {
        expect(TokenType.LPAREN);
        String cond = parseExpression();
        if (!cond.equals("Boolean"))
            throw new SemanticException("if condition must be Boolean");
        expect(TokenType.RPAREN);

        parseBlock();

        if (matchKeyword("else")) {
            parseBlock();
        }
    }

    private void parseForStmt() {
        expect(TokenType.LPAREN);
        Token var = expect(TokenType.IDENT);
        expect(TokenType.COLON);
        Token varType = expect(TokenType.TYPE);

        if (!"Int".equals(varType.value())) {
            throw new SemanticException("Iterator must be Int (line " + var.line() + ")");
        }

        expectKeyword();
        String startType = parseExpression();
        expect(TokenType.RANGE_OP);
        String endType = parseExpression();

        if (!"Int".equals(startType) || !"Int".equals(endType)) {
            throw new SemanticException("Range must be Int (line " + var.line() + ")");
        }

        expect(TokenType.RPAREN);

        Scope old = currentScope;
        currentScope = new Scope(old, "for");

        currentScope.declare(new Symbol(var.value(), "Int", true, var.line()));
        parseBlock();

        currentScope = old;
    }

    private void parseWhileStmt() {
        expect(TokenType.LPAREN);
        String cond = parseExpression();
        if (!cond.equals("Boolean"))
            throw new SemanticException("while condition must be Boolean");
        expect(TokenType.RPAREN);
        parseBlock();
    }

    private String parseFuncCall() {
        Token name = expect(TokenType.IDENT);
        Symbol f = currentScope.lookup(name.value(), name.line());

        if (!f.type().equals("Function"))
            throw new SemanticException(name.value() + " is not a function");

        expect(TokenType.LPAREN);
        if (current().type() != TokenType.RPAREN)
            parseArgList();
        expect(TokenType.RPAREN);

        return f.returnType();
    }

    private void parseArgList() {
        parseExpression();
        while (match(TokenType.COMMA))
            parseExpression();
    }

    private String parseExpression() { return parseLogicOr(); }

    private String parseLogicOr() {
        String left = parseLogicAnd();
        boolean used = false;

        while (match(TokenType.OR_OP)) {
            used = true;
            String right = parseLogicAnd();
            if (!left.equals("Boolean") || !right.equals("Boolean"))
                throw new SemanticException("|| requires Boolean");
        }
        return used ? "Boolean" : left;
    }

    private String parseLogicAnd() {
        String left = parseEquality();
        boolean used = false;

        while (match(TokenType.AND_OP)) {
            used = true;
            String right = parseEquality();
            if (!left.equals("Boolean") || !right.equals("Boolean"))
                throw new SemanticException("&& requires Boolean");
        }
        return used ? "Boolean" : left;
    }

    private String parseEquality() {
        String left = parseComparison();

        while (current().type() == TokenType.EQ_OP || current().type() == TokenType.NEQ_OP) {
            advance();
            String right = parseComparison();
            if (!left.equals(right))
                throw new SemanticException("==/!= type mismatch: " + left + " vs " + right);
            left = "Boolean";
        }
        return left;
    }

    private String parseComparison() {
        String left = parseAddition();

        while (match(TokenType.REL_OP)) {
            String right = parseAddition();

            if (!(isNumber(left) && isNumber(right)))
                throw new SemanticException("Comparison requires numeric types");

            left = "Boolean";
        }
        return left;
    }

    private String parseAddition() {
        String left = parseMultiplication();

        while (match(TokenType.ADD_OP)) {
            String right = parseMultiplication();

            if (left.equals("String") || right.equals("String"))
                left = "String";
            else if (left.equals("Double") || right.equals("Double"))
                left = "Double";
            else if (left.equals("Int") && right.equals("Int"))
                left = "Int";
            else
                throw new SemanticException("Invalid + or - types");

        }
        return left;
    }

    private String parseMultiplication() {
        String left = parsePower();

        while (match(TokenType.MULT_OP)) {
            String right = parsePower();

            if (left.equals("Double") || right.equals("Double"))
                left = "Double";
            else if (left.equals("Int") && right.equals("Int"))
                left = "Int";
            else
                throw new SemanticException("Invalid * / % types");
        }
        return left;
    }

    private String parsePower() {
        String left = parseUnary();

        if (match(TokenType.POW_OP)) {
            String right = parsePower();
            if (isNumber(left) && isNumber(right))
                return (left.equals("Double") || right.equals("Double")) ? "Double" : "Int";
            throw new SemanticException("Invalid ^ types");
        }
        return left;
    }

    private String parseUnary() {
        if (match(TokenType.NOT_OP)) {
            String type = parseUnary();
            if (!type.equals("Boolean"))
                throw new SemanticException("! requires Boolean");
            return "Boolean";
        }
        if (match(TokenType.ADD_OP)) {
            return parseUnary();
        }
        return parsePrimary();
    }

    private String parsePrimary() {
        Token curr = current();

        switch (curr.type()) {
            case INT -> { advance(); return "Int"; }
            case REAL, INF -> { advance(); return "Double"; }
            case STRING -> { advance(); return "String"; }
            case TRUE, FALSE -> { advance(); return "Boolean"; }

            case IDENT -> {
                advance();
                Symbol sym = currentScope.lookup(curr.value(), curr.line());

                if (current().type() == TokenType.LPAREN) {
                    pos--;
                    return parseFuncCall();
                }

                if (match(TokenType.CAST_OP)) {
                    Token target = expect(TokenType.TYPE);
                    if (!canCast(sym.type(), target.value()))
                        throw new SemanticException("Invalid cast: " + sym.type() + " -> " + target.value());
                    return target.value();
                }

                return sym.type();
            }

            case LPAREN -> {
                advance();
                String type = parseExpression();
                expect(TokenType.RPAREN);
                return type;
            }

            default -> throw new SyntaxException("Invalid primary: " + curr.type());
        }
    }

    private boolean isNumber(String t) {
        return t.equals("Int") || t.equals("Double");
    }

    private boolean isCompatible(String target, String source) {
        if (target.equals(source)) return false;
        return !target.equals("Double") || !source.equals("Int");
    }

    private boolean canCast(String from, String to) {
        if (from.equals(to)) return true;
        return (isNumber(from) && isNumber(to)) || to.equals("String");
    }
}
