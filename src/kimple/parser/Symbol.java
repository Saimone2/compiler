package kimple.parser;

import java.util.List;

public record Symbol(
        String name,         // ім'я
        String type,         // "Int", "Double" тощо
        boolean isConst,     // true для val
        int declaredLine,    // рядок оголошення
        List<String> paramTypes,  // для функцій (null для змінних)
        String returnType    // для функцій (null для змінних)
) {
    public Symbol(String name, String type, boolean isConst, int line) {
        this(name, type, isConst, line, null, null);
    }
}
