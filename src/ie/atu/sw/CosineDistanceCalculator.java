package ie.atu.sw;

/**
 * An <b>implementation</b> of <code>EmbeddingsDistanceCalculator</code>. It
 * compares two words based on their GloVe embeddings by calculating their
 * cosine similarity: the dot product of their vectors divided by the product of
 * their lengths.
 * 
 * @author Mark Davidson
 * @see EmbeddingDistanceCalculator
 * @see DotProductDistanceCalculator
 */
public class CosineDistanceCalculator implements EmbeddingDistanceCalculator {

	DotProductDistanceCalculator dp = new DotProductDistanceCalculator();

	// Big-O: O(n), as the method loops over an array, so running time increases
	// with the array size.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double compare(double[] da1, double[] da2) {
		double distance = 0;
		double sumOfSquaresDa1 = 0;
		double sumOfSquaresDa2 = 0;
		double dotProduct = dp.compare(da1, da2);
		for (int i = 0; i <= da1.length - 1; i++) {
			double j = (da1[i] * da1[i]);
			sumOfSquaresDa1 = sumOfSquaresDa1 + j;

			double k = (da2[i] * da2[i]);
			sumOfSquaresDa2 = sumOfSquaresDa2 + k;
		}

		distance = dotProduct / (Math.sqrt((sumOfSquaresDa1) * (sumOfSquaresDa2)));
		return distance;
	}

}
