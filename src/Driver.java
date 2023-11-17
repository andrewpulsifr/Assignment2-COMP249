 
//-----------------------------------------------------
// Assignment 2
// Question: 1,2,3
// Written by: Andrew Pulsifer 40234525
// -----------------------------------------------------


import java.util.Scanner;

import ExceptionClasses.BadIsbn10Exception;
import ExceptionClasses.BadIsbn13Exception;
import ExceptionClasses.BadPriceException;
import ExceptionClasses.BadYearException;
import ExceptionClasses.MissingFieldException;
import ExceptionClasses.TooFewFieldsException;
import ExceptionClasses.TooManyFieldsException;
import ExceptionClasses.UnknownGenreException;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;

/**
 * Class that creates csv and binary files and reads back the values in a organized menu
 * @author Andrew Pulsifer 40234525 
 */

public class Driver {
	
	private static Book [] Cartoons_Comics =null;
	private static Book [] Hobbies_Collectibles=null;
	private static Book [] Movies_TV_Books=null;
	private static Book [] Music_Radio_Books=null;
	private static Book [] Nostalgia_Eclectic_Books=null;
	private static Book [] Old_Time_Radio_Books=null;
	private static Book [] Sports_Sports_Memorabilia=null;
	private static Book [] Trains_Planes_Automobiles=null;
	
	private static final String [] genreFileName
    = {"Cartoons_Comics.csv","Hobbies_Collectibles.csv","Movies_TV_Books.csv",
    		"Music_Radio_Books.csv","Nostalgia_Eclectic_Books.csv",
    		"Old_Time_Radio_Books.csv","Sports_Sports_Memorabilia.csv",
    		"Trains_Planes_Automobiles.csv"}; 
	
	
	private static void createGenreFiles() {
		
		for(int c=0; c<genreFileName.length;c++ ) {
			 try {
				 FileWriter genreFileWriter = new FileWriter(genreFileName[c]);
				 BufferedWriter writer = new BufferedWriter(genreFileWriter);
				 writer.write("Title,Authors,Price,Isbn,Genre,Year");
				 writer.newLine();
				 
				 writer.close();
			 }catch (IOException e) {
		            e.printStackTrace();
		            System.out.println("An error occurred while creating the CSV file.");
		        }
		}
	}
	
private static void createErrorFiles() {
	try {
		 FileWriter genreFileWriter = new FileWriter("syntax_error_file.txt");
	 }catch (IOException e) {
           e.printStackTrace();
           System.out.println("An error occurred while creating the txt file.");
       }
		
}

