package ExceptionClasses;

public class BadIsbn10Exception extends Exception {

	
	private String book = "";	
	
	public BadIsbn10Exception(String book) {
			
			super("Semantic Error: Bad ISBN of length 10\n"
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
