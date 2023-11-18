package ExceptionClasses;

/**
 * Custom exception class for handling syntax errors related to an unknown genre.
 * This exception is thrown when a record contains a genre that is not recognized.
 */
public class UnknownGenreException extends Exception {

	private String book = ""; // Stores the information about the book associated with the exception
	
	/**
	 * Constructs an UnknownGenreException with details about the associated book record.
	 * @param book The book record associated with the exception.
	 */
	public UnknownGenreException(String book) {
		super("Syntax Error: Unknown Genre\n" +
		      "Record: " + book + "\n");
		this.setBook(book);
	}

	/**
	 * Gets the information about the book associated with the exception.
	 * @return The book record.
	 */
	public String getBook() {
		return book;
	}

	/**
	 * Sets the information about the book associated with the exception.
	 * @param book The book record.
	 */
	public void setBook(String book) {
		this.book = book;
	}
}