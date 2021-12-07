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

import edu.odu.cs.cs350.Interfaces.TokenInterface;
import edu.odu.cs.cs350.Mocks.*;

import java.util.*;
import java.io.*;
import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestOutput {

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
        File f = new File("src/test/data/test.cpp");
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

        MockRefactoring r = new MockRefactoring(5, "src/test/data/test.cpp", 1, 1, refactoringTokenList, 100);
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

    @Test
    public void testParameterizedConstructor() {
        Output out = new Output(input, recommender);
        assertThat(out.getRefactoringsToPrint(), is(RefactoringsToPrint));
        assertThat(out.getRefactorings(), is(mockRefactorings));
        assertThat(out.getFiles(), is(files));
    }

    @Test
    public void testGetRefactoringsToPrint() {
        Output out = new Output(input, recommender);
        assertThat(out.getRefactoringsToPrint(), is(1));
    }

    @Test
    public void testSetRefactoringsToPrint() {
        Output out = new Output(input, recommender);
        out.setRefactoringsToPrint(5);
        assertThat(out.getRefactoringsToPrint(), is(5));
    }

    @Test
    public void testGetFiles() {
        Output out = new Output(input, recommender);
        List<File> k = new ArrayList<File>();
        k.add(new File("src/test/data/test.cpp"));
        assertThat(out.getFiles().size(), is(k.size()));
    }

    @Test
    public void testSetFiles() {
        Output out = new Output(input, recommender);
        List<File> files = new ArrayList<File>();
        files.add(new File("src/test/data/test.cpp"));
        files.add(new File("src/test/data/test.cpp"));
        File k = new File("test.cpp");
        files.add(k);
        out.setFiles(files);
        assertThat(out.getFiles().get(2).toString(), equalTo("test.cpp"));
    }

    @Test
    public void testGetRefactorings() {
        Output out = new Output(input, recommender);
        assertThat(out.getRefactorings(), is(mockRefactorings));
    }

    @Test
    public void testSetRefactorings() {
        Output out = new Output(input, recommender);
        List<MockRefactoring> k = new ArrayList<MockRefactoring>();
        k.add(new MockRefactoring());
        k.add(new MockRefactoring());
        MockRefactoring s = new MockRefactoring();
        String path = "testPath";
        s.absolutePath = path;
        k.add(s);
        out.setRefactorings(k);
        assertThat(out.getRefactorings(), is(k));
    }

    @Test
    public void testGetSectionOneNoFiles() {
        Output out = new Output(emptyInput, emptyRecommender);
        String s = out.getSectionOne();
        assertThat(s.length(), is(0));
    }

    @Test
    public void testGetSectionOne() {
        Output out = new Output(input, recommender);
        String subject = out.getSectionOne();
        assertThat(subject.length(), greaterThan(0));
        assertTrue(subject.contains("Files Scanned:\n"));
        for (var f : files) {
            assertTrue(subject.contains(f.getAbsolutePath()));
        }
    }

    @Test
    public void testGetSectionTwoNoFiles() {
        Output out = new Output(emptyInput, emptyRecommender);
        String s = out.getSectionTwo();
        assertThat(s.length(), is(0));
    }

    @Test
    public void testGetSectionTwo() {
        Output out = new Output(input, recommender);
        String subject = out.getSectionTwo();
        assertThat(subject.length(), greaterThan(0));
        assertTrue(subject.contains("Opportunity #"));
        for (var r : mockRefactorings) {
            assertTrue(subject.contains(r.getNumberOfTokens()));
            assertTrue(subject.contains(r.getAbsolutePath()));
            assertTrue(subject.contains(r.getLineNumber()));
            assertTrue(subject.contains(r.getColumnNumber()));
            assertTrue(subject.contains(r.getTokenList()));
            assertTrue(subject.contains(Integer.toString(r.numberOfTokens)));
        }
    }

    @Test
    public void testGetSectionTwoLimitedRefactorings() {
        Output out = new Output(input, recommender);
        String subject = out.getSectionTwo();
        int lines = subject.split("\r\n|\r|\n").length;
        assertThat(lines, is(RefactoringsToPrint * 3));
    }

    @Test
    public void testGetCompleteOutputNoFiles() {
        Output out = new Output(emptyInput, emptyRecommender);
        String correctOutput = "No files scanned.\n";
        assertThat(out.getCompleteOutput(), is(correctOutput));
    }

    @Test
    public void testGetCompleteOutput() {
        Output out = new Output(input, recommender);
        String subject = out.getCompleteOutput();
        String correctOutput =
        "Files Scanned:\n"                                                          +
        "    /home/wgs/src/cs350/DupDetector/src/test/data/test.cpp, 5\n"           +
        "\n"                                                                        +
        "Opportunity #1, 5 tokens\n"                                                +
        "src/test/data/test.cpp:1:1\n"                                              +
        "INT:1:1IDENTIFIER:1:5ASSIGN_OP:1:7CONSTANT_NUMBERS:1:9SEMI_COLON:1:10\n"   ;

        assertThat(subject.toString(), is(correctOutput));
    }
}

/*
“Files Scanned:\n" +
"    /home/wgs/src/cs350/DupDetector/src/test/data/test.cpp, 5\n" +
"\n" +
"Opportunity #1, 5 tokens\n" +
"src/test/data/test.cpp:1:1\n" +
INT:1:1IDENTIFIER:1:5ASSIGN_OP:1:7CONSTANT_NUMBERS:1:9SEMI_COLON:1:10\n”
*/