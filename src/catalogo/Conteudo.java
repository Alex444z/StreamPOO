package catalogo;

public abstract class Conteudo {
	private String nome;
	private String descricao;
	private int classificacao;
	
	
	public Conteudo(String nome, String descricao, int classificacao) {
		this.nome = nome;
		this.descricao = descricao;
		this.classificacao = classificacao;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return this.descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public int getClassificacao() {
		return classificacao;
	}


	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}
	
	
	
}
