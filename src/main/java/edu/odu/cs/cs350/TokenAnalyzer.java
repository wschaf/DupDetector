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
    private int minimumSequenceLength;
    private int maxLexemesSubstitution;
    
    /**
     * The constructor for analyzing tokens in a file given by the parameter.
     * Creates a list of token and adds each token in the container determined
     * by the token rule given in the jflex file.
     * 
     * @param input: The input file to be read to determine the tokens in the file
     */
    public TokenAnalyzer(Reader input) {
        tokensContainer = new LinkedList<Token>();
        scanner = new LexerAnalyzer(input);
        minimumSequenceLength = 10;
        maxLexemesSubstitution = 8;
    }

    public void processFileIntoTokens() {
        try {
            token = scanner.yylex();
            while(token != null && token.getTokenType() != TokenType.EOF) {
                tokensContainer.add(token);
                token = scanner.yylex();
            }
            System.out.println(tokensContainer.toString());
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
}
