package ExceptionClasses;

public class UnknownGenreException extends Exception{

private String book = "";
	
	
	public UnknownGenreException(String book) {
		
		super("Syntax Error: Unknown Genre\n"
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
