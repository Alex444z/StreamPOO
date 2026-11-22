package exceptions;

public class NomeIgualException extends Exception {

	public NomeIgualException() {
		super("O novo nome não pode ser igual ao antigo!");
	}

}
