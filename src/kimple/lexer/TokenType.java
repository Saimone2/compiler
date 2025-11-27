package kimple.lexer;

public enum TokenType {
    IDENT,          // ідентифікатор
    INT,            // ціле число
    REAL,           // дійсне число
    TRUE,           // true
    FALSE,          // false
    INF,            // inf
    STRING,         // рядок

    KEYWORD,        // ключові слова (var, val, if тощо)
    TYPE,           // типи (Int, Double тощо)

    ASSIGN_OP,      // =
    ADD_OP,         // + -
    MULT_OP,        // * / %
    POW_OP,         // ^
    NOT_OP,         // !
    REL_OP,         // < <= > >=
    EQ_OP,          // ==
    NEQ_OP,         // !=
    AND_OP,         // &&
    OR_OP,          // ||

    LPAREN,         // (
    RPAREN,         // )
    LBRACE,         // {
    RBRACE,         // }

    SEMICOLON,      // ;
    COMMA,          // ,
    COLON,          // :

    CAST_OP,        // as
    RANGE_OP,       // ..

    EOF,            // кінець файлу
    ERROR           // помилка
}
