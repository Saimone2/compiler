package kimple.poliz;

import java.util.*;

public class PolizModule {
    public final List<PolizInstruction> code = new ArrayList<>();
    // after resolve: label -> instruction index
    public final Map<String, Integer> labelMap = new HashMap<>();

    public void emit(String opcode) { code.add(new PolizInstruction(opcode)); }
    public void emit(String opcode, String operand) { code.add(new PolizInstruction(opcode, operand)); }

    /**
     * Записує мітки (LABEL name) у labelMap і повертає версію інструкцій без LABEL
     * readyForExec: список інструкцій, де LABEL видалені — адреси переходів представлені як числа
     */
    public List<PolizInstruction> resolveLabels() {
        labelMap.clear();
        // first pass: find labels and remember their target indices in the final code (excluding LABEL instrs)
        int pc = 0;
        for (int i = 0; i < code.size(); ++i) {
            PolizInstruction ins = code.get(i);
            if ("LABEL".equals(ins.opcode) && ins.operand != null) {
                labelMap.put(ins.operand, pc);
            } else {
                pc++;
            }
        }
        // second pass: produce new list where LABEL removed, and JMP/JZ/CALL operands with label names preserved for runtime to resolve via labelMap
        List<PolizInstruction> out = new ArrayList<>();
        for (PolizInstruction ins : code) {
            if ("LABEL".equals(ins.opcode)) continue;
            out.add(ins);
        }
        return out;
    }
}
