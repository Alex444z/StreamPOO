package exceptions;

public class CampoVazioException extends Exception{
	
	public CampoVazioException (){
		super("O campo nao pode ser vazio!");
	}
}
