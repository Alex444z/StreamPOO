package conta;

import java.util.ArrayList;

import catalogo.Conteudo;

public class Perfil {
	private String apelido;
	private int idade;
	private ArrayList<Conteudo> historico;
	
	Perfil (String apelido, int idade) {
		this.apelido = apelido;
		this.idade = idade;
		historico = new ArrayList<>();
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public void adicionarAoHistorico(Conteudo conteudo) {
		historico.add(conteudo);
	}
}
 