package exceptions;

public class IdadeStringException extends Exception{

	public IdadeStringException() {
		super("A idade nao pode conter letras!");
	}

}
