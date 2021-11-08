package edu.odu.cs.cs350;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TokenAnalyzer implements Iterable<Token> {
    
    private List<Token> tokens;
    
    public TokenAnalyzer(final Reader input) {
        tokens = new LinkedList<Token>();
        LexerAnalyzer scanner = new LexerAnalyzer(input);
        try {
            Token token = scanner.yylex();
            while (token != null && token.getTokenType() != TokenType.EOF) {
                tokens.add (token);
                token = scanner.yylex();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Return an iterator over the list of tokens.
     */
    @Override
    public final Iterator<Token> iterator() {
        return tokens.iterator();
    }
}
