package edu.odu.cs.cs350.Interfaces;

import java.util.*;

public abstract interface OutputInterface {

	public int getRefactoringsToPrint();

	public void setRefactoringsToPrint(int refactoringsToPrint);

	public List<FileInterface> getFiles();

	public void setFiles(List<? extends FileInterface> files);

	public List<RefactoringInterface> getRefactorings();

	public void setRefactorings(List<? extends RefactoringInterface> refactorings);

	public String getSectionOne();

	public String getSectionTwo();

	public String getCompleteOutput();
}