	/**
	 * Static method that checks for syntax errors and creates cvs files sorted by genre
	 * of all books without syntax errors
	 */	
	private static void part1() {
		Scanner scInput = null;
		Scanner scFileReader = null;
		PrintStream errStream = null;
		File inputNames= new File("part1_input_file_names.txt");
		int errorCounter=0;
		int numInput=0;
		int ctr =0;
		String book="";
		String genre="";
		
		
		try {
			scInput= new Scanner (new FileInputStream(inputNames));//open list of file names
			
			createErrorFiles();
			
			try {
				errStream = new PrintStream(new FileOutputStream("syntax_error_file.txt",false));
			} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.setErr(errStream);
			
			if(scInput.hasNextInt()) { //checks there is a number stating how many names to iterate through
				Driver.createGenreFiles();
				
				numInput = scInput.nextInt();//stores number
				scInput.nextLine();//moves down a line from the number
				
				while(ctr<=numInput && scInput.hasNextLine()) {//iterates through all book names
			
						try {
							String name =scInput.nextLine();
							
							scFileReader= new Scanner (new FileInputStream(name));
							errorCounter =0;
							while(scFileReader.hasNextLine()) {
								book = scFileReader.nextLine();
								try {
									genre=Driver.checkSyntax(book);
									Driver.printGenereFile(genre, book);
								}
								catch(MissingFieldException e) {
									if(errorCounter==0)
										Driver.printErrorFileName(name);
									System.err.println(e.getMessage());
									errorCounter++;
								}
								catch(UnknownGenreException e) {
									if(errorCounter==0)
										Driver.printErrorFileName(name);
									System.err.println(e.getMessage());
									errorCounter++;
								}
								catch(TooManyFieldsException e) {
									if(errorCounter==0)
										Driver.printErrorFileName(name);
									System.err.println(e.getMessage());
									errorCounter++;
								}
								catch(TooFewFieldsException e) {
									if(errorCounter==0)
										Driver.printErrorFileName(name);
									System.err.println(e.getMessage());
									errorCounter++;
									
								}
								
							
							
							}
						
							scFileReader.close();	
						}
						catch(FileNotFoundException e){
							
						}
					ctr++;
				}
			}
				
		
			scInput.close();
			errStream.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		} 
	}

	
	/**
	 * Check for syntax errors and throws exception if one exists
	 * 
	 * @param book	book input read from file 
	 * @throws TooManyFieldsException
	 * @throws TooFewFieldsException 
	 * @throws	MissingFieldException
	 * @throws UnknownGenreException 
	 */	
	private static String checkSyntax(String book) throws TooManyFieldsException,TooFewFieldsException,MissingFieldException,UnknownGenreException {
	
		String orgBook=book;
		int commaCount = 0;
		int holdStart=0;
		int holdEnd=-1;
		String field = "";
		String genre="";
		boolean missingField=false;
		int fieldPos=0;
		boolean genreCheck=false;
		
		
		if(book.charAt(0)=='"') {
			book=book.substring(book.indexOf('"',1)-1,book.length());
		}

        for (int i = 0; i < book.length(); i++) {
        	
            if (book.charAt(i) == ',') {
                commaCount++;
                holdStart=holdEnd+1;
                holdEnd=i;
                field =book.substring(holdStart,holdEnd);
                     		
        		if(Driver.isMissing(field)==true) {
        			missingField=true;
        			if(fieldPos==0) {
        				fieldPos=commaCount;
        			}
        		}
        		
        		if(commaCount==5) {
    				if(Driver.checkGenre(field))
    					genreCheck=true;
    				else
    					genre=field;
    					
    			}
        		if(i==book.length()-1) {
	        		holdStart=holdEnd+1;
	                holdEnd=book.length();
	                field =book.substring(holdStart,holdEnd);
	                        		
	        		if(Driver.isMissing(field)==true) {
	        			missingField=true;
	        			if(fieldPos==0) {
	        				fieldPos=6;
	        				
	        			}
	        		
	        		}
        		
                
        		} 
        
            }
        }
		if(commaCount>5) {
			throw new TooManyFieldsException(orgBook);
		}
			else if(commaCount<5) {
				throw new TooFewFieldsException(orgBook);
			}
				else if(missingField) {
					throw new MissingFieldException(returnGenre(fieldPos),orgBook);
				}
					else if(genreCheck) {
						throw new UnknownGenreException(orgBook);
					}
		
		return genre;
		
		
	}
	
	/**
	 * Checks if a field is empty
	 * 
	 * @param field	space between commas
	 * @return true/false
	 */	
	private static boolean isMissing(String field) {
		return field.length()==0;
	}
	
	private static String returnGenre(int field) {
		switch(field) {
		case 1:
			return "title";

		case 2:
			return "authors";
		
		case 3:
			return "price";

		case 4:
			return "isbn";

		case 5:
			return "genre";

		case 6:
			return "year";
	}
		return "Unknown";
	}
	
	private static void printGenereFile(String genre, String book) {
		File inputGenre=null;
		PrintStream genreWriter = null;
		switch(genre) {
			case "CCB":
				 inputGenre =new File(genreFileName[0]);
				 break;
			case "HCB":
				inputGenre =new File(genreFileName[1]);
				 break;
			case "MTV":
				inputGenre =new File(genreFileName[2]);
				 break;
			case "MRB":
				inputGenre =new File(genreFileName[3]);
				 break;
			case "NEB":
				inputGenre =new File(genreFileName[4]);
				 break;
			case "OTR":
				inputGenre =new File (genreFileName[5]);
				 break;
			case "SSM":
				inputGenre =new File(genreFileName[6]);
				 break;
			case "TPA":
				inputGenre =new File(genreFileName[7]);
				 break;
		
		}
		try {
			genreWriter = new PrintStream(new FileOutputStream(inputGenre,true));
			
			genreWriter.println(book);
			
			genreWriter.close();
		}
		catch(FileNotFoundException e){
		}
	}
	
	
	private static void printErrorFileName(String name) {
		System.err.println("syntax error in file: "+name
				+"\n====================");
	}
	
	
	
private static boolean checkGenre(String field) {
		
		switch(field) {
			case "CCB":
				return false;
			case "HCB":
				return false;
			case "MTV":
				return false;
			case "MRB":
				return false;
			case "NEB":
				return false;
			case "OTR":
				return false;
			case "SSM":
				return false;
			case "TPA":
				return false;
		default:
				return true;
		}
	}
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

private static double parsePrice(String priceStr, String book) throws BadPriceException {
	try {
	   return Double.parseDouble(priceStr);
	} catch (NumberFormatException e) {
	   throw  new BadPriceException(book);
	}
}
private static int parseYear(String yearStr, String book) throws BadPriceException {
	try {
	   return Integer.parseInt(yearStr);
	} catch (NumberFormatException e) {
	   throw  new BadPriceException(book);
	}
}

private static Book[] createArr(int c) throws FileNotFoundException {
	
	int currentSize=0;
	int errorCounter=0;
	Book[] arrOfGenres= new Book[1];
	String [] fieldArr;
	Scanner sc=null;
		
			sc= new Scanner(new FileInputStream(genreFileName[c]));//opens genre cvs file
			sc.nextLine();
			
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
					arrOfGenres[currentSize] = new Book(fieldArr[0], fieldArr[1], checkPrice(parsePrice(fieldArr[2],book),book),
		                    checkISBN(fieldArr[3],book), fieldArr[4], checkYear(parseYear(fieldArr[5],book),book));
					currentSize++;
				}
				catch(BadPriceException e) {
					if(errorCounter==0)
						printErrorFileName(genreFileName[c]);
					System.err.println(e.getMessage());
					errorCounter++;
				}
				
