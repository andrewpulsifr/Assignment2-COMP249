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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
public class Debug {
	
	private static final String [] genreFileName
    = {"Cartoons_Comics.csv","Hobbies_Collectibles.csv","Movies_TV_Books.csv",
    		"Music_Radio_Books.csv","Nostalgia_Eclectic_Books.csv",
    		"Old_Time_Radio_Books.csv","Sports_Sports_Memorabilia.csv",
    		"Trains_Planes_Automobiles.csv"}; 
	
	
	private static void createGenreFiles() {
		
		for(int c=0; c<genreFileName.length;c++ ) {
			 try {
				 FileWriter genreFileWriter = new FileWriter(genreFileName[c]);
			 }catch (IOException e) {
		            e.printStackTrace();
		            System.out.println("An error occurred while creating the CSV file.");
		        }
		}
	}

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
		
		
		
		String name="TESt";
						
							errorCounter =0;
							
								book = "\"Manilow, Barry - Biography\",Patricia Butler,3.95,0711991979,ABC, 2006";
								try {
									genre=Debug.checkSyntax(book);
									//Driver.printGenereFile(genre, book);
								}
								catch(TooManyFieldsException e) {
									if(errorCounter==0)
										Debug.printErrorFileName(name);
									System.err.println("Error: Too many fields\n"
											+ "Record: "+book+"\n");
									errorCounter++;
								}
								catch(TooFewFieldsException e) {
									if(errorCounter==0)
										Debug.printErrorFileName(name);
									System.err.println("Error: Too few Fields\n"
											+ "Record: "+book+"\n");
									errorCounter++;
									
								}
								catch(MissingFieldException e) {
									if(errorCounter==0)
										Debug.printErrorFileName(name);
									System.out.println("Syntax Error: Missing field exception");
									System.err.println("Error: missing "+e.getErrorField()+"\n"
											+ "Record: "+book+"\n");
									errorCounter++;
								}
								catch(UnknownGenreException e) {
									if(errorCounter==0)
										Debug.printErrorFileName(name);
									System.err.println("Error: Unknown Genre\n"
											+ "Record: "+book+"\n");
									errorCounter++;
								}
								
								
							
							
							
						
							
						
			}
				
		
			

	private static String checkSyntax(String book) throws TooManyFieldsException,TooFewFieldsException,MissingFieldException,UnknownGenreException {
	
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
                     		
        		if(Debug.isMissing(field)==true) {
        			missingField=true;
        			if(fieldPos==0) {
        				fieldPos=commaCount;
        			}
        		}
        		
        		if(commaCount==5) {
    				if(Debug.checkGenre(field))
    					genreCheck=true;
    				else
    					genre=field;
    					
    			}
        		if(i==book.length()-1) {
	        		holdStart=holdEnd+1;
	                holdEnd=book.length();
	                field =book.substring(holdStart,holdEnd);
	                        		
	        		if(Debug.isMissing(field)==true) {
	        			missingField=true;
	        			if(fieldPos==0) {
	        				fieldPos=6;
	        				
	        			}
	        		
	        		}
        		
                
        		} 
        
            }
        }
        System.out.println(commaCount);
		if(commaCount>5) {
			throw new TooManyFieldsException();
		}
			else if(commaCount<5) {
				throw new TooFewFieldsException();
			}
				else if(missingField) {
					throw new MissingFieldException(fieldPos);
				}
					else if(genreCheck) {
						throw new UnknownGenreException();
					}
		
		return genre;
		
		
	}
	
	private static boolean isMissing(String field) {
		return field.length()==0;
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
			System.out.println("Invalid name");
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
	
	public static void main(String[] args) {
		
		Debug.part1();// validating syntax, partition book records based on genre.
		/*
		part2(); // validating semantics, read the genre files each into arrays of Book objects,
		 // then serialize the arrays of Book objects each into binary files.
		part3(); // reading the binary files, deserialize the array objects in each file, and
		*/
	} // then provide an interacive program to allow the user to navigate the arrays.


}
