package ExceptionClasses;

public class BadYearException  extends Exception  {

	
private String book = "";
	
	
	public BadYearException(String book) {
		
		super("Semantic Error: Bad Year\n"
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
