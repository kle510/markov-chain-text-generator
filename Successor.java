// Author: Kevin L
// https://github.com/kle510
// November 2016

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/*
 * Successor Class
 * 
 * Helper class to store and access successor words and their quantities.
 * Uses a hash map as successor structure to organize successors.
 * Key = Successor word to the prefix. Type is String.
 * Value = Number of times that successor occurs after the prefix. Type is Integer.
 * 
 */

public class Successor {

	private HashMap<String, Integer> successor;
	private int size = 0; // initialize amount of successors

	/*
	 * Successor Constructor
	 * 
	 * Creates a new HashMap.
	 */

	public Successor() {
		successor = new HashMap<String, Integer>();

	}

	/*
	 * getSize method
	 * 
	 * @return number of successors.
	 * 
	 */

	public int getSize() {
		return size;
	}

	/*
	 * getSize method
	 * 
	 * @return set of successors.
	 * 
	 */

	public Set<String> getSuccessors() {
		return successor.keySet();
	}

	/*
	 * add Method
	 * 
	 * Increase total number of successor words by one. Adds a successor word to
	 * the HashMap if it isn't there already Increments amount of the successor
	 * word by one if it's in the HashMap. Updates the most frequent successor
	 * word.
	 * 
	 * 
	 * @param successor word.
	 * 
	 */

	public void add(String successorWord) {

		size++;

		if (successor.containsKey(successorWord)) {
			int num = successor.get(successorWord);
			num++;
			successor.put(successorWord, num);
		}

		else {
			successor.put(successorWord, 1);
		}

	}

	/*
	 * getRandomWord method
	 * 
	 * Generates a random number between 0 and total number of successors .
	 * Accesses key set of successor words and puts it in for each loop. Random
	 * number tells us when to stop and what value to return. As each successor
	 * word is looped, ask how many times the word appeared and add it to the
	 * sum. If the random number generated is less than sum, return the word.
	 * This way, probability is handled so that the chances of choosing the
	 * random successor word is proportional to the frequency of the word in the
	 * set.
	 * 
	 * @return successor word. 
	 */

	public String getRandomWord(boolean DEBUG) {

		
		int random = new Random().nextInt(getSize());
		if (DEBUG) {random = new Random(1).nextInt(getSize());}
		int sum = 0;

		for (String randomWord : successor.keySet()) {

			sum += successor.get(randomWord);

			if (random < sum) {
				return randomWord;
			}
		}

		return ""; // potential room for error handled in upper levels.

	}

	/*
	 * override equals method
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
		Successor other = (Successor) obj;
		if (successor == null) {
			if (other.successor != null) {
				return false;
			}
		} else if (!successor.equals(other.successor)) {
			return false;
		}
		return true;
	}

	/*
	 * override hashCode method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((successor == null) ? 0 : successor.hashCode());
		return result;
	}

}
