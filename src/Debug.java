import java.util.Scanner;

import ExceptionClasses.BadIsbn10Exception;
import ExceptionClasses.BadIsbn13Exception;
import ExceptionClasses.BadPriceException;
import ExceptionClasses.BadYearException;
import ExceptionClasses.MissingFieldException;
import ExceptionClasses.TooFewFieldsException;
import ExceptionClasses.TooManyFieldsException;
import ExceptionClasses.UnknownGenreException;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.File;
public class Debug {
	
	private static final String [] genreFileName
    = {"Cartoons_Comics.csv","Hobbies_Collectibles.csv","Movies_TV_Books.csv",
    		"Music_Radio_Books.csv","Nostalgia_Eclectic_Books.csv",
    		"Old_Time_Radio_Books.csv","Sports_Sports_Memorabilia.csv",
    		"Trains_Planes_Automobiles.csv"}; 
	
	
	private static String[] containsQ(String book) {
		
		String [] quoteSeperated = book.split("\"");
		String [] commaSeperated=quoteSeperated[2].split(",");
		String [] bookArr= new String[commaSeperated.length];
		bookArr[0]=quoteSeperated[1];
		for(int c=1; c<commaSeperated.length;c++) {
			bookArr[c]=commaSeperated[c];
		}
		return bookArr;
	}

	private static double parsePrice(String priceStr) throws BadPriceException {
		try {
		   return Double.parseDouble(priceStr);
		} catch (NumberFormatException e) {
		   throw  new BadPriceException();
		}
	}
	private static int parseYear(String yearStr) throws BadPriceException {
		try {
		   return Integer.parseInt(yearStr);
		} catch (NumberFormatException e) {
		   throw  new BadPriceException();
		}
	}

	private static Book[] createArr(int c) throws FileNotFoundException {
	
	int currentSize=0;
	int errorCounter=0;
	Book[] arrOfGenres= new Book[1];
	String [] fieldArr;
		
		PrintStream errStream = null;
		
			//Sets the output of err to semantic error file
			errStream = new PrintStream(new FileOutputStream("semantic_error_file.txt",false));
			System.setErr(errStream);
			
			Scanner sc= new Scanner(new FileInputStream(genreFileName[c]));//opens genre cvs file
			
			while(sc.hasNextLine()) {//while there is a book on the next line continue
			
					String book= sc.nextLine();//read in book
					
					if(book.contains("\""))//if book title has surrounding quotations seperate name 
						fieldArr=containsQ(book);
					else
						fieldArr= book.split(",");
					
					// Check if the array needs to be resized
			        if (currentSize == arrOfGenres.length) {
			            // Create a new array with double the size
			            Book[] tempArr = new Book[arrOfGenres.length * 2];
			            // Copy elements from arrOfGenres to tempArr
			            System.arraycopy(arrOfGenres, 0, tempArr, 0, arrOfGenres.length);
			            // Update arrOfGenres to point to the new array
			            arrOfGenres = tempArr;
			        }
					
				try {
					arrOfGenres[currentSize] = new Book(fieldArr[0], fieldArr[1], checkPrice(parsePrice(fieldArr[2])),
		                    checkISBN(fieldArr[3]), fieldArr[4], checkYear(parseYear(fieldArr[5])));
					currentSize++;
				}
				catch(BadPriceException e) {
					if(errorCounter==0)
						printErrorFileName(genreFileName[c]);
					System.err.println("Error: Bad Price\n"
							+ "Record: "+book+"\n");
					errorCounter++;
				}
				
				catch(BadIsbn10Exception e) {
					if(errorCounter==0)
						printErrorFileName(genreFileName[c]);
					System.err.println("Error: Bad Isbn of length 10\n"
							+ "Record: "+book+"\n");
					errorCounter++;
					
				}
				catch(BadIsbn13Exception e) {
					if(errorCounter==0)
						printErrorFileName(genreFileName[c]);
					System.err.println("Error: Bad Isbn of length 13\n"
							+ "Record: "+book+"\n");
					errorCounter++;
				}
				catch(BadYearException e) {
					if(errorCounter==0)
						printErrorFileName(genreFileName[c]);
					System.err.println("Error: Bad year\n"
							+ "Record: "+book+"\n");
					errorCounter++;
					
				}
				
			}
			arrOfGenres = lengthCorrect(arrOfGenres, currentSize);

		
		return arrOfGenres;
	}
	
	private static Book[] lengthCorrect(Book [] arrOfGenres, int currentSize) {
		Book[] newArr = new Book[currentSize];
		
		for(int c=0; c<currentSize; c++) {
			newArr[c]=arrOfGenres[c];
		}
		return newArr;
	}
	
	private static void printToBinary(Book [] arrBooks, String genreFileName) {
		try {
			ObjectOutputStream oos= new ObjectOutputStream( new FileOutputStream(genreFileName+".ser"));
			
			for(int c=0; c<arrBooks.length;c++) {
				oos.writeObject(arrBooks[c]);
			}
			oos.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found exception thrown.");
			e.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("File input output thrown.");
			e.printStackTrace();
		}
		
	}

	private static void part2() {
		
		
		
		for(int c=0; c<genreFileName.length;c++) {
			try {
				
				printToBinary( createArr(c),genreFileName[c]);
				
			}
			catch(FileNotFoundException e) {
				System.out.println("Error: Genre File Not Found");
			}
		}
		
		
			
		}

		
	private static double checkPrice(double price) throws BadPriceException {
		
		if(price<0)
			throw new BadPriceException();	
		
		return price;

	}

	private static int checkYear(int year) throws BadYearException {
		if(year<1995|| year>2010)
			throw new BadYearException();
		
		return year;
	}

	private static String checkISBN(String ISBN) throws BadIsbn10Exception, BadIsbn13Exception{
		
		if(ISBN.length()==10) {
			
				if(ISBN.contains("X")) 
					throw new BadIsbn10Exception();
				
				if(checkISBN10(ISBN))
					throw new BadIsbn10Exception();
		}
		else {
			
				if(ISBN.contains("X")) 
					throw new BadIsbn10Exception();
				
				if(checkISBN13(ISBN)) 
					throw new BadIsbn13Exception();
			
		}
		
		return ISBN;
		
		
	}

	private static boolean checkISBN10(String ISBN ) {
		int sum =0;
		
		for(int c=0; c<10; c++) {
			sum +=(int)(ISBN.charAt(c))*(c+1);
			
		}
		
		return !(sum%11==0);
	}

	private static boolean checkISBN13(String ISBN) {
		long sum =0;
		
		
		
		for(int c=0; c<13; c++) {
			
			if((c+1)%2==0) 
				sum+= (int)ISBN.charAt(c)*3;
				else
					sum+=(int)ISBN.charAt(c);
		}
		
		return !(sum%10==0);

	}

	private static void printErrorFileName(String name) {
		System.err.println("syntax error in file: "+name
				+"\n====================");
	}
	
		public static void main(String[] args) {
			
			//Driver.part1();// validating syntax, partition book records based on genre.
			
			part2(); // validating semantics, read the genre files each into arrays of Book objects,
			

					
			
			/*
			 // then serialize the arrays of Book objects each into binary files.
			part3(); // reading the binary files, deserialize the array objects in each file, and
			*/
		} // then provide an interacive program to allow the user to navigate the arrays.


	}
