package edu.odu.cs.cs350;

import java.util.*;

/**
 * 
 * Contains the file paths and number of tokens
 * 
 */

public class File {
    private String filePaths;
    private int numOfTokens;


    /**
     * Default Constructor
     * 
     */
    public File() {
        this.filePaths = "";
        this.numOfTokens = 0;
    }

    /**
     * Parametized Constructor
     * @param desireTokens: number of tokens
     * @param filePaths: absolute file path of the files
     *                   with cppExtensions
     */
    public File(int desireTokens, String filePaths) {
        this.filePaths = filePaths;
        this.numOfTokens = desireTokens;
    }

    /**
     * Copy Constructor
     * @param src: File objects to be copied
     */
    public File (File src) {
        this.filePaths = src.filePaths;
        this.numOfTokens = src.numOfTokens;
    }

    /**
     * Set the file paths that are being read
     * @param file: absolute file path of the files
     *              with cppExtensions
     */
    public void setfilePaths(String file) {
        this.filePaths = file;
    }

    /**
     * @return: get all the list of file paths
     * 
     */
    public String getfilePaths() {
        return this.filePaths;
    }

    /**
     * set the number of desired tokens
     * @param n: the number of tokens
     */
    public void setNumOfTokens(int n) {
        this.numOfTokens = n;
    }

    /**
     * @return: get the number of tokens
     * 
     */
    public int getNumOfTokens() {
        return this.numOfTokens;
    }

     /**
     *  Check for equivalence based on name
     *  
     *  @param rhs object against which to compare equality
     *  
     *  @return true if two Css objects are equal
     */
    public boolean equals(Object rhs) {
        File f = (File) rhs;

        return this.filePaths.equals(f.filePaths);
    }

     /**
     *  Hashcode
     *  
     *  @return integer hashcode
     */
    public int hashCode() {
        
        return this.filePaths.hashCode();
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
        return ("filePath: " + filePaths.toString());
    }

}
