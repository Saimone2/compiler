package kimple.poliz;

import kimple.ast.*;
import java.util.*;

public class KimplePolizGenerator {
    private final Map<String, String> symbolTable = new HashMap<>();
    private final Map<String, PolizModule> modules = new LinkedHashMap<>();
    private PolizModule currentModule;
    private final Deque<String> funcStack = new ArrayDeque<>();
    private int labelCounter = 0;

    // таблиця типів повернення функцій
    private final Map<String, String> functionReturnTypes = new HashMap<>();

    /* ====================================================================== */
    /*                          PUBLIC ENTRY POINT                            */
    /* ====================================================================== */

    public Map<String, PolizModule> generateAll(ProgramNode program) {
        modules.clear();
        currentModule = new PolizModule("program");

        List<FuncDeclNode> funcDecls = new ArrayList<>();
        List<ASTNode> others = new ArrayList<>();

        for (ASTNode top : program.getTopLevel()) {
            if (top instanceof FuncDeclNode f) funcDecls.add(f);
            else others.add(top);
        }

        for (FuncDeclNode f : funcDecls) {
            genFunction(f);
        }
        boolean hasMain = funcDecls.stream().anyMatch(fd -> "main".equals(fd.getName()));
        if (hasMain) {
            String mainFull = funcFullName("main");
            currentModule.emit(mainFull + " CALL");
        }

        for (ASTNode o : others) {
            if (o instanceof VarDeclNode v) genVarDecl(v);
            else if (o instanceof StatementNode s) genStatement(s);
        }

        modules.put("program", currentModule);
        return modules;
    }

    /* ====================================================================== */
    /*                              LABEL UTILS                                */
    /* ====================================================================== */

    private String newLabel() {
        return "L" + (labelCounter++);
    }

    private String getFunctionReturnType(String fullName) {
        String type = functionReturnTypes.get(fullName);
        if (type == null) {
            throw new RuntimeException("Function not found: " + fullName);
        }
        return type;
    }

    private String funcFullName(String name) {
        if (funcStack.isEmpty()) return name;
        return String.join("$", funcStack) + "$" + name;
    }

    /* ====================================================================== */
    /*                               FUNCTIONS                                 */
    /* ====================================================================== */

    private void genFunction(FuncDeclNode fn) {
        String fullName = funcFullName(fn.getName());

        PolizModule saved = currentModule;
        currentModule = new PolizModule(fullName);

        funcStack.push(fn.getName());

        saved.addFunc(fn.getName(), mapType(fn.getReturnType()), fn.getParamNames().size());

        functionReturnTypes.put(fullName, mapType(fn.getReturnType()));

        for (int i = 0; i < fn.getParamNames().size(); i++) {
            String paramName = fn.getParamNames().get(i);
            String paramType = mapType(fn.getParamTypes().get(i));
            currentModule.addVar(paramName, paramType);

            symbolTable.put(paramName, paramType);
        }
        currentModule.emit(fullName + " label");

        genBlock(fn.getBody());
        currentModule.emit("RET");
        modules.put(fullName, currentModule);

        for (FuncDeclNode nested : fn.getNested()) {
            genFunction(nested);
        }

        funcStack.pop();
        currentModule = saved;
    }

    /* ====================================================================== */
    /*                         DECLARATIONS (VAR / CONST)                      */
    /* ====================================================================== */

    private void genVarDecl(VarDeclNode v) {
        String type = mapType(v.getType());
        symbolTable.put(v.getName(), type);
        currentModule.addVar(v.getName(), type);

        if (v.getInit() != null) {
            currentModule.emit(v.getName() + " l-val");
            genExpression(v.getInit());
            currentModule.emit("= assign_op");
        }
    }

    /* ====================================================================== */
    /*                               STATEMENTS                                */
    /* ====================================================================== */

    private void genStatement(StatementNode s) {
        if (s instanceof VarDeclNode v) genVarDecl(v);
        else if (s instanceof AssignmentNode a) genAssignment(a);
        else if (s instanceof InputNode i) genInput(i);
        else if (s instanceof OutputNode o) genOutput(o);
        else if (s instanceof ReturnNode r) genReturn(r);
        else if (s instanceof IfNode i) genIf(i);
        else if (s instanceof WhileNode w) genWhile(w);
        else if (s instanceof ForNode f) genFor(f);
        else if (s instanceof FuncCallStmtNode fc) {
            genFuncCall(fc.getCall());
            currentModule.emit("POP stack_op");
        }
        else if (s instanceof BlockNode b) genBlock(b);
        else if (s instanceof FuncDeclNode fd) {
            genFunction(fd);
        }
        else throw new RuntimeException("Unsupported statement: " + s);
    }

