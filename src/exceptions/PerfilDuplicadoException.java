package exceptions;

public class PerfilDuplicadoException extends Exception{

	public PerfilDuplicadoException() {
		super("Já existe um perfil com este apelido!");
	}

}
