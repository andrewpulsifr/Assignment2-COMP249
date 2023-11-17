//-----------------------------------------------------
// Assignment 2
// Question: 1,2,3
// Written by: Andrew Pulsifer 40234525
// -----------------------------------------------------


import java.io.Serializable;

public class Book implements Serializable  {
	
	private static int nextSerialNumber = 1;
	private int serialNumber;
	private String name;
	private String author;
	private String ISBN;
	private double price;
	private String genre;
	private int year;
	
	public Book(String name, String author,double price,String ISBN,String genre, int year) {
		this.setSerialNumber(nextSerialNumber++);
		this.setName(name);
		this.setAuthor(author);
		this.setISBN(ISBN);
		this.setPrice(price);
		this.setYear(year);
		this.setGenre(genre);
	}

	private void setSerialNumber(int serialNumber) {
		this.serialNumber=serialNumber;
		
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
	
	public String toString() {
		return "Name: "+name+
				"\nAuthor: "+author+
				"\nISBN: "+ ISBN+
				"\nPrice: "+ price+
				"\nGenre: "+ genre+
				"\nYear: "+ year+
				"\nISBN: "+ serialNumber;
		
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
