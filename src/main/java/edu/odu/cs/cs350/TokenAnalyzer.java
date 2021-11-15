package edu.odu.cs.cs350;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * TokenAnalyzer analyzes each lexemes in the given file
 * and determines the type of token. The result returns
 * a list of tokens and the total number of tokens in the file.
 * 
 */
public class TokenAnalyzer implements Iterable<Token> {
    
    private List<Token> tokensContainer;
    private LexerAnalyzer scanner;
    private Token token;
    
    /**
     * The default constructor for token analyzer. Creates an
     * empty constructor for each data members.
     */
    public TokenAnalyzer() {
        tokensContainer = new LinkedList<Token>();
        scanner = new LexerAnalyzer(null);
        token = new Token(null, 0, 0);
    }

    /**
     * The constructor for analyzing tokens in a file given by the parameter.
     * Creates a list of object Token and a LexerAnalyzer scanner.
     * @param input: The input file to be read to determine the tokens in the file
     */
    public TokenAnalyzer(Reader input) {
        tokensContainer = new LinkedList<Token>();
        scanner = new LexerAnalyzer(input);
    }

    /**
     * Divides source codes into tokens.
     * The function will scan each token base on the token type
     */
    public void processSourceCode() {
        try {
            token = scanner.yylex();
            while(token != null && token.getTokenType() != TokenType.EOF) {
                tokensContainer.add(token);
                token = scanner.yylex();
            }
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     *  
     * @return The total number of tokens in the file that is being read.
     */
    public final int getFileTokenCount() {
        return tokensContainer.size();
    }

    /**
     * Return an iterator over the list of tokens.
     */
    @Override
    public final Iterator<Token> iterator() {
        return tokensContainer.iterator();
    }

    /**
     * For debugging purposes
     * @return format of tokenAnalyzer which returns the number of tokens in the file
     */
    @Override
    public String toString() {
        return Integer.toString(getFileTokenCount());
    }
}
