package catalogo;

public class Temporada {
  private ArrayList<Episodio> episodios;
  private String descricao;

  public Temporada(String descricao){
    this.descricao = descricao;
    new episodios = ArrayList<>();
  }

  public void adicionarEpisodio(String nome, double duracao, int quantiepisodio){
    Episodio episodio = new Episodio(nome, duracao, quantiepisodio);
    episodios.add(episodio);
  }
}
