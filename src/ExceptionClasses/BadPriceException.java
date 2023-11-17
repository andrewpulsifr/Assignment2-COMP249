package ExceptionClasses;

public class BadPriceException extends Exception  {

	
private String book = "";
	
	
	public BadPriceException(String book) {
		
		super("Semantic Error: Bad Price\n"
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
