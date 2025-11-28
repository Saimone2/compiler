package kimple.poliz;

import java.util.*;

public class PolizModule {

    private final String name;

    private final List<String> vars = new ArrayList<>();
    private final Map<String, Integer> labels = new LinkedHashMap<>();
    private final List<String> funcs = new ArrayList<>();
    private final List<String> code = new ArrayList<>();

    public PolizModule(String name) {
        this.name = name;
    }

    public int currentAddress() {
        return code.size();
    }

    public void addVar(String name, String type) {
        vars.add(name + " " + type);
    }

    public void addLabel(String label, int addr) {
        labels.put(label, addr);
    }

    public void addFunc(String name, String retType, int params) {
        funcs.add(name + " " + retType + " " + params);
    }

    public void emit(String inst) {
        code.add(inst);
    }

    public List<String> getCode() {
        return code;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();

        sb.append(".vars(\n");
        vars.forEach(v -> sb.append("    ").append(v).append("\n"));
        sb.append(")\n\n");

        sb.append(".labels(\n");
        labels.forEach((k, v) ->
                sb.append("    ").append(k).append(" ").append(v).append("\n"));
        sb.append(")\n\n");

        sb.append(".funcs(\n");
        funcs.forEach(f -> sb.append("    ").append(f).append("\n"));
        sb.append(")\n\n");

        sb.append(".code(\n");
        code.forEach(c -> sb.append("    ").append(c).append("\n"));
        sb.append(")\n");

        return sb.toString();
    }
}
