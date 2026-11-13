package exceptions;

public class EmailInvalidoException extends Exception {

	public EmailInvalidoException() {
		super("Esse email é invalido!");
	}
}
