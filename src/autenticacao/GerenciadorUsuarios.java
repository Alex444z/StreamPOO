package autenticacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import catalogo.Conteudo;
import conta.Usuario;
import exceptions.EmailExistenteException;
import exceptions.EmailNaoExisteException;
import exceptions.SenhaIncorretaException;

public class GerenciadorUsuarios {
	
	private ArrayList<Usuario> usuarios;
	
	public GerenciadorUsuarios() throws FileNotFoundException{
		this.usuarios = this.recuperarJSON();
		if (this.usuarios == null) {
	        this.usuarios = new ArrayList<Usuario>();
	    }
	}
	
	public boolean addUsuario(Usuario usuario) throws EmailExistenteException{
		for (Usuario u : usuarios) {
			if (u.getEmail().equals(usuario.getEmail())) {
				throw new EmailExistenteException();
			}
		}
		return this.usuarios.add(usuario);
	}
	
	public int listarQuantiUsuario() {
		return this.usuarios.size();
		
	}
	
	public String verificarDuploEmail(String email) {
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(email)){
			    return "Email existente!";
      }
		}
		return "Email disponivel!";
	}
	
	public void salvarJSON() throws IOException {
		  Gson gson = new GsonBuilder().setPrettyPrinting().create();
		  FileWriter writer = new FileWriter("usuarios.json");
		  gson.toJson(getUsuarios(), writer);
		  writer.close();
	  }
	
	public ArrayList<Usuario> recuperarJSON() throws FileNotFoundException {
		File arquivo = new File("usuarios.json");
		System.out.println("CAMINHO REAL: " + arquivo.getAbsolutePath());
		  Gson gson = new GsonBuilder().setPrettyPrinting().create();
		  FileReader reader = new FileReader("usuarios.json");
		  Type tipoLista = new TypeToken<ArrayList<Usuario>>() {}.getType();
		  ArrayList<Usuario> usuariosrecuperados = gson.fromJson(reader, tipoLista);
		return usuariosrecuperados;
	}
	
	private ArrayList<Usuario> getUsuarios() {
		return new ArrayList<>(usuarios);
	}

	public Usuario logarUsuario(String email, String senha) throws SenhaIncorretaException, EmailNaoExisteException {
		for (Usuario u : usuarios) {
			if (u.autenticarEmail(email)) {
				if(u.autenticarSenha(senha)) {
					return u;
				}else {
					throw new SenhaIncorretaException();
				}
			}
		}
		throw new EmailNaoExisteException();
	}
	
}
