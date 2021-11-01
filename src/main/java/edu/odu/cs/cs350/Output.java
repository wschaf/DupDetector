package edu.odu.cs.cs350;

/**
 * 
 * Display the result into two section
 * Section one prints all the absolute file paths and the number of tokens within its file
 * 
 */
public class Output {

    /**
     * Print section one of the output
     */
    public void printSectionOne() {
        
        // Create a ListOfFiles object to add multple files
        ListOfFiles files = new ListOfFiles();

        // This is a fake array of files
        String[] listFiles = {"DupDetector/src/main/data/edu/odu/cs/cs350/boo.cpp",
                              "DupDetector/src/main/data/edu/odu/cs/cs350/boz.cpp",
                              "DupDetector/src/main/data/edu/odu/cs/cs350/foo.cpp"};

        System.out.println("Files Scanned:");
        for(int i = 0; i < listFiles.length; i++) {
            // Initialize a file with file path from listFiles
            File f = new File(listFiles[i]);
            // count number of tokens in this file
            f.countTokens();
            // add the file in files
            files.addFiles(f);
            // print the file path and number of tokens of this file
            // System.out.println(f.getfilePath() + ", " + f.getNumOfTokens());
        }

        // print the file path and number of tokens of each file
        for(int i = 0; i < files.numFiles(); ++i) {
            System.out.println(files.getListOfFiles().get(i));
        }
        
    }
    
    public void printSectionTwo() {
    	
    	List<File> Files = new ArrayList<>();
    	
    	for(int i = 0; i < 5; i++) {
    		// initialize file to pass
    		File F = new File("DupDetector/src/main/data/edu/odu/cs/cs350/boo.cpp");
    		System.out.println("Opportuniy" + F.getOpportunities() + ", " + F.getNumRefTokens());
    		//incomplete
    		for (File f : Files) {
    		Scanner fileScanner = new Scanner(F);
    		
    		int lineNumber = 0;
            while(fileScanner.hasNextLine()){
                System.out.println(fileScanner.nextLine());
                lineNumber++;
            }

            fileScanner.close();
            System.out.printf("\t" + f.getfilePath() + ", " + lineNumber);
            
    		}
    }
    }


}
