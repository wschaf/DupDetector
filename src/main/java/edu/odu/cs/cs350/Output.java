package edu.odu.cs.cs350;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * Display the result into two section
 * Section one prints all the absolute file paths and the number of tokens within its file
 * 
 */
public class Output {

    /**
     * Print section one of the output
     */
    public void printSectionOne() {

        // create a list of files
        List<File> Files = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            // initialize a file and pass in the file path
            File F = new File("DupDetector/src/main/data/edu/odu/cs/cs350/boo.cpp");
            // count the number of tokens in that file
            F.countTokens();
            // add the file to the list of files
            Files.add(F);
        }
            
        System.out.println("Files Scanned:");
        for (File f : Files) {
            System.out.println("\t" + f.getfilePath() + ", " + f.getNumOfTokens());
        }
    }


}
