package edu.odu.cs.cs350;

import java.io.File;
import java.util.*;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestRecursiveSearch {
	
	private List<File> actual = new ArrayList<>();
	
	@Test
    public void testSearchWithProperties() throws Exception {
		
		RecursiveSearch search = new RecursiveSearch();
		String directory = "src\\test\\data";
		String properties = "properties.ini";

        actual = new ArrayList<>(search.searchWithProperties(directory, properties));
        File file1 = new File("src\\test\\data\\Point.cpp");
        File file2 = new File("src\\test\\data\\Point.h");
        File file3 = new File("src\\test\\data\\readingList.cpp");
        File file4 = new File("src\\test\\data\\readingList.h");
        File file5 = new File("src\\test\\data\\test.cpp");
        assertThat(actual, containsInAnyOrder(file1, file2, file3, file4, file5));
      
        assertThat(actual, hasSize(5));
        assertThat(actual.size(), is(5));
        assertThat(actual, not(IsEmptyCollection.empty()));
        assertThat(new ArrayList<>(), IsEmptyCollection.empty());
        search.clear();
    }
	
	@Test
    public void testSearchDirectory() throws Exception {
		
		RecursiveSearch search = new RecursiveSearch();
		String directory = "src\\test\\data";

        actual = new ArrayList<>(search.searchDirectory(directory));
        File file1 = new File("src\\test\\data\\Point.cpp");
        File file2 = new File("src\\test\\data\\Point.h");
        File file3 = new File("src\\test\\data\\readingList.cpp");
        File file4 = new File("src\\test\\data\\readingList.h");
        File file5 = new File("src\\test\\data\\test.cpp");
        assertThat(actual, containsInAnyOrder(file1, file2, file3, file4, file5));
      
        assertThat(actual, hasSize(5));
        assertThat(actual.size(), is(5));
        assertThat(actual, not(IsEmptyCollection.empty()));
        assertThat(new ArrayList<>(), IsEmptyCollection.empty());
        search.clear();
    }
	
	public void testClear() {	
		
		RecursiveSearch search = new RecursiveSearch();
		search.clear();
		assertThat(actual, IsEmptyCollection.empty());
	}
	
}