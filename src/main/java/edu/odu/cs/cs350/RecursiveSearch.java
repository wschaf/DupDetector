package edu.odu.cs.cs350;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.*;

	/**
     * searchDirectory recursively searches a given file path and
     * check if the file path ends with a directory
<<<<<<< HEAD
     * @param startDir: the start of the absolute file path
=======
     * @param startDir the start of the absolute file path
>>>>>>> 247394ac463505cb4f0e3cb3a6b3d61f8d9c028e
     */
public class RecursiveSearch {
	
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
            				System.out.println(t);
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