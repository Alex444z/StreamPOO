package conta;

import java.util.ArrayList;

import catalogo.Conteudo;
import exceptions.CampoVazioException;

public class Perfil {
	private String apelido;
	private boolean adulto;
	private ArrayList<Conteudo> historico;
	
	Perfil (String apelido, boolean adulto) throws CampoVazioException {
		setApelido(apelido);
		this.adulto = adulto;
		historico = new ArrayList<>();
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) throws CampoVazioException {
		if(apelido == null || apelido.trim().isEmpty()){
			throw new CampoVazioException("Apelido não pode ser vazio!");
		}
		this.apelido = apelido.trim();
	}

	public boolean isAdulto() {
		return adulto;
	}
	
	public void setAdulto(boolean adulto) {
		this.adulto = adulto;
	}
	
	public void adicionarAoHistorico(Conteudo conteudo) {
		historico.add(conteudo);
	}
}
 