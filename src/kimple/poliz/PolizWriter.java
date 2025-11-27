package kimple.poliz;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class PolizWriter {

    public static void writeFiles(Map<String, PolizModule> modules, String baseName) {
        modules.forEach((name, module) -> {
            List<PolizInstruction> code = module.resolveLabels();

            String fileName = name.equals("MAIN")
                    ? baseName + ".postfix"
                    : baseName + "$" + name + ".postfix";

            writeFile(fileName, code);
        });
    }

    private static void writeFile(String name, List<PolizInstruction> code) {
        try {
            StringBuilder sb = new StringBuilder();
            for (PolizInstruction i : code) sb.append(i).append("\n");
            Files.writeString(Path.of(name), sb.toString());
            System.out.println("Записано: " + name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
