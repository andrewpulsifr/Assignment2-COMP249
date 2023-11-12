package ExceptionClasses;


public class MissingFieldException extends Exception {
	
	private String errorField = "";
	private String book = "";
	
	
	public MissingFieldException(String field, String book) {
		
		super("Syntax Error: Missing field exception\n"
				+"Error: missing "+field+"\n"
				+ "Record: "+book+"\n");
		this.setErrorField(field);
		this.setBook(book);
		
	}

	public String getErrorField() {
		return errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

}