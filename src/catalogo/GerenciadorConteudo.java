package catalogo;

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


public class GerenciadorConteudo {
  private ArrayList<Conteudo> conteudos;

  public GerenciadorConteudo() throws FileNotFoundException{
	  this.conteudos = recuperarJSON();
    this.conteudos = new ArrayList<>();
  }

  public ArrayList<Conteudo> recuperarJSON() throws FileNotFoundException {
	  Gson gson = new GsonBuilder().setPrettyPrinting().create();
	  FileReader reader = new FileReader("conteudos.json");
	  Type tipoLista = new TypeToken<ArrayList<Conteudo>>() {}.getType();
	  ArrayList<Conteudo> conteudorecuperados = gson.fromJson(reader, tipoLista);
	return conteudorecuperados;
}

  public void adicionarConteudo(Conteudo conteudo){
    this.conteudos.add(conteudo);
  }
  public void salvarJSON() throws IOException {
	  Gson gson = new GsonBuilder().setPrettyPrinting().create();
	  FileWriter writer = new FileWriter("conteudos.json");
	  gson.toJson(getConteudos(), writer);
  }
  private ArrayList<Conteudo> getConteudos() {
	return new ArrayList<>(conteudos);
  }

  public void removerConteudo(String nome){
    for (Conteudo c : conteudos){
      if (c.getNome().equals(nome)){
        this.conteudos.remove(c);
      }
    }
    
  }
}
