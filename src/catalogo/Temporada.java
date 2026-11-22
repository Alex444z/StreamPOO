package catalogo;

import java.util.ArrayList;

import exceptions.CampoVazioException;

public class Temporada {
  private ArrayList<Episodio> episodios;
  //private int totalepisodio = episodios.size();;
  private String descricao;

  public Temporada(String descricao) throws CampoVazioException{
    setDescricao(descricao);
    episodios = new ArrayList<>();
  }

  public String getDescricao() {
	return descricao;
}

  	/*public int getTotalEpisodio() {
  		return this.totalepisodio;
  	}*/
  public void setDescricao(String descricao) throws CampoVazioException {
      if (descricao == null || descricao.trim().isEmpty()) {
          throw new CampoVazioException();
      }
      this.descricao = descricao.trim();
  }

  public void adicionarEpisodio(String nome, double duracao) throws CampoVazioException {
	    Episodio episodio = new Episodio(nome, duracao);
	    episodios.add(episodio);
	}
  
  public Episodio getEpisodio(int numeroepi) {
	    if (numeroepi < 0 || numeroepi >= episodios.size()) {
	        return null;
	    }
	    return episodios.get(numeroepi);
	}
  
  public double getDuracaoTemporada() {
		double total = 0;
		for (Episodio ep : episodios) {
			total += ep.getDuracao();
		}
		return total;
	}
  
  public ArrayList<Episodio> getEpisodios() {
	    return new ArrayList<>(this.episodios);
	}

	public int getTotalEpisodios() {
		return episodios.size();
	}
}
