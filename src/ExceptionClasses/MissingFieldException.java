package ExceptionClasses;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MissingFieldException extends Exception {
	
	private String errorField = "";
	
	public MissingFieldException() {
		super("Syntax Error: Missing Feild exception.");
	}
	
	public MissingFieldException(int field) {
		super("Syntax Error: Missing Feild exception at field number: "+field);
	
		
		switch(field) {
			case 1:
				this.setErrorField("title");
			break;
			case 2:
				this.setErrorField("authors");
			break;
			case 3:
				this.setErrorField("price");
			break;
			case 4:
				this.setErrorField("isbn");
			break;
			case 5:
				this.setErrorField("genre");
			break;
			case 6:
				this.setErrorField("year");
			break;
		}
	}

	public String getErrorField() {
		return errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}

}