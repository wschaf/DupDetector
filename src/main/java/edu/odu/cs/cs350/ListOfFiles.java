package edu.odu.cs.cs350;

import java.util.ArrayList;
import java.util.List;

public class ListOfFiles {
    private List<File> ListFiles;


    public ListOfFiles() {
        ListFiles = new ArrayList<File>();
    }

    public ListOfFiles(List<File> files) {
        ListFiles = files;
    }

    public void setListOfFiles(List<File> Files) {
        this.ListFiles = Files;
    }

    public List<File> getListOfFiles() {
        return ListFiles;
    }

    public void addFiles(File f) {
        this.ListFiles.add(f);
    }

    public int numFiles() {
        return ListFiles.size();
    }
}