    private void genAssignment(AssignmentNode a) {
        currentModule.emit(a.getName() + " l-val");
        genExpression(a.getExpr());
        currentModule.emit("= assign_op");
    }

    private void genInput(InputNode in) {
        currentModule.emit("IN inp_op");
        currentModule.emit(in.getTargetName() + " l-val");
        currentModule.emit("= assign_op");
    }

    private void genOutput(OutputNode o) {
        for (ExpressionNode e : o.getArgs()) {
            genExpression(e);
            currentModule.emit("OUT out_op");
        }
    }

    private void genReturn(ReturnNode r) {
        if (funcStack.isEmpty()) return; // ігноруємо return у wrapper
        if (r.getExpr() != null) genExpression(r.getExpr());
        currentModule.emit("RET"); // тільки RET, без типів
    }

    private void genBlock(BlockNode b) {
        for (StatementNode s : b.getStatements()) {
            genStatement(s);
        }
    }

    /* ====================================================================== */
    /*                           CONTROL FLOW                                  */
    /* ====================================================================== */

    private void genIf(IfNode n) {
        genExpression(n.getCond());

        if (n.getElseBlock() != null) {
            String elseLabel = newLabel();
            String endLabel  = newLabel();

            currentModule.emit(elseLabel + " label");
            currentModule.emit("JF jf");

            genBlock(n.getThenBlock());

            currentModule.emit(endLabel + " label");
            currentModule.emit("JMP jump");

            currentModule.emit(elseLabel + " label");
            currentModule.emit(": colon");
            genBlock(n.getElseBlock());

            currentModule.emit(endLabel + " label");
            currentModule.emit(": colon");

        } else {
            String endLabel = newLabel();

            currentModule.emit(endLabel + " label");
            currentModule.emit("JF jf");

            genBlock(n.getThenBlock());

            currentModule.emit(endLabel + " label");
            currentModule.emit(": colon");
        }
    }

    private void genWhile(WhileNode n) {
        String Lstart = newLabel();
        String Lend = newLabel();

        currentModule.addLabel(Lstart, currentModule.currentAddress());
        currentModule.emit(Lstart + " label");
        currentModule.emit(": colon");

        genExpression(n.getCond());

        currentModule.emit(Lend + " label");
        currentModule.emit("JF jf");

        genBlock(n.getBody());

        currentModule.emit(Lstart + " label");
        currentModule.emit("JMP jump");

        currentModule.emit(Lend + " label");
        currentModule.emit(": colon");
    }

    private void genFor(ForNode f) {
        String varName = f.getVarName();
        currentModule.addVar(varName, "int");
        symbolTable.put(varName, "int"); // ВАЖЛИВО: додати у symbolTable

        // i = start
        currentModule.emit(varName + " l-val");
        genExpression(f.getStartExpr());
        currentModule.emit("= assign_op");

        String Lloop = newLabel();
        String Lend = newLabel();

        // LOOP:
        currentModule.addLabel(Lloop, currentModule.currentAddress());
        currentModule.emit(Lloop + " label");
        currentModule.emit(": colon");

        // i <= end ?
        currentModule.emit(varName + " r-val");
        genExpression(f.getEndExpr());
        currentModule.emit("<= rel_op");

        currentModule.addLabel(Lend, currentModule.currentAddress());
        currentModule.emit(Lend + " label");
        currentModule.emit("JF jf");

        // body
        genBlock(f.getBody());

        // i = i + 1
        currentModule.emit(varName + " l-val");
        currentModule.emit(varName + " r-val");
        currentModule.emit("1 int");
        currentModule.emit("+ math_op");
        currentModule.emit("= assign_op");

        // JMP → LOOP
        currentModule.emit(Lloop + " label");
        currentModule.emit("JMP jump");

        // END:
        currentModule.addLabel(Lend, currentModule.currentAddress());
        currentModule.emit(Lend + " label");
        currentModule.emit(": colon");
    }


    /* ====================================================================== */
    /*                               EXPRESSIONS                               */
    /* ====================================================================== */

