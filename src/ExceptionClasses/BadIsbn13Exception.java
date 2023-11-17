package ExceptionClasses;

public class BadIsbn13Exception extends Exception  {

private String book = "";	
	
public BadIsbn13Exception(String book) {
		
		super("Semantic Error: Bad ISBN of length 13\n"
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
