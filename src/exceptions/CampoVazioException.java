package exceptions;

public class CampoVazioException extends Exception{
	
	public CampoVazioException (){
		super("Nenhum campo pode ser vazio!");
	}
}
