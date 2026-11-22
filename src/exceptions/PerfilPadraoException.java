package exceptions;

public class PerfilPadraoException extends Exception {
    public PerfilPadraoException() {
        super("O perfil Infantil padrão não pode ser removido!");
    }

    public PerfilPadraoException(String mensagem) {
        super(mensagem);
    }
}
