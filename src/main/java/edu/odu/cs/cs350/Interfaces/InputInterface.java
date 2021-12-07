package edu.odu.cs.cs350.Interfaces;

import java.io.*;
import java.util.*;

public interface InputInterface {

    /**
	 * @param f: The file given from the input, provided by the user.
	 * @return Character stream of the file that will be read
	 * in token analyzer
	 */
	public Reader readFiles(File f);

    public void setTokens();

    public List<File> getFiles();

    public List<? extends TokenInterface> getTokens();

    public int getNSuggestions();

    public Dictionary<File, Integer> getTokenCountForFiles();

    public int getTokenCountForFile(File file);
}
