package edu.odu.cs.cs350;

import java.util.*;
import java.io.*;
import java.io.File;
import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class IntergrationTest {
    
    private RecursiveSearch search;
    private Output out1;
    private Output out2;
    private static String directory;
    private static String properties;
    private static List<File> expectedFiles;

    @BeforeEach
    public void setup() throws Exception {
        out1 = new Output();
        out2 = new Output();
        search = new RecursiveSearch();
        expectedFiles = new ArrayList<File>();
        
        directory = "src/";
        properties = "properties.ini";

        expectedFiles.add(new File("/home/runner/work/DupDetector/DupDetector/src/test/data/Point.cpp"));
        expectedFiles.add(new File("/home/runner/work/DupDetector/DupDetector/src/test/data/Point.h"));
        expectedFiles.add(new File("/home/runner/work/DupDetector/DupDetector/src/test/data/readingList.cpp"));
        expectedFiles.add(new File("/home/runner/work/DupDetector/DupDetector/src/test/data/readingList.h"));
        expectedFiles.add(new File("/home/runner/work/DupDetector/DupDetector/src/test/data/test.cpp"));
    }

    @Test
    public void testSectionOneFunctionality() throws Exception {
        String expected = 
        "Files Scanned:\n" +
        "    /home/runner/work/DupDetector/DupDetector/src/test/data/readingList.cpp, 720\n" + 
        "    /home/runner/work/DupDetector/DupDetector/src/test/data/Point.cpp, 189\n" +
        "    /home/runner/work/DupDetector/DupDetector/src/test/data/Point.h, 200\n" +
        "    /home/runner/work/DupDetector/DupDetector/src/test/data/test.cpp, 24\n" +
        "    /home/runner/work/DupDetector/DupDetector/src/test/data/readingList.h, 194\n";

        out1.setFiles(search.searchDirectory(directory));
        assertTrue(out1.getFiles().containsAll(expectedFiles));
        assertThat(out1.getFiles().size(), is(5));
        assertThat(out1.getSectionOne(), equalTo(expected));

        out2.setFiles(search.searchWithProperties(directory, properties));
        assertTrue(out2.getFiles().containsAll(expectedFiles));
        assertThat(out2.getFiles().size(), is(5));
        assertThat(out2.getSectionOne(), equalTo(expected));
    }

    @Test
    public void testSectionTwoFunctionality() throws Exception {

    }

}
