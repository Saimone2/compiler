package kimple.parser;

import kimple.lexer.Token;
import kimple.lexer.TokenType;

import java.util.List;

public class KimpleParserOnlySyntax {
    private final List<Token> tokens;
    private int pos = 0;

    public KimpleParserOnlySyntax(List<Token> tokens) {
        this.tokens = tokens;
    }

    private void log(String message) {
        Token curr = current();
        String tokenInfo = curr.type() == TokenType.EOF
                ? "EOF"
                : String.format("('%s', '%s')", curr.value(), curr.type().name().toLowerCase());
        System.out.printf("parseToken: В рядку %d токен %s %s%n",
                curr.line(), tokenInfo, message);
    }

    private Token current() {
        return pos < tokens.size() ? tokens.get(pos) : new Token(TokenType.EOF, null, -1, -1);
    }

    private Token peek() {
        return pos + 1 < tokens.size() ? tokens.get(pos + 1) : new Token(TokenType.EOF, null, -1, -1);
    }

    private void advance() {
        pos++;
    }

    private void expect(TokenType type) {
        log("очікується " + type);
        Token curr = current();
        if (curr.type() != type) {
            throw new SyntaxException("Expected " + type + ", got " + curr.type() +
                    " at line " + curr.line() + ", col " + curr.column());
        }
        advance();
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
        if (curr.type() == TokenType.KEYWORD && keyword.equals(curr.value())) {
            log("знайдено KEYWORD '" + keyword + "'");
            advance();
            return true;
        }
        return false;
    }

