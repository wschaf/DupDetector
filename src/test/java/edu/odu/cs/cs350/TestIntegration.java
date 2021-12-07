/*
Complete DupDetector Output as follows:

Files scanned:
	<FileAbsolutePath>, <NumberofTokens>
	<FileAbsolutePath>, <NumberofTokens>
	<FileAbsolutePath>, <NumberofTokens>

Opportunity <Opportunity#>, <NumberofTokensInOpportunity> tokens
<RefactoringAbsolutePath>:<RefactoringLineNumber>:<RefactoringColumnNumber>
<ListofTokens>

*/

package edu.odu.cs.cs350;

import edu.odu.cs.cs350.Interfaces.*;
import edu.odu.cs.cs350.Mocks.*;

import java.util.*;
import java.io.*;
import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestIntegration {

    private static List<File> files;
    private static List<MockRefactoring> mockRefactorings;
    private static int RefactoringsToPrint;
    private static MockInput emptyInput;
    private static MockInput input;
    private static MockRecommender emptyRecommender;
    private static MockRecommender recommender;
    private static Hashtable<File, Integer> tokenCountForFiles;

    @BeforeEach
    public void setup() {
        files = new ArrayList<File>();
        File f = new File("src/test/data/testA.cpp");
        files.add(f);

        tokenCountForFiles = new Hashtable<File, Integer>();
        tokenCountForFiles.put(f, 5);

        List<TokenInterface> tokens = Arrays.asList (
            (new Token(TokenType.INT, 1, 1)),
            (new Token(TokenType.IDENTIFIER, 1, 5)),
            (new Token(TokenType.ASSIGN_OP, 1, 7)),
            (new Token(TokenType.CONSTANT_NUMBERS, 1, 9)),
            (new Token(TokenType.SEMI_COLON, 1, 10)),

            (new Token(TokenType.INT, 2, 1)),
            (new Token(TokenType.IDENTIFIER, 2, 5)),
            (new Token(TokenType.ASSIGN_OP, 2, 7)),
            (new Token(TokenType.CONSTANT_NUMBERS, 2, 9)),
            (new Token(TokenType.SEMI_COLON, 2, 10))
        );
        List<TokenInterface> refactoringTokenList = Arrays.asList (
            (new Token(TokenType.INT, 1, 1)),
            (new Token(TokenType.IDENTIFIER, 1, 5)),
            (new Token(TokenType.ASSIGN_OP, 1, 7)),
            (new Token(TokenType.CONSTANT_NUMBERS, 1, 9)),
            (new Token(TokenType.SEMI_COLON, 1, 10))
        );

        MockRefactoring r = new MockRefactoring(5, "src/test/data/testA.cpp", 1, 1, refactoringTokenList, 100);
        mockRefactorings = new ArrayList<MockRefactoring>();
        mockRefactorings.add(r);

        RefactoringsToPrint = 1;

        emptyInput = new MockInput();
        emptyRecommender = new MockRecommender();
        input = new MockInput(RefactoringsToPrint, files, tokenCountForFiles, tokens);
        recommender = new MockRecommender(tokens, mockRefactorings, 0, 100);
    }

    @Test
    public void testDefaultConstructor() {
        Output out = new Output();
        assertThat(out.getRefactoringsToPrint(), equalTo(0));
        assertThat(out.getFiles().size(), equalTo(0));
        assertThat(out.getRefactorings().size(), equalTo(0));
    }
}
