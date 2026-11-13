package catalogo;

public class Serie extends Conteudo{
	private int quantidadeEpisodio;
	private int temporadas;
	private int episodio;
	
	public Serie(String nome, String descricao, int classificacao, int quantidadeEpisodio, int temporadas, int episodio) {
		super(nome, descricao, classificacao);
		this.quantidadeEpisodio = quantidadeEpisodio;
		this.temporadas = temporadas;
		this.episodio = episodio;
	}

	public String reproduzir() {
		// TODO Auto-generated method stub
		return null;
	}

}
