package edu.odu.cs.cs350;

import java.io.Reader;
import java.io.File;
import java.util.Scanner;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestTokenAnalyzer {
    
    @BeforeEach
    void setup() {
       //
    }

    @Test
    public void TestTokenAnalyzer() {
        try{
            String source = "";
            File f = new File("test1.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                source += s.nextLine() + "\n";
            }
            s.close();
            String inputString = "123\tident1 \nKEYWORD /** */    42\n= //ignoreme more more \n x = y \n";
            Reader input = new StringReader(inputString);
            TokenAnalyzer tokenAnalyzer = new TokenAnalyzer(input);
            ArrayList<Token> tokens = new ArrayList<Token>();

            for(Token t: tokenAnalyzer) {
                tokens.add(t);
            }
            System.out.println(tokens);

        }
        catch(Exception e){ 
            //
        }
    }
}
