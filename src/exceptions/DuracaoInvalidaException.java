package exceptions;

public class DuracaoInvalidaException extends Exception {
	
	public DuracaoInvalidaException() {
		super("A duração deve conter apenas números!");
	}
}