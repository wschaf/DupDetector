package edu.odu.cs.cs350;

import java.io.*;
import java.io.File;
import java.util.*;

public class RecursiveSearch {
	
	private List<File> listofFiles;
	private List<String> extensions;
	
	public RecursiveSearch() {
		listofFiles = new ArrayList<File>();
		extensions = new ArrayList<String>();
	}
	
	/**
     * findFiles recursively searches a given file path using
     * properties loaded from a property file (.ini) and
     * check if the file path ends with a directory
     * @param startDir - the start of the absolute file path
     * @param propertiesName - the name of the .ini file
     */
	public List<File> findFiles(String startDir, List<String> extensions) throws Exception {		
		File dir = new File(startDir);
		if (dir.isFile()) {
			listofFiles.add(dir);
		}
		else { 
			for (File f : dir.listFiles()) {
				try {
					// Check if the file is a directory
					if (f.isDirectory()) {
						findFiles(f.getAbsolutePath(), extensions);
					} 
					else {      
						for (String extension : extensions) {
							if (f.getName().endsWith(extension)) listofFiles.add(f);
						}
					} 
				}
				catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
				}
			}
		}
        return listofFiles;
    }

	/**
     * findFiles recursively searches a given file path using
     * properties loaded from a property file (.ini) and
     * check if the file path ends with a directory
     * @param startDir - the start of the absolute file path
     * @param propertiesName - the name of the .ini file
     */
	public List<File> findFiles(String startDir) throws Exception {
		extensions = new ArrayList<String>();
		extensions.add(".h");
		extensions.add(".cpp");		
		File dir = new File(startDir);
		if (dir.isFile()) {
			listofFiles.add(dir);
		}
		else { 
			for (File f : dir.listFiles()) {
				try {
					// Check if the file is a directory
					if (f.isDirectory()) {
						findFiles(f.getAbsolutePath());
					} 
					else {      
						for (String extension : extensions) {
							if (f.getName().endsWith(extension)) listofFiles.add(f);
						}
					} 
				}
				catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
				}
			}
		}
        return listofFiles;
    }
	
	/**
     * searchDirectory recursively searches a given file and
     * check if the file path ends with a directory
     * @param startDir - the start of the absolute file path
     */
	public List<File> searchDirectory(String startDir) throws Exception {
    	
        File dir = new File(startDir);  
        if (dir.isFile()) {
        	listofFiles.add(dir);
        }
        else { 
        	for (File f : dir.listFiles()) {
        		try {
        			// Check if the file is a directory
        			if (f.isDirectory()) {
        				searchDirectory(f.getAbsolutePath());
        			} 
        			else {      
        				if(f.getName().endsWith(".cpp") || f.getName().endsWith(".h")) {
        					listofFiles.add(f);
        				}
        			} 
        		}
        		catch(FileNotFoundException e) {
                   System.out.println(e.getMessage());
        		}
        	}
         }
         return listofFiles;   
	}
}