package exceptions;

public class ConteudoNaoEcontradoException extends Exception{

	public ConteudoNaoEcontradoException() {
		super("Conteúdo não encontrado!");
	}
}
