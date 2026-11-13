package catalogo;
import java.util.ArrayList;

public class Serie extends Conteudo{
	private int totalepisodio;
	private ArrayList<Temporada> temporadas;
	
	public Serie(String nome, String descricao, int classificacao, int quantidadeEpisodio) {
		super(nome, descricao, classificacao);
		this.totalepisodio = totalepisodio;
		this.temporadas = new ArrayList<>();
	}

	public String reproduzir() {
		// TODO Auto-generated method stub
		return null;
	}
  public void adicionarTemporada(Temporada temporada){
    this.temporadas.add(temporada);
  }
    
}
