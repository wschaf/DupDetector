package edu.odu.cs.cs350.Interfaces;

/**
 * Token file contains the information needed for a token defined in the TokenType class.
 * Token metadata includes tokentype, string lexeme of Identifier or Number, the line number
 * of the token, and the column number of the token.
 */
public abstract interface TokenInterface {
    
    public String getLexeme();

    public String getLineNumber();

    public String getColumnNumber();

    public String toString();

    public String getAbsolutePath();
}
