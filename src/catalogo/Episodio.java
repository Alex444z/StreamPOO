package catalogo;

public class Episodio {
  private String nome;
  private double duracao;

  public Episodio(String nome, double duracao){
    this.nome = nome;
    this.duracao = duracao;
  }
  
  public double getDuracao() {
	return duracao;
  }

  public void setDuracao(double duracao) {
	this.duracao = duracao;
  }

  public String getNome() {
	return this.nome;
  }

  public void setNome(String nome){
    this.nome = nome;
  }  
  public void setNome(double duracao){
    this.duracao = duracao;
  }
}