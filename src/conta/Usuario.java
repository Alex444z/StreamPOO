package conta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
	EmailInvalidoException, PerfilDuplicadoException{
		this.perfils = new ArrayList<>();
		addPerfil("Infantil", false);
		setEmail(email);
		setSenha(senha);
		setNome(nome);
		setIdade(idade);
		this.id=proximoId++;
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

	public void setEmail(String email) throws CampoVazioException, EmailInvalidoException {
		if(email == null || email.equals("")) {
			throw new CampoVazioException();
		}else if(!email.contains("@") || !email.contains(".")) {
			throw new EmailInvalidoException();		
		}
		this.email = email;
	}

	private void setSenha(String senha) throws CampoVazioException, TamanhoInvalidoException {
		if(senha == null || senha.equals("")) {
			throw new CampoVazioException();
		}else if(senha.length() < TAMANHO_MINIMO_SENHA){
			throw new TamanhoInvalidoException();
		}
		this.senha = senha;
	}

	public void setNome(String nome) throws CampoVazioException {
		if(nome == null || nome.equals("")) {
			throw new CampoVazioException();
		}
		this.nome = nome;
	}

	public void setIdade(int idade) throws IdadeInvalidaException {
		if(idade < IDADE_MINIMA || (idade) > IDADE_MAXIMA) {
			throw new IdadeInvalidaException();
		}
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
	
	public void alterarEmail(String novoemail, String antigoemail) 
	        throws EmailExistenteException, CampoVazioException, EmailInvalidoException {
	    if (novoemail == null || antigoemail == null || novoemail.equals("") || antigoemail.equals("")) {
	        throw new CampoVazioException();
	    } else if (!autenticarEmail(antigoemail)) {
	        throw new EmailInvalidoException("O email antigo informado está incorreto!");
	    } else if (autenticarEmail(novoemail)) {
	        throw new EmailExistenteException("O novo email é igual ao email antigo!");
	    }
	    setEmail(novoemail);
	}
	
	public void alterarNome(String novonome, String antigonome) throws CampoVazioException {
	    if (novonome == null || antigonome == null || novonome.equals("") || antigonome.equals("")) {
	        throw new CampoVazioException();
	    } else if (!autenticarNome(antigonome)) {
	        throw new CampoVazioException("O nome antigo informado está incorreto!");
	    } else if (autenticarNome(novonome)) {
	        throw new CampoVazioException("O novo nome não pode ser igual ao antigo!");
	    }
	    setNome(novonome);
	}
	
	public void alterarIdade(int novaidade, int antigaidade) throws IdadeInvalidaException {
	    if (!autenticarIdade(antigaidade)) {
	        throw new IdadeInvalidaException("A idade antiga informada está incorreta!");
	    } else if (autenticarIdade(novaidade)) {
	        throw new IdadeInvalidaException("A nova idade não pode ser igual à antiga!");
	    }
	    setIdade(novaidade);
	}
	
	public void alterarSenha(String novaSenha, String antigaSenha) throws CampoVazioException, TamanhoInvalidoException, SenhaIgualException, SenhaIncorretaException {
		if (novaSenha == null || antigaSenha == null || novaSenha.equals("") || antigaSenha.equals("")) {
			throw new CampoVazioException();
		}else if(!autenticarSenha(antigaSenha)){
			throw new SenhaIncorretaException("Senha antiga incorreta!");
		}else if (autenticarSenha(novaSenha)) {
			throw new SenhaIgualException();
		}
		setSenha(novaSenha);
	}
	

	/*private String limparTexto(String texto) {
		return texto.trim();
	}*/

	public void addPerfil(String apelido, boolean adulto) throws PerfilDuplicadoException, CampoVazioException {
		if(apelido == null || apelido.equals("")) {
			throw new CampoVazioException("Apelido não pode ser vazio");
		}else if (getPerfil(apelido) != null) {
			throw new PerfilDuplicadoException();
		}
		Perfil perfil = new Perfil(apelido, adulto);
		perfils.add(perfil);	
	}
	
	public String listarPerfils() {
		int cont = 1;
		StringBuilder sb = new StringBuilder();
		for (Perfil p : perfils) {
			sb.append(cont +" - " + p.getApelido()+"\n");
			cont ++;
		}return sb.toString();
		
	}
	
	public boolean removerPerfil(String apelido) throws CampoVazioException, PerfilNaoExisteException {
	    if (apelido == null || apelido.trim().isEmpty()) {
	        throw new CampoVazioException("Informe o apelido do perfil a ser removido.");
	    }
	    if (apelido.trim().equalsIgnoreCase("Infantil")) {
	        throw new CampoVazioException("O perfil Infantil padrão não pode ser removido!");
	    }
	    boolean removido = perfils.removeIf(p -> p.getApelido().equalsIgnoreCase(apelido.trim()));
	    if (!removido) {
	        throw new PerfilNaoExisteException();
	    }
	    return removido;
	}
	
	public void alterarApelidoPerfil(String apelidoAntigo, String novoApelido) 
	        throws CampoVazioException, PerfilPadraoException, PerfilNaoExisteException, PerfilDuplicadoException {
	    
	    if (novoApelido == null || novoApelido.trim().isEmpty()) {
	        throw new CampoVazioException("O novo apelido não pode ser vazio.");
	    }
	    if (apelidoAntigo.trim().equalsIgnoreCase("Infantil")) {
	        throw new PerfilPadraoException("O apelido do perfil Infantil padrão não pode ser alterado!");
	    }
	    if (getPerfil(novoApelido.trim()) != null) {
	        throw new PerfilDuplicadoException();
	    }

	    Perfil perfil = getPerfil(apelidoAntigo);
	    if (perfil == null) {
	        throw new PerfilNaoExisteException();
	    }

	    perfil.setApelido(novoApelido.trim());
	}
	
	@Override
	public String toString() {
		return String.format("Id: %d Nome: %s Idade: %d Email: %s", this.id, this.nome, this.idade, this.email);
	}
}