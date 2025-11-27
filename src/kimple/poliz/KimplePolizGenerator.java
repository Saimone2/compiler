package kimple.poliz;

import kimple.ast.*;
import java.util.*;


public class KimplePolizGenerator {

    private final Map<String, PolizModule> funcModules = new LinkedHashMap<>();
    private PolizModule currentModule;
    private final Deque<String> funcStack = new ArrayDeque<>();
    private int lblCounter = 0;

    public Map<String, PolizModule> generateAll(ProgramNode program) {
        funcModules.clear();
        lblCounter = 0;

        currentModule = new PolizModule();

        for (ASTNode top : program.getTopLevel()) {
            if (top instanceof FuncDeclNode f) {
                genFunction(f);
            } else if (top instanceof VarDeclNode v) {
                genVarDecl(v, true);
            } else {
                genStatement((StatementNode) top);
            }
        }

        funcModules.put("MAIN", currentModule);
        return funcModules;
    }

    private String newLabel() { return "L" + (lblCounter++); }

    private String buildFuncName(String name) {
        if (funcStack.isEmpty()) return name;
        return String.join("$", funcStack) + "$" + name;
    }

    /* ==================== FUNCTIONS ==================== */
    private void genFunction(FuncDeclNode f) {
        String fullName = buildFuncName(f.getName());

        PolizModule old = currentModule;
        currentModule = new PolizModule();

        funcStack.push(f.getName());

        currentModule.emit("LABEL", "fun_" + fullName);
        currentModule.emit("ENTER", Integer.toString(f.getParamNames().size()));

        genBlock(f.getBody());

        currentModule.emit("PUSH", "0");
        currentModule.emit("RET");

        funcModules.put(fullName, currentModule);
        for (FuncDeclNode nested : f.getNested()) {
            genFunction(nested);
        }

        funcStack.pop();
        currentModule = old;
    }

    /* ==================== DECLARATIONS ==================== */
    private void genVarDecl(VarDeclNode node, boolean isGlobal) {
        if (isGlobal)
            currentModule.emit("ALLOC_GLOBAL", node.getName());
        else
            currentModule.emit("ALLOC_LOCAL", node.getName());

        if (node.getInit() != null) {
            genExpression(node.getInit());
            currentModule.emit(
                    isGlobal ? "STORE_GLOBAL" : "STORE_LOCAL",
                    node.getName()
            );
        }
    }

    /* ==================== STATEMENTS ==================== */
    private void genStatement(StatementNode s) {
        if (s instanceof VarDeclNode v) genVarDecl(v, false);
        else if (s instanceof AssignmentNode a) genAssignment(a);
        else if (s instanceof InputNode i) genInput(i);
        else if (s instanceof OutputNode o) genOutput(o);
        else if (s instanceof ReturnNode r) genReturn(r);
        else if (s instanceof IfNode i) genIf(i);
        else if (s instanceof WhileNode w) genWhile(w);
        else if (s instanceof ForNode f) genFor(f);
        else if (s instanceof FuncCallStmtNode call) {
            genFuncCall(call.getCall());
            currentModule.emit("POP");
        }
        else if (s instanceof BlockNode b) genBlock(b);
        else if (s instanceof FuncDeclNode fd) {
            genFunction(fd);
        }
        else throw new RuntimeException("Unsupported stmt: " + s);
    }

    private void genAssignment(AssignmentNode n) {
        genExpression(n.getExpr());
        currentModule.emit("STORE", n.getName());
    }

    private void genInput(InputNode in) {
        currentModule.emit("READ");
        currentModule.emit("STORE", in.getTargetName());
    }

    private void genOutput(OutputNode out) {
        for (ExpressionNode arg : out.getArgs()) {
            genExpression(arg);
            currentModule.emit("PRINT");
        }
    }

    private void genReturn(ReturnNode r) {
        if (r.getExpr() != null) genExpression(r.getExpr());
        else currentModule.emit("PUSH", "0");

        currentModule.emit("RET");
    }

    private void genBlock(BlockNode b) {
        for (StatementNode s : b.getStatements())
            genStatement(s);
    }

    /* ==================== CONTROL FLOW ==================== */
    private void genIf(IfNode node) {
        genExpression(node.getCond());
        String elseLbl = newLabel();
        String endLbl = newLabel();

        currentModule.emit("JZ", elseLbl);
        genBlock(node.getThenBlock());
        currentModule.emit("JMP", endLbl);
        currentModule.emit("LABEL", elseLbl);

        if (node.getElseBlock() != null)
            genBlock(node.getElseBlock());

        currentModule.emit("LABEL", endLbl);
    }

    private void genWhile(WhileNode node) {
        String start = newLabel();
        String end = newLabel();

        currentModule.emit("LABEL", start);
        genExpression(node.getCond());
        currentModule.emit("JZ", end);

        genBlock(node.getBody());

        currentModule.emit("JMP", start);
        currentModule.emit("LABEL", end);
    }

    private void genFor(ForNode f) {
        genExpression(f.getStartExpr());
        currentModule.emit("STORE", f.getVarName());

        String loop = newLabel();
        String end = newLabel();

        currentModule.emit("LABEL", loop);

        currentModule.emit("LOAD", f.getVarName());
        genExpression(f.getEndExpr());
        currentModule.emit("<=");
        currentModule.emit("JZ", end);

        genBlock(f.getBody());

        currentModule.emit("LOAD", f.getVarName());
        currentModule.emit("PUSH", "1");
        currentModule.emit("+");
        currentModule.emit("STORE", f.getVarName());

        currentModule.emit("JMP", loop);
        currentModule.emit("LABEL", end);
    }

    /* ==================== EXPRESSIONS ==================== */
    private void genExpression(ExpressionNode e) {
        if (e instanceof LiteralNode l) {
            currentModule.emit("PUSH", l.getValue());
        }
        else if (e instanceof IdentNode id) {
            currentModule.emit("LOAD", id.getName());
        }
        else if (e instanceof BinaryOpNode b) {
            genBinary(b);
        }
        else if (e instanceof UnaryOpNode u) {
            genExpression(u.getExpr());
            if (u.getOp().equals("!")) currentModule.emit("NOT");
            else if (u.getOp().equals("-")) currentModule.emit("NEG");
        }
        else if (e instanceof FuncCallExprNode c) {
            genFuncCall(c);
        }
        else if (e instanceof CastNode c) {
            genExpression(c.getTarget());
            currentModule.emit("CAST", c.getToType());
        }
        else {
            throw new RuntimeException("Unknown expr: " + e);
        }
    }

    private void genBinary(BinaryOpNode b) {
        genExpression(b.getLeft());
        genExpression(b.getRight());
        currentModule.emit(b.getOp());
    }

    private void genFuncCall(FuncCallExprNode c) {
        for (ExpressionNode a : c.getArgs())
            genExpression(a);
        currentModule.emit("CALL", c.getName());
    }
}
