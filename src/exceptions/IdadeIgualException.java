package exceptions;

public class IdadeIgualException extends Exception{

	public IdadeIgualException() {
		super("A nova idade não pode ser igual à antiga!");
	}

}
