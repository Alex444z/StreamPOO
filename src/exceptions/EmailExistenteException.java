package exceptions;

public class EmailExistenteException extends Exception{

	public EmailExistenteException() {
		super("Email já cadastrado!");
	}
	public EmailExistenteException(String txt) {
		super(txt);
	}

}
