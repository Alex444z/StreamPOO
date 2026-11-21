package catalogo;

import java.util.ArrayList;

public class Temporada {
  private ArrayList<Episodio> episodios;
  //private int totalepisodio = episodios.size();;
  private String descricao;

  public Temporada(String descricao){
    this.descricao = descricao;
    episodios = new ArrayList<>();
  }

  public String getDescricao() {
	return descricao;
}

  	/*public int getTotalEpisodio() {
  		return this.totalepisodio;
  	}*/
  public void setDescricao(String descricao) {
	this.descricao = descricao;
  }

  public void adicionarEpisodio(String nome, double duracao){
    Episodio episodio = new Episodio(nome, duracao);
    episodios.add(episodio);
  }
  
  public Episodio getEpisodio(int numeroepi) {
	  return episodios.get(numeroepi);
  }
}
