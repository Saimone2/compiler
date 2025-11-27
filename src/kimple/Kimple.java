package kimple;

import kimple.ast.ProgramNode;
import kimple.lexer.*;
import kimple.parser.AstParser;
import kimple.parser.KimpleParser;
import kimple.parser.SemanticException;
import kimple.parser.SyntaxException;
import kimple.poliz.KimplePolizGenerator;
import kimple.poliz.PolizModule;
import kimple.psm.PolizMachine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Kimple {
    public static void main(String[] args) {
        String code = """
                fun main() {
                    val x: Double = 10 + 2.5
                    var y: Double = 0.012
                    if (x > 0 && y != inf) {
                        print("Hello😊 $x")
                    } else {
                        return false
                    }
                    for (i: Int in 1..10) {}
                    // Коментар
                }
                """;

        String code1 = """
                fun main() {
                    val MAX: Double = 5 + 4.5
                    val MESSAGE✉️: String = "Factorial: "
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
                        print(MESSAGE✉️, fact as String, " large!")
                    } else {
                        print(MESSAGE✉️, fact as String, " small.")
                    }
                   \s
                    var test: Double = 2 ^ 3 + 4.0 / 0
                    var isTrue: Boolean = (test == inf) && true
                    print("Тест: ", isTrue as String)
                }
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
        PolizModule module = gen.generate(program);
        module.resolveLabels();

        StringBuilder sb = new StringBuilder();
        for (var ins : module.code)
            sb.append(ins.toString()).append("\n");

        try {
            Files.writeString(Path.of("poliz.txt"), sb.toString());
        } catch (IOException e) {
            System.err.println("Не вдалося записати файл POLIZ: " + e.getMessage());
        }

        System.out.println("Виконання POLIZ...");
        System.out.println("======================================\n");
        PolizMachine vm = new PolizMachine(module);
        vm.execute();
        System.out.println("\n\n======================================");
        System.out.println("Виконання завершено.");
    }
}
