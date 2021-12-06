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
    private Output out;
    private static String directory;
    private static String properties;
    private static List<File> expectedFiles;

    @BeforeEach
    public void setup() throws Exception {
        out = new Output();
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
        "File Scanned:\n" + 
        "    /home/runner/work/DupDetector/DupDetector/src/test/data/Point.cpp, 189\n" +
        "    /home/runner/work/DupDetector/DupDetector/src/test/data/Point.h, 200\n" +
        "    /home/runner/work/DupDetector/DupDetector/src/test/data/readingList.cpp, 720\n" +
        "    /home/runner/work/DupDetector/DupDetector/src/test/data/readingList.h, 194\n" +
        "    /home/runner/work/DupDetector/DupDetector/src/test/data/test.cpp, 24\n";

        out.setFiles(search.searchDirectory(directory));
        assertThat(out.getFiles(), contains(expectedFiles.get(0)));
        assertThat(out.getFiles().size(), is(5));
        assertThat(out.getSectionOne(), equalTo(expected));

        out.setFiles(search.searchWithProperties(directory, properties));
        assertThat(out.getFiles(), equalTo(expectedFiles.get(1)));
        assertThat(out.getFiles().size(), is(5));
        assertThat(out.getSectionOne(), equalTo(expected));
    }

    @Test
    public void testSectionTwoFunctionality() throws Exception {

    }

}
