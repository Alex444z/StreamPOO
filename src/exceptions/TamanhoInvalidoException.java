package exceptions;

public class TamanhoInvalidoException extends Exception{
	
	public TamanhoInvalidoException() {
		super("A senha deve ter no mínimo 8 caráacteres!");
	}
}
