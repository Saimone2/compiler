package kimple.parser;

import kimple.lexer.Token;
import kimple.lexer.TokenType;
import kimple.ast.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AstParser {
    private final List<Token> tokens;
    private int pos = 0;
    private final Deque<FuncDeclNode> funcStack = new ArrayDeque<>();

    public AstParser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token current() {
        return pos < tokens.size() ? tokens.get(pos) : new Token(TokenType.EOF, null, -1, -1);
    }

    private Token peek() {
        return pos + 1 < tokens.size() ? tokens.get(pos + 1) : new Token(TokenType.EOF, null, -1, -1);
    }

    private void advance() { pos++; }

    private Token expect(TokenType expected) {
        Token t = current();
        if (t.type() != expected) {
            throw new SyntaxException("Expected " + expected + ", got " + t.type() + " at line " + t.line());
        }
        advance();
        return t;
    }

    private boolean match(TokenType type) {
        if (current().type() == type) {
            advance();
            return true;
        }
        return false;
    }

    private boolean matchKeyword(String value) {
        Token t = current();
        if (t.type() == TokenType.KEYWORD && t.value().equals(value)) {
            advance();
            return true;
        }
        return false;
    }

    public ProgramNode parse() {
        ProgramNode program = new ProgramNode();
        while (current().type() != TokenType.EOF) {
            ASTNode top = parseTopLevel();
            if (top != null) program.addTopLevel(top);
        }
        expect(TokenType.EOF);
        return program;
    }

    private ASTNode parseTopLevel() {
        if (matchKeyword("fun")) {
            return parseFunctionDecl();
        } else if (matchKeyword("val")) {
            return parseGlobalConstDecl();
        } else if (matchKeyword("var")) {
            return parseGlobalVarDecl();
        } else {
            return parseStatement();
        }
    }

    private VarDeclNode parseGlobalVarDecl() {
        Token name = expect(TokenType.IDENT);
        expect(TokenType.COLON);
        Token typeTok = expect(TokenType.TYPE);
        ExpressionNode init = null;
        if (match(TokenType.ASSIGN_OP)) {
            init = parseExpression();
        }
        match(TokenType.SEMICOLON);
        return new VarDeclNode(name.value(), typeTok.value(), init, false, name.line());
    }

    private VarDeclNode parseGlobalConstDecl() {
        Token name = expect(TokenType.IDENT);
        expect(TokenType.COLON);
        Token typeTok = expect(TokenType.TYPE);
        expect(TokenType.ASSIGN_OP);
        ExpressionNode init = parseExpression();
        match(TokenType.SEMICOLON);
        return new VarDeclNode(name.value(), typeTok.value(), init, true, name.line());
    }

    private FuncDeclNode parseFunctionDecl() {
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
            Token rt = expect(TokenType.TYPE);
            returnType = rt.value();
        }

        FuncDeclNode fn = new FuncDeclNode(funcName, paramNames, paramTypes, returnType, null, nameTok.line());
        if (!funcStack.isEmpty()) {
            funcStack.peek().addNested(fn);
        }

        funcStack.push(fn);
        BlockNode body = parseBlock();
        fn.setBody(body);
        funcStack.pop();

        return fn;
    }

    private StatementNode parseStatement() {
        Token t = current();
        if (t.type() == TokenType.KEYWORD) {
            return switch (t.value()) {
                case "val" -> { advance(); yield parseVarDecl(true); }
                case "var" -> { advance(); yield parseVarDecl(false); }
                case "fun" -> { advance(); yield parseFunctionDecl(); }
                case "read" -> { advance(); yield parseInputStmt(); }
                case "print" -> { advance(); yield parseOutputStmt(); }
                case "return" -> { advance(); yield parseReturnStmt(); }
                case "if" -> { advance(); yield parseIfStmt(); }
                case "for" -> { advance(); yield parseForStmt(); }
                case "while" -> { advance(); yield parseWhileStmt(); }
                default -> throw new SyntaxException("Unexpected keyword " + t.value() + " at line " + t.line());
            };
        } else if (t.type() == TokenType.IDENT) {
            if (peek().type() == TokenType.ASSIGN_OP) {
                return parseAssignment();
            } else {
                FuncCallExprNode call = parseFuncCallExpr();
                match(TokenType.SEMICOLON);
                return new FuncCallStmtNode(call, t.line());
            }
        } else if (t.type() == TokenType.LBRACE) {
            return parseBlock();
        } else {
            throw new SyntaxException("Unexpected token in statement: " + t.type() + " at line " + t.line());
        }
    }

    private VarDeclNode parseVarDecl(boolean isConst) {
        Token nameTok = expect(TokenType.IDENT);
        expect(TokenType.COLON);
        Token typeTok = expect(TokenType.TYPE);
        ExpressionNode init = null;
        if (match(TokenType.ASSIGN_OP)) {
            init = parseExpression();
        }
        match(TokenType.SEMICOLON);
        return new VarDeclNode(nameTok.value(), typeTok.value(), init, isConst, nameTok.line());
    }

    private AssignmentNode parseAssignment() {
        Token nameTok = expect(TokenType.IDENT);
        expect(TokenType.ASSIGN_OP);
        ExpressionNode expr = parseExpression();
        match(TokenType.SEMICOLON);
        return new AssignmentNode(nameTok.value(), expr, nameTok.line());
    }

    private InputNode parseInputStmt() {
        expect(TokenType.LPAREN);
        Token var = expect(TokenType.IDENT);
        expect(TokenType.RPAREN);
        match(TokenType.SEMICOLON);
        return new InputNode(var.value(), var.line());
    }

    private OutputNode parseOutputStmt() {
        expect(TokenType.LPAREN);
        OutputNode out = new OutputNode(current().line());
        if (current().type() != TokenType.RPAREN) {
            List<ExpressionNode> args = parseArgListExpr();
            for (ExpressionNode e : args) out.addArg(e);
        }
        expect(TokenType.RPAREN);
        match(TokenType.SEMICOLON);
        return out;
    }

    private List<ExpressionNode> parseArgListExpr() {
        List<ExpressionNode> args = new ArrayList<>();
        args.add(parseExpression());
        while (match(TokenType.COMMA)) {
            args.add(parseExpression());
        }
        return args;
    }

    private ReturnNode parseReturnStmt() {
        ExpressionNode expr = null;
        if (current().type() != TokenType.SEMICOLON) {
            expr = parseExpression();
        }
        match(TokenType.SEMICOLON);
        return new ReturnNode(expr, current().line());
    }

    private IfNode parseIfStmt() {
        expect(TokenType.LPAREN);
        ExpressionNode cond = parseExpression();
        expect(TokenType.RPAREN);
        BlockNode thenBlock = parseBlock();
        BlockNode elseBlock = null;
        if (matchKeyword("else")) {
            elseBlock = parseBlock();
        }
        return new IfNode(cond, thenBlock, elseBlock, cond.getLine());
    }

    private ForNode parseForStmt() {
        expect(TokenType.LPAREN);
        Token varTok = expect(TokenType.IDENT);
        expect(TokenType.COLON);
        Token varType = expect(TokenType.TYPE);
        Token inTok = current();
        if (inTok.type() != TokenType.KEYWORD || !"in".equals(inTok.value())) {
            throw new SyntaxException("Expected 'in' keyword in for-loop at line " + inTok.line());
        }
        advance();
        ExpressionNode start = parseExpression();
        expect(TokenType.RANGE_OP); // ".."
        ExpressionNode end = parseExpression();
        expect(TokenType.RPAREN);
        BlockNode body = parseBlock();
        return new ForNode(varTok.value(), varType.value(), start, end, body, varTok.line());
    }

    private WhileNode parseWhileStmt() {
        expect(TokenType.LPAREN);
        ExpressionNode cond = parseExpression();
        expect(TokenType.RPAREN);
        BlockNode body = parseBlock();
        return new WhileNode(cond, body, cond.getLine());
    }

    private BlockNode parseBlock() {
        Token lb = expect(TokenType.LBRACE);
        BlockNode block = new BlockNode(lb.line());
        while (current().type() != TokenType.RBRACE && current().type() != TokenType.EOF) {
            StatementNode s = parseStatement();
            block.addStatement(s);
        }
        expect(TokenType.RBRACE);
        return block;
    }

    private FuncCallExprNode parseFuncCallExpr() {
        Token name = expect(TokenType.IDENT);
        String funcName = name.value();
        expect(TokenType.LPAREN);
        List<ExpressionNode> args = new ArrayList<>();
        if (current().type() != TokenType.RPAREN) {
            args.add(parseExpression());
            while (match(TokenType.COMMA)) {
                args.add(parseExpression());
            }
        }
        expect(TokenType.RPAREN);
        return new FuncCallExprNode(funcName, args, name.line());
    }

    private ExpressionNode parseExpression() {
        return parseLogicOr();
    }

    private ExpressionNode parseLogicOr() {
        ExpressionNode left = parseLogicAnd();
        while (current().type() == TokenType.OR_OP) {
            Token op = current(); advance();
            ExpressionNode right = parseLogicAnd();
            left = new BinaryOpNode("||", left, right, op.line());
        }
        return left;
    }

    private ExpressionNode parseLogicAnd() {
        ExpressionNode left = parseEquality();
        while (current().type() == TokenType.AND_OP) {
            Token op = current(); advance();
            ExpressionNode right = parseEquality();
            left = new BinaryOpNode("&&", left, right, op.line());
        }
        return left;
    }

    private ExpressionNode parseEquality() {
        ExpressionNode left = parseComparison();
        while (current().type() == TokenType.EQ_OP || current().type() == TokenType.NEQ_OP) {
            Token op = current(); advance();
            String opname = op.type() == TokenType.EQ_OP ? "==" : "!=";
            ExpressionNode right = parseComparison();
            left = new BinaryOpNode(opname, left, right, op.line());
        }
        return left;
    }

    private ExpressionNode parseComparison() {
        ExpressionNode left = parseAddition();
        while (current().type() == TokenType.REL_OP) {
            Token op = current(); advance();
            String opname = op.value();
            ExpressionNode right = parseAddition();
            left = new BinaryOpNode(opname, left, right, op.line());
        }
        return left;
    }

    private ExpressionNode parseAddition() {
        ExpressionNode left = parseMultiplication();
        while (current().type() == TokenType.ADD_OP) {
            Token op = current(); advance();
            String opname = op.value(); // "+" or "-"
            ExpressionNode right = parseMultiplication();
            left = new BinaryOpNode(opname, left, right, op.line());
        }
        return left;
    }

    private ExpressionNode parseMultiplication() {
        ExpressionNode left = parsePower();
        while (current().type() == TokenType.MULT_OP) {
            Token op = current(); advance();
            String opname = op.value(); // "*", "/", "%"
            ExpressionNode right = parsePower();
            left = new BinaryOpNode(opname, left, right, op.line());
        }
        return left;
    }

    private ExpressionNode parsePower() {
        ExpressionNode base = parseUnary();
        if (current().type() == TokenType.POW_OP) {
            Token op = current(); advance();
            ExpressionNode exponent = parsePower();
            return new BinaryOpNode("^", base, exponent, op.line());
        }
        return base;
    }

    private ExpressionNode parseUnary() {
        Token t = current();
        if (t.type() == TokenType.NOT_OP) {
            advance();
            ExpressionNode e = parseUnary();
            return new UnaryOpNode("!", e, t.line());
        }
        if (t.type() == TokenType.ADD_OP) {
            advance();
            return parseUnary();
        }
        if (t.type() == TokenType.MULT_OP) {
            advance();
            ExpressionNode e = parseUnary();
            return new UnaryOpNode("-", e, t.line());
        }
        return parsePrimary();
    }

    private ExpressionNode parsePrimary() {
        Token curr = current();
        switch (curr.type()) {
            case INT -> {
                advance();
                return new LiteralNode(curr.value(), "Int", curr.line());
            }
            case REAL, INF -> {
                advance();
                return new LiteralNode(curr.value(), "Double", curr.line());
            }
            case STRING -> {
                advance();
                return new LiteralNode(curr.value(), "String", curr.line());
            }
            case TRUE -> {
                advance();
                return new LiteralNode(curr.value(), "Boolean", curr.line());
            }
            case FALSE -> {
                advance();
                return new LiteralNode(curr.value(), "Boolean", curr.line());
            }
            case IDENT -> {
                Token idTok = curr;
                advance();
                if (current().type() == TokenType.LPAREN) {
                    pos--;
                    return parseFuncCallExpr();
                }
                if (current().type() == TokenType.CAST_OP) {
                    advance();
                    Token typeTok = expect(TokenType.TYPE);
                    IdentNode id = new IdentNode(idTok.value(), idTok.line());
                    return new CastNode(id, typeTok.value(), idTok.line());
                }
                return new IdentNode(idTok.value(), idTok.line());
            }
            case LPAREN -> {
                advance();
                ExpressionNode e = parseExpression();
                expect(TokenType.RPAREN);
                return e;
            }
            default -> throw new SyntaxException("Invalid primary token: " + curr.type() + " at line " + curr.line());
        }
    }

    public static class SyntaxException extends RuntimeException {
        public SyntaxException(String msg) { super(msg); }
    }
}
