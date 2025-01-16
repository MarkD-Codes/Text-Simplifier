package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Counts the number of lines in a text file, tracking lines by updating a
 * lineCount variable.
 * 
 * @author Mark Davidson
 * @version 1.0
 */
public class LineCounter {

	private int lineCount = 0;

	// Big-O: O(n) as the time complexity depends on n = the number of lines in the
	// text file.
	/**
	 * Iterates over a file using a Buffered Reader, incrementing the lineCount
	 * variable each time the reader encounters a line.
	 * 
	 * @param textFile A text file.
	 * @return The lineCount value
	 * @throws IOException
	 */
	public int fileLineCounter(String textFile) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(textFile)));

		while (br.readLine() != null)
			lineCount++;

		br.close();

		return lineCount;
	}

}
