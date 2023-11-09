import java.io.Serializable;

public class Book implements Serializable  {
	
	private String name;
	private String author;
	private String ISBN;
	private double price;
	private int year;
	
	public Book(String name, String author,double price,String ISBN,String genre, int year) {
		this.setName(name);
		this.setAuthor(author);
		this.setISBN(ISBN);
		this.setPrice(price);
		this.setYear(year);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
