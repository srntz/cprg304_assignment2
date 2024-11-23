package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import implementations.MyStack;

/**
 * This Parser class reads and checks XML files to make sure they are correct.
 * It uses a custom stack (MyStack) to track opening and closing tags.
 */
public class Parser {
	private MyStack<String> tagStack; // Stack to keep track of XML tags.
	private boolean hasErrors = false; // Tracks if there are errors in the XML.
	private boolean foundFirstTag = false; // Tracks if the first tag has been found.

	/**
	 * Constructor for the Parser class. It sets up the stack for checking XML tags.
	 */
	public Parser() {
		tagStack = new MyStack<>(); // Create the stack.
	}

	/**
	 * Reads the XML file and checks its structure.
	 *
	 * @param filePath The path of the XML file to check.
	 */
	public void parseXML(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			int lineNumber = 0;

			// Process the file line by line.
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				line = line.trim();

				// Skip empty lines or lines without XML tags.
				if (line.isEmpty() || (!line.contains("<") && !line.contains(">"))) {
					continue;
				}

				// Mark the beginning of parsing when the first tag is found.
				if (!foundFirstTag && line.contains("<")) {
					foundFirstTag = true;
				}

				// Validate each line for valid XML tags.
				validateLine(line, lineNumber);
			}

			if (hasErrors == false) {
				System.out.println("No errors found.");
			}

		} catch (IOException e) {
			// Handle file reading errors.
			System.out.println("Error reading the file: " + e.getMessage());
		}
	}

	public static String[] extractTags(String line) {
		// Count the number of tags (incomplete or complete)
		int count = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '<') {
				count++;
			}
		}

		// Array to hold extracted tags
		String[] tags = new String[count];
		int index = 0;
		int start = -1; // Tracks the start of a tag

		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '<') {
				start = i; // Start of a tag
			} else if (line.charAt(i) == '>' && start != -1) {
				// End of a complete tag
				tags[index++] = line.substring(start, i + 1);
				start = -1; // Reset start for the next tag
			}
		}

		// Handle incomplete tag if present
		if (start != -1) {
			tags[index++] = line.substring(start); // Add incomplete tag to array
		}

		// Resize the array to remove null entries
		String[] result = new String[index];
		for (int i = 0; i < index; i++) {
			result[i] = tags[i];
		}

		return result;
	}

	/**
	 * Checks a line of XML for valid tags.
	 *
	 * @param line       The line to check.
	 * @param lineNumber The line number for error reporting.
	 */
	private void validateLine(String line, int lineNumber) {

		// Split the line into parts, keeping < and > in the result.

		// extract the tags in a line since some lines have multiple tags
		String[] tags = extractTags(line);
		boolean thisLineHasErrors = false;

		for (String tag : tags) {
			if (tag.startsWith("<?")) {
				continue;
			}

			String[] parts = tag.split("(?<=<)|(?=>)");
			int openingTagCounter = 0;
			int closingTagCounter = 0;
			for (int i = 0; i < parts.length; i++) {
				String part = parts[i];

				boolean isOpeningTag = part.equals("<");
				boolean isClosingTag = part.equals(">");
			
				if (isOpeningTag) {
					openingTagCounter++;
				}

				if (isClosingTag) {
					closingTagCounter++;

				}

				// reset counter when closed
				if (openingTagCounter == 1 && closingTagCounter == 1) {
					openingTagCounter = 0;
					closingTagCounter = 0;
					continue;
				}

				boolean isFirstOpeningTag = isOpeningTag && openingTagCounter == 1 && closingTagCounter == 0;
				boolean isFirstClosingTag = isClosingTag && openingTagCounter == 1 && closingTagCounter == 1;
				
				// ignore if empty, if the first opening tag, first closing tag or if its starts with <? tags
				if (part.isEmpty() || isFirstOpeningTag || isFirstClosingTag || part.startsWith("?")) {
					continue;
				}

				// excess opening tag
				if (isOpeningTag) {
					System.out.println("Invalid open tag at line " + lineNumber + "\n\t" + line);
					openingTagCounter = 0;
					closingTagCounter = 0;
					hasErrors = true;
					continue;
				}

				// excess closing tag
				if (isClosingTag) {
					System.out.println("Invalid close tag at line " + lineNumber + "\n\t" + line);
					openingTagCounter = 0;
					closingTagCounter = 0;
					hasErrors = true;

					continue;
				}
			}

			if (tag.startsWith("</") && tag.endsWith(">")) {
				
				// if its a closing tag, show an error if it doesnt match the last element on the stack
				String tagName = tag.split("(?<=</)|(?=>)")[1].split(" ")[0];

				if (!tagName.equals(tagStack.peek())) {
					if (!thisLineHasErrors) {

						System.out.println("Error at line " + lineNumber + "\n" + line);

						thisLineHasErrors = true;
						hasErrors = true;
					}

				} 
				// if its a closing tag, pop the last tag if it matches the current tag
				else {

					tagStack.pop();
				}

				// clear all the tags in this line if there are errors found. 
				// this happens especially with nested tags in one line
				if (thisLineHasErrors) {
					while (!tagStack.isEmpty()) {
						if (line.contains("<" + tagStack.peek()) || line.contains("</" + tagStack.peek())) {
							tagStack.pop();
						} else {
							break;
						}

					}

				}

			} 
			// continue with self closing
			else if (tag.startsWith("<") && tag.endsWith("/>")) {
				continue;
			}
			// if its an opening tag, add the tag except when its supposed to be a self-closing tag such as Package Creation Location
			else if (tag.startsWith("<") && tag.endsWith(">")) {
				String tagName = tag.split("(?<=<)|(?=>)")[1].split(" ")[0];
				if (!tagName.equals("PackageCreationLocation")) {
					tagStack.push(tagName);
				} else {
					System.out.println("Error at line " + lineNumber + "\n" + line);
				}
			}
			// when its an improper nested tag, pop the latest from the stack
			else {
				System.out.println("Error at line " + lineNumber + "\n" + line);
				tagStack.pop();
			}
		}

	}

	/**
	 * Main method to run the Parser from the command line. Accepts the path to the
	 * XML file as an argument.
	 *
	 * @param args The command-line arguments (expects the file path).
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Parser <file_path>");
			return;
		}

		Parser parser = new Parser();
		parser.parseXML(args[0]);
	}
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
