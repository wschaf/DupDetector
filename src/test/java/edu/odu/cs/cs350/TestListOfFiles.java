package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestListOfFiles {
    
    /*ListOfFiles blankList;
    ListOfFiles nonBlankList;
    File file1;
    File file2;
    List<File> fileList;

    File[] files;

    @BeforeEach
    public void setup() {
        file1 = new File("test1.cpp");
        file2 = new File("test2.cpp");
        fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);

        File[] fileContainer = {file1, file2};
        files = fileContainer;
        blankList = new ListOfFiles();
        nonBlankList = new ListOfFiles(fileList);
    }

    @Test
    public void testDefaultConstructor() {
        assertThat(blankList.getListOfFiles(),is(empty()));
        assertThat(blankList.numFiles(), equalTo(0));
        assertThat(blankList.toString(), not(containsString("test")));
    }

    @Test
    public void testParametizedConstructor() {
        assertThat(nonBlankList.getListOfFiles(), equalTo(fileList));
        assertThat(nonBlankList.numFiles(), equalTo(2));
        assertThat(nonBlankList.getListOfFiles().get(0), equalTo(file1));
    }

    @Test
    public void testSetListOfFiles() {
        List<File> fileList2 = new ArrayList<>();
        File file3 = new File("test3.h");
        File file4 = new File("test4.h");
        fileList2.add(file3);
        fileList2.add(file4);
        blankList.setListOfFiles(fileList2);

        assertThat(blankList.getListOfFiles(), not(equalTo(nonBlankList.getListOfFiles())));
    }

    @Test
    public void testAddFilesAndNumFiles() {
        assertThat(blankList.numFiles(), equalTo(0));
        blankList.addFiles(file1);
        
        assertThat(blankList.getListOfFiles().get(0), equalTo(file1));
        assertThat(blankList.numFiles(), equalTo(1));
    }*/

}
