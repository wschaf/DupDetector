/*
Complete DupDetector Output as follows:

Files scanned:
	<FileAbsolutePath>, <NumberofTokens>
	<FileAbsolutePath>, <NumberofTokens>
	<FileAbsolutePath>, <NumberofTokens>

Opportunity <Opportunity#>, <NumberofTokensInOpportunity> tokens
<RefactoringAbsolutePath>:<RefactoringLineNumber>:<RefactoringColumnNumber>
<ListofTokens>

*/

package edu.odu.cs.cs350;

import edu.odu.cs.cs350.Interfaces.*;

import java.util.*;

public class Output implements OutputInterface {
    
	private int refactoringsToPrint;
	private List<FileInterface> files;
	private List<RefactoringInterface> refactorings;

	public Output() {
		refactoringsToPrint = 0;
		files = new ArrayList<FileInterface>();
		refactorings = new ArrayList<RefactoringInterface>();
	}

	public Output(int refactoringsToPrint, List<? extends FileInterface> files, List<? extends RefactoringInterface> refactorings) {
		this.refactoringsToPrint = refactoringsToPrint;

		List<FileInterface> fileList = new ArrayList<FileInterface>();
		for (var f : files) fileList.add(f);
		this.files = fileList;

		List<RefactoringInterface> rList = new ArrayList<RefactoringInterface>();
		for (var r : refactorings) rList.add(r);
		this.refactorings = rList;
	}

	@Override
	public int getRefactoringsToPrint() {
		return refactoringsToPrint;
	}

	@Override
	public void setRefactoringsToPrint(int refactoringsToPrint) {
		this.refactoringsToPrint = refactoringsToPrint;
	}

	@Override
	public List<FileInterface> getFiles() {
		return files;
	}

	@Override
	public void setFiles(List<? extends FileInterface> files) {
		List<FileInterface> fileList = new ArrayList<FileInterface>();
		for (var f : files) fileList.add(f);
		this.files = fileList;
	}

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

	@Override
	public String getSectionOne() {
		if (files.size() == 0) return new String();
		String sectionOne = new String();
		sectionOne = sectionOne + "Files Scanned:\n";
		String f = new String();
		for (var file : files) {
			f = f + "    ";
			f = f + file.getAbsolutePath();
			f = f + ", ";
			f = f + file.getNumberOfTokens();
			f = f + "\n";
		}
		sectionOne = sectionOne + f;
		return sectionOne;
	}

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
