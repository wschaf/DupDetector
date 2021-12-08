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
 * Tests functionality of token analyzer
 */
public class TestTokenAnalyzer {
    
    @BeforeEach
    void setup() {
       //
    }

    @Test
    public void testConstructors() {
        TokenAnalyzer tA = new TokenAnalyzer();
        assertThat(tA.getFileTokenCount(), equalTo(0));
        assertThat(tA.toString(), equalTo("0"));

        
        Reader in = new StringReader("abc");
        TokenAnalyzer tB = new TokenAnalyzer(in);
        assertThat(tB.getFileTokenCount(), equalTo(0));
        assertThat(tB.toString(), equalTo("0"));
    }


    @Test
    public void testProcessSourceCode() throws Exception {
        
        try {
            File f = new File("src/test/data/test.cpp");
            Scanner s = new Scanner(f);
            String source = "";
            while (s.hasNext()) {
                source += s.nextLine() + "\n";
            }
            s.close();

            ArrayList <Token> tokens = new ArrayList<Token>();
            Reader input = new StringReader(source);
            TokenAnalyzer tokenAnalyzer = new TokenAnalyzer(input);
            tokenAnalyzer.processSourceCode();
            
            assertThat(tokens.size(), equalTo(0));
            for(Token t: tokenAnalyzer) tokens.add(t);
            assertThat(tokens.size(), equalTo(24));
            
            Token t = tokens.get(0);
            assertThat(TokenType.HASH_SYMBOL, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo(""));
            assertThat(t.getLineNumber(),equalTo("1"));
            assertThat(t.getColumnNumber(), equalTo("1"));
            assertThat(t.toString(), equalTo(""));

            t = tokens.get(3);
            assertThat(TokenType.IOSTREAM, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo(""));
            assertThat(t.getLineNumber(),equalTo("1"));
            assertThat(t.getColumnNumber(), equalTo("11"));
            assertThat(t.toString(), equalTo(""));

            t = tokens.get(12);
            assertThat(TokenType.RIGHT_PAREN, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo(""));
            assertThat(t.getLineNumber(),equalTo("8"));
            assertThat(t.getColumnNumber(), equalTo("10"));
            assertThat(t.toString(), equalTo(""));

            t = tokens.get(16);
            assertThat(TokenType.STRING_LITERAL, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo("Hello World"));
            assertThat(t.getLineNumber(),equalTo("9"));
            assertThat(t.getColumnNumber(), equalTo("25"));
            assertThat(t.toString(), equalTo("Hello World"));

            t = tokens.get(17);
            assertThat(TokenType.OSTREAM, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo(""));
            assertThat(t.getLineNumber(),equalTo("9"));
            assertThat(t.getColumnNumber(), equalTo("27"));
            assertThat(t.toString(), equalTo(""));
            
            t = tokens.get(21);
            assertThat(TokenType.CONSTANT_NUMBERS, equalTo(t.getTokenType()));
            assertThat(t.getLexeme(), equalTo("0"));
            assertThat(t.getLineNumber(),equalTo("11"));
            assertThat(t.getColumnNumber(), equalTo("12"));
            assertThat(t.toString(), equalTo("0"));
        } 
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