				catch(BadIsbn10Exception e) {
					if(errorCounter==0)
						printErrorFileName(genreFileName[c]);
					System.err.println(e.getMessage());
					errorCounter++;
					
				}
				catch(BadIsbn13Exception e) {
					if(errorCounter==0)
						printErrorFileName(genreFileName[c]);
					System.err.println(e.getMessage());
					errorCounter++;
				}
				catch(BadYearException e) {
					if(errorCounter==0)
						printErrorFileName(genreFileName[c]);
					System.err.println(e.getMessage());
					errorCounter++;
					
				}
				
			}
			arrOfGenres = lengthCorrect(arrOfGenres, currentSize);
			sc.close();
		
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
		oos.writeObject(arrBooks);
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
	PrintStream errStream = null;
	
	try {
		errStream = new PrintStream(new FileOutputStream("semantic_error_file.txt",false));
	} catch (FileNotFoundException e1) {
		System.out.println("Error: Genre File Not Found");
	}
	System.setErr(errStream);
	
	for(int c=0; c<genreFileName.length;c++) {
		try {
			
			printToBinary(createArr(c),genreFileName[c]);
			
		}
		catch(FileNotFoundException e) {
			System.out.println("Error: Genre File Not Found");
		}
	}
	
	
		
}

	
private static double checkPrice(double price, String book) throws BadPriceException {
	
	if(price<0)
		throw new BadPriceException(book);	
	
	return price;

}

private static int checkYear(int year, String book) throws BadYearException {
	if(year<1995|| year>2010)
		throw new BadYearException(book);
	
	return year;
}

private static String checkISBN(String ISBN, String book) throws BadIsbn10Exception, BadIsbn13Exception{
	
	if(ISBN.length()==10) {
		
			if(ISBN.contains("X")) 
				throw new BadIsbn10Exception(book);
			
			if(Driver.checkISBN10(ISBN))
				throw new BadIsbn10Exception(book);
	}
	else if(ISBN.length()==13) {
		
			if(ISBN.contains("X")) 
				throw new BadIsbn10Exception(book);
			
			if(Driver.checkISBN13(ISBN)) 
				throw new BadIsbn13Exception(book);
		
	}
	else
		throw new BadIsbn13Exception(book);
	
	return ISBN;
	
	
}

