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

import java.util.*;
import java.io.*;
import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIntegration {

    private static String argsWithPropertiesFile[];
    private static String argsWithoutPropertiesFile[];
    private static String testArg[];

    @BeforeEach
    public void setup() {

        argsWithoutPropertiesFile = new String[] {
            "5",
            "/home/wgs/src/cs350/DupDetector/src/test/data/"
        };

        argsWithPropertiesFile = new String[] {
            "5",
            "/home/wgs/src/cs350/DupDetector/src/test/data/properties.ini",
            "/home/wgs/src/cs350/DupDetector/src/test/data/"
        };

        testArg = new String[] {
            "5",
            "src/test/data/testA.cpp"
        };
    }

    @Test
    public void testArgs() {
        assertThat(argsWithoutPropertiesFile.length, is(2));
        assertThat(argsWithoutPropertiesFile[0], is("5"));
        assertThat(argsWithoutPropertiesFile[1], is("/home/wgs/src/cs350/DupDetector/src/test/data/"));

        assertThat(argsWithPropertiesFile.length, is(3));
        assertThat(argsWithPropertiesFile[0], is("5"));
        assertThat(argsWithPropertiesFile[1], is("/home/wgs/src/cs350/DupDetector/src/test/data/properties.ini"));
        assertThat(argsWithPropertiesFile[2], is("/home/wgs/src/cs350/DupDetector/src/test/data/"));
    }

    @Test
    public void testIntegratedInput() throws Exception {
        Input inputA = new Input(argsWithoutPropertiesFile);
        Input inputB = new Input(argsWithPropertiesFile);

        List<File> expectedFiles = new ArrayList<File>();
        expectedFiles.add(new File("src/test/data/Point.cpp"));
        expectedFiles.add(new File("src/test/data/Point.h"));
        expectedFiles.add(new File("src/test/data/readingList.cpp"));
        expectedFiles.add(new File("src/test/data/readingList.h"));
        expectedFiles.add(new File("src/test/data/test.cpp"));
        expectedFiles.add(new File("src/test/data/testA.cpp"));
        expectedFiles.add(new File("src/test/data/testB.cpp"));

        List<String> expectedProperties = new ArrayList<String>();
        expectedProperties.add(".h");
        expectedProperties.add(".cpp");

        int expectedMinSequence = 10;
        int expectedMaxSubstitutions = 8;

        assertThat(inputA.getNSuggestions(), is(5));
        assertThat(inputA.getfileExtensions(), is(expectedProperties));
        assertThat(inputA.getMinSequenceLength(), is(expectedMinSequence));
        assertThat(inputA.getMaxSubstitutions(), is(expectedMaxSubstitutions));
        assertThat(inputA.getFiles().size(), is(7));

        assertThat(inputB.getNSuggestions(), is(5));
        assertThat(inputB.getfileExtensions().get(0), is(expectedProperties.get(0)));
        assertThat(inputB.getfileExtensions().get(1), is(expectedProperties.get(1)));
        assertThat(inputB.getMinSequenceLength(), is(16));
        assertThat(inputB.getMaxSubstitutions(), is(20));
        assertThat(inputB.getFiles().size(), is(7));
    }

    @Test
    public void testIntegratedRecommender() throws Exception {
        Input input = new Input(testArg);
        Recommender recommender = new Recommender(input.getTokens());
        assertThat(recommender.getRefactorings().size(), is(6));
    }

    @Test
    public void testIntegratedOutput() throws Exception{
        Input input = new Input(testArg);
        Recommender recommender = new Recommender(input.getTokens());
        Output output = new Output(input, recommender);
        String subject = output.getCompleteOutput();

        assertTrue(subject.contains("Files Scanned:\n"));
        assertTrue(subject.contains("testA.cpp, 10"));
        assertTrue(subject.contains("Opportunity #1, 3 tokens"));
        assertTrue(subject.contains(" 5 "));
    }
}
