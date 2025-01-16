package ie.atu.sw;

import java.io.*;
import java.util.*;

/**
 * Simplifies a piece of text from a .txt file. It scans the input file and
 * swaps words for more commonly used synonyms where possible. It outputs a new
 * text file, preserving punctuation.
 * 
 * @author Mark Davidson
 * @version 1.0
 */
public class TextSimplifier {

	EmbeddingsFileParser embedParser = new EmbeddingsFileParser();
	Google1000Parser gp = new Google1000Parser();
	InputTextParser itp = new InputTextParser();
	WordSubstitutor ws = new WordSubstitutor();

	/*
	 * Big O: O(n^2) where n = number of words in text to be converted... Method
	 * iterates over array list and at each String in that list calls a method that
	 * loops over another collection
	 */

	/**
	 * Simplifies a text file by replacing each word, where possible, with one from
	 * a user-specified list of common words. The user specifies a set of reference
	 * files to facilitate the substitution. The method outputs the simplified text
	 * as a .txt file.
	 * 
	 * @param embeddingFile    A text file containing a list of words and their
	 *                         GloVe embeddings.
	 * @param googleFile       A text file containing a list of common words to
	 *                         reference when simplifying the target text.
	 * @param userInputFile    The text file the user wishes to simplify.
	 * @param outputFile       The simplified text file-path, as specified by the
	 *                         user.
	 * @param comparisonMethod This int is specified by the user and chooses which
	 *                         method to use when comparing words.
	 * @see EmbeddingsFileParser
	 * @see Google1000Parser
	 * @see InputTextParser
	 * @see WordSubstitutor
	 */
	public void simplifyText(String embeddingFile, String googleFile, String userInputFile, String outputFile,
			int comparisonMethod) {

		HashMap<String, double[]> embedMap = new HashMap<>();
		HashMap<String, double[]> googleMap = new HashMap<>();
		Collection<String> inputText = new ArrayList<String>();
		embedMap = embedParser.parse(embeddingFile);
		googleMap = gp.parse(googleFile, embedMap);
		inputText = itp.parse(userInputFile);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			String word = "";

			for (String str : inputText) { // O(n) - iterating over array list
				if (str == "^\\w") {
					word = str;
				} else {
					word = ws.substitute(str, embedMap, googleMap, comparisonMethod); // O(n)
				}
				writer.write(word);
			}

			writer.close();

		} catch (FileNotFoundException e) {
			System.out.println("The specified file path for the text to be simplified could not be found.");
			System.out.println("Please check the file path and try again.");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
