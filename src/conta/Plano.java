package conta;

public class Plano {
	private String nome;
	private double preco;
	private int telas;
	
	public Plano(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getTelas() {
		return telas;
	}

	public void setTelas(int telas) {
		this.telas = telas;
	}
	
}
