Title: DupDetector - Sample Solution (reqts only) for Phase 1
Author: Steven J Zeil:
Date: @docModDate@
TOC: yes

# Requirements

## Functional Requirements

I chose to divide by "feature". This program really does not have
distinct modes, and there's only one user group, so division by
"operating mode" or by "user class" would not actually yield any
divisions. Division by "Object" might be possible, but presumes an OO
Analysis has already been completed. 

The features that I settled on were input handling, properties,
duplicate code detection, and the refactoring report.

### Input handling

1. The system shall run from the command line.
2. The first command parameter will supply the number of desired refactorings to be printed.
3. The second command parameter is optional. If present it gives the path to a _properties file_, identified by an `.ini` extension.
4. The remaining CLI parameters will be interpreted as paths to files and directories.
5. Each file in this list will be considered a C++ source code file regardless of extension.
6. Each directory in this list will be searched, together with any directories nested within it, for files whose name ends with one of the extensions listed in CppExtensions. These will be considered C++ source code files.
7. Each C++ source code file will be divided into tokens. [Normally, this would be a design decision, not a requirement, but because the output includes a token count, tokenization becomes a requirement.]
8. Tokens will not be assigned to whitespace or comments.

### Properties

9. A legal properties file consists of zero or more lines of the form:

    _propertyName_ `=` _value_

    The '=' is separated by zero or more blanks from the strings to wither side.

10. Legal property names are: CppExtensions, MinSequenceLength, and MaxSubstitutions.
11. Legal values for property CppExtensions consist of a comma-separated list of one or more file extensions.
12. File extensions in the CppExtensions list are case sensitive.
13. File extensions in the CppExtensions list may not contain commas.
14. If not specified in the properties file, CppExtensions defaults to "cpp,h".
15. Legal values for properties MinSequenceLength and MaxSubstitution are positive integers.
16. If not specified in the properties file, MinSequenceLength defaults to 10.
17. If not specified in the properties file, MaxSubstitutions defaults to 8.


### Duplicate Code Detection

18. The program will compute a collection of potential refactorings.
19. Each such refactoring will consist of two or more "nearly identical" subsequences of the tokens from one or more C++ files.
20. A set of sequences of tokens are nearly identical if
    1. they have the same number of tokens
    2. that number of tokens is greater than or equal to MinSequenceLength.
    3. between any pair of sequences in the set, a one-to-one mapping of lexemes can be found that would make the resulting lexemes sequences identical
    4. The set of tokens involved in this mapping is identical for each such pair of sequences
    5. the number of substitutions in those mappings is no greater than MaxSubstitutions
21. The _opportunity value_ for a refactoring is defined as $(n-1)*k$ where $n$ is the number of sequences and $k$ is the token length of the sequences.


### Refactoring Report

22. Output from the program is in two sections.
23. The first section consists of a list of all C++ source files found in the input files nd directories listed on the command line.
24. Each file listed in section 1 will be displayed as the absolute path to the files, followed by the number of tokens in the file.
25. Files will be listed in section 1 in ascending order by absolute path.
26. Sections 1 and 2 of the output will be separated by an empty line.
27. Section 2 of the output will consist of a list of suggested refactorings.
28. If fewer refactorings are found than were requested by the first
   command line parameter, only those found will be printed.
29. If more refactorings are found than were requested by that parameter, only the number requested will be printed.

30. The refactorings displayed in the output will be presented in non-increasing order by opportunity value, starting with the maximum opportunity found.
31. Each refactoring in the output begins with a line presenting the number of tokens and the opportunity cost for that refactoring.
32. This is followed by each of the sequences making up the proposed refactoring, presented in non-descending order by absolute path of the files involved, with ties broken by line numbers in ascending order.
33. Each sequence described by two lines, a starting position and list of substitutions.
34. The starting position consists of the absolute path to the file containing the sequence, the line number on which the first token in the sequence occurred, and column within which it occurred, separated by a ':'.   Line and column numbers begin at 1.
35. The list of substitutions consists of a list of lexemes that would need to be replaced to make this sequence identical to any of the others.  
    1. These lexemes are separated by a blank.
    2. If the lexeme is a string or character constant, appropriate quotation marks will be included.
    3. The ordering of the lexemes must be consistent from sequence to sequence within a refactoring, so that pairs of lexemes 
        from matching positions in two sequences denote the actual substitution that would be required.
37. Each refactoring will be separated followed in the output by an empty line.
38. After the list of refactorings, the program shall print a line "Printed $k$ of $n$ suggestions.", where $k$  is the number printed and $n$ is the number of refactorings that were found to satisfy the requirements stated earlier.

## Nonfunctional Requirements

1. Implemented in Java 11
2. Portable across Windows, MacOS, & Linux.
3. Delivered in `DupDetector.jar`.
4. Jar file includes all compiled code, data, & 3rd-party libraries required for execution.
5. System shall handle up to ?? source code files up to ?? characters/tokens.  (You must quantify the ?? with with a reasonable values.)