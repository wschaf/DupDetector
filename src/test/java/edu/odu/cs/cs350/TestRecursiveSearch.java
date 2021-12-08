package edu.odu.cs.cs350;

import java.io.File;
import java.util.*;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestRecursiveSearch {
	
	private List<File> actual;
	
	@BeforeEach
	public void setup() {
        actual = new ArrayList<File>();
    }
	
	@Test
    public void testfindFiles() throws Exception {
		
		RecursiveSearch search = new RecursiveSearch();
		String directory = "src/test/data";
		List<String> mockExtensions = new ArrayList<String>();
		mockExtensions.add(".h");
		mockExtensions.add(".cpp");

        actual = new ArrayList<>(search.findFiles(directory, mockExtensions));
        File file1 = new File("src/test/data/Point.cpp");
        File file2 = new File("src/test/data/Point.h");
        File file3 = new File("src/test/data/readingList.cpp");
        File file4 = new File("src/test/data/readingList.h");
        File file5 = new File("src/test/data/test.cpp");
		File file6 = new File("src/test/data/testA.cpp");
		File file7 = new File("src/test/data/testB.cpp");
        assertThat(actual, containsInAnyOrder(file1, file2, file3, file4, file5, file6, file7));
      
        assertThat(actual.size(), is(7));
        assertThat(actual, not(IsEmptyCollection.empty()));
        assertThat(new ArrayList<>(), IsEmptyCollection.empty());
    }
	
	@Test
    public void testSearchDirectory() throws Exception {
		
		RecursiveSearch search = new RecursiveSearch();
		String directory = "src/test/data";

        actual = new ArrayList<>(search.searchDirectory(directory));
        File file1 = new File("src/test/data/Point.cpp");
        File file2 = new File("src/test/data/Point.h");
        File file3 = new File("src/test/data/readingList.cpp");
        File file4 = new File("src/test/data/readingList.h");
        File file5 = new File("src/test/data/test.cpp");
		File file6 = new File("src/test/data/testA.cpp");
		File file7 = new File("src/test/data/testB.cpp");

        assertThat(actual, containsInAnyOrder(file1, file2, file3, file4, file5, file6, file7));
        assertThat(actual.size(), is(7));
        assertThat(actual, not(IsEmptyCollection.empty()));
        assertThat(new ArrayList<>(), IsEmptyCollection.empty());
    }
	
	//test recursive search with properties using just 1 file input
	@Test
	public void testSWPIfFile() throws Exception {
		
		RecursiveSearch search = new RecursiveSearch();
		String directory = "src/test/data/test.cpp";
		List<String> mockExtensions = new ArrayList<String>();
		mockExtensions.add(".h");
		mockExtensions.add(".cpp");

        actual = new ArrayList<>(search.findFiles(directory, mockExtensions));
	    File file1 = new File("src/test/data/test.cpp");
		
	    assertThat(actual, contains(file1));
	    assertThat(actual, hasSize(1));
	    assertThat(actual.size(), is(1));
	    assertThat(actual, not(IsEmptyCollection.empty()));
	    assertThat(new ArrayList<>(), IsEmptyCollection.empty());
	}
	
	//test recursive search without properties using just 1 file input
	@Test
	public void testRSIfFile() throws Exception {
		
		RecursiveSearch search = new RecursiveSearch();
		String directory = "src/test/data/test.cpp";
		List<String> mockExtensions = new ArrayList<String>();
		mockExtensions.add(".h");
		mockExtensions.add(".cpp");

        actual = new ArrayList<>(search.findFiles(directory, mockExtensions));
	    File file1 = new File("src/test/data/test.cpp");
	    assertThat(actual, contains(file1));
	      
	    assertThat(actual, hasSize(1));
	    assertThat(actual.size(), is(1));
	    assertThat(actual, not(IsEmptyCollection.empty()));
	    assertThat(new ArrayList<>(), IsEmptyCollection.empty());
	}
}