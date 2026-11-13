package exceptions;

public class TamanhoInvalidoException extends Exception{
	
	public TamanhoInvalidoException() {
		super("Quantidade de caracteres invalidos");
	}
}
