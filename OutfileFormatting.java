// Author: Kevin L
// https://github.com/kle510
// November 2016

/*
 * OutfileFormatting Class
 * 
 * Takes in output string from RandomTextGenerator class as input
 * Splits the string based on its words
 * Divides the string by line under the constraint of 80 characters per line
 * Condition: 80 character lines length, flush left, ragged right
 * 
 * 

*/

public class OutfileFormatting {

	private final static char MAX_WIDTH = 80;

	/*
	 * outFileFormat method
	 * 
	 * Split the string input into words in a String array. Add each word to
	 * line such that no line is more than 80 characters.
	 * 
	 * @param input the string from RandomTextGenerator output.
	 * 
	 * @return Resulting string in appropriate format.
	 * 
	 */

	public String outfileFormat(String input) {
		String result = "";
		String currentLine = "";
		String space = " ";
		String[] splitString = input.split(" ");

		for (int i = 1; i < splitString.length; i++) {
			if (currentLine.isEmpty()) {
				currentLine += splitString[i] + space;

			} else if (currentLine.length() + splitString[i].length() < MAX_WIDTH) {
				currentLine += splitString[i] + space;

			} else {
				result += currentLine + "\r\n";
				currentLine = splitString[i] + space;
			}
		}

		if (!currentLine.isEmpty()) {
			result += currentLine + "\r\n";
		}

		return result;
	}

}
