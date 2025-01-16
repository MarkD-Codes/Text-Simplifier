package ie.atu.sw;

/**
 * An abstract class for parsing a text file. Inheriting classes can return any
 * Object type. The expected return type is a Collection, Array or Map of
 * Strings.
 * 
 * @author Mark Davidson
 * @version 1.0
 */
public abstract class TextFileParser {
	/**
	 * @param textFile The pathname of the text file to be parsed.
	 * @return An object. Typically an Array, Collection or Map containing Strings.
	 */
	public abstract Object parse(String textFile);

}
