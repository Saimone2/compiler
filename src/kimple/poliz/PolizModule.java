package kimple.poliz;

import java.util.*;

public class PolizModule {
    public final List<PolizInstruction> code = new ArrayList<>();
    public final Map<String, Integer> labelMap = new HashMap<>();

    public void emit(String opcode) { code.add(new PolizInstruction(opcode)); }
    public void emit(String opcode, String operand) { code.add(new PolizInstruction(opcode, operand)); }

    public List<PolizInstruction> resolveLabels() {
        labelMap.clear();
        int pc = 0;
        for (PolizInstruction ins : code) {
            if ("LABEL".equals(ins.opcode) && ins.operand != null) {
                labelMap.put(ins.operand, pc);
            } else {
                pc++;
            }
        }

        List<PolizInstruction> out = new ArrayList<>();
        for (PolizInstruction ins : code) {
            if ("LABEL".equals(ins.opcode)) continue;
            out.add(ins);
        }
        return out;
    }
}
