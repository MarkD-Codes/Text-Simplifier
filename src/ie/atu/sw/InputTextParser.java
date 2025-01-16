package ie.atu.sw;

import java.io.*;
import java.util.*;

/**
 * Takes a text file and loads each word and non-word character into an
 * ArrayList, preserving the order that they appear in the text file.
 * 
 * @author Mark Davidson
 * @version 1.0
 * @see TextFileParser
 */
public class InputTextParser extends TextFileParser {
	
	Collection<String> inputTextList = new ArrayList<String>();
	LineCounter lc = new LineCounter();
	int lineCount = 0;

	/*
	 * Referenced ChatGPT: See Reference doc
	 * Big-O of "parse": O(n^2) as it iterates through a file line by line, splits
	 * each line into an array, then loops over the array
	 */
	/**
	 * Takes a text file and converts it into an ArrayList containing all word and
	 * non-word Strings in their order of appearance. This is to preserve
	 * punctuation and word-order.
	 */
	@Override
	public Collection<String> parse(String textFile) {
		try {
			BufferedReader reader = new BufferedReader((new InputStreamReader(new FileInputStream(textFile))));
			//Collection<String> inputTextList = new ArrayList<String>();
			String[] lineArray;
			String line;
			lineCount = lc.fileLineCounter(textFile);

			for (int i = 0; i < lineCount; i++) {
				line = reader.readLine();
				lineArray = line.splitWithDelimiters("\\W", 0);

				for (String str : lineArray) {

					inputTextList.add(str);

				}
			}
			reader.close();
			return inputTextList;

		} catch (Exception e) {
			System.out.println("The Specified file to be simplified was not found.");
		}
		
		return inputTextList;
	}

}
