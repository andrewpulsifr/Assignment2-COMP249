package ExceptionClasses;

public class TooFewFieldsException extends Exception {
	
private String book = "";
	
	
	public TooFewFieldsException(String book) {
		
		super("Syntax Error: Error: Too few Fields\n"
				+ "Record: "+book+"\n");
		this.setBook(book);
		
	}


	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	
}
