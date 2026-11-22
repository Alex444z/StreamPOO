package exceptions;

public class NomeIncorretoException extends Exception{

	public NomeIncorretoException() {
		super("O nome antigo informado está incorreto!");
	}

}
