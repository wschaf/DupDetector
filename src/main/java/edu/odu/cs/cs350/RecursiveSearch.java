package edu.odu.cs.cs350;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;

	/**
     * searchDirectory recursively searches a given file path and
     * check if the file path ends with a directory
     * @param startDir - the start of the absolute file path
     */
public class RecursiveSearch {

	public void searchDirectory(String startDir) {
    	
        File dir = new File(startDir);
        FilenameFilter filter = new FilenameFilter() {
        	
        	/**
        	 * check if file is a C++ source code by checking file extension
        	 * @param f - file type to be accepted
        	 * @param name - the name of the given file
        	 */
            @Override
            public boolean accept(File f, String name) {
                
            	if(name.endsWith(".cpp") || name.endsWith(".h")) {
            		return true;
            	}
            	else{
            		System.out.println("File not Supported");
            		return false;
            	}
            }
        };
        
        File[] files = dir.listFiles(filter);
        
            for (File f : files) {
            	
            	try {
                // Check if the file is a directory
                if (f.isDirectory()) {
                    searchDirectory(f.getAbsolutePath());
                } 
                else {
                	
                		Scanner s = new Scanner(f);
                		String source = " ";
                        while (s.hasNext()) {
                            source += s.nextLine() + "\n";
                        }
                        s.close();
                        
                        //Reader input = new StringReader(source);
                        //TokenAnalyzer t = new TokenAnalyzer(input);
                		System.out.println(f);
                	} 
            	}
            	
            	catch(FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
         }
        
    }
}