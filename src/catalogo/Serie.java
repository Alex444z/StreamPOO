package catalogo;
import java.util.ArrayList;

import exceptions.CampoVazioException;
import exceptions.ClassificacaoInvalidaException;

public class Serie extends Conteudo{
	private ArrayList<Temporada> temporadas;
	
	public Serie(String nome, String descricao, int classificacao) throws CampoVazioException, ClassificacaoInvalidaException {
		super(nome, descricao, classificacao);
		this.temporadas = new ArrayList<>();
	}

	public String reproduzir(int numeroTemp, int numeroep) {
		Temporada temporada = temporadas.get(numeroTemp);
		temporada.getEpisodio(numeroep);
		return "Você assitiu o episodio o : " + temporada.getEpisodio(numeroep).getNome();
	}
	

	public ArrayList<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(ArrayList<Temporada> temporadas) {
		this.temporadas = temporadas;
	}

	public void adicionarTemporada(String descricao) throws CampoVazioException{
		Temporada temporada = new Temporada(descricao);
	 this.temporadas.add(temporada);
  }
  
	public Temporada getTemporada() {
		return temporadas.get(temporadas.size()-1);

	}
	/*public int getDuracao() {
		int temp = 0;
		for (Temporada temporada : temporadas) {
			temp += temporada.getTotalEpisodio();
		}
		return temp;
	}
*/
	

	
}
