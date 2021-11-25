package edu.odu.cs.cs350;

import java.io.*;
import java.io.File;
import java.util.*;

public class RecursiveSearch {
	
	private List<File> listofFiles;
	
	public RecursiveSearch() {
		listofFiles = new ArrayList<>();
	}
	
	/**
     * searchWithProperties recursively searches a given file path using
     * properties loaded from a property file (.ini) and
     * check if the file path ends with a directory
     * @param startDir - the start of the absolute file path
     * @param propertiesName - the name of the .ini file
     */
	public List<File> searchWithProperties(String startDir, String propertiesName) throws Exception {
    	
		File properties = new File(propertiesName);
		Properties props = new Properties();
		try(Reader reader = new FileReader(properties)){
    		props.load(reader);
    	}
		// after loading the .ini file, string are split by "," and stored in an array of extension
		String[] extensions = props.getProperty("CppExtensions").split(", ");
		
        File dir = new File(startDir);
        if (dir.isFile()) {
        	listofFiles.add(dir);
        }
        else { 
        	for (File f : dir.listFiles()) {	
        		try {
        			// Check if the file is a directory
        			if (f.isDirectory()) {
        				searchWithProperties(f.getAbsolutePath(), propertiesName);
        			} 
        			else {      
            		// check if files extension matches any of the properties defined by the .ini
        				if(Arrays.stream(extensions).anyMatch(entry -> f.getName().endsWith(entry))) {
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
	
	public List<File> getFiles() {
		return listofFiles;
	}

	/**
	 * Sets the list of files that will be output in Section 1 of
	 * the final output.
	 * @param files is a list of file objects.
	 */
	public void setFiles(List<File> listofFiles) {
		List<File> fileList = new ArrayList<File>();
		for (var f : listofFiles) fileList.add(f);
		this.listofFiles = fileList;
	}
}