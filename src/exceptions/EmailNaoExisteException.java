package exceptions;

public class EmailNaoExisteException extends Exception{

	public EmailNaoExisteException() {
		super("Email não existe!");
	}

}
