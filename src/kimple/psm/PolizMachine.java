package kimple.psm;

import kimple.poliz.PolizInstruction;
import kimple.poliz.PolizModule;

import java.util.*;

public class PolizMachine {
    private final PolizModule module;
    private final List<PolizInstruction> instrs;
    private final Deque<Object> stack = new ArrayDeque<>();
    private final Map<String,Object> globals = new HashMap<>();
    private final Deque<Frame> callStack = new ArrayDeque<>();
    private final Scanner scanner = new Scanner(System.in);

    public PolizMachine(PolizModule module) {
        this.module = module;
        this.instrs = module.resolveLabels();
        Map<String, Integer> labels = module.labelMap;
    }

    private static class Frame {
        Map<String,Object> locals = new HashMap<>();
        int retPc;
        Frame(int retPc) { this.retPc = retPc; }
    }

    public void execute() {
        module.resolveLabels();

        int pc = 0;
        while (pc < instrs.size()) {
            PolizInstruction ins = instrs.get(pc);
            String op = ins.opcode;
            String arg = ins.operand;
            switch (op) {
                case "PUSH":
                    stack.push(parseLiteral(arg));
                    pc++; break;
                case "LOAD": {
                    Object v = loadVar(arg);
                    stack.push(v);
                    pc++; break;
                }
                case "STORE": {
                    Object value = stack.pop();
                    storeVar(arg, value);
                    pc++; break;
                }
                case "ALLOC_GLOBAL": {
                    globals.put(arg, 0);
                    pc++; break;
                }
                case "ALLOC_LOCAL": {
                    if (!callStack.isEmpty()) callStack.peek().locals.put(arg, 0);
                    else globals.put(arg, 0);
                    pc++; break;
                }
                case "STORE_LOCAL": {
                    Object v = stack.pop();
                    if (callStack.isEmpty()) globals.put(arg, v);
                    else callStack.peek().locals.put(arg, v);
                    pc++; break;
                }
                case "STORE_GLOBAL": {
                    Object v = stack.pop();
                    globals.put(arg, v);
                    pc++; break;
                }
                case "LOAD_LOCAL": {
                    Object val = callStack.isEmpty() ? globals.get(arg) : callStack.peek().locals.get(arg);
                    stack.push(val == null ? 0 : val);
                    pc++; break;
                }
                case "STORE_GLOBAL_VAR": {
                    Object v = stack.pop(); globals.put(arg, v); pc++; break;
                }
                case "PRINT": {
                    Object v = stack.pop();
                    System.out.print(v);
                    pc++; break;
                }
                case "READ": {
                    String line = scanner.nextLine();
                    stack.push(line);
                    pc++; break;
                }
                case "+":
                case "-":
                case "*":
                case "/":
                case "%": {
                    Number b = asNumber(stack.pop());
                    Number a = asNumber(stack.pop());
                    Number res = switch (op) {
                        case "+" ->
                                (a instanceof Double || b instanceof Double) ? a.doubleValue() + b.doubleValue() : a.intValue() + b.intValue();
                        case "-" ->
                                (a instanceof Double || b instanceof Double) ? a.doubleValue() - b.doubleValue() : a.intValue() - b.intValue();
                        case "*" ->
                                (a instanceof Double || b instanceof Double) ? a.doubleValue() * b.doubleValue() : a.intValue() * b.intValue();
                        case "/" -> a.doubleValue() / b.doubleValue();
                        default -> a.intValue() % b.intValue();
                    };
                    stack.push(res); pc++; break;
                }
                case "==": case "!=": case "<": case "<=": case ">": case ">=": {
                    Object rb = stack.pop();
                    Object ra = stack.pop();
                    boolean r = switch (op) {
                        case "==" -> ra.equals(rb);
                        case "!=" -> !ra.equals(rb);
                        case "<" -> toDouble(ra) < toDouble(rb);
                        case "<=" -> toDouble(ra) <= toDouble(rb);
                        case ">" -> toDouble(ra) > toDouble(rb);
                        default -> toDouble(ra) >= toDouble(rb);
                    };
                    stack.push(r ? 1 : 0);
                    pc++; break;
                }
                case "NOT": {
                    Object a = stack.pop();
                    boolean val = !(isTruthy(a));
                    stack.push(val ? 1 : 0);
                    pc++; break;
                }
                case "NEG": {
                    Number a = asNumber(stack.pop());
                    if (a instanceof Double) stack.push(-a.doubleValue());
                    else stack.push(-a.intValue());
                    pc++; break;
                }
                case "JZ": {
                    Object cond = stack.pop();
                    boolean isZero = !isTruthy(cond);
                    if (isZero) {
                        Integer target = module.labelMap.get(arg);
                        if (target == null) throw new RuntimeException("Unknown label: " + arg);
                        pc = target;
                    } else pc++;
                    break;
                }
                case "JNZ": {
                    Object cond = stack.pop();
                    boolean isTrue = isTruthy(cond);
                    if (isTrue) {
                        Integer target = module.labelMap.get(arg);
                        if (target == null) throw new RuntimeException("Unknown label: " + arg);
                        pc = target;
                    } else pc++;
                    break;
                }
                case "JMP": {
                    Integer target = module.labelMap.get(arg);
                    if (target == null) throw new RuntimeException("Unknown label: " + arg);
                    pc = target; break;
                }
                case "CALL": {
                    Integer entry = module.labelMap.get("fun_" + arg);
                    if (entry == null) throw new RuntimeException("Unknown function: " + arg);
                    Frame frame = new Frame(pc + 1);
                    callStack.push(frame);
                    pc = entry;
                    break;
                }
                case "RET": {
                    Frame fr = callStack.isEmpty() ? null : callStack.pop();
                    if (fr == null) {
                        return;
                    } else {
                        pc = fr.retPc;
                    }
                    break;
                }
                case "POP": { stack.pop(); pc++; break; }
                default:
                    pc++;
                    break;
            }
        }
    }

    private static Number asNumber(Object o) {
        if (o instanceof Number) return (Number)o;
        try { return Integer.parseInt(o.toString()); } catch (Exception e) { return Double.parseDouble(o.toString()); }
    }

    private static double toDouble(Object o) {
        if (o instanceof Number) return ((Number)o).doubleValue();
        try { return Double.parseDouble(o.toString()); } catch (Exception e) { return 0.0; }
    }

    private static boolean isTruthy(Object o) {
        if (o == null) return false;
        if (o instanceof Boolean) return (Boolean)o;
        if (o instanceof Number) return ((Number)o).doubleValue() != 0.0;
        if (o instanceof String) return !((String)o).isEmpty();
        return true;
    }

    private Object loadVar(String name) {
        if (!callStack.isEmpty() && callStack.peek().locals.containsKey(name)) {
            return callStack.peek().locals.get(name);
        }
        return globals.getOrDefault(name, 0);
    }

    private void storeVar(String name, Object value) {
        if (!callStack.isEmpty() && callStack.peek().locals.containsKey(name)) {
            callStack.peek().locals.put(name, value);
        } else {
            globals.put(name, value);
        }
    }

    private Object parseLiteral(String lit) {
        if (lit == null) return 0;
        if (lit.startsWith("\"") && lit.endsWith("\"") && lit.length() >= 2) return lit.substring(1, lit.length()-1);
        if ("true".equalsIgnoreCase(lit) || "false".equalsIgnoreCase(lit)) return Boolean.parseBoolean(lit);
        if (lit.contains(".")) {
            try { return Double.parseDouble(lit); } catch (Exception e) { return lit; }
        }
        try { return Integer.parseInt(lit); } catch (Exception e) { return lit; }
    }
}
