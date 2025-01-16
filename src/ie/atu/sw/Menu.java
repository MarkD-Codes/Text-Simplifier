package ie.atu.sw;

import static java.lang.System.out;

import java.util.Map;
import java.util.Scanner;

//Menu code substantially reused from OOSD Summer project
/**
 * Outputs a simple interactive menu display in command line. The user interacts
 * with the menu by inputting numbers to set parameters and select which options
 * to execute. The user inputs are taken in by a Scanner object.
 * 
 * @author Mark Davidson
 * @version 1.0
 * @see TextSimplifier
 */
public class Menu {
	private Scanner s;
	private boolean keepRunning = true;
	private String embeddingFile = "NOT ENTERED";
	private String googleFile = "NOT ENTERED";
	private String outputFilePath = ".//src//out.txt";
	private String inputFilePath = "NOT ENTERED";
	private UserInputChecker ic = new UserInputChecker();
	// 1 = Cosine (default); 2 = Dot-Product;
	private int comparisonMethod = 1;
	TextSimplifier ts = new TextSimplifier();

	// Big O: O(1) Constructing an instance of a class
	/**
	 * Constructor class for Menu. Creates a Scanner object to detect user inputs.
	 */
	public Menu() {
		s = new Scanner(System.in);
	}

	// Big-O: O(n). Printing a fixed length String to command line.
	/**
	 * Prints the Main Menu display to the command line.
	 */
	private void MainMenu() {
		System.out.println(ConsoleColour.YELLOW);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*             			Text Simplifier		               *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Embeddings File. Currently set to: " + embeddingFile);
		System.out.println("(2) Specify Google 1000 File. Currently set to: " + googleFile);
		System.out.println("(3) Specify an Output File. Currently set to: " + outputFilePath);
		System.out.println("(4) Specify an Input File. Currently set to: " + inputFilePath);
		System.out.println("(5) Specify Comparison Method Used To Find Synonyms for Words in User's Text");
		System.out.println("(6) Execute, Analyse and Report");
		System.out.println("(7) Quit");
		System.out.println("");
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-7]>");
		System.out.println();
	}

	// Big-O: O(1) Constant time operation
	/**
	 * Calls <code>MainMenu()</code> and listens for user input as long as the
	 * boolean keepRunning is true. Checks user input is valid using
	 * <code>InputChecker</code>. Uses switch to allow users to select options and
	 * call different methods.
	 * 
	 * @see UserInputChecker
	 * @see #setEmbeddingFile()
	 * @see #setGoogle1000File()
	 * @see #setOutputFile()
	 * @see #setInputFile()
	 * @see #setComparisonMethod()
	 * @see TextSimplifier
	 * 
	 */
	public void start() {

		while (keepRunning) {
			MainMenu();
			int choice = ic.InputChecker(s.next());// checks that user input is valid and returns valid int

			switch (choice) {
			case 1 -> setEmbeddingFile();
			case 2 -> setGoogle1000File();
			case 3 -> setOutputFile();
			case 4 -> setInputFile();
			case 5 -> setComparisonMethod();
			case 6 -> {
				ts.simplifyText(embeddingFile, googleFile, inputFilePath, outputFilePath, comparisonMethod);
				System.out.println("Your file " + inputFilePath + " has been simplified!");
				System.out.println("Enter any key and hit ENTER to return to the main menu");
				s.next();
			}
			case 7 -> quit();
			default -> out.println("[Error] Invalid Selection");
			}
		}
	}

	// Big-O: O(1) changing a variable is constant time
	/**
	 * Changes boolean <code>keepRunning</code> to <b>false</b>, quitting the
	 * programme
	 */
	private void quit() {
		System.out.println("Quitting Programme...");
		keepRunning = false;
	}

	// Big-O: O(1) changing a variable is constant time
	/**
	 * Allows user to change the comparisonMethod variable. This governs which
	 * method for calculating vector distances of embeddings is used during
	 * similarity calculations. The method prompts user to confirm their choice
	 * before returning to the Main Menu.
	 * 
	 * @see CosineDistanceCalculator
	 * @see DotProductDistanceCalculator
	 */
	private void setComparisonMethod() {
		out.println("**************************************");
		out.println("*******Select Comparison Method*******");
		out.println("**************************************");
		out.println("(1) Cosine Distance Comparison");
		out.println("(2) Dot-Product Comparison");
		out.println("(3) Back to Main Menu");

		int choice = ic.InputChecker(s.next());
		switch (choice) {
		case 1 -> {
			comparisonMethod = 1;
			out.println("You have set Analysis Method to Cosine Distance Calculator");
			out.println("Type any letter and press ENTER to go back to the Main Menu.");
			s.next();

		}
		case 2 -> {
			comparisonMethod = 2;
			out.println("You have set Analysis Method to Dot Product Distance Calculator");
			out.println("Type any letter and press press ENTER to go back to the Main Menu.");
			s.next();

		}
		case 3 -> MainMenu();
		default -> {
			out.println("[Error] Invalid Selection");
			setComparisonMethod();
		}
		}
	}

	// Big-O: O(1) changing a variable is constant time
	/**
	 * Prompts user to type the filepath for a text file contaning the reference
	 * words with their GloVe embeddings. User is asked to confirm filepath before
	 * returning to the Main Menu. This file is passed to the EmbeddingsFileParser.
	 * 
	 * @see EmbeddingsFileParser
	 */
	private void setEmbeddingFile() {

		out.println("Type the file path for your embeddings file here. Be sure to write full file-path correctly!>");
		String t = s.next();
		embeddingFile = t;

		out.println("You have set the embeddings file path as: " + embeddingFile);
		out.println("Is this correct?");
		out.println("(1) Yes! Return to the main menu!");
		out.println("(2) No! I need to retype the file name...");

		int choice = ic.InputChecker(s.next());

		switch (choice) {
		case 1 -> keepRunning = true;
		case 2 -> setEmbeddingFile();
		default -> {
			out.println("[Error] Invalid Selection");
			setEmbeddingFile();
		}
		}
	}

	// Big-O: O(1) changing a variable is constant time
	/**
	 * Prompts user to type the desired output file path for the simplified text
	 * file. User is asked to confirm the file path before returning to the Main
	 * Menu. TextSimplifier outputs a text file to this path.
	 * 
	 * @see TextSimplifier
	 */
	private void setOutputFile() {

		out.println("Type the file path for the output file here. Be sure to write full file-path correctly!>");
		String t = s.next();
		outputFilePath = t;

		out.println("You have set the Output file path as: " + outputFilePath);
		out.println("Is this correct?");
		out.println("(1) Yes! Return to the main menu!");
		out.println("(2) No! I need to retype the file name...");

		int choice = ic.InputChecker(s.next());

		switch (choice) {
		case 1 -> keepRunning = true;
		case 2 -> setOutputFile();
		default -> {
			out.println("[Error] Invalid Selection");
			setOutputFile();
		}
		}
	}

	// Big-O: O(1) changing a variable is constant time
	/**
	 * Prompts user to type the desired output file path for the Google1000/list of
	 * common words text file. This file is passed to Google1000Parser and is an
	 * important reference: the programme finds suitable words from this file to
	 * replace words in the target text file when running <code>simplifyText</code>.
	 * User is asked to confirm file path before returning to the Main Menu.
	 * 
	 * @see Google1000Parser
	 * @see TextSimplifier
	 */
	private void setGoogle1000File() {

		out.println(
				"Type the file path for your reference Google1000 file here. Be sure to write full file-path correctly!>");
		String t = s.next();
		googleFile = t;

		out.println("You have set the Google1000 file path as: " + googleFile);
		out.println("Is this correct?");
		out.println("(1) Yes! Return to the main menu!");
		out.println("(2) No! I need to retype the file name...");

		int choice = ic.InputChecker(s.next());

		switch (choice) {
		case 1 -> keepRunning = true;
		case 2 -> setGoogle1000File();
		default -> {
			out.println("[Error] Invalid Selection");
			setGoogle1000File();
		}
		}
	}

	// Big-O: O(1) changing a variable is constant time
	/**
	 * User is asked to specify the file path for the text file they wish to
	 * simplify. This text file is passed to InputTextParser which is called by
	 * TextSimplifier.
	 * 
	 * @see InputTextParser
	 * @see TextSimplifier
	 */
	private void setInputFile() {

		out.println(
				"Type the file path for the file you want to simplify here. Be sure to write full file-path correctly!>");
		String t = s.next();
		inputFilePath = t;

		out.println("You have set the input file path as: " + inputFilePath);
		out.println("Is this correct?");
		out.println("(1) Yes! Return to the main menu!");
		out.println("(2) No! I need to retype the file name...");

		int choice = ic.InputChecker(s.next());

		switch (choice) {
		case 1 -> keepRunning = true;
		case 2 -> setInputFile();
		default -> {
			out.println("[Error] Invalid Selection");
			setInputFile();
		}
		}
	}

}
