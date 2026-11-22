package catalogo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import exceptions.CampoVazioException;
import exceptions.ClassificacaoInvalidaException;
import exceptions.ConteudoNaoEcontradoException;


public class GerenciadorConteudo {
	
	private HashMap<String, ArrayList<Conteudo>> conteudos;

  public GerenciadorConteudo() throws FileNotFoundException{
	  recuperarJSON();
  }
  public void salvarJSON() throws IOException {
	  Gson gson = new GsonBuilder().setPrettyPrinting().create();
	  FileWriter writer = new FileWriter("conteudos.json");
	  gson.toJson(getConteudos(), writer);
	  writer.close();
  }
  
  public void recuperarJSON() throws FileNotFoundException {
	  File arquivo = new File("conteudos.json");
	  if (!arquivo.exists()) {
			this.conteudos = new HashMap<>();
			this.conteudos.put("filmes", new ArrayList<>());
			this.conteudos.put("series", new ArrayList<>());
			return;
		}
	  Type tipoFilmes = new TypeToken<ArrayList<Filme>>() {}.getType();
	  Type tipoSeries = new TypeToken<ArrayList<Serie>>() {}.getType();

	  Gson gson = new GsonBuilder().setPrettyPrinting().create();
	  FileReader reader = new FileReader("conteudos.json");
	  Type tipoMap = new TypeToken<HashMap<String, JsonElement>>() {}.getType();
	  HashMap<String, JsonElement> conteudorecuperados = gson.fromJson(reader, tipoMap);
	  if (conteudorecuperados != null) {
		  ArrayList<Filme> filmes = gson.fromJson(gson.toJsonTree(conteudorecuperados.get("filmes")),tipoFilmes);
		  ArrayList<Serie> series = gson.fromJson(gson.toJsonTree(conteudorecuperados.get("series")),tipoSeries);
		  
		  this.conteudos = new HashMap<>();
		  this.conteudos.put("filmes", new ArrayList<>(filmes));
		  this.conteudos.put("series", new ArrayList<>(series));
	  }
	  
	  else if (conteudorecuperados == null) {
		  this.conteudos = new HashMap<>();
		  this.conteudos.put("filmes", new ArrayList<>());
		  this.conteudos.put("series", new ArrayList<>());
	  }
  }
  public ArrayList<Conteudo> buscarConteudo(String chave, String nome) {
		ArrayList<Conteudo> encontrados = new ArrayList<>();
		ArrayList<Conteudo> lista = conteudos.get(chave);

		if (lista != null && nome != null && !nome.trim().isEmpty()) {
			for (Conteudo c : lista) {
				if (c.getNome().equalsIgnoreCase(nome.trim())) {
					encontrados.add(c);
					break;
				}
			}
		}

		return encontrados;
	}

  public void adicionarConteudo(Conteudo conteudo) throws Exception {
		if (conteudo == null) {
			return;
		}

		String chave = (conteudo instanceof Serie) ? "series" : "filmes";
		if (!buscarConteudo(chave, conteudo.getNome()).isEmpty()) {
			throw new Exception("Já existe um conteúdo cadastrado com este nome!");
		}

		if (conteudo instanceof Filme) {
			ArrayList<Conteudo> s = conteudos.get("filmes");
			s.add(conteudo);
			this.conteudos.put("filmes", s);
		} else if (conteudo instanceof Serie) {
			ArrayList<Conteudo> s = conteudos.get("series");
			s.add(conteudo);
			this.conteudos.put("series", s);
		}
	}
  
  public void atualizarNomeConteudo(String chave, String nomeAtual, String novoNome) throws CampoVazioException, ConteudoNaoEcontradoException {
		ArrayList<Conteudo> encontrados = buscarConteudo(chave, nomeAtual);
		if (encontrados.isEmpty()) {
			throw new ConteudoNaoEcontradoException();
		}
		encontrados.get(0).setNome(novoNome.trim());
	}

	public void atualizarDescricaoConteudo(String chave, String nomeAtual, String novaDescricao) throws ConteudoNaoEcontradoException, CampoVazioException {
		ArrayList<Conteudo> encontrados = buscarConteudo(chave, nomeAtual);
		if (encontrados.isEmpty()) {
			throw new ConteudoNaoEcontradoException();
		}
		encontrados.get(0).setDescricao(novaDescricao.trim());
	}

	public void atualizarClassificacaoConteudo(String chave, String nomeAtual, int novaClassificacao)
			throws  ClassificacaoInvalidaException, ConteudoNaoEcontradoException {
		ArrayList<Conteudo> encontrados = buscarConteudo(chave, nomeAtual);
		if (encontrados.isEmpty()) {
			throw new ConteudoNaoEcontradoException();
		}
		encontrados.get(0).setClassificacao(novaClassificacao);
	}
	
	public void removerConteudo(String chave, String nome) throws CampoVazioException, ConteudoNaoEcontradoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new CampoVazioException();
		}
		ArrayList<Conteudo> lista = conteudos.get(chave);
		if (lista == null || !lista.removeIf(c -> c.getNome().equalsIgnoreCase(nome.trim()))) {
			throw new ConteudoNaoEcontradoException();
		}
	}
  
  public HashMap<String, ArrayList<Conteudo>> getConteudos() {
	return new HashMap<>(this.conteudos);
  }
  public  ArrayList<Conteudo> getConteudo(String chave) {
		return new ArrayList<>(conteudos.get(chave));
  }
	  
 
 
  
}
