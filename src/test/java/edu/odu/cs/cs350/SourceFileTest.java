package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class SourceFileTest {
    
    String path = "Dupdetector/src/main/java/edu/odu/cs/cs350/foo.cpp";
    File source = new File("Dupdetector/src/main/java/edu/odu/cs/cs350/bar.cpp");
    File blank = new File();


    @Test
    public void testDefaultConstructor() {
        File file = new File();
        assertThat(file.getNumOfTokens(), equalTo(0));
        assertThat(file.getfilePath(), equalTo(""));
        assertThat(file, equalTo(blank));
        assertThat(file.toString(), not(containsString(path)));

    }

    @Test
    public void testParametizedConstructor() {
        File file = new File(path);
        assertThat(file.getNumOfTokens(), equalTo(0));
        assertThat(file.getfilePath(), equalTo(path));
        assertThat(file, not(equalTo(source)));
        assertThat(file.toString(), containsString(path));
    }

    @Test
    public void testCopyConstructor() {
        File file = new File();
        file.setfilePath(path);
        file.setNumOfTokens(10);

        File copy = new File(file);

        assertThat(file, equalTo(copy));
        assertThat(file.getNumOfTokens(),equalTo(copy.getNumOfTokens()));
        assertThat(file.getfilePath(),equalTo(copy.getfilePath()));

        copy.setfilePath("test.cpp");

        assertThat(file.getfilePath(), not(equalTo(copy.getNumOfTokens())));
    }

    @Test
    public void testSetFilePath() {
        String path1 = "source.cpp";
        File file = new File(path1);

        file.setfilePath(path1);
        assertThat(file.getfilePath(), equalTo(path1));
        assertThat(file.getNumOfTokens(), equalTo(0));
        assertThat(file.toString(), containsString(path1));
        assertThat(file.toString(),not(containsString(path)));
    }

    @Test
    public void testSetTokens() {
        File file = new File(path);
        int num = 10;

        file.setNumOfTokens(num);

        assertThat(file.getNumOfTokens(),equalTo(num));

    }


    @Test
    public void testCountTokens() {
        File file = new File(path);
        
        assertThat(file.getNumOfTokens(), equalTo(0));

        file.countTokens();
        assertThat(file.getNumOfTokens(), equalTo(14));
    }

    @Test
    public void testEquals() {
        File file1 = new File();
        File file2 = new File();

        assertTrue(file1.equals(file2));
        assertFalse(file1.equals(source));

        file1.setfilePath("changing file path");
        assertFalse(file1.equals(file2));
    }

    @Test
    public void testClone() {
        File original = new File();
        original.setfilePath("file.cpp");
        original.setNumOfTokens(10);

        File copy = (File) original.clone();

        assertThat(original.getNumOfTokens(), equalTo(copy.getNumOfTokens()));
        assertThat(original.getfilePath(), equalTo(copy.getfilePath()));
        assertThat(original, equalTo(copy));
        assertThat(original.toString(), equalTo(copy.toString()));

        copy.setfilePath("test.cpp");
        assertThat(copy.getfilePath(), equalTo("test.cpp"));
        assertThat(original.getfilePath(),equalTo("file.cpp"));
        assertThat(original, not(equalTo(copy)));
    }

    @Test
    public void testHashCode() {
        File file1 = new File();
        File file2 = new File();

        file1.setfilePath(".cpp");
        file2.setfilePath(".cpp");

        assertThat(file1.hashCode(), equalTo(file2.hashCode()));
    }

    @Test
    public void testToString() {
        File file = new File(path);
        
        assertThat(file.toString(), equalTo("filePath: " +path));
    }


}
