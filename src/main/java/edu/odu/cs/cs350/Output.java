package edu.odu.cs.cs350;

import edu.odu.cs.cs350.Interfaces.*;

import java.util.*;
import java.io.*;

/**
 * Output contains all of the functionality to format and print to the console
 * the complete output of the program. This includes the list of files, their metadata,
 * the list of suggested refactorings, and their metadata.
 */
public class Output implements OutputInterface {
    
	private Input input;
	private Recommender recommender;
	private int refactoringsToPrint;
	private List<File> files;
	private List<RefactoringInterface> refactorings;

	/**
	 * The default constructor for Output. Creates a new Output object
	 * by calling the default constructor for each data member in turn.
	 */
	public Output() {
		refactoringsToPrint = 0;
		files = new ArrayList<File>();
		refactorings = new ArrayList<RefactoringInterface>();
	}

	/**
	 * 
	 * @param refactoringsToPrint The number of refactoring suggestions to be printed in the final output.
	 * @param files The list of files that have been processed by the system.
	 * @param refactorings The list of suggested refactorings that resulted from processing @param files.
	 */
	public Output(int refactoringsToPrint, List<File> files, List<? extends RefactoringInterface> refactorings) {
		this.refactoringsToPrint = refactoringsToPrint;

		//	Java tip: You cannot assign a interface list to a concrete list; you have to iterate through the list.
		List<File> fileList = new ArrayList<File>();
		for (var f : files) fileList.add(f);
		this.files = fileList;

		List<RefactoringInterface> rList = new ArrayList<RefactoringInterface>();
		for (var r : refactorings) rList.add(r);
		this.refactorings = rList;
	}

	public Output(Input input, Recommender recommender) {
		this.input = input;
		this.recommender = recommender;
		this.refactoringsToPrint = input.getNSuggestions();

		List<File> fileList = new ArrayList<File>();
		for (var f : input.getFiles()) fileList.add(f);
		this.files = fileList;

		List<RefactoringInterface> rList = new ArrayList<RefactoringInterface>();
		for (var r : this.recommender.getRefactorings()) rList.add(r);
		this.refactorings = rList;
	}

	/**
	 * @return the number of suggested refactorings that will be
	 * printed in the final output.
	 */
	@Override
	public int getRefactoringsToPrint() {
		return refactoringsToPrint;
	}

	/**
	 * @param refactoringsToPrint sets the number of refactorings
	 * that will be printed in the final output.
	 */
	@Override
	public void setRefactoringsToPrint(int refactoringsToPrint) {
		this.refactoringsToPrint = refactoringsToPrint;
	}

	/**
	 * @return the list of file objects that have been processed by the system.
	 */
	@Override
	public List<File> getFiles() {
		return files;
	}

	/**
	 * Sets the list of files that will be output in Section 1 of
	 * the final output.
	 * @param files is a list of file objects.
	 */
	@Override
	public void setFiles(List<File> files) {
		List<File> fileList = new ArrayList<File>();
		for (var f : files) fileList.add(f);
		this.files = fileList;
	}

	/**
	 * @return the list of Refactoring objects to be printed in Section 2 of the final output.
	 */
	@Override
	public List<RefactoringInterface> getRefactorings() {
		return refactorings;
	}

	@Override
	public void setRefactorings(List<? extends RefactoringInterface> refactorings) {
		List<RefactoringInterface> rList = new ArrayList<RefactoringInterface>();
		for (var r : refactorings) rList.add(r);
		this.refactorings = rList;
	}

	/**
	 * @return a string containing the complete output for Section 1.
	 * Section 1 includes the absolute path for each file processed
	 * and the total number of tokens discovered in that file.
	 * Returns an empty string if there were no files processed.
	 */
	@Override
	public String getSectionOne() {
		if (files.size() == 0) return new String();
		String sectionOne = new String();
		sectionOne = sectionOne + "Files Scanned:\n";
		String f = new String();
		for (File file : files) {
			f = f + "    ";
			f = f + file.getAbsolutePath();
			f = f + ", ";
			f = f + input.getTokenCountForFile(file);
			f = f + "\n";
		}
		sectionOne = sectionOne + f;
		return sectionOne;
	}

	/**
	 * @return a string containing the complete output for Section 2.
	 * Section 2 includes the number of tokens, absolute path,
	 * line/column number for the start, and the list of tokens
	 * in each refactoring opportunity.
	 * Returns an empty string if there were no files processed.

	 * If @param refactoringsToPrint less than the number of refactorings,
   
	 * prints all refactorings. Else, prints only that number of refactorings.
	 */
	@Override
	public String getSectionTwo() {
		String s2 = new String();
		if (files.size() == 0) return s2;
		for (int i = 0; i < refactoringsToPrint; i++) {
			s2 = s2 + "Opportunity #";
			s2 = s2 + Integer.toString(i + 1);
			s2 = s2 + ", ";
			s2 = s2 + refactorings.get(i).getNumberOfTokens();
			s2 = s2 + " tokens\n";
			s2 = s2 + refactorings.get(i).getAbsolutePath();
			s2 = s2 + ":";
			s2 = s2 + refactorings.get(i).getLineNumber();
			s2 = s2 + ":";
			s2 = s2 + refactorings.get(i).getColumnNumber();
			s2 = s2 + "\n";
			s2 = s2 + refactorings.get(i).getTokenList();
			s2 = s2 + "\n";
		}
		return s2;
	}

	/**
	 * Cocatenates the strings for Section 1 and Section 2.
	 * @return the complete string to printed to standard output.
	 */
	@Override
	public String getCompleteOutput() {
		if (files.size() == 0) return "No files scanned.\n";
		String s = new String();

		s = s + getSectionOne();
		s = s + "\n";
		s = s + getSectionTwo();

		return s;
	}
}
