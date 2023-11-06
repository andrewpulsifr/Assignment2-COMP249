

	import java.util.Scanner;
	import java.io.PrintWriter;
	import java.io.FileOutputStream;
	import java.io.FileNotFoundException;
	import java.io.FileInputStream;
	import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		public static void main(String[] args) {
			
			Debug.createGenreFiles();// validating syntax, partition book records based on genre.
			/*
			part2(); // validating semantics, read the genre files each into arrays of Book objects,
			 // then serialize the arrays of Book objects each into binary files.
			part3(); // reading the binary files, deserialize the array objects in each file, and
			*/
		} // then provide an interacive program to allow the user to navigate the arrays.


	}

