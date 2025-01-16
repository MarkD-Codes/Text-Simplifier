package ie.atu.sw;

//Code taken from OOSD Summer project
/**
 * Checks if user input in command line interface is an integer, prompts user
 * for valid input if not.
 */
public class UserInputChecker {

	// Big-O: O(1)
	/**
	 * Checks if a String can be converted to an int. If not, input is invalid and
	 * user is prompted to try again.
	 * 
	 * @param s String to be checked.
	 * @return The integer value of the String, if it is valid.
	 */
	public int InputChecker(String s) {
		int validInt = 0;
		boolean isValid = false;

		// Keep asking for input until a valid integer is entered
		if (!isValid) {
			try {
				validInt = Integer.parseInt(s);
				isValid = true; // If no exception, input is valid
			} catch (NumberFormatException e) {
				// Catch the exception and prompt the user to input again
				System.out.println("Invalid input. Please input a number to choose a menu option");
			}
		}
		return validInt; // Return the valid integer
	}

}
