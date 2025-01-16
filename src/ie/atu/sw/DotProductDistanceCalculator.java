package ie.atu.sw;

/**
 * An <b>implementation</b> of <code>EmbeddingsDistanceCalculator</code>. It
 * compares two words based on their GloVe embeddings by calculating the dot
 * product of their vectors.
 * 
 * @author Mark Davidson
 * @see EmbeddingDistanceCalculator
 * @see CosineDistanceCalculator
 */
public class DotProductDistanceCalculator implements EmbeddingDistanceCalculator {

	/*
	 * Big-O: O(n), as the method loops over an array, so running time increases
	 * with the array size. Source:
	 * https://stackoverflow.com/questions/18775744/dot-product-with-arrays
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double compare(double[] da1, double[] da2) {

		double sum = 0;
		for (int i = 0; i <= da1.length - 1; i++) {
			sum = sum + (da1[i] * da2[i]);
		}
		return sum;
	}

}
