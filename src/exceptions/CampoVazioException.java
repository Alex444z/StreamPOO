package exceptions;

public class CampoVazioException extends Exception{
	
	public CampoVazioException (){
		super("Nenhum campo pode ser vazio!");
	}
	public CampoVazioException (String txt){
		super(txt);
	}
}

