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
     * searchWithProperties recursively searches a given file path using
     * properties loaded from a property file (.ini) and
     * check if the file path ends with a directory
     * @param startDir - the start of the absolute file path
     * @param propertiesName - the name of the .ini file
     */
	public List<File> searchWithProperties(String startDir, File propertiesFile) throws Exception {
		if (propertiesFile == null || propertiesFile == new File("")) {
			extensions = new ArrayList<String>();
			extensions.add(".h");
			extensions.add(".cpp");
		}
		else {
			extensions = new ArrayList<String>();
			Properties props = new Properties();
			try(Reader reader = new FileReader(propertiesFile)){
				props.load(reader);
			}
			// after loading the .ini file, string are split by "," and stored in an array of extension
			extensions = Arrays.asList(props.getProperty("CppExtensions").split(", "));
		}
		
		File dir = new File(startDir);
		if (dir.isFile()) {
			listofFiles.add(dir);
		}
		else { 
			for (File f : dir.listFiles()) {
				try {
					// Check if the file is a directory
					if (f.isDirectory()) {
						searchWithProperties(f.getAbsolutePath(), propertiesFile);
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
     * searchWithProperties recursively searches a given file path using
     * properties loaded from a property file (.ini) and
     * check if the file path ends with a directory
     * @param startDir - the start of the absolute file path
     * @param propertiesName - the name of the .ini file
     */
	public List<File> searchWithProperties(String startDir) throws Exception {
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
						searchWithProperties(f.getAbsolutePath());
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