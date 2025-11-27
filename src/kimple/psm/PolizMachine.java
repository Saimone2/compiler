package kimple.psm;

import kimple.poliz.PolizInstruction;
import kimple.poliz.PolizModule;

import java.util.*;

public class PolizMachine {

    private final Map<String, PolizModule> modules;
    private PolizModule currentModule;
    private List<PolizInstruction> instrs;

    private final Deque<Object> stack = new ArrayDeque<>();
    private final Map<String, Object> globals = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    private final Deque<Frame> callStack = new ArrayDeque<>();

    private static class Frame {
        public final PolizModule module;
        public final List<PolizInstruction> instrs;
        public final Map<String, Object> locals = new HashMap<>();
        public int pc;
        public final int retValueAddr;

        Frame(PolizModule module, List<PolizInstruction> instrs, int pc, int retValueAddr) {
            this.module = module;
            this.instrs = instrs;
            this.pc = pc;
            this.retValueAddr = retValueAddr;
        }
    }

    public PolizMachine(Map<String, PolizModule> modules) {
        this.modules = modules;
        this.currentModule = modules.get("MAIN");

        if (currentModule == null)
            throw new RuntimeException("MAIN module not found");

        this.instrs = currentModule.resolveLabels();
    }

    public void execute() {
        executeModule(currentModule);
    }

    private void executeModule(PolizModule module) {

        this.currentModule = module;
        this.instrs = module.resolveLabels();

        Frame frame = new Frame(module, instrs, 0, -1);
        callStack.push(frame);

        runLoop();
    }

    private void runLoop() {

        while (!callStack.isEmpty()) {

            Frame frame = callStack.peek();

            if (frame.pc >= frame.instrs.size()) {
                callStack.pop();
                if (!callStack.isEmpty()) continue;
                break;
            }

            PolizInstruction ins = frame.instrs.get(frame.pc);
            String op = ins.opcode;
            String arg = ins.operand;

            switch (op) {
                case "PUSH" -> {
                    stack.push(parseLiteral(arg));
                    frame.pc++;
                }

                case "LOAD" -> {
                    stack.push(loadVar(arg));
                    frame.pc++;
                }

                case "STORE" -> {
                    Object v = stack.pop();
                    storeVar(arg, v);
                    frame.pc++;
                }

                case "ALLOC_GLOBAL" -> {
                    globals.put(arg, 0);
                    frame.pc++;
                }

                case "ALLOC_LOCAL" -> {
                    frame.locals.put(arg, 0);
                    frame.pc++;
                }

                case "STORE_LOCAL" -> {
                    Object v = stack.pop();
                    frame.locals.put(arg, v);
                    frame.pc++;
                }

                case "STORE_GLOBAL" -> {
                    Object v = stack.pop();
                    globals.put(arg, v);
                    frame.pc++;
                }

                case "READ" -> {
                    String line = scanner.nextLine();
                    stack.push(line);
                    frame.pc++;
                }

                case "PRINT" -> {
                    System.out.print(stack.pop());
                    frame.pc++;
                }

                case "+", "-", "*", "/", "%", "==", "!=", "<", "<=", ">", ">=" -> {
                    evalBinary(op);
                    frame.pc++;
                }

                case "NOT" -> {
                    boolean v = !isTruthy(stack.pop());
                    stack.push(v ? 1 : 0);
                    frame.pc++;
                }

                case "NEG" -> {
                    Number a = asNumber(stack.pop());
                    stack.push(a instanceof Double ? -a.doubleValue() : -a.intValue());
                    frame.pc++;
                }

                case "JMP" -> {
                    frame.pc = jumpTo(arg, frame.module);
                }

                case "JZ" -> {
                    Object cond = stack.pop();
                    if (!isTruthy(cond)) frame.pc = jumpTo(arg, frame.module);
                    else frame.pc++;
                }

                case "JNZ" -> {
                    Object cond2 = stack.pop();
                    if (isTruthy(cond2)) frame.pc = jumpTo(arg, frame.module);
                    else frame.pc++;
                }

                case "CALL" -> {
                    callFunction(arg, frame);
                }

                case "RET" -> {
                    returnFromFunction();
                }

                case "POP" -> {
                    stack.pop();
                    frame.pc++;
                }

                default -> frame.pc++;
            }
        }
    }

