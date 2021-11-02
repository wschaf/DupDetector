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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestOutput {

    @BeforeEach
    public void setup() {
        List<MockFile> mockFiles = new ArrayList<MockFile>();
        mockFiles.add(new MockFile());
        mockFiles.add(new MockFile());
        mockFiles.add(new MockFile());

        List<MockRefactoring> mockRefactorings = new ArrayList<MockRefactoring>();
        mockRefactorings.add(new MockRefactoring());
        mockRefactorings.add(new MockRefactoring());
        mockRefactorings.add(new MockRefactoring());

        int RefactoringsToPrint = 2;
    }

    @Test
    public void testDefaultConstructor() {
        MockOutput out = new MockOutput();
        assertThat(out.getRefactoringsToPrint(), equalTo(0));
        assertThat(out.getFiles().size(), equalTo(0));
        assertThat(out.getRefactorings().size(), equalTo(0));
    }

    @Test
    public void testConstructor() {
        MockOutput out = new MockOutput(RefactoringsToPrint, mockFiles, mockRefactorings);
    }
}
