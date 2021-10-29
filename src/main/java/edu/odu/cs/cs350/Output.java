package edu.odu.cs.cs350;

import java.util.List;

public class Output {
    private List<File> Files;
    private List<Refactoring> refactorings;

    public Output() {

    }

    public Output(List<File> F, List<Refactoring> R) {
        this.Files = F;
        this.refactorings = R;
    }

    public void addAbsolutePaths() {
        
    }
}