    private void callFunction(String fname, Frame caller) {
        PolizModule fmod = modules.get("fun_" + fname);
        if (fmod == null)
            fmod = modules.get(fname);

        if (fmod == null)
            throw new RuntimeException("Function not found: " + fname);

        List<PolizInstruction> code = fmod.resolveLabels();
        int entry = fmod.labelMap.get("fun_" + fname);

        Frame newFrame = new Frame(fmod, code, entry, caller.pc + 1);
        callStack.push(newFrame);
    }

    private void returnFromFunction() {
        Frame fr = callStack.pop();
        if (callStack.isEmpty()) {
            return;
        }

        Frame caller = callStack.peek();
        caller.pc = fr.retValueAddr;
    }

    private int jumpTo(String label, PolizModule mod) {
        Integer pc = mod.labelMap.get(label);
        if (pc == null)
            throw new RuntimeException("Unknown label: " + label);
        return pc;
    }

    private Object loadVar(String name) {
        if (!callStack.isEmpty()) {
            Frame fr = callStack.peek();
            if (fr.locals.containsKey(name)) return fr.locals.get(name);
        }
        return globals.getOrDefault(name, 0);
    }

    private void storeVar(String name, Object value) {
        if (!callStack.isEmpty()) {
            Frame fr = callStack.peek();
            if (fr.locals.containsKey(name)) {
                fr.locals.put(name, value);
                return;
            }
        }
        globals.put(name, value);
    }

    private void evalBinary(String op) {
        Object rb = stack.pop();
        Object ra = stack.pop();

        switch (op) {
            case "+" -> stack.push(asNumber(ra).doubleValue() + asNumber(rb).doubleValue());
            case "-" -> stack.push(asNumber(ra).doubleValue() - asNumber(rb).doubleValue());
            case "*" -> stack.push(asNumber(ra).doubleValue() * asNumber(rb).doubleValue());
            case "/" -> stack.push(asNumber(ra).doubleValue() / asNumber(rb).doubleValue());
            case "%" -> stack.push(asNumber(ra).intValue() % asNumber(rb).intValue());
            case "==" -> stack.push(ra.equals(rb) ? 1 : 0);
            case "!=" -> stack.push(!ra.equals(rb) ? 1 : 0);
            case "<" -> stack.push(toDouble(ra) < toDouble(rb) ? 1 : 0);
            case "<=" -> stack.push(toDouble(ra) <= toDouble(rb) ? 1 : 0);
            case ">" -> stack.push(toDouble(ra) > toDouble(rb) ? 1 : 0);
            case ">=" -> stack.push(toDouble(ra) >= toDouble(rb) ? 1 : 0);
        }
    }

    private static Number asNumber(Object o) {
        if (o instanceof Number) return (Number) o;
        try { return Integer.parseInt(o.toString()); }
        catch (Exception e) { return Double.parseDouble(o.toString()); }
    }

    private static double toDouble(Object o) {
        if (o instanceof Number) return ((Number) o).doubleValue();
        return Double.parseDouble(o.toString());
    }

    private static boolean isTruthy(Object o) {
        if (o == null) return false;
        if (o instanceof Boolean) return (Boolean) o;
        if (o instanceof Number) return ((Number) o).doubleValue() != 0.0;
        if (o instanceof String) return !((String) o).isEmpty();
        return true;
    }

    private Object parseLiteral(String lit) {
        if (lit == null) return 0;

        if (lit.startsWith("\"") && lit.endsWith("\""))
            return lit.substring(1, lit.length() - 1);

        if ("true".equals(lit)) return 1;
        if ("false".equals(lit)) return 0;

        if (lit.contains(".")) return Double.parseDouble(lit);
        return Integer.parseInt(lit);
    }
}
