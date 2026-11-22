package exceptions;

public class ConteudoExistenteException extends Exception{

	public ConteudoExistenteException() {
		super("Já existe um conteúdo cadastrado com este nome!");
	}

}