    private String genExpression(ExpressionNode e) {
        if (e instanceof LiteralNode l) {
            return genLiteral(l);
        }
        else if (e instanceof IdentNode id) {
            currentModule.emit(id.getName() + " r-val");
            return getVariableType(id.getName());
        }
        else if (e instanceof BinaryOpNode b) {
            return genBinary(b);
        }
        else if (e instanceof UnaryOpNode u) {
            return genUnary(u);
        }
        else if (e instanceof FuncCallExprNode fc) {
            return genFuncCall(fc);
        }
        else if (e instanceof CastNode c) {
            genCast(c);
            return mapType(c.getToType());
        }
        else {
            throw new RuntimeException("Unknown expression: " + e);
        }
    }

    private String getVariableType(String name) {
        String type = symbolTable.get(name);
        if (type == null) throw new RuntimeException("Unknown variable: " + name);
        return type;
    }

    private String genLiteral(LiteralNode l) {
        if (l.getType().equals("String")) {
            String val = l.getValue();
            if (!val.startsWith("\"")) val = "\"" + val + "\"";
            currentModule.emit(val + " string");
            return "string";
        } else if (l.getType().equals("Boolean")) {
            String val = l.getValue().equalsIgnoreCase("true") ? "true" : "false";
            currentModule.emit(val + " bool");
            return "bool";
        } else {
            currentModule.emit(l.getValue() + " " + mapType(l.getType()));
            return mapType(l.getType());
        }
    }

    private String genUnary(UnaryOpNode u) {
        String type = genExpression(u.getExpr());
        if (u.getOp().equals("-")) {
            if (type.equals("int")) {
                currentModule.emit("NEG math_op");
            } else {
                currentModule.emit("NEG math_op");
            }
        } else if (u.getOp().equals("!")) {
            currentModule.emit("NOT bool_op");
            return "bool";
        }
        return type;
    }

    private String genBinary(BinaryOpNode b) {
        String leftType = genExpression(b.getLeft());
        String rightType = genExpression(b.getRight());

        String op = b.getOp();

        if ((leftType.equals("float") && rightType.equals("int"))) {
            currentModule.emit("i2f conv");
            rightType = "float";
        } else if ((leftType.equals("int") && rightType.equals("float"))) {
            currentModule.emit("SWAP stack_op");
            currentModule.emit("i2f conv");
            currentModule.emit("SWAP stack_op");
            leftType = "float";
        }

        if ("^".equals(op)) {
            if (rightType.equals("int")) {
                currentModule.emit("i2f conv");
            }
            if (leftType.equals("int")) {
                currentModule.emit("SWAP stack_op");
                currentModule.emit("i2f conv");
                currentModule.emit("SWAP stack_op");
            }
            currentModule.emit("^ pow_op");
            return "float";
        }

        switch (b.getOp()) {
            case "+" -> currentModule.emit("+ math_op");
            case "-" -> currentModule.emit("- math_op");
            case "*" -> currentModule.emit("* math_op");
            case "/" -> currentModule.emit("/ math_op");
            case "%" -> currentModule.emit("% math_op");
            case "^" -> currentModule.emit("^ pow_op");

            case "==" -> currentModule.emit("== rel_op");
            case "!=" -> currentModule.emit("!= rel_op");
            case "<" -> currentModule.emit("< rel_op");
            case ">" -> currentModule.emit("> rel_op");
            case "<=" -> currentModule.emit("<= rel_op");
            case ">=" -> currentModule.emit(">= rel_op");

            case "&&" -> currentModule.emit("AND bool_op");
            case "||" -> currentModule.emit("OR bool_op");

            default -> throw new RuntimeException("Unknown operator: " + b.getOp());
        }

        return (leftType.equals("float") || rightType.equals("float")) ? "float" : "int";
    }

    private void genCast(CastNode c) {
        genExpression(c.getTarget());
        switch (c.getToType()) {
            case "Int" -> currentModule.emit("f2i conv");
            case "Double" -> currentModule.emit("i2f conv");
            case "String" -> currentModule.emit("i2s conv");
            case "Boolean" -> currentModule.emit("i2b conv");
        }
    }

    private String genFuncCall(FuncCallExprNode c) {
        for (ExpressionNode arg : c.getArgs()) {
            genExpression(arg);
        }
        String full = funcFullName(c.getName());
        currentModule.emit(full + " CALL");
        return getFunctionReturnType(full);
    }

    /* ====================================================================== */
    /*                                TYPE MAP                                 */
    /* ====================================================================== */

    private String mapType(String t) {
        String tl = t == null ? "" : t.toLowerCase();
        return switch (tl) {
            case "int"     -> "int";
            case "double"  -> "float";
            case "string"  -> "string";
            case "boolean" -> "bool";
            case "unit", "void" -> "void";
            default -> throw new RuntimeException("Unknown type: " + t);
        };
    }
}