package ExceptionClasses;

public class TooManyFieldsException extends Exception {

	private String book = "";
	
	
	public TooManyFieldsException(String book) {
		
		super("Syntax Error: Too many fields\n"
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