private static boolean checkISBN10(String ISBN ) {
	int sum =0;
	
	for(int c=0; c<ISBN.length(); c++) {
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

private static void printObject(Book [] arr,int currentPos) {
	System.out.println("\n-----------------------------");
	System.out.println("Object at Position:"+ currentPos);
	System.out.println("-----------------------------\n");
	System.out.println(arr[currentPos]);
}

private static int printInstructions(Scanner sc) {
	System.out.println("\n-----------------------------");
	System.out.print("Input Range or 0 to exit:");
	int userInput=sc.nextInt();
	System.out.println("-----------------------------");
	
	return userInput;
}
	
private static void objectNavigator(Book [] arr,Scanner sc) {
	int currentPos=0;
	int endPos = 0;
	int userInput=1;
	int m=1;
	
	printObject(arr,currentPos);
	userInput=printInstructions(sc);
	
	
	while(userInput!=0) {
		
		if(userInput<0) {
			m=-1;
			userInput*=-1;
		}
		endPos=currentPos + ((userInput-1)*m);
		if(endPos>arr.length-1) {
			System.out.println("\n----------------------------------------------------------");
			System.out.println("----------------------------------------------------------");
			System.out.println("BOF has been reached");
			System.out.println("----------------------------------------------------------");
			System.out.println("----------------------------------------------------------");
			endPos=arr.length-1;
		}
			else if(endPos<0) {
				System.out.println("\n----------------------------------------------------------");
				System.out.println("----------------------------------------------------------");
				System.out.println("BOF has been reached");
				System.out.println("----------------------------------------------------------");
				System.out.println("----------------------------------------------------------");
				endPos=0;
				}
				
		for (; currentPos!= endPos; currentPos += m) {
			printObject(arr,currentPos);
		}
		if(userInput!=0) {
			printObject(arr,currentPos);
		}
		
		userInput=printInstructions(sc);
		
		
	}
}
	
private static void part3() {
		Cartoons_Comics=deSerialize(0);
		Hobbies_Collectibles=deSerialize(1);
		Movies_TV_Books=deSerialize(2);
		Music_Radio_Books=deSerialize(3);
		Nostalgia_Eclectic_Books=deSerialize(4);
		Old_Time_Radio_Books=deSerialize(5);
		Sports_Sports_Memorabilia=deSerialize(6);
		Trains_Planes_Automobiles=deSerialize(7);
		
		int selectHolder =1;
		String userInput="";
		
		Scanner sc = new Scanner(System.in);
		
		
		do {
			System.out.print(printMenu(selectHolder-1,returnArray(selectHolder)));
			userInput=sc.nextLine();
			
			if(userInput.equals("s")) {
				printFileSubMenu();
				int check = sc.nextInt();
				if(check!=9)
					selectHolder=check;
				System.out.println();
			}
			
			if(userInput.equals("v")) {
				System.out.println("----------------------------------------------------------");
				System.out.println("---------------------------------------------------------------------------------------");
				System.out.println("Viewing: "+genreFileName[selectHolder-1]+".ser ("+ returnArray(selectHolder).length +" records))");
				System.out.println("---------------------------------------------------------------------------------------");
				System.out.println("----------------------------------------------------------");
				if(returnArray(selectHolder).length!=0)
					objectNavigator(returnArray(selectHolder),sc);
				
			}
			
			sc.nextLine();
			
		}
		while(!userInput.equals("x") && !userInput.equals("X"));
		
		sc.close();
		System.exit(0);
}
	
private static Book [] deSerialize(int c) {
	
		ObjectInputStream ois = null;
		Book [] b1= null;
		File inputFile = new File(genreFileName[c]+".ser");
		try {
		ois = new ObjectInputStream(new FileInputStream(inputFile));
		
			try {
				b1= (Book[])ois.readObject();
			}
			catch(ClassNotFoundException e)
			{
				System.out.println("Error has occurred while reading the file: " + inputFile.getName() + ".");
			}
			catch(EOFException e)
			{
				b1= new Book[0];
			}
		ois.close();		// Close the file

				
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File: " + inputFile.getName() + " could not been found.");
		}
		catch(IOException e)
		{
			System.out.println("Error: Problem Reading from file: " + inputFile.getName() + ".");		
		}
		
		return b1;

	}
	
private static String printMenu(int file, Book[] array) {
		
		return "-----------------------------\n"+
				"Main Menu\n"+
				"-----------------------------\n"+
				"v View the selected file: "+genreFileName[file]+".ser ("+ array.length +" records)\n"+
				"s Select a file to view\n"+
				"x Exit\n"+
				"-----------------------------\n"+
				"Enter Your Choice: ";
	}
	

private static Book[] returnArray(int i) {
		switch(i) {
		case 1:
		return Cartoons_Comics;
		case 2:
		return Hobbies_Collectibles;
		case 3:
		return Movies_TV_Books;
		case 4:
		return Music_Radio_Books;
		case 5:
		return Nostalgia_Eclectic_Books;
		case 6:
		return Old_Time_Radio_Books;
		case 7:
		return Sports_Sports_Memorabilia;
		case 8:
		return Trains_Planes_Automobiles;
		}
		return null;
		
	}

private static void printFileSubMenu() {
	System.out.println("\n------------------------------\n"
			+ "File Sub-Menu\n"
			+ "------------------------------\n");
	for(int c=0; c<8; c++) {
		System.out.printf("%-2d %-40s%10s%n", c + 1,genreFileName[c]+".ser","("+ returnArray(c+1).length +" records)" );
	}
	System.out.printf("%-2d %-40s%n",9,"Exit");

	System.out.print("------------------------------\n\n"
			+ "Enter Your Choice: ");
	
}


	public static void main(String[] args) {
		
		Driver.part1();// validating syntax, partition book records based on genre.
		
		part2(); // validating semantics, read the genre files each into arrays of Book objects,
		
		 // then serialize the arrays of Book objects each into binary files.
		part3(); // reading the binary files, deserialize the array objects in each file, and
	} // then provide an interacive program to allow the user to navigate the arrays.


}
