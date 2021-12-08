package edu.odu.cs.cs350.Interfaces;

import java.util.*;
import java.io.File;

/**
 * Output contains all of the functionality to format and print to the console
 * the complete output of the program. This includes the list of files, their metadata,
 * the list of suggested refactorings, and their metadata.
 */
public abstract interface OutputInterface {

	public int getRefactoringsToPrint();

	public void setRefactoringsToPrint(int refactoringsToPrint);

	public List<File> getFiles();

	public void setFiles(List<File> files);

	public List<RefactoringInterface> getRefactorings();

	public void setRefactorings(List<? extends RefactoringInterface> refactorings);

	public String getSectionOne();

	public String getSectionTwo();

	public String getCompleteOutput();
}
