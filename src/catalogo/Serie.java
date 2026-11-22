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
		if (numeroTemp < 0 || numeroTemp >= temporadas.size()) {
			return "Temporada não encontrada!";
		}
		Temporada temporada = temporadas.get(numeroTemp);
		Episodio episodio = temporada.getEpisodio(numeroep);
		if (episodio == null) {
			return "Episódio não encontrado!";
		}
		return "Você assistiu o episódio: " + episodio.getNome();
	}
	

	public ArrayList<Temporada> getTemporadas() {
		return new ArrayList<>(temporadas);
	}

	public void setTemporadas(ArrayList<Temporada> temporadas) {
		this.temporadas = (temporadas != null) ? new ArrayList<>(temporadas) : new ArrayList<>();
	}

	public void adicionarTemporada(String descricao) throws CampoVazioException{
		Temporada temporada = new Temporada(descricao);
	 this.temporadas.add(temporada);
  }
  
	public Temporada getTemporada() {
		if (temporadas.isEmpty()) {
			return null;
		}
		return temporadas.get(temporadas.size() - 1);
	}
	@Override
	public double getDuracaoTotal() {
		double total = 0;
		for (Temporada t : temporadas) {
			total += t.getDuracaoTemporada();
		}
		return total;
	}

	@Override
	public String getResumoDetalhes() {
		return "Série • " + temporadas.size() + " temporada(s) • Duração total: " + getDuracaoTotal() + " min";
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
