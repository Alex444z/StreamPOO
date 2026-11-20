package catalogo;

public class Filme extends Conteudo {
	private int duracao;
	
	public Filme(String nome, String descricao, int classificacao, int duracao) {
		super(nome, descricao, classificacao);
		this.duracao = duracao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}


	
}
