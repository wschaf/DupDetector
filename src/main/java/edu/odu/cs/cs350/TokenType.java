package edu.odu.cs.cs350;

public enum TokenType {
    End_of_input, Op_multiply,  Op_divide, Op_mod, Op_add, Op_subtract,
    Op_negate, Op_not, Op_less, Op_lessequal, Op_greater, Op_greaterequal,
    Op_equal, Op_notequal, Op_assign, Op_and, Op_or, Keyword_if,
    Keyword_else, Keyword_while, Keyword_print, Keyword_putc, Keyword_dereference, LeftParen, RightParen,
    LeftBrace, RightBrace, LeftBracket, RightBracket, Semicolon, Colon, Comma, Period, Qstn_mark, Identifier, 
    Integer, String, Hash_symbol, Tilda, At_symbol
}
