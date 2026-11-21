package catalogo;

public class Filme extends Conteudo {
	private int duracao;
	
	public Filme(String nome, String descricao, int classificacao, int duracao) {
		super(nome, descricao, classificacao);
		this.duracao = duracao;
	}



}