    private void expectKeyword(String keyword) {
        log("очікується KEYWORD '" + keyword + "'");
        Token curr = current();
        if (curr.type() != TokenType.KEYWORD || !keyword.equals(curr.value())) {
            throw new SyntaxException("Expected keyword '" + keyword + "'");
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
        expect(TokenType.IDENT);
        expect(TokenType.COLON);
        parseType();
        expect(TokenType.ASSIGN_OP);
        parseExpression();
        match(TokenType.SEMICOLON);
    }

    private void parseGlobalVarDecl() {
        expect(TokenType.IDENT);
        expect(TokenType.COLON);
        parseType();
        if (match(TokenType.ASSIGN_OP)) {
            parseExpression();
        }
        match(TokenType.SEMICOLON);
    }

    private void parseFunctionDecl() {
        log("parseFunctionDecl() → оголошення функції");
        expect(TokenType.IDENT);
        expect(TokenType.LPAREN);
        if (current().type() != TokenType.RPAREN) {
            parseParamList();
        }
        expect(TokenType.RPAREN);
        if (match(TokenType.COLON)) {
            parseType();
        }
        parseBlock();
    }

    private void parseParamList() {
        expect(TokenType.IDENT);
        expect(TokenType.COLON);
        parseType();
        while (match(TokenType.COMMA)) {
            expect(TokenType.IDENT);
            expect(TokenType.COLON);
            parseType();
        }
    }

    private void parseBlock() {
        log("parseBlock() → вхід у блок {");
        expect(TokenType.LBRACE);
        while (current().type() != TokenType.RBRACE && current().type() != TokenType.EOF) {
            parseStatement();
        }
        expect(TokenType.RBRACE);
        log("parseBlock() → вихід з блоку }");
    }

    private void parseStatement() {
        Token curr = current();
        if (matchKeyword("val")) {
            log("parseStatement() → оголошення константи val");
            parseConstDecl();
        } else if (matchKeyword("var")) {
            log("parseStatement() → оголошення змінної var");
            parseVarDecl();
        } else if (matchKeyword("fun")) {
            log("parseStatement() → вкладена функція fun");
            parseFunctionDecl();
        } else if (matchKeyword("read")) {
            log("parseStatement() → оператор вводу read");
            parseInputStmt();
        } else if (matchKeyword("print")) {
            log("parseStatement() → оператор виводу print");
            parseOutputStmt();
        } else if (matchKeyword("return")) {
            log("parseStatement() → вихід з функції return");
            parseReturnStmt();
        } else if (matchKeyword("if")) {
            log("parseStatement() → умовний оператор if");
            parseIfStmt();
        } else if (matchKeyword("for")) {
            log("parseStatement() → оператор циклу for");
            parseForStmt();
        } else if (matchKeyword("while")) {
            log("parseStatement() → оператор циклу while");
            parseWhileStmt();
        } else if (curr.type() == TokenType.IDENT) {
            if (peek().type() == TokenType.ASSIGN_OP) {
                log("parseStatement() → присвоювання");
                parseAssignment();
            } else {
                log("parseStatement() → виклик функції");
                parseFuncCall();
                match(TokenType.SEMICOLON);
            }
        } else {
            throw new SyntaxException("Unknown operator: " + curr);
        }
    }

    private void parseVarDecl() {
        expect(TokenType.IDENT);
        expect(TokenType.COLON);
        parseType();
        if (match(TokenType.ASSIGN_OP)) {
            parseExpression();
        }
        match(TokenType.SEMICOLON);
    }

    private void parseConstDecl() {
        expect(TokenType.IDENT);
        expect(TokenType.COLON);
        parseType();
        expect(TokenType.ASSIGN_OP);
        parseExpression();
        match(TokenType.SEMICOLON);
    }

    private void parseAssignment() {
        expect(TokenType.IDENT);
        expect(TokenType.ASSIGN_OP);
        parseExpression();
        match(TokenType.SEMICOLON);
    }

    private void parseInputStmt() {
        expect(TokenType.LPAREN);
        expect(TokenType.IDENT);
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
        if (current().type() != TokenType.SEMICOLON) {
            parseExpression();
        }
        match(TokenType.SEMICOLON);
    }

    private void parseIfStmt() {
        expect(TokenType.LPAREN);
        parseExpression();
        expect(TokenType.RPAREN);
        parseBlock();
        if (matchKeyword("else")) {
            parseBlock();
        }
    }

    private void parseForStmt() {
        expect(TokenType.LPAREN);
        expect(TokenType.IDENT);
        expect(TokenType.COLON);
        parseType();
        expectKeyword("in");
        parseExpression();
        expect(TokenType.RANGE_OP);
        parseExpression();
        expect(TokenType.RPAREN);
        parseBlock();
    }

    private void parseWhileStmt() {
        expect(TokenType.LPAREN);
        parseExpression();
        expect(TokenType.RPAREN);
        parseBlock();
    }

    private void parseType() {
        expect(TokenType.TYPE);
    }

    private void parseFuncCall() {
        expect(TokenType.IDENT);
        expect(TokenType.LPAREN);
        if (current().type() != TokenType.RPAREN) {
            parseArgList();
        }
        expect(TokenType.RPAREN);
    }

    private void parseArgList() {
        parseExpression();
        while (match(TokenType.COMMA)) {
            parseExpression();
        }
    }

    private void parseExpression() {
        parseLogicOr();
    }

    private void parseLogicOr() {
        parseLogicAnd();
        while (match(TokenType.OR_OP)) {
            parseLogicAnd();
        }
    }

    private void parseLogicAnd() {
        parseEquality();
        while (match(TokenType.AND_OP)) {
            parseEquality();
        }
    }

    private void parseEquality() {
        parseComparison();
        while (match(TokenType.EQ_OP) || match(TokenType.NEQ_OP)) {
            parseComparison();
        }
    }

    private void parseComparison() {
        parseAddition();
        while (match(TokenType.REL_OP)) {
            parseAddition();
        }
    }

    private void parseAddition() {
        parseMultiplication();
        while (match(TokenType.ADD_OP)) {
            parseMultiplication();
        }
    }

    private void parseMultiplication() {
        parsePower();
        while (match(TokenType.MULT_OP)) {
            parsePower();
        }
    }

    private void parsePower() {
        parseUnary();
        if (match(TokenType.POW_OP)) {
            parsePower();
        }
    }

    private void parseUnary() {
        if (match(TokenType.NOT_OP) || match(TokenType.ADD_OP)) {
            parseUnary();
        } else {
            parsePrimary();
        }
    }

    private void parsePrimary() {
        Token curr = current();

        if (curr.type() == TokenType.INT || curr.type() == TokenType.REAL ||
                curr.type() == TokenType.STRING || curr.type() == TokenType.TRUE ||
                curr.type() == TokenType.FALSE || curr.type() == TokenType.INF) {
            advance();

        } else if (match(TokenType.LPAREN)) {
            parseExpression();
            expect(TokenType.RPAREN);

        } else if (curr.type() == TokenType.IDENT) {
            advance();

            if (current().type() == TokenType.LPAREN) {
                pos--;
                parseFuncCall();
            } else if (current().type() == TokenType.CAST_OP) {
                expect(TokenType.CAST_OP);
                parseType();
            }

        } else {
            throw new SyntaxException("Invalid original expression: " + curr);
        }
    }
}
