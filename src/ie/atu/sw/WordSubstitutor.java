package ie.atu.sw;

import java.util.Map;

/**
 * Takes in a word and swaps it for a more common synonym.
 * 
 * @author Mark Davidson
 * @version 1.0
 */
public class WordSubstitutor {

	// Big-O: O(1) as key based operation.
	/**
	 * Checks if input String can be found in target map containing common words.
	 * 
	 * @param s Word to be checked.
	 * @param m Reference Map, generated from user specified text file.
	 * @return True if word has been found in map of common words.
	 */
	private boolean checkIfCommon(String s, Map<String, double[]> m) {
		if (m.containsKey(s)) {
			return true;
		} else {
			return false;
		}
	}

	// Big O: O(n log(n)), as findSynonym (worst case) is O(n log(n)) n =
	// embedMap.size
	/**
	 * Takes a String(word) as an input and returns a more common synonym for that
	 * word.
	 * 
	 * @param s
	 * @param embedMap         Map generated by EmbeddingsFileParser
	 * @param googleMap        Map generated by Google1000Parser
	 * @param comparisonMethod User selected comparison method.
	 * @return Either the input word, if it is common, or a synonym.
	 * @see SynonymFinder
	 * @see EmbeddingDistanceCalculator
	 * @see Google1000Parser
	 * @see EmbeddingsFileParser
	 */
	public String substitute(String s, Map<String, double[]> embedMap, Map<String, double[]> googleMap,
			int comparisonMethod) {
		SynonymFinder sf = new SynonymFinder();
		String t = "";
		if (checkIfCommon(s, googleMap)) {
			return s;
		} else {
			t = sf.findSynonym(s, embedMap, googleMap, comparisonMethod); // O(n log(n))
			return t;
		}
	}

}
