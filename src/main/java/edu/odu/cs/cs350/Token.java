package edu.odu.cs.cs350;

public class Token {
    
    private TokenType tokenType;

    private String lexeme;

    private int lineNumber;

    private int ColumnNumber;


    public Token(TokenType type, int line, int column) {
        this.tokenType = type;
        this.lexeme = "";
        this.lineNumber = line;
        this.ColumnNumber = column;
    }

    public Token(TokenType type, int line, int column, String lexeme) {
        this.tokenType = type;
        this.lexeme = lexeme;
        this.lineNumber = line;
        this.ColumnNumber = column;
    }

    public TokenType getTokenType() {
        return this.tokenType;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public int getColumnNumber() {
        return this.ColumnNumber;
    }


}
