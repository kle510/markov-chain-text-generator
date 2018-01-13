// Author: Kevin L
// https://github.com/kle510
// November 2016

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * RandomTextGenerator class
 * 
 * Takes in parameters for numWords, prefixLength, sourceFile, and outFile 
 * from GenText class and processes it to produce the outFile. 
 * 
 * 
 */

public class RandomTextGenerator {

	Prefix prefix;

	/*
	 * RandomTextGenerator Constructor
	 * 
	 * Creates a new prefix.
	 * 
	 */

	public RandomTextGenerator() {
		prefix = new Prefix();
	}

	/*
	 * inputText method
	 * 
	 * Retrieves name of sourcefile and prefixLength and processes it.
	 * 
	 * @param source file, prefix length.
	 * 
	 */

	public void inputText(String inFile, int prefixLength, boolean DEBUG) throws IOException {
		prefix.processInputText(inFile, prefixLength);
	}

	/*
	 * outputText method
	 * 
	 * Takes information from prefix and writes the output file.
	 * 
	 * @param name of output file, numWords, prefixLength.
	 * 
	 */

	public void outputText(String outFile, int numWords, int prefixLength, boolean DEBUG) throws IOException {

		List<String> currentWordSequence = new ArrayList<String>(prefix.getRandomSequence(DEBUG));
		
		if (DEBUG) {System.out.println("DEBUG: chose an initial prefix: " + currentWordSequence);}

		String output = "";

		for (int i = 0; i < numWords; i++) {
			if (DEBUG) {System.out.println("DEBUG: prefix: " + currentWordSequence);}

			String nextWord;

			if (prefix.getNextWord(currentWordSequence, DEBUG) == "") {
				if (DEBUG) {System.out.println("DEBUG: successors: <END OF FILE>");}

				currentWordSequence = new ArrayList<String>(prefix.getRandomSequence(DEBUG));

				if (DEBUG) {System.out.println("DEBUG: chose an initial prefix: " + currentWordSequence);}
				if (DEBUG) {System.out.println("DEBUG: prefix: " + currentWordSequence);}
			}
			
			nextWord = prefix.getNextWord(currentWordSequence, DEBUG);

			if (DEBUG) {System.out.println("DEBUG: successors: " + prefix.getAllSuccessors(currentWordSequence));}
			if (DEBUG) {System.out.println("DEBUG: word generated: " + nextWord);}

			output += " " + nextWord;
			
			if (DEBUG) {System.out.println("DEBUG: Output so far: " + output);}

			List<String> tempList = new ArrayList<String>();
			tempList = currentWordSequence.subList(1, prefixLength);
			tempList.add(nextWord);

			currentWordSequence = new ArrayList<String>(tempList);

		}

		File out = new File(outFile);
		BufferedWriter writer = new BufferedWriter(new FileWriter(out));
		OutfileFormatting o = new OutfileFormatting();
		writer.write(o.outfileFormat(output));
		writer.close();
	}

}
