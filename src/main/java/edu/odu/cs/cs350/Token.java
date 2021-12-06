package edu.odu.cs.cs350;

import edu.odu.cs.cs350.Interfaces.TokenInterface;

/**
 * Token file contains the information needed for a token defined in the TokenType class.
 * Token metadata includes tokentype, string lexeme of Identifier or Number, the line number
 * of the token, and the column number of the token.
 */
public class Token implements TokenInterface{
    
    private TokenType tokenType;
    private String lexeme;
    private int lineNumber;
    private int ColumnNumber;
    private String absolutePath;


    /**
     * 
     * @param type: Type of token given in the TokenType class
     * @param line: line number of the current token
     * @param column: line number of the current token
     */
    public Token(TokenType type, int line, int column) {
        this.tokenType = type;
        this.lexeme = "";
        this.lineNumber = line;
        this.ColumnNumber = column;
    }

    /**
     * 
     * @param type: Type of token given in the TokenType class
     * @param line: line number of the current token
     * @param column: starting column number of the current token
     * @param lexeme: string representation of this token
     */
    public Token(TokenType type, int line, int column, String lexeme) {
        this.tokenType = type;
        this.lexeme = lexeme;
        this.lineNumber = line;
        this.ColumnNumber = column;
    }

    /**
     * 
     * @return the token type of this current token.
     */
    public TokenType getTokenType() {
        return this.tokenType;
    }

    /**
     * 
     * @return the string lexeme of this current token, empty if it's not 
     *         an Identifier or a number.
     */
    @Override
    public String getLexeme() {
        return this.lexeme;
    }

    /**
     * 
     * @return the line number of this current token in string format
     */
    @Override
    public String getLineNumber() {
        return Integer.toString(this.lineNumber);
    }

    /**
     * 
     * @return the column number of this current token in string format
     *         for string literals, the column number will be the ending
     *          of the string. (i.e. the second quotation)
     */
    @Override
    public String getColumnNumber() {
        return Integer.toString(this.ColumnNumber);
    }

     /**
     * Representation of the token in two format
     * according to the use of constructor.
     */
    @Override
    public String toString() {
        if (getLexeme().length() > 0) {
            return getTokenType() + ":" + 
                   getLexeme() + ":" + 
                   getLineNumber() + ":" + 
                   getColumnNumber();
        } else {
            return getTokenType().toString()+ ":" + 
                   getLineNumber() + ":" + 
                   getColumnNumber();
        }
    }

    @Override
    public String getAbsolutePath() {
        return this.absolutePath;
    }
}
