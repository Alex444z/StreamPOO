package catalogo;
import java.util.ArrayList;
public class GerenciadorConteudo {
  private ArrayList<Conteudo> conteudos;

  public GerenciadorConteudo(){
    this.conteudos = new ArrayList<>();
  }

  public void adicionarConteudo(conteudo){
    this.conteudos.add(conteudo);
  }

  public void removerConteudo(String nome){
    for (Conteudo c : conteudos){
      if (c.getNome(nome).equals(nome)){
        this.conteudos.remove(c);
      }
    }
    
  }
}
