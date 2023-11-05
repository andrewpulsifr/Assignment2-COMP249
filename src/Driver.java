import java.util.Scanner;

import ExceptionClasses.TooFewFieldsException;
import ExceptionClasses.TooManyFieldsException;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;



public class Driver {
	
	private String [] feildHolder= null;
	

	private static void part1() {
		Scanner scInput = null;
		Scanner scFileReader = null;
		Scanner fieldsScanner =null;
		PrintStream errStream = null;
		File inputNames= new File("part1_input_file_names.txt");
		String genre="";
		int numInput=0;
		int ctr =0;
		int fieldCounter =0;
		String [] holder= null;
		
		try {
			scInput= new Scanner (new FileInputStream(inputNames));//open list of file names
			
			if(scInput.hasNextInt()) { //checks there is a number stating how many names to iterate through
				numInput = scInput.nextInt();//stores number
				scInput.nextLine();//moves down a line from the number
				
				while(ctr<=numInput && scInput.hasNextLine()) {//iterates through all book names
						try {
							String name =scInput.nextLine();
							System.out.println(name);
							scFileReader= new Scanner (new FileInputStream(name));
							
							while(scFileReader.hasNextLine()) {
								try {
									String book = scFileReader.nextLine();
									
									if(book.charAt(0) == '"') {
										numFields = book.substring(book.indexOf('"',1),book.length()).split(",").length;
										System.out.println(numFields+" :using quoation");
									}
										else
										{
											numFields=book.split(",").length;
											System.out.println(numFields);
										}
									if(numFields==6) {
										holder= book.split(",");
										
									}
										else if(numFields<6) {
											throw new TooManyFieldsException();
										}
										else
											throw new TooFewFieldsException();
									fieldCounter =0;
									while(fieldCounter<holder.length) {
										
										if(holder[fieldCounter].length()==0)
											throw new MissingFieldException(fileCounter);
										fieldCounter++;
									}
										
								}
								catch(TooManyFieldsException e) {
									
								}
								catch(TooFewFieldsException e) {
									
								}
								catch(MissingFieldException e) {
									
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
			scInput.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}

	}
	
	private boolean checkNumberOfFields(String book) throws TooManyFieldsException, TooFewFieldsException{
		boolean correctNum = false;
		int numFields;
		
		if(book.charAt(0) == '"') {
			numFields = book.substring(book.indexOf('"',1),book.length()).split(",").length;
			book=book.substring(book.indexOf('"',1),book.length());
			System.out.println(numFields+" :using quoation");
		}
			else
			{
				numFields=book.split(",").length;
				System.out.println(numFields);
			}
		if(numFields==6) {
			feildHolder= book.split(",");
			
		}
			else if(numFields<6) {
				throw new TooManyFieldsException();
			}
			else
				throw new TooFewFieldsException();
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
