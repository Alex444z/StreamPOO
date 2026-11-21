package catalogo;

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

  public void adicionarConteudo(Conteudo conteudo){
	  if(conteudo instanceof Filme) {
		  ArrayList<Conteudo> s = conteudos.get("filmes");
		  s.add(conteudo);
		  this.conteudos.put("filmes", s);
	  }else if(conteudo instanceof Serie) {
		  ArrayList<Conteudo> s = conteudos.get("series");
		  s.add(conteudo);
		  this.conteudos.put("series", s);
	  }
  }
  
  
  public HashMap<String, ArrayList<Conteudo>> getConteudos() {
	return new HashMap<>(this.conteudos);
  }
  public  ArrayList<Conteudo> getConteudo(String chave) {
		return new ArrayList<>(conteudos.get(chave));
  }
	  
 
 
  
}
