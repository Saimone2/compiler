package kimple.antlr;

import kimple.ast.*;
import kimple.clr.KimpleClrGenerator;
import kimple.gen.KimpleLexer;
import kimple.gen.KimpleParser;
import kimple.lexer.LexicalException;
import kimple.lexer.Token;
import kimple.parser.AstParser;
import kimple.parser.SemanticException;
import kimple.parser.SyntaxException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class KimpleRunner {

    private static void compileWithIlasm() throws IOException, InterruptedException {
        String ilasmPath = "C:\\Windows\\Microsoft.NET\\Framework64\\v4.0.30319\\ilasm.exe";
        String ilFile = new File("out/production/lab2/kimple/program.il").getAbsolutePath();

        ProcessBuilder pb = new ProcessBuilder(
                ilasmPath,
                ilFile,
                "/exe",
                "/output=program.exe",
                "/quiet"
        );

        pb.directory(new File("out/production/lab2/kimple"));
        pb.inheritIO();

        int exitCode = pb.start().waitFor();
        if (exitCode == 0) {
            System.out.println("Успішно зібрано: program.exe");
            System.out.println("======================================\n");
            new ProcessBuilder("cmd", "/c", "program.exe")
                    .directory(new File("out/production/lab2/kimple"))
                    .inheritIO()
                    .start();
        } else {
            System.err.println("Помилка збірки (ilasm вернув " + exitCode + ")");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String code = """
                val x: Int = 5
                var MAX: Int = 6
                
                fun factorial(n: Int): Int {
                    var result: Int = 1
                    for (i: Int in 1..n) {
                        result = result * i
                    }
                    return result
                }
                
                var rez: Int = factorial(x)
                print("rez: ", rez)
                
                var test: Double = 2 ^ 3 ^ 4 + 4.0 * 5.6
                print("Test: ", test)
                
                var test2: Double = 5.6 + 4
                print("Test2: ", test2)
                
                // if умова
                if (test2 < 10.0) {
                    print("test2 is less than 10")
                }
                
                var isTrue: Boolean = (test == inf) && true
                print(isTrue)
                """;

        CharStream input = CharStreams.fromString(code);
        KimpleLexer lexer = new KimpleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KimpleParser parser = new KimpleParser(tokens);


        ParseTree tree = parser.program();

        AstBuilder visitor = new AstBuilder();
        ProgramNode program;
        try {
            program = (ProgramNode) visitor.visit(tree);
        } catch (RuntimeException e) {
            System.err.println("Parse error: " + e.getMessage());
            return;
        }
        program = parser(code);

        KimpleClrGenerator clrGen = new KimpleClrGenerator(program);
        clrGen.generate();

        compileWithIlasm();
    }













    private static ProgramNode parser(String code) {
        List<Token> tokens = List.of();
        kimple.lexer.KimpleLexer lexer = new kimple.lexer.KimpleLexer(code);
        try {
            tokens = lexer.tokenize();
        } catch (LexicalException e) {
            System.err.println("Lexical error: " + e.getMessage());
        }

        try {
            kimple.parser.KimpleParser parser = new kimple.parser.KimpleParser(tokens);
            parser.parse();
        } catch (SemanticException | SyntaxException e) {
            System.err.println("Syntax or semantic error: " + e.getMessage());
        }

        AstParser astp = new AstParser(tokens);
        ProgramNode program = null;
        try {
            program = astp.parse();
        } catch (RuntimeException e) {
            System.err.println("Parse error: " + e.getMessage());
        }
        return program;
    }
}
