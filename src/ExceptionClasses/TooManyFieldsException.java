package ExceptionClasses;

/**
 * Custom exception class for handling syntax errors related to having too many fields in a record.
 * This exception is thrown when the number of fields in a record exceeds the expected limit.
 */
public class TooManyFieldsException extends Exception {

	private String book = ""; // Stores the information about the book associated with the exception
	
	/**
	 * Constructs a TooManyFieldsException with details about the associated book record.
	 * @param book The book record associated with the exception.
	 */
	public TooManyFieldsException(String book) {
		super("Syntax Error: Too many fields\n" +
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