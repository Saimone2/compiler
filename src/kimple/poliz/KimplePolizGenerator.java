package kimple.poliz;

import kimple.ast.*;

import java.util.ArrayList;
import java.util.List;

public class KimplePolizGenerator {

    private PolizModule module;
    private int lblCounter = 0;

    public PolizModule generate(ProgramNode program) {
        module = new PolizModule();
        lblCounter = 0;

        // top-level: vars, consts, function decls and statements
        for (ASTNode top : program.getTopLevel()) {
            if (top instanceof VarDeclNode) {
                genVarDecl((VarDeclNode) top, true); // treat top-level as global
            } else if (top instanceof FuncDeclNode) {
                genFunction((FuncDeclNode) top);
            } else if (top instanceof StatementNode) {
                genStatement((StatementNode) top);
            } else {
                throw new RuntimeException("Unknown top-level node: " + top);
            }
        }

        return module;
    }

    private String newLabel() { return "L" + (lblCounter++); }

    /* -------------------- Declarations / Functions -------------------- */

    private void genVarDecl(VarDeclNode node, boolean isGlobal) {
        if (isGlobal) {
            // allocate global (implicit)
            module.emit("ALLOC_GLOBAL", node.getName());
            if (node.getInit() != null) {
                genExpression(node.getInit());
                module.emit("STORE_GLOBAL", node.getName());
            }
        } else {
            // local in function/block
            module.emit("ALLOC_LOCAL", node.getName());
            if (node.getInit() != null) {
                genExpression(node.getInit());
                module.emit("STORE_LOCAL", node.getName());
            }
        }
    }

    private void genFunction(FuncDeclNode f) {
        // function label
        module.emit("LABEL", "fun_" + f.getName());
        // enter frame (create locals area)
        module.emit("ENTER", Integer.toString(f.getParamNames().size()));
        // declare params as locals implicitly — we assume caller pushes args, ENTER will create frame and assign
        // generate body
        genBlock(f.getBody());
        // ensure return
        module.emit("PUSH", "0"); // default return 0/Unit
        module.emit("RET");
        module.emit("END_FUNC", f.getName());
    }

    /* -------------------- Statements -------------------- */

    private void genStatement(StatementNode s) {
        if (s instanceof VarDeclNode) genVarDecl((VarDeclNode) s, false);
        else if (s instanceof AssignmentNode) genAssignment((AssignmentNode) s);
        else if (s instanceof InputNode) genInput((InputNode) s);
        else if (s instanceof OutputNode) genOutput((OutputNode) s);
        else if (s instanceof ReturnNode) genReturn((ReturnNode) s);
        else if (s instanceof IfNode) genIf((IfNode) s);
        else if (s instanceof WhileNode) genWhile((WhileNode) s);
        else if (s instanceof ForNode) genFor((ForNode) s);
        else if (s instanceof FuncCallStmtNode) { genFuncCall(((FuncCallStmtNode) s).getCall()); module.emit("POP"); }
        else if (s instanceof BlockNode) genBlock((BlockNode) s);
        else throw new RuntimeException("Unsupported statement: " + s);
    }

    private void genAssignment(AssignmentNode n) {
        genExpression(n.getExpr());
        module.emit("STORE", n.getName());
    }

    private void genInput(InputNode in) {
        module.emit("READ");
        module.emit("STORE", in.getTargetName());
    }

    private void genOutput(OutputNode out) {
        for (ExpressionNode arg : out.getArgs()) {
            genExpression(arg);
            module.emit("PRINT");
        }
    }

    private void genReturn(ReturnNode r) {
        if (r.getExpr() != null) genExpression(r.getExpr());
        else module.emit("PUSH", "0");
        module.emit("RET");
    }

    private void genBlock(BlockNode b) {
        for (StatementNode s : b.getStatements()) genStatement(s);
    }

    /* -------------------- Control flow -------------------- */

    private void genIf(IfNode node) {
        genExpression(node.getCond());
        String elseLbl = newLabel();
        String endLbl = newLabel();
        module.emit("JZ", elseLbl);
        genBlock(node.getThenBlock());
        module.emit("JMP", endLbl);
        module.emit("LABEL", elseLbl);
        if (node.getElseBlock() != null) genBlock(node.getElseBlock());
        module.emit("LABEL", endLbl);
    }

    private void genWhile(WhileNode node) {
        String start = newLabel();
        String end = newLabel();
        module.emit("LABEL", start);
        genExpression(node.getCond());
        module.emit("JZ", end);
        genBlock(node.getBody());
        module.emit("JMP", start);
        module.emit("LABEL", end);
    }

    private void genFor(ForNode f) {
        // i := start
        genExpression(f.getStartExpr());
        module.emit("STORE", f.getVarName());
        String start = newLabel();
        String end = newLabel();
        module.emit("LABEL", start);
        module.emit("LOAD", f.getVarName());
        genExpression(f.getEndExpr());
        module.emit("<=");
        module.emit("JZ", end);
        genBlock(f.getBody());
        // i = i + 1
        module.emit("LOAD", f.getVarName());
        module.emit("PUSH", "1");
        module.emit("+");
        module.emit("STORE", f.getVarName());
        module.emit("JMP", start);
        module.emit("LABEL", end);
    }

    /* -------------------- Expressions -------------------- */

    private void genExpression(ExpressionNode e) {
        if (e instanceof LiteralNode) {
            LiteralNode l = (LiteralNode) e;
            module.emit("PUSH", l.getValue());
        } else if (e instanceof IdentNode) {
            IdentNode id = (IdentNode) e;
            module.emit("LOAD", id.getName());
        } else if (e instanceof BinaryOpNode) {
            BinaryOpNode b = (BinaryOpNode) e;
            // short-circuit for && and ||
            String op = b.getOp();
            if ("&&".equals(op)) {
                String falseLbl = newLabel();
                String end = newLabel();
                genExpression(b.getLeft());
                module.emit("JZ", falseLbl);
                genExpression(b.getRight());
                module.emit("JZ", falseLbl);
                module.emit("PUSH", "1");
                module.emit("JMP", end);
                module.emit("LABEL", falseLbl);
                module.emit("PUSH", "0");
                module.emit("LABEL", end);
                return;
            } else if ("||".equals(op)) {
                String trueLbl = newLabel();
                String end = newLabel();
                genExpression(b.getLeft());
                module.emit("JNZ", trueLbl);
                genExpression(b.getRight());
                module.emit("JNZ", trueLbl);
                module.emit("PUSH", "0");
                module.emit("JMP", end);
                module.emit("LABEL", trueLbl);
                module.emit("PUSH", "1");
                module.emit("LABEL", end);
                return;
            }
            genExpression(b.getLeft());
            genExpression(b.getRight());
            // arithmetic or comparison or other
            switch (op) {
                case "+": module.emit("+"); break;
                case "-": module.emit("-"); break;
                case "*": module.emit("*"); break;
                case "/": module.emit("/"); break;
                case "%": module.emit("%"); break;
                case "==": module.emit("=="); break;
                case "!=": module.emit("!="); break;
                case "<": module.emit("<"); break;
                case "<=": module.emit("<="); break;
                case ">": module.emit(">"); break;
                case ">=": module.emit(">="); break;
                default: module.emit(op); break;
            }
        } else if (e instanceof UnaryOpNode) {
            UnaryOpNode u = (UnaryOpNode) e;
            genExpression(u.getExpr());
            String op = u.getOp();
            if ("!".equals(op)) module.emit("NOT");
            else if ("-".equals(op)) module.emit("NEG");
            else module.emit("UNARY_" + op);
        } else if (e instanceof FuncCallExprNode) {
            genFuncCall((FuncCallExprNode) e);
        } else if (e instanceof CastNode) {
            // cast only after Ident: in AST CastNode has Ident target
            CastNode c = (CastNode) e;
            module.emit("LOAD", c.getTarget().getName());
            module.emit("CAST", c.getToType());
        } else {
            throw new RuntimeException("Unhandled expression node: " + e.getClass());
        }
    }

    private void genFuncCall(FuncCallExprNode c) {
        // push args left-to-right
        for (ExpressionNode a : c.getArgs()) genExpression(a);
        // CALL operand is function label name
        module.emit("CALL", c.getName());
    }
}
