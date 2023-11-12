
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;

public class part3 {
	
	private static final String [] genreFileName
    = {"Cartoons_Comics.csv","Hobbies_Collectibles.csv","Movies_TV_Books.csv",
    		"Music_Radio_Books.csv","Nostalgia_Eclectic_Books.csv",
    		"Old_Time_Radio_Books.csv","Sports_Sports_Memorabilia.csv",
    		"Trains_Planes_Automobiles.csv"}; 
	
	private static Book [] Cartoons_Comics =null;
	private static Book [] Hobbies_Collectibles=null;
	private static Book [] Movies_TV_Books=null;
	private static Book [] Music_Radio_Books=null;
	private static Book [] Nostalgia_Eclectic_Books=null;
	private static Book [] Old_Time_Radio_Books=null;
	private static Book [] Sports_Sports_Memorabilia=null;
	private static Book [] Trains_Planes_Automobiles=null;
	
private static void printObject(Book [] arr,int currentPos) {
	System.out.println("-----------------------------\n");
	System.out.println("Object at Position:"+ currentPos);
	System.out.println("-----------------------------\n");
	System.out.println(arr[currentPos]);
}
	
private static void objectNavigator(Book [] arr,Scanner sc) {
	int currentPos=0;
	int endPos = 0;
	int userInput=1;
	int m=1;

	printObject(arr,currentPos);
	do {
		System.out.print("Input Range or 0 to exit:");
		userInput=sc.nextInt();
		
		if(userInput<0) {
			m=-1;
			userInput*=-1;
		}
		endPos=currentPos + ((userInput-1)*m);
		if(endPos>arr.length-1) {
			System.out.println("BOF has been reached");
			endPos=arr.length-1;
		}
			else if(endPos<0) {
				System.out.println("BOF has been reached");
				endPos=0;
				}
				
		for (; currentPos!= endPos; currentPos += m) {
			printObject(arr,currentPos);
		}
		if(userInput!=0) {
			printObject(arr,currentPos);
		}
		
	}while(userInput!=0);
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
				System.out.println("viewing: "+genreFileName[selectHolder]+".ser ("+ returnArray(selectHolder).length +" records))");
				if(returnArray(selectHolder).length!=0)
					objectNavigator(returnArray(selectHolder),sc);
				
			}
			
			sc.nextLine();
			
		}
		while(!userInput.equals("x") && !userInput.equals("X"));
		
		sc.close();
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
		// TODO Auto-generated method stub
		
		part3();
	}

}
