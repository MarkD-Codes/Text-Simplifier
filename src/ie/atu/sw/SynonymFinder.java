package ie.atu.sw;

import java.util.Map;
import java.util.TreeMap;

/**
 * Takes in a word and finds its closest match in the reference list of common
 * words. It returns the closest match.
 * 
 * @author Mark Davidson
 * @version 1.0
 */
public class SynonymFinder {

	CosineDistanceCalculator cdc = new CosineDistanceCalculator();
	DotProductDistanceCalculator dpdc = new DotProductDistanceCalculator();

	/*
	 * Big-O: O(n log(n)) as the compare method is O(k < n) and is called within a
	 * loop of O(n) to iterate over the values in a map. n = googleMap.size
	 */
	/**
	 * Takes in a String (word) and two HashMaps. The method checks the
	 * <b>googleMap</b> HashMap first, the Map containing the referenced common
	 * words. If the word is found in this Map, then it is simply returned. Else,
	 * the method finds the embeddings of the word in the <b>embedMap</b> and calls
	 * the <b>compare<b/> method to calculate similarity scores. Higher similarity
	 * scores imply higher similarity. All the scores:words are mapped into a
	 * TreeMap and the highest value score returned as the synonym.
	 * 
	 * @param word             Word for which a synonym is to be found.
	 * @param embedMap         The reference map generated by EmbeddingsFileParser.
	 * @param googleMap        The reference Map generated by Google1000Parser.
	 * @param comparisonMethod User set int that selects which comparison method to
	 *                         use.
	 * @return The best match for the target word OR the word itself if its
	 *         embeddings cannot be found.
	 */
	public String findSynonym(String word, Map<String, double[]> embedMap, Map<String, double[]> googleMap,
			int comparisonMethod) {
		String synonym = "";
		String wordLow = word.toLowerCase();

		if (embedMap.containsKey(wordLow)) {// O(1)
			double[] da1 = embedMap.get(wordLow);

			TreeMap<Double, String> rankMap = new TreeMap<>(); // sorted map ranks the common words in order of their
																// similarity to target word
			googleMap.forEach((String str, double[] da2) -> { // calculate similarity of common words to the target word
																// O(n)
				double similarity = compare(da1, da2, comparisonMethod); // O(n) similarity score returned
				rankMap.put(similarity, str); // O(log n) words from Google1000 loaded into TreeMap -> sorted by
												// similarity
			});

			synonym = rankMap.lastEntry().getValue();// retrieves value of highest key ie. most similar word from common
														// words list
			return synonym;
		} else {
			return word;
		}
	}

	/*
	 * Big-O: O(n) as compare methods of CosineDistanceCalculator and
	 * DotProductDistanceCalculator are both O(n). The switch operation is O(1), so
	 * complexity is O(1)*O(n).
	 */
	/**
	 * Calculates the similarity score between two words using the words' embeddings
	 * represented as double[]
	 * 
	 * @param da1              The embeddings array of word A.
	 * @param da2              The embeddings array of word B.
	 * @param comparisonMethod An <b>int</b> value, input by the user.
	 * @return The words' similarity score.
	 */
	private double compare(double[] da1, double[] da2, int comparisonMethod) { // O(n)
		double d = 0;
		switch (comparisonMethod) {
		case 1 -> d = cdc.compare(da1, da2);
		case 2 -> d = dpdc.compare(da1, da2);
		}
		return d;
	}

}
