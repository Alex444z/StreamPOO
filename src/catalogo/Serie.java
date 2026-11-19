package catalogo;
import java.util.ArrayList;

public class Serie extends Conteudo{
	private int totalepisodio;
	private ArrayList<Temporada> temporadas;
	
	public Serie(String nome, String descricao, int classificacao) {
		super(nome, descricao, classificacao);
		this.temporadas = new ArrayList<>();
	}

	public String reproduzir(int numeroTemp, int numeroep) {
		Temporada temporada = temporadas.get(numeroTemp);
		temporada.getEpisodio(numeroep);
		return "Você assitiu o episodio o : " + temporada.getEpisodio(numeroep).getNome();
	}
	
	public int getTotalepisodio() {
		return totalepisodio;
	}

	public void setTotalepisodio(int totalepisodio) {
		this.totalepisodio = totalepisodio;
	}

	public ArrayList<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(ArrayList<Temporada> temporadas) {
		this.temporadas = temporadas;
	}

	public void adicionarTemporada(String descricao){
		Temporada temporada = new Temporada(descricao);
	 this.temporadas.add(temporada);
  }
  
	public Temporada getTemporada() {
		return temporadas.get(0);
	}

	
}
