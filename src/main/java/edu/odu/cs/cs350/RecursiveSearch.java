package edu.odu.cs.cs350;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.*;

public class RecursiveSearch {
	
	/**
     * searchDirectory recursively searches a given file path and
     * check if the file path ends with a directory
     * @param startDir the start of the absolute file path
     */
	public void searchDirectory(String startDir) {
    	
        File dir = new File(startDir);     
        File[] files = dir.listFiles();
            for (File f : files) {
            	
            	try {
                // Check if the file is a directory
            		if (f.isDirectory()) {
            			searchDirectory(f.getAbsolutePath());
            		} 
            		else {      
            			if(f.getName().endsWith(".cpp") || f.getName().endsWith(".h")) {
            				Scanner s = new Scanner(f);
            				String source = " ";
            				while (s.hasNext()) {
            					source += s.nextLine() + "\n";
            				}
            				s.close();
                        
            				Reader input = new StringReader(source);
            				TokenAnalyzer t = new TokenAnalyzer(input);
							t.processSourceCode();
            				System.out.println(f + ", " + t);
            			}
            			else {
            				System.out.println("File not Supported");
            			}
                	} 
            	}
            	
            	catch(FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
         }
        
    }
}