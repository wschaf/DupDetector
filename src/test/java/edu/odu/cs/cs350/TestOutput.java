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

import edu.odu.cs.cs350.Mocks.*;

import java.util.*;
import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestOutput {

    private static List<MockFile> mockFiles;
    private static List<MockRefactoring> mockRefactorings;
    private static int RefactoringsToPrint;

    @BeforeEach
    public void setup() {
        mockFiles = new ArrayList<MockFile>();

        mockFiles.add(new MockFile());
        mockFiles.add(new MockFile());
        mockFiles.add(new MockFile());

        mockRefactorings = new ArrayList<MockRefactoring>();
        mockRefactorings.add(new MockRefactoring());
        mockRefactorings.add(new MockRefactoring());
        mockRefactorings.add(new MockRefactoring());

        RefactoringsToPrint = 2;
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
        Output out = new Output(RefactoringsToPrint, mockFiles, mockRefactorings);
        assertThat(out.getRefactoringsToPrint(), is(RefactoringsToPrint));
        assertThat(out.getRefactorings(), is(mockRefactorings));
        assertThat(out.getFiles(), is(mockFiles));
    }

    @Test
    public void testGetRefactoringsToPrint() {
        Output out = new Output(RefactoringsToPrint, mockFiles, mockRefactorings);
        assertThat(out.getRefactoringsToPrint(), is(2));
    }

    @Test
    public void testSetRefactoringsToPrint() {
        Output out = new Output(RefactoringsToPrint, mockFiles, mockRefactorings);
        out.setRefactoringsToPrint(5);
        assertThat(out.getRefactoringsToPrint(), is(5));
    }

    @Test
    public void testGetFiles() {
        Output out = new Output(RefactoringsToPrint, mockFiles, mockRefactorings);
        List<MockFile> k = new ArrayList<MockFile>();
        k.add(new MockFile());
        k.add(new MockFile());
        k.add(new MockFile());
        assertThat(out.getFiles().size(), is(k.size()));
    }

    @Test
    public void testSetFiles() {
        Output out = new Output(RefactoringsToPrint, mockFiles, mockRefactorings);
        List<MockFile> files = new ArrayList<MockFile>();
        files.add(new MockFile());
        files.add(new MockFile());
        MockFile k = new MockFile();
        String path = "testPath";
        k.setAbsolutePath(path);
        files.add(k);
        out.setFiles(files);
        assertTrue(out.getFiles().get(2).getAbsolutePath() == path);
    }

    @Test
    public void testGetRefactorings() {
        Output out = new Output(RefactoringsToPrint, mockFiles, mockRefactorings);
        assertThat(out.getRefactorings(), is(mockRefactorings));
    }

    @Test
    public void testSetRefactorings() {
        Output out = new Output(RefactoringsToPrint, mockFiles, mockRefactorings);
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
        List<MockRefactoring> r = new ArrayList<MockRefactoring>();
        List<MockFile> f = new ArrayList<MockFile>();
        Output out = new Output(0, f, r);
        String s = out.getSectionOne();
        assertThat(s.length(), is(0));
    }

    @Test
    public void testGetSectionOne() {
        Output out = new Output(RefactoringsToPrint, mockFiles, mockRefactorings);
        String subject = out.getSectionOne();
        assertThat(subject.length(), greaterThan(0));
        assertTrue(subject.contains("Files Scanned:\n"));
        for (var f : mockFiles) {
            assertTrue(subject.contains(f.getAbsolutePath()));
            assertTrue(subject.contains(f.getNumberOfTokens()));
        }
    }

    @Test
    public void testGetSectionTwoNoFiles() {
        List<MockRefactoring> r = new ArrayList<MockRefactoring>();
        List<MockFile> f = new ArrayList<MockFile>();
        Output out = new Output(0, f, r);
        String s = out.getSectionTwo();
        assertThat(s.length(), is(0));
    }

    @Test
    public void testGetSectionTwo() {
        Output out = new Output(RefactoringsToPrint, mockFiles, mockRefactorings);
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
        Output out = new Output(RefactoringsToPrint, mockFiles, mockRefactorings);
        String subject = out.getSectionTwo();
        int lines = subject.split("\r\n|\r|\n").length;
        assertThat(lines, is(RefactoringsToPrint * 3));
    }

    @Test
    public void testGetCompleteOutputNoFiles() {
        List<MockRefactoring> r = new ArrayList<MockRefactoring>();
        List<MockFile> f = new ArrayList<MockFile>();
        Output out = new Output(0, f, r);
        String correctOutput = "No files scanned.\n";
        assertThat(out.getCompleteOutput(), is(correctOutput));
    }

    @Test
    public void testGetCompleteOutput() {
        Output out = new Output(RefactoringsToPrint, mockFiles, mockRefactorings);
        String subject = out.getCompleteOutput();
        String correctOutput =
        "Files Scanned:\n" +
        "    /home/wgs/src/cs350/DupDetector/src/test/data/hello.cpp, 19\n" +
        "    /home/wgs/src/cs350/DupDetector/src/test/data/hello.cpp, 19\n" +
        "    /home/wgs/src/cs350/DupDetector/src/test/data/hello.cpp, 19\n" +
        "\n" +
        "Opportunity #1, 6 tokens\n" +
        "/home/wgs/src/cs350/DupDetector/src/test/data/hello.cpp:32:64\n" +
        "if this then that\n" +
        "Opportunity #2, 6 tokens\n" +
        "/home/wgs/src/cs350/DupDetector/src/test/data/hello.cpp:32:64\n" +
        "if this then that\n";

        assertThat(subject, is(correctOutput));
    }
}
