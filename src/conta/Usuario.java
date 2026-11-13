package conta;

import java.util.ArrayList;

public class Usuario {
	private static final int TAMANHO_MINIMO_SENHA = 8;
	private ArrayList<Perfil> perfils;
	private static int proximoId = 0;
	private String email;
	private String senha;
	private String nome;
	private int idade;
	private int id;

	// Construtor
	public Usuario(String email, String senha, String nome, int idade) {
		String emailf = limparTexto(email);
		String nomef = limparTexto(nome);

		this.email = emailf;
		this.senha = senha;
		this.nome = nomef;
		this.idade = idade;
		this.id = proximoId++;
		perfils = new ArrayList<>();
	}

	// getters e setters
	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return this.nome;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setEmail(String email) {
		String emailf = limparTexto(email);

		this.email = emailf;
	}

	private void setSenha(String senha) {
		this.senha = senha;
	}

	public void setNome(String nome) {
		String nomef = limparTexto(nome);

		this.nome = nomef;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public boolean autenticarSenha(String senha) {
		return this.senha.equals(senha);
	}

	public boolean autenticarEmail(String email) {
		return this.email.equals(email);
	}

	public boolean alterarSenha(String novaSenha, String antigaSenha) {
		if (autenticarSenha(antigaSenha) && !novaSenha.equals(antigaSenha)) {
			setSenha(novaSenha);
			return true;
		}
		return false;
	}

	private String limparTexto(String texto) {
		return texto.trim();
	}

	public void criarPerfil(String apelido, int idade) {
		Perfil perfil = new Perfil(apelido, idade);
		perfils.add(perfil);
	}
	
	public String listarPerfils() {
		int cont = 1;
		StringBuilder sb = new StringBuilder();
		for (Perfil p : perfils) {
			sb.append(1 +" - " + p.getApelido()+"\n");
			cont ++;
		}return sb.toString();
		
	}
	
	@Override
	public String toString() {
		return String.format("Id: %d Nome: %s Idade: %d Email: %s", this.id, this.nome, this.idade, this.email);
	}
}