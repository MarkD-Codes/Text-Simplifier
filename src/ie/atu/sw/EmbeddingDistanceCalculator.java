package ie.atu.sw;

/**
 * The Interface EmbeddingsComparator is an <b>abstraction</b> of a tool for
 * calculating similarities between two words. The comparison is made based on
 * GloVe embeddings of each word represented as an array of Doubles.
 * 
 * @author Mark Davidson
 * @version 1.0
 * 
 */
public interface EmbeddingDistanceCalculator {

	/**
	 * Compares the embeddings arrays of two words.
	 * 
	 * @param da1 An array of embedding values for a given word.
	 * @param da2 Another array of embedding values for a word to be compared with
	 *            the first.
	 * @return Implementations should return a double value representing the
	 *         similarity of the two words being compared.
	 */
	public double compare(double[] da1, double[] da2);

}
