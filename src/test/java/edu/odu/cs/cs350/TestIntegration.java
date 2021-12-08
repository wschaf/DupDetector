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
    private static String recursiveArgs[];
    private static Input input;
    // private static Recommender recommender;
    // private static Output output;

    @BeforeEach
    public void setup() {

        argsWithoutPropertiesFile = new String[] {
            "5",
            "/home/wgs/src/cs350/DupDetector/src/test/data/Point.cpp",
            "/home/wgs/src/cs350/DupDetector/src/test/data/Point.h",
            "/home/wgs/src/cs350/DupDetector/src/test/data/readingList.cpp",
            "/home/wgs/src/cs350/DupDetector/src/test/data/readingList.h",
            "/home/wgs/src/cs350/DupDetector/src/test/data/test.cpp",
            "/home/wgs/src/cs350/DupDetector/src/test/data/testA.cpp"
        };

        argsWithPropertiesFile = new String[] {
            "5",
            "/home/wgs/src/cs350/DupDetector/src/test/data/properties.ini",
            "/home/wgs/src/cs350/DupDetector/src/test/data/Point.cpp",
            "/home/wgs/src/cs350/DupDetector/src/test/data/Point.h",
            "/home/wgs/src/cs350/DupDetector/src/test/data/readingList.cpp",
            "/home/wgs/src/cs350/DupDetector/src/test/data/readingList.h",
            "/home/wgs/src/cs350/DupDetector/src/test/data/test.cpp",
            "/home/wgs/src/cs350/DupDetector/src/test/data/testA.cpp"
        };

        recursiveArgs = new String[] {
            "5",
            "/home/wgs/src/cs350/DupDetector/src/test/data/properties.ini",
            "/home/wgs/src/cs350/DupDetector/src/test/data/"
        };
    }

    @Test
    public void testArgs() {
        assertThat(argsWithoutPropertiesFile.length, is(7));
        assertThat(argsWithoutPropertiesFile[0], is("5"));
        assertThat(argsWithoutPropertiesFile[1], is("/home/wgs/src/cs350/DupDetector/src/test/data/Point.cpp"));
        assertThat(argsWithoutPropertiesFile[2], is("/home/wgs/src/cs350/DupDetector/src/test/data/Point.h"));
        assertThat(argsWithoutPropertiesFile[3], is("/home/wgs/src/cs350/DupDetector/src/test/data/readingList.cpp"));
        assertThat(argsWithoutPropertiesFile[4], is("/home/wgs/src/cs350/DupDetector/src/test/data/readingList.h"));
        assertThat(argsWithoutPropertiesFile[5], is("/home/wgs/src/cs350/DupDetector/src/test/data/test.cpp"));
        assertThat(argsWithoutPropertiesFile[6], is("/home/wgs/src/cs350/DupDetector/src/test/data/testA.cpp"));

        assertThat(argsWithPropertiesFile.length, is(8));
        assertThat(argsWithPropertiesFile[0], is("5"));
        assertThat(argsWithPropertiesFile[1], is("/home/wgs/src/cs350/DupDetector/src/test/data/properties.ini"));
        assertThat(argsWithPropertiesFile[2], is("/home/wgs/src/cs350/DupDetector/src/test/data/Point.cpp"));
        assertThat(argsWithPropertiesFile[3], is("/home/wgs/src/cs350/DupDetector/src/test/data/Point.h"));
        assertThat(argsWithPropertiesFile[4], is("/home/wgs/src/cs350/DupDetector/src/test/data/readingList.cpp"));
        assertThat(argsWithPropertiesFile[5], is("/home/wgs/src/cs350/DupDetector/src/test/data/readingList.h"));
        assertThat(argsWithPropertiesFile[6], is("/home/wgs/src/cs350/DupDetector/src/test/data/test.cpp"));
        assertThat(argsWithPropertiesFile[7], is("/home/wgs/src/cs350/DupDetector/src/test/data/testA.cpp"));
    }

    @Test
    public void testInputWithoutPropertiesFile() throws Exception{
        input = new Input(argsWithoutPropertiesFile);

        List<File> expectedFiles = new ArrayList<File>();
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/Point.cpp"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/Point.h"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/readingList.cpp"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/readingList.h"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/test.cpp"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/testA.cpp"));

        assertThat(input.getNSuggestions(), is(5));
        for (File file : input.getFiles()) assertTrue(expectedFiles.contains(file));
    }

    @Test
    public void testInputWithPropertiesFile() throws Exception{
        input = new Input(argsWithPropertiesFile);

        List<File> expectedFiles = new ArrayList<File>();
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/properties.ini"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/Point.cpp"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/Point.h"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/readingList.cpp"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/readingList.h"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/test.cpp"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/testA.cpp"));

        assertThat(input.getNSuggestions(), is(5));
        for (File file : input.getFiles()) assertTrue(expectedFiles.contains(file));
    }

    @Test
    public void testRecursiveInputWithPropertiesFile() throws Exception {
        input = new Input(recursiveArgs);

        List<File> expectedFiles = new ArrayList<File>();
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/properties.ini"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/Point.cpp"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/Point.h"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/readingList.cpp"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/readingList.h"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/test.cpp"));
        expectedFiles.add(new File("/home/wgs/src/cs350/DupDetector/src/test/data/testA.cpp"));

        assertThat(input.getNSuggestions(), is(5));
        for (File file : input.getFiles()) assertTrue(expectedFiles.contains(file));
    }

    @Test
    public void testRecommendations() throws Exception {
        
    }

    @Test
    public void testCompleteOutput() throws Exception{
    }
}
