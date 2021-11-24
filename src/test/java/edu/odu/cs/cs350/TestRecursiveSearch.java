package edu.odu.cs.cs350;

import java.io.File;
import java.util.*;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestRecursiveSearch {
	
	@Test
    public void testSearchDirectory() throws Exception {
		
		RecursiveSearch search = new RecursiveSearch();
		String directory = "src\\test\\data";

        List<File> actual = new ArrayList<>(search.searchDirectory(directory));
        List<File> expected = new ArrayList<>();
        File f1 = new File("src\\test\\data\\Point.cpp");
        File f2 = new File("src\\test\\data\\Point.h");
        File f3 = new File("src\\test\\data\\readingList.cpp");
        File f4 = new File("src\\test\\data\\readingList.h");
        File f5 = new File("src\\test\\data\\test.cpp");
        expected.add(f1);
        expected.add(f2);
        expected.add(f3);
        expected.add(f4);
        expected.add(f5);
        
        //1. Test equal.
        assertThat(actual, is(expected));

        //2. Check List Size
        assertThat(actual, hasSize(5));

        assertThat(actual.size(), is(5));

        //4. check empty list
        assertThat(actual, not(IsEmptyCollection.empty()));

        assertThat(new ArrayList<>(), IsEmptyCollection.empty());

    }

}