package ExceptionClasses;

public class UnknownGenreException extends Exception{

	public UnknownGenreException() {
		super("Syntax Error: Unknown Genre");
	}
}
