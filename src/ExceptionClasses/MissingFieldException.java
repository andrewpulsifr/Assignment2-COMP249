package ExceptionClasses;

/**
 * Custom exception class for handling syntax errors related to missing fields.
 * This exception is thrown when a required field is missing in a record.
 */
public class MissingFieldException extends Exception {
	
	private String errorField = ""; // Stores the information about the missing field
	private String book = ""; // Stores the information about the book associated with the exception
	
	/**
	 * Constructs a MissingFieldException with details about the missing field and associated book record.
	 * @param field The missing field.
	 * @param book The book record associated with the exception.
	 */
	public MissingFieldException(String field, String book) {
		super("Syntax Error: Missing field exception\n" +
		      "Error: missing " + field + "\n" +
		      "Record: " + book + "\n");
		this.setErrorField(field);
		this.setBook(book);
	}

	/**
	 * Gets the information about the missing field associated with the exception.
	 * @return The missing field.
	 */
	public String getErrorField() {
		return errorField;
	}

	/**
	 * Sets the information about the missing field associated with the exception.
	 * @param errorField The missing field.
	 */
	public void setErrorField(String errorField) {
		this.errorField = errorField;
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