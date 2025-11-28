package kimple;

import kimple.ast.ProgramNode;
import kimple.lexer.*;
import kimple.parser.AstParser;
import kimple.parser.KimpleParser;
import kimple.parser.SemanticException;
import kimple.parser.SyntaxException;
import kimple.poliz.KimplePolizGenerator;
import kimple.poliz.PolizModule;
import kimple.poliz.PolizWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Kimple {

    private static void runPSMFromJava(String module) {
        try {
            List<String> cmd = new ArrayList<>();
            cmd.add("python");
            cmd.add("PSM.py");
            cmd.add("-p");
            cmd.add(new File("out/production/lab2/kimple").getAbsolutePath());
            cmd.add("-m");
            cmd.add(module);

            ProcessBuilder pb = new ProcessBuilder(cmd);

            pb.directory(new File("src/kimple/psm"));
            pb.redirectErrorStream(true);

            Process proc = pb.start();

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(proc.getInputStream(), StandardCharsets.UTF_8))) {

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String code = """
                val x: Int = 5
                var y: Double = 0.012
                print(x + y)

                fun factorial(n: Int): Int {
                    var result: Int = 1
                    for (i: Int in 1..n) {
                        result = result * i
                    }
                    return result
                }

                var rez✉️: Int = factorial(x)
                print(rez✉️)
                """;

        String code1 = """

                    val MAX: Double = 5
                    val MESSAGE: String = "Factorial: "
                   \s
                    fun factorial(n: Int): Int {
                        var result: Int = 0
                        for (i: Int in 1..n) {
                            result = result * i
                        }
                        return result
                    }
                   \s
                    var input: Int
                    print("Input number: ")
                    input = 5
                   \s
                    var fact: Int = factorial(input)
                    if (fact >= MAX * 10) {
                        print(MESSAGE, fact as String, " large!")
                    } else {
                        print(MESSAGE, fact as String, " small.")
                    }
                   \s
                    var test: Double = 2 ^ 3 + 4.0 / 0
                    var isTrue: Boolean = (test == inf) && true
                    print("Тест: ", isTrue as String)

               \s""";

        List<Token> tokens;

        System.out.println("==== Лексичний аналіз ====");
        KimpleLexer lexer = new KimpleLexer(code);
        try {
            tokens = lexer.tokenize();
            for (Token token : tokens) {
                System.out.println(token);
            }
            System.out.println("=================================");
            System.out.println("Лексичний аналіз пройшов успішно!");
            System.out.println("=================================\n");
        } catch (LexicalException e) {
            System.err.println("Lexical error: " + e.getMessage());
            return;
        }

        System.out.println("==== Синтаксичний та семантичний аналіз ====");
        try {
            KimpleParser parser = new KimpleParser(tokens);
            parser.parse();
            System.out.println("====================================");
            System.out.println("Синтаксичний та семантичний аналіз пройшов успішно!");
            System.out.println("====================================\n");
        } catch (SemanticException | SyntaxException e) {
            System.err.println("Syntax or semantic error: " + e.getMessage());
        }


        AstParser astp = new AstParser(tokens);
        ProgramNode program;
        try {
            program = astp.parse();
        } catch (RuntimeException e) {
            System.err.println("Parse error: " + e.getMessage());
            return;
        }

        KimplePolizGenerator gen = new KimplePolizGenerator();
        Map<String, PolizModule> modules = gen.generateAll(program);

        PolizWriter.writeFiles(modules);

        System.out.println("\nВиконання POLIZ...");
        System.out.println("======================================\n");

        runPSMFromJava("program");

        System.out.println("\n======================================");
        System.out.println("Виконання завершено.");
    }
}
