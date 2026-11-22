package exceptions;

public class IdadeInvalidaException extends Exception {

	public IdadeInvalidaException() {
		super("Idade Inválida!");
	}
	public IdadeInvalidaException(String txt) {
		super(txt);
	}


}
