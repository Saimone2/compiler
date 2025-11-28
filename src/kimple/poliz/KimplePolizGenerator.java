package kimple.poliz;

import kimple.ast.*;
import java.util.*;

public class KimplePolizGenerator {

    private final Map<String, PolizModule> modules = new LinkedHashMap<>();
    private PolizModule currentModule;
    private final Deque<String> funcStack = new ArrayDeque<>();
    private int labelCounter = 0;

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
            if (o instanceof VarDeclNode v) genVarDecl(v, true);
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

        // Додаємо запис у root-модуль .funcs
        saved.addFunc(fullName, mapType(fn.getReturnType()), fn.getParamNames().size());

        // Додаємо параметри у секцію vars функціонального модуля
        for (int i = 0; i < fn.getParamNames().size(); i++) {
            currentModule.addVar(fn.getParamNames().get(i),
                    mapType(fn.getParamTypes().get(i)));
        }

        // Початок функції
        currentModule.emit(fullName + " label");

        // Генеруємо тіло
        genBlock(fn.getBody());

        // Завершення функції
        currentModule.emit("RET");

        // Зберігаємо модуль функції
        modules.put(fullName, currentModule);

        // Генеруємо вкладені функції
        for (FuncDeclNode nested : fn.getNested()) {
            genFunction(nested);
        }

        funcStack.pop();
        currentModule = saved;
    }

    /* ====================================================================== */
    /*                         DECLARATIONS (VAR / CONST)                      */
    /* ====================================================================== */

    private void genVarDecl(VarDeclNode v, boolean global) {
        String type = mapType(v.getType());
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
        if (s instanceof VarDeclNode v) genVarDecl(v, false);
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

        String Lelse = newLabel();  // наприклад L2
        String Lend  = newLabel();  // наприклад L3

        // 1. Спочатку оголошуємо мітки, які будемо використовувати
        currentModule.emit(Lelse + " label");
        currentModule.emit("JF jf");           // false → на else

        currentModule.emit(Lend + " label");
        currentModule.emit("JMP jump");        // пропустити else

        // 2. Тепер ставимо : colon для міток
        currentModule.emit(Lelse + " label");
        currentModule.emit(": colon");

        if (n.getElseBlock() != null) {
            genBlock(n.getElseBlock());
        }

        currentModule.emit(Lend + " label");
        currentModule.emit(": colon");

        // 3. then-гілка (виконується, якщо умова true)
        genBlock(n.getThenBlock());
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
        currentModule.addVar(f.getVarName(), "int");

        // i = start
        currentModule.emit(f.getVarName() + " l-val");
        genExpression(f.getStartExpr());
        currentModule.emit("= assign_op");

        String Lloop = newLabel();
        String Lend = newLabel();

// LOOP:
        currentModule.addLabel(Lloop, currentModule.currentAddress());
        currentModule.emit(Lloop + " label");
        currentModule.emit(": colon");

// i <= end ?
        currentModule.emit(f.getVarName() + " r-val");
        genExpression(f.getEndExpr());
        currentModule.emit("<= rel_op");

        currentModule.addLabel(Lend, currentModule.currentAddress());
        currentModule.emit(Lend + " label");
        currentModule.emit("JF jf");

// body
        genBlock(f.getBody());

// i = i + 1
        currentModule.emit(f.getVarName() + " l-val");
        currentModule.emit(f.getVarName() + " r-val");
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

    private void genExpression(ExpressionNode e) {
        if (e instanceof LiteralNode l) genLiteral(l);
        else if (e instanceof IdentNode id) {
            currentModule.emit(id.getName() + " r-val");
        }
        else if (e instanceof BinaryOpNode b) genBinary(b);
        else if (e instanceof UnaryOpNode u) genUnary(u);
        else if (e instanceof FuncCallExprNode fc) genFuncCall(fc);
        else if (e instanceof CastNode c) genCast(c);
        else throw new RuntimeException("Unknown expression: " + e);
    }

    private void genLiteral(LiteralNode l) {
        if (l.getType().equals("String")) {
            String val = l.getValue();
            if (!val.startsWith("\"")) val = "\"" + val;
            if (!val.endsWith("\"")) val = val + "\"";
            currentModule.emit(val + " string");
            return;
        }
        currentModule.emit(l.getValue() + " " + mapType(l.getType()));
    }

    private void genUnary(UnaryOpNode u) {
        genExpression(u.getExpr());
        if (u.getOp().equals("!"))
            currentModule.emit("NOT bool_op");
        else if (u.getOp().equals("-"))
            currentModule.emit("NEG math_op");
    }

    private void genBinary(BinaryOpNode b) {
        genExpression(b.getLeft());
        genExpression(b.getRight());

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

    private void genFuncCall(FuncCallExprNode c) {
        for (ExpressionNode arg : c.getArgs()) {
            genExpression(arg);
        }

        String full = funcFullName(c.getName());
        currentModule.emit(full + " CALL");
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