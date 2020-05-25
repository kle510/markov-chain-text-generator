import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.Random;

/*
 * Prefix Class
 * 
 * Used to represent a HashMap of sequences of words generated from file.
 * Key = List of word sequences that precede the successor word.
 * Value = Successor word that come with a particular word sequence.
 * 
 */

public class Prefix {

	private HashMap<List<String>, Successor> map;

	public Prefix() {
		map = new HashMap<List<String>, Successor>();

	}

	/*
	 * add method
	 * 
	 * Retrieve the word sequence from the HashMap if the word sequence is already
	 * there Add word sequence to HashMap if the word sequence isn't there already
	 * Add the successor word to that word sequence.
	 */

	public void add(List<String> wordSequence, String nextWord) {

		if (map.containsKey(wordSequence)) {
			Successor successor = map.get(wordSequence);
			successor.add(nextWord);
			map.put(wordSequence, successor);
		} else {
			Successor successor = new Successor();
			successor.add(nextWord);
			map.put(wordSequence, successor);
		}
	}

	/*
	 * getAllWords method
	 * 
	 * Passes source file through Scanner to read text from file Adds words to
	 * ArrayList of strings
	 */

	public static ArrayList<String> getAllWords(String inFile, int prefixLength) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		Scanner s = new Scanner(new File(inFile));
		while (s.hasNext()) {
			list.add(s.next());
		}

		if (prefixLength >= list.size()) {
			throw new InvalidDataException("prefix Length >= number of words in sourceFile");
		}
		s.close();

		return list;

	}

	/*
	 * buildWordSequences method
	 * 
	 * Receives ArrayList of words as parameter. Cuts them into word sequences based
	 * on prefixLength. Cuts out the successor word following the sequence. Adds the
	 * word sequence and successor word to the HashMap.
	 */

	public void buildWordSequences(ArrayList<String> list, int prefixLength) {

		for (int i = 0; i < list.size() - prefixLength; i++) {
			List<String> wordSequence;
			String nextWord;
			wordSequence = list.subList(i, i + prefixLength);
			nextWord = list.get(i + prefixLength);
			add(wordSequence, nextWord);
		}
	}

	/*
	 * processInputText method
	 * 
	 * Retrieves name of sourceFile and prefixLength. Executes getAllWords and
	 * buildWordSequences method.
	 */

	public void processInputText(String inFile, int prefixLength) throws IOException {
		ArrayList<String> inputText = getAllWords(inFile, prefixLength);
		buildWordSequences(inputText, prefixLength);
	}

	/*
	 * getNextWord method
	 * 
	 * Retrieves a word sequence as input and gets a successor word from the
	 * sequence.
	 */

	public String getNextWord(List<String> wordSequence, boolean DEBUG) {

		if (map.containsKey(wordSequence)) {

			Successor sucessor = map.get(wordSequence);
			String successorWord = sucessor.getRandomWord(DEBUG);
			return successorWord;
		}

		// No word sequence case is handled in outputText method of RandomTextGenerator
		return "";
	}

	/*
	 * getAllSuccessors method Returns all successors for a word sequence.
	 */

	public Set<String> getAllSuccessors(List<String> wordSequence) {

		Successor sucessor = map.get(wordSequence);
		Set<String> successorSet = sucessor.getSuccessors();
		return successorSet;

	}

	/*
	 * getRandomSequence method
	 * 
	 * Generates a random number between 0 and total number of sequences. Accesses
	 * key set of word sequences and puts it in for each loop. Random number tells
	 * us when to stop and what value to return.
	 * 
	 */

	public List<String> getRandomSequence(boolean DEBUG) {

		Set<List<String>> allSequences = map.keySet();

		int item = new Random().nextInt(allSequences.size());
		if (DEBUG) {
			item = new Random(1).nextInt(allSequences.size());
		}

		int i = 0;
		for (List<String> sequence : allSequences) {
			if (i == item) {
				return sequence;
			}
			i++;
		}

		// Empty keySet case handled in getAllWords method
		return Collections.emptyList();

	}

	/*
	 * Override hashCode method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		return result;
	}

	/*
	 * Override equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Prefix other = (Prefix) obj;
		if (map == null) {
			if (other.map != null) {
				return false;
			}
		} else if (!map.equals(other.map)) {
			return false;
		}
		return true;
	}
}