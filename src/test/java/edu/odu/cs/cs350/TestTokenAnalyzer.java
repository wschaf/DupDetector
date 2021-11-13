package edu.odu.cs.cs350;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;


/**
 * Unit tests for both TokenAnalyzer and Token Classes
 */
public class TestTokenAnalyzer {
    
    @BeforeEach
    void setup() {
       //
    }

    @Test
    public void testTokenConstructors() {
        Token t = new Token(TokenType.ADD, 1, 1);
        assertThat(t.getTokenType(), equalTo(TokenType.ADD));
        assertThat(t.getLineNumber(), equalTo("1"));
        assertThat(t.getColumnNumber(), equalTo("1"));
        assertThat(t.getLexeme(), equalTo(""));

        t = new Token(TokenType.CONSTANT_NUMBERS, 2, 2, "35");
        assertThat(t.getTokenType(), equalTo(TokenType.CONSTANT_NUMBERS));
        assertThat(t.getLineNumber(), equalTo("2"));
        assertThat(t.getColumnNumber(), equalTo("2"));
        assertThat(t.getLexeme(), equalTo("35"));
    }

    @Test
    public void TestAnalyzer() {
        
        try {
            File f = new File("src\\test\\data\\test.cpp");
            Scanner s = new Scanner(f);
            String source = "";
            while (s.hasNext()) {
                source += s.nextLine() + "\n";
            }
            s.close();

            ArrayList <Token> tokens = new ArrayList<Token>();
            
            Reader input = new StringReader(source);
            TokenAnalyzer tokenAnalyzer = new TokenAnalyzer(input);
            tokenAnalyzer.processFileIntoTokens();
            
            assertThat(tokens.size(), not(equalTo(tokenAnalyzer.getFileTokenCount())));

            for(Token t: tokenAnalyzer) tokens.add(t);

            assertThat(tokens.size(), equalTo(tokenAnalyzer.getFileTokenCount()));
            
            Token t = tokens.get(0);
            assertThat(TokenType.HASH_SYMBOL, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo(""));
            assertThat(t.getLineNumber(),equalTo("1"));
            assertThat(t.getColumnNumber(), equalTo("1"));

            t = tokens.get(3);
            assertThat(TokenType.IOSTREAM, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo(""));
            assertThat(t.getLineNumber(),equalTo("1"));
            assertThat(t.getColumnNumber(), equalTo("11"));

            t = tokens.get(12);
            assertThat(TokenType.RIGHT_PAREN, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo(""));
            assertThat(t.getLineNumber(),equalTo("8"));
            assertThat(t.getColumnNumber(), equalTo("10"));

            t = tokens.get(16);
            assertThat(TokenType.STRING_LITERAL, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo("Hello World"));
            assertThat(t.getLineNumber(),equalTo("9"));
            assertThat(t.getColumnNumber(), equalTo("25"));

            t = tokens.get(17);
            assertThat(TokenType.OSTREAM, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo(""));
            assertThat(t.getLineNumber(),equalTo("9"));
            assertThat(t.getColumnNumber(), equalTo("27"));
            
            t = tokens.get(21);
            assertThat(TokenType.CONSTANT_NUMBERS, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo("0"));
            assertThat(t.getLineNumber(),equalTo("11"));
            assertThat(t.getColumnNumber(), equalTo("12"));

            // for debugging purposes
            System.out.println(tokens);

        } 
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
