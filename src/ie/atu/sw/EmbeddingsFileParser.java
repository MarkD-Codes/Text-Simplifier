package ie.atu.sw;

import java.io.*;
import java.util.HashMap;

/**
 * Extends TextFileParserator. Takes a text file containing a list of words and
 * their embeddings. The target file is specified by a user-input pathname and
 * should be formatted as: the word, followed by the word's embedding values
 * separated by commas. This class in particular takes an embeddings file and
 * returns a HashMap with the word as the <b>Key</b> and the embeddings
 * (represented as an array of doubles) as the <b>Value</b>.
 * 
 * @author Mark Davidson
 * @version 1.0
 * @see TextFileParser
 */
public class EmbeddingsFileParser extends TextFileParser {
	LineCounter lc = new LineCounter();

	// Big-O of "parse": O(n^2) as it iterates through a file line by line, a nested
	// loop looping over each line
	/**
	 * The method takes a text file containing words and their embeddings. It
	 * returns a HashMap that keys the words to their embeddings as values. The
	 * embeddings are represented as arrays of doubles. It uses a line counter to
	 * iterate over the file.
	 * 
	 * @param embeddingFile The pathname of the file to be parsed.
	 * @exception In the case that the filename
	 */
	@Override
	public HashMap<String, double[]> parse(String embeddingFile) {
		HashMap<String, double[]> hm = new HashMap<String, double[]>();
		int lineCount = 0;

		try {
			BufferedReader br = new BufferedReader((new InputStreamReader(new FileInputStream(embeddingFile))));
			double[] embeddings = null;
			String word = "";
			String line;
			lineCount = lc.fileLineCounter(embeddingFile);

			for (int i = 0; i < lineCount; i++) {
				line = br.readLine();
				word = getWord(line);
				embeddings = getEmbeddings(line); // contains a for loop! O(n)
				hm.put(word, embeddings);
			}

			br.close();

		} catch (Exception e) {
			System.out.println("The Specified Embeddings file was not found.");
			e.printStackTrace();
		}
		return hm;
	}

	/*
	 * Big-O: O(n). That is, in the worst case, as time complexity depends on the
	 * length of the string as the algorithm searches for the regex.
	 */
	/**
	 * Divides a String into two substrings. Used to separate the word from it's
	 * following embedding values in this case.
	 * 
	 * @param s A String of comma separated values.
	 * @return The first substring, split around the first instance of ",".
	 */
	private String getWord(String s) {
		String[] tempArray = s.split(",", 2);
		return tempArray[0];
	}

	// Big O : O(n) Looping over an array and split, complexity of both determined
	// by n
	// I reworked some code from my OOSD Summer project here.
	/**
	 * Splits a String containing comma separated values into substrings and then
	 * converts those substrings into doubles and loads them into an array.
	 * 
	 * @param s The String of comma separated numbers to be split.
	 * @return An array of doubles.
	 */
	private double[] getEmbeddings(String s) {
		String[] parts = s.split(", ", 2);
		String[] embeddingsStrings = parts[1].split(", ");
		double[] embeddings = new double[embeddingsStrings.length];

		for (int i = 0; i < embeddingsStrings.length; i++) {
			embeddings[i] = Double.parseDouble(embeddingsStrings[i]);
		}
		return embeddings;
	}

}
