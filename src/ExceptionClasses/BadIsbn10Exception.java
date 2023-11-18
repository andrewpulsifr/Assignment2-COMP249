package ExceptionClasses;

/**
 * Custom exception class for handling semantic errors related to ISBN with length 10.
 * This exception is thrown when an invalid ISBN of length 10 is encountered.
 */
public class BadIsbn10Exception extends Exception {

	
	private String book = "";// Stores the information about the book associated with the exception
	
	/**
     * Constructs a BadIsbn10Exception with details about the invalid ISBN and associated book record.
     * @param book The book record associated with the exception.
     */
	public BadIsbn10Exception(String book) {
			
			super("Semantic Error: Bad ISBN of length 10\n"
					+ "Record: "+book+"\n");
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
