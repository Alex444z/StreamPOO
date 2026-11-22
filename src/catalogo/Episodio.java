package catalogo;

import exceptions.CampoVazioException;

public class Episodio {
  private String nome;
  private double duracao;

  public Episodio(String nome, double duracao) throws CampoVazioException{
	  setNome(nome);
	  setDuracao(duracao);
  }
  
  public double getDuracao() {
	return duracao;
  }

  public void setDuracao(double duracao) throws CampoVazioException {
	    if (duracao <= 0) {
	      throw new CampoVazioException("A duração do episódio deve ser maior que zero.");
	    }
	    this.duracao = duracao;
	  }

  public String getNome() {
	return this.nome;
  }

  public void setNome(String nome) throws CampoVazioException {
	    if (nome == null || nome.trim().isEmpty()) {
	      throw new CampoVazioException("O nome do episódio não pode ser vazio!");
	    }
	    this.nome = nome.trim();
	  }
}