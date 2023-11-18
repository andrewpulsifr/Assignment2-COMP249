package ExceptionClasses;

/**
 * Custom exception class for handling syntax errors related to having too few fields in a record.
 * This exception is thrown when the expected number of fields is not present in a record.
 */
public class TooFewFieldsException extends Exception {
	
	private String book = ""; // Stores the information about the book associated with the exception
	
	/**
	 * Constructs a TooFewFieldsException with details about the associated book record.
	 * @param book The book record associated with the exception.
	 */
	public TooFewFieldsException(String book) {
		super("Syntax Error: Too few Fields\n" +
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
