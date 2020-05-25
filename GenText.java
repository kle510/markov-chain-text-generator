import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * GenText class
 * 
 * Contains main method.
 * Processes command line arguments.
 * Command line arguments are processed in order of: order of prefix, 
 * number of words in outputfile, name of input file, and name of output file.
 * Handles errors.
 * 
 */

public class GenText {
	public static void main(String[] args) {

		boolean DEBUG = false;
		int prefixLength;
		int numWords;
		String outFile;
		String inFile;

		try {
			if (args.length < 1) {
				throw new InvalidDataException(
						"Missing command line arguments. Format: java GenText [-d] prefixLength numWords sourceFile outFile");
			} else if (args.length < 4) {
				throw new InvalidDataException("Can't write output to file");
			} else if (args.length == 5) {
				if (args[0].equals("-d")) {
					DEBUG = true;
					prefixLength = Integer.parseInt(args[1]);
					numWords = Integer.parseInt(args[2]);
					inFile = args[3];
					outFile = args[4];
				} else {
					throw new InvalidDataException(
							"Too many command line arguments. Format: java GenText [-d] prefixLength numWords sourceFile outFile ");
				}
			} else if (args.length > 5) {
				throw new InvalidDataException(
						"Too many command line arguments. Format: java GenText [-d] prefixLength numWords sourceFile outFile ");
			} else {
				prefixLength = Integer.parseInt(args[0]);
				numWords = Integer.parseInt(args[1]);
				inFile = args[2];
				outFile = args[3];
			}

			if (prefixLength < 1) {
				throw new InvalidDataException(
						"prefixLength < 1 error. Format: java GenText [-d] prefixLength numWords sourceFile outFile ");
			} else if (numWords < 0) {
				throw new InvalidDataException(
						"numWords < 0 error. Format: java GenText [-d] prefixLength numWords sourceFile outFile ");
			}

			RandomTextGenerator randomText = new RandomTextGenerator();
			randomText.inputText(inFile, prefixLength, DEBUG);
			randomText.outputText(outFile, numWords, prefixLength, DEBUG);
		}
		catch (FileNotFoundException exception) {
			System.out.println("File not found");
			System.exit(0);
		} catch (NumberFormatException exception) {
			System.out.println(
					"prefixLength or numWords are not integers. Format: java GenText [-d] prefixLength numWords sourceFile outFile ");
			System.exit(0);
		} catch (InvalidDataException exception) {
			System.out.println("Invalid data: " + exception.getMessage());
			System.exit(0);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
