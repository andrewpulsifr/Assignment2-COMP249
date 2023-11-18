package ExceptionClasses;

/**
 * Custom exception class for handling semantic errors related to bad years.
 * This exception is thrown when an invalid year is encountered.
 */
public class BadYearException extends Exception {

    private String book = ""; // Stores the information about the book associated with the exception

    /**
     * Constructs a BadYearException with details about the bad year and associated book record.
     * @param book The book record associated with the exception.
     */
    public BadYearException(String book) {
        super("Semantic Error: Bad Year\n" +
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