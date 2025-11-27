package kimple.poliz;

public class PolizInstruction {
    public final String opcode;
    public final String operand;

    public PolizInstruction(String opcode) {
        this(opcode, null);
    }

    public PolizInstruction(String opcode, String operand) {
        this.opcode = opcode;
        this.operand = operand;
    }

    @Override
    public String toString() {
        return operand == null ? opcode : opcode + " " + operand;
    }
}
