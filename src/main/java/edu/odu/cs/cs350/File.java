package edu.odu.cs.cs350;

import java.util.StringTokenizer;

/**
 * 
 * Contains the file paths and number of tokens
 * 
 */

public class File {
    private String filePath;
    private int numOfTokens;

    /**
     * Default Constructor
     * 
     */
    public File() {
        this.filePath = "";
        this.numOfTokens = 0;
    }

    /**
     * Parametized Constructor
     * 
     * @param filePath: absolute file path of the File
     *                   with cppExtensions
     */
    public File(String filePath) {
        this.filePath = filePath;
        this.numOfTokens = 0;
    }

    /**
     * Copy Constructor
     * @param src: File objects to be copied
     */
    public File (File src) {
        this.filePath = src.filePath;
        this.numOfTokens = src.numOfTokens;
    }

    /**
     * Set the file paths that are being read
     * @param file: absolute file path of the File
     *              with cppExtensions
     */
    public void setfilePath(String file) {
        this.filePath = file;
    }

    /**
     * get the file path
     * @return: the absolute file path of a file
     * 
     */
    public String getfilePath() {
        return this.filePath;
    }

    /**
     * set the number of tokens of input file
     * @param n: the number of tokens
     */
    public void setNumOfTokens(int n) {
        this.numOfTokens = n;
    }

    /**
     * get the number of tokens
     * 
     * @return: get the number of tokens of the file
     */
    public int getNumOfTokens() {
        return this.numOfTokens;
    }
    

    /**
     * Count the number of tokens within a file
     * 
     * reference: https://docs.oracle.com/javase/7/docs/api/java/util/StringTokenizer.html
     */
    public void countTokens() {
        //this is a placeholder that represents the data of a file
        String fakeData = "if (x = y) { do this stuff } else { do this }";

        // breaks a string into tokens, but does not distinguish identifiers, numbers, 
        // quoted strings, nor recognize and skip comments
        StringTokenizer stringTokens = new StringTokenizer(fakeData);

        // count the number of tokens
        numOfTokens = stringTokens.countTokens();
    }
    

     /**
     *  Check for equivalence based on name
     *  
     *  @param rhs object against which to compare equality
     *  
     *  @return true if two File objects are equal
     */
    public boolean equals(Object rhs) {
        File f = (File) rhs;

        return this.filePath.equals(f.filePath);
    }

     /**
     *  Hashcode
     *  
     *  @return integer hashcode
     */
    public int hashCode() {
        
        return this.filePath.hashCode();
    }

     /**
     *  Clone
     *  
     *  @return cloned File object
     */
    public File clone() {
        return new File(this);
    }

     /**
     *  toString function 
     *  
     *  @return File object as a string
     */
    public String toString() {
        return ("filePath: " + filePath.toString());
    }

}
