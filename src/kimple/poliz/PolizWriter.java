package kimple.poliz;

import java.io.PrintWriter;
import java.util.Map;

public class PolizWriter {

    public static void writeFiles(Map<String, PolizModule> modules) {
        for (var e : modules.entrySet()) {
            String moduleName = e.getKey();
            String filename;
            if (moduleName.equals("program")) {
                filename = "program.postfix";
            } else {
                filename = "program$" + moduleName + ".postfix";
            }
            try (PrintWriter pw = new PrintWriter("out/production/lab2/kimple/" + filename)) {
                pw.println(e.getValue().build());
                System.out.println("Wrote: " + filename);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
