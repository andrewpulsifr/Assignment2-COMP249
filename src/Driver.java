import java.util.Scanner;

import ExceptionClasses.MissingFieldException;
import ExceptionClasses.TooFewFieldsException;
import ExceptionClasses.TooManyFieldsException;
import ExceptionClasses.UnknownGenreException;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;



public class Driver {
	

	private static void part1() {
		Scanner scInput = null;
		Scanner scFileReader = null;
		Scanner fieldsScanner =null;
		PrintStream errStream = null;
		File inputNames= new File("part1_input_file_names.txt");
		int errorCounter=0;
		int numInput=0;
		int ctr =0;
		int fieldCounter =0;
		boolean correctSyntax=false;
		String book="";
		
		
		
		try {
			scInput= new Scanner (new FileInputStream(inputNames));//open list of file names
			
			try {
				errStream = new PrintStream(new FileOutputStream("syntax_error_file.txt",false));
			} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.setErr(errStream);
			
			if(scInput.hasNextInt()) { //checks there is a number stating how many names to iterate through
				numInput = scInput.nextInt();//stores number
				System.out.println(numInput);
				scInput.nextLine();//moves down a line from the number
				
				//while(ctr<=numInput && scInput.hasNextLine()) {//iterates through all book names
				while(ctr<3 && scInput.hasNextLine()) {
						try {
							String name =scInput.nextLine();
							System.out.println(name);
							System.out.println();
							
							scFileReader= new Scanner (new FileInputStream(name));
							errorCounter =0;
							while(scFileReader.hasNextLine()) {
								book = scFileReader.nextLine();
								try {
									//scFileReader.nextLine();
									System.out.println(book);
									Driver.checkSyntax(book);
								}
								catch(MissingFieldException e) {
									if(errorCounter==0)
										Driver.printErrorFileName(name);
									System.out.println("Syntax Error: Missing field exception");
									System.err.println("Error: missing "+e.getErrorField()+"\n"
											+ "Record: "+book+"\n");
									errorCounter++;
									correctSyntax=false;
								}
								catch(UnknownGenreException e) {
									if(errorCounter==0)
										Driver.printErrorFileName(name);
									System.err.println("Error: Unknown Genre\n"
											+ "Record: "+book+"\n");
									errorCounter++;
									correctSyntax=false;
								}
								catch(TooManyFieldsException e) {
									if(errorCounter==0)
										Driver.printErrorFileName(name);
									System.err.println("Error: Too many fields\n"
											+ "Record: "+book+"\n");
									errorCounter++;
									correctSyntax=false;
								}
								catch(TooFewFieldsException e) {
									if(errorCounter==0)
										Driver.printErrorFileName(name);
									System.err.println("Error: Too few Fields\n"
											+ "Record: "+book+"\n");
									errorCounter++;
									correctSyntax=false;
									
								}
								
							
							
							}
						
							scFileReader.close();	
						}
						catch(FileNotFoundException e){
							System.out.println("Invalid name");
						}
					ctr++;
				}
			}
				
			//}
			scInput.close();
			errStream.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		} 
	}

	private static void checkSyntax(String book) throws TooManyFieldsException,TooFewFieldsException,MissingFieldException,UnknownGenreException {
		boolean correctSyntax= true;
		Scanner sc = new Scanner(book);
		Scanner sc1 = new Scanner(book);
		int numOfFields=0;
		int feildCounter=0;
				
		if(book.charAt(0)=='"') {
			sc.useDelimiter("\"");
			System.out.println(sc.next());
			System.out.println(book.substring(book.indexOf('"',1)+1,book.length()));
			numOfFields=Driver.countCommas(book.substring(book.indexOf('"',1),book.length()));
		}
		
		numOfFields=Driver.countCommas(book);
	
		sc.useDelimiter(",");
		
		while(sc.hasNext()) {
			String field =sc.next();
			System.out.println(field);
			System.out.println(Driver.isMissing(field));
			
			if(Driver.isMissing(field)==true) {
				throw new MissingFieldException(numOfFields,book);
			}
			if(feildCounter==4) {
				if(Driver.checkGenre(field))
					throw new UnknownGenreException();
			}
			
			feildCounter++;
		}
		
		if(numOfFields>5) {
			System.out.println(numOfFields);
			throw new TooManyFieldsException();
		}
			else if(numOfFields<5) {
				System.out.println(numOfFields);
				throw new TooFewFieldsException();
			}
		
		System.out.println(numOfFields);
		
		
	}
	
	private static boolean isMissing(String field) {
		System.out.println(field.length());
		return field.length()==0;
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
	
	
	private static void printErrorFileName(String name) {
		System.err.println("syntax error in file: "+name
				+"\n====================");
	}
	
	private static int countCommas(String book) {
		int commaCount = 0;

        for (int i = 0; i < book.length(); i++) {
            if (book.charAt(i) == ',') {
                commaCount++;
            }
        }
        
        return commaCount;
	}
	
	public static void main(String[] args) {
		
		Driver.part1();// validating syntax, partition book records based on genre.
		/*
		part2(); // validating semantics, read the genre files each into arrays of Book objects,
		 // then serialize the arrays of Book objects each into binary files.
		part3(); // reading the binary files, deserialize the array objects in each file, and
		*/
	} // then provide an interacive program to allow the user to navigate the arrays.


}
