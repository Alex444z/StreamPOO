package exceptions;

public class SenhaIncorretaException extends Exception {

	public SenhaIncorretaException() {
		super("Senha Incorreta!");
	}
	public SenhaIncorretaException(String txt) {
		super(txt);
	}

}
