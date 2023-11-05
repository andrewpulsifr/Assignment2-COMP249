

	import java.util.Scanner;
	import java.io.PrintWriter;
	import java.io.FileOutputStream;
	import java.io.FileNotFoundException;
	import java.io.FileInputStream;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.File;


	public class Debug {
		private static void part1() {
			Scanner scInput = null;
			Scanner scFileReader = null;
			Scanner fieldsScanner =null;
			File inputNames= new File("part1_input_file_names.txt");
			String genre="";
			int numInput=0;
			int ctr =0;
			
			
							try {
								scFileReader= new Scanner (new FileInputStream("books2000.csv.txt"));

								
								while(scFileReader.hasNextLine()) {
									String book = scFileReader.nextLine();
									int numFields =0;
									
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
										String [] holder= book.split(",");
										System.out.print(holder[4]);
									}
								
								}
								scFileReader.close();	
							}
							catch(FileNotFoundException e){
								System.out.println("Invalid name");
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

