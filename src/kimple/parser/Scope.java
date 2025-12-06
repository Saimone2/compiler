package kimple.parser;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private final Scope parent;
    private final Map<String, Symbol> symbols = new HashMap<>();
    private final String scopeName;  // "global", "main", "factorial" тощо

    public Scope(Scope parent, String scopeName) {
        this.parent = parent;
        this.scopeName = scopeName;
    }

    public void declare(Symbol symbol) {
        if (symbols.containsKey(symbol.name())) {
            throw new SemanticException("Semantic error: '" + symbol.name() + "' already declared in scope '" + scopeName + "' (line " + symbol.declaredLine() + ")");
        }
        symbols.put(symbol.name(), symbol);
        //System.out.println("Declared: " + symbol.name() + " : " + symbol.type() + " in scope '" + scopeName + "'");
    }

    public Symbol lookup(String name, int line) {
        Symbol sym = symbols.get(name);
        if (sym != null) return sym;
        if (parent != null) return parent.lookup(name, line);
        throw new SemanticException("Semantic error: '" + name + "' not declared (line " + line + ")");
    }

    public Scope getParent() {
        return parent;
    }
}
