package conta;

import java.util.ArrayList;

//import exceptions.CampoVazioException;
//import exceptions.IdadeStringException;
import exceptions.*;
public class Usuario {
	private static final int TAMANHO_MINIMO_SENHA = 8;
	private static final int IDADE_MINIMA = 18;
	private static final int IDADE_MAXIMA = 100;
	private ArrayList<Perfil> perfils;
	private static int proximoId = 0;
	private String email;
	private String senha;
	private String nome;
	private int idade;
	private int id;
	

	// Construtor
	public Usuario(String nome, String email, String senha, int idade) throws CampoVazioException, TamanhoInvalidoException, IdadeInvalidaException,
	EmailInvalidoException{
		String emailf = limparTexto(email);
		String nomef = limparTexto(nome);
		
		if(nome == null || email == null || idade == 0 || senha == null|| nome.equals("") || email.equals("") || senha.equals("")) {
			throw new CampoVazioException();
		}else if(senha.length() < TAMANHO_MINIMO_SENHA){
			throw new TamanhoInvalidoException();
		}else if(idade < IDADE_MINIMA || (idade) > IDADE_MAXIMA) {
			throw new IdadeInvalidaException();
		}else if(!email.contains("@") || !email.contains(".")) {
			throw new EmailInvalidoException();		}
		this.email = emailf;
		this.senha = senha;
		this.nome = nomef;
		this.idade = idade;
		this.id=proximoId;
		this.perfils = new ArrayList<>();
		try {
			addPerfil("Infantil", 13);
		} catch (PerfilDuplicadoException e) {
			e.printStackTrace();
		}
		proximoId++;
	}

	// getters e setters
	public String getEmail() {
		return email;
	}
	
	
	public ArrayList<Perfil> getPerfils() {
    return new ArrayList<>(perfils);
	}

	public Perfil getPerfil(String apelido) {
		for (Perfil perfil : perfils) {
			if (perfil.getApelido().equals(apelido)) {
				return perfil;
			}
		}
		return null;
	}
	
	public int getId() {
		return id;
	}
	
	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return this.nome;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setEmail(String email) {
		//String emailf = limparTexto(email);
		this.email = email;
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
	
	public boolean autenticarNome(String nome) {
		return this.nome.equals(nome);
	}

	public boolean autenticarEmail(String email) {
		return this.email.equals(email);
	}
	
	public boolean autenticarIdade(int idade){
		return this.idade == idade;
	}
	
	public void alterarEmail(String novoemail,String antigoemail) {
		if (autenticarEmail(antigoemail) && !autenticarEmail(novoemail)) {
			setEmail(novoemail);
		}
	}
	
	public void alterarNome(String novonome,String antigonome) {
		if (autenticarNome(antigonome) && !autenticarNome(novonome)) {
			setNome(novonome);
		}
	}
	
	public void alterarIdade(int novaidade,int antigaidade) {
		if (autenticarIdade(antigaidade) && !autenticarIdade(novaidade)) {
			setIdade(novaidade);
		}
	}
	
	public boolean alterarSenha(String novaSenha, String antigaSenha) {
		if (autenticarSenha(antigaSenha) && !autenticarSenha(novaSenha)) {
			setSenha(novaSenha);
			return true;
		}
		return false;
	}
	

	private String limparTexto(String texto) {
		return texto.trim();
	}

	public void addPerfil(String apelido, int idade) throws PerfilDuplicadoException {
		if (getPerfil(apelido) != null) {
			throw new PerfilDuplicadoException();
		}else if(){
			
		}	
		Perfil perfil = new Perfil(apelido, idade);
		perfils.add(perfil);
	}
	
	/*public boolean getPerfil(String apelido) {
		for (Perfil p : perfils) {
			if (p.equals(apelido)) {
				return true;
			}
		}return false;
	}*/
	
	public String listarPerfils() {
		int cont = 1;
		StringBuilder sb = new StringBuilder();
		for (Perfil p : perfils) {
			sb.append(cont +" - " + p.getApelido()+"\n");
			cont ++;
		}return sb.toString();
		
	}
	
	@Override
	public String toString() {
		return String.format("Id: %d Nome: %s Idade: %d Email: %s", this.id, this.nome, this.idade, this.email);
	}
}