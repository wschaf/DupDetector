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

import java.util.*;
import java.io.*;
import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestIntegration {

    private static String args[];
    private static Input input;
    private static Recommender recommender;
    private static Output output;

    @BeforeEach
    public void setup() {
        args = new String[] {
            "5",
            "/home/wgs/src/cs350/DupDetector/src/test/data/properties.ini"
        };
    }

    @Test
    public void testArgs() {
        assertThat(args[0], is("5"));
    }
}
