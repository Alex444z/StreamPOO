package exceptions;

public class SenhaIgualException extends Exception{

	public SenhaIgualException() {
		super("A senha nova é igual à antiga!");
	}

}
