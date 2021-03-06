package edu.odu.cs.cs350;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;

import edu.odu.cs.cs350.Interfaces.TokenInterface;

/**
 * TokenAnalyzer analyzes each lexemes in the given file
 * and determines the type of token. The result returns
 * a list of tokens and the total number of tokens in the file.
 */
public class TokenAnalyzer implements Iterable<Token> {
    
    /** Container for token object found in the file. */
    private List<Token> tokensContainer;

    /** Scan the input file. */
    private LexerAnalyzer scanner;

    /** Token object found in the file. Contains necessary metadata defined in Token class. */
    private Token token;

    private File inputFile;
    
    /**
     * The default constructor for token analyzer.
     * Creates an empty constructor for each data members.
     */
    public TokenAnalyzer() {
        tokensContainer = new LinkedList<Token>();
        scanner = new LexerAnalyzer(null);
        token = new Token(null, 0, 0);
    }

    /**
     * The constructor for analyzing tokens in a file given by the parameter.
     * Creates a list of object Token and a LexerAnalyzer scanner.
     * @param input The input file to be read to determine the tokens in the file
     */
    public TokenAnalyzer(Reader input) {
        tokensContainer = new LinkedList<Token>();
        scanner = new LexerAnalyzer(input);
    }

    public TokenAnalyzer(File inputFile) {
        Scanner scanner;
        this.inputFile = inputFile;
        try {
            scanner = new Scanner(inputFile);
            String source = "";
            while(scanner.hasNext()) source += scanner.nextLine() + "\n";
            scanner.close();
            Reader input = new StringReader(source);
            this.tokensContainer = new LinkedList<Token>();
            this.scanner = new LexerAnalyzer(input);
        } catch (Exception e) {
            System.err.println("TokenAnalyzer error: unable to read file " +
                inputFile.getAbsolutePath()
            );
        }
    }

    /**
     * Divides source codes into tokens.
     * The function will scan each token and add it to the 
     * list container if the token type is defined in the TokenType class
     */
    public void processSourceCode() {
        try {
            token = scanner.yylex();
            while (token != null && token.getTokenType() != TokenType.EOF) {
                if (this.inputFile == null || !this.inputFile.exists()) {
                    token.setAbsolutePath("");
                }
                else {
                    token.setAbsolutePath(this.inputFile.getAbsolutePath());
                }
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
     * For debugging purposes.
     * @return format of tokenAnalyzer which returns the number of tokens in the file
     */
    @Override
    public String toString() {
        return Integer.toString(getFileTokenCount());
    }

	public List<? extends TokenInterface> getTokens() {
		return this.tokensContainer;
	}
}
