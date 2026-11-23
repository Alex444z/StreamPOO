package catalogo;

import exceptions.CampoVazioException;
import exceptions.ClassificacaoInvalidaException;

public class Filme extends Conteudo {
	private double duracao;
	
	public Filme(String nome, String descricao, int classificacao, double duracao) throws CampoVazioException, ClassificacaoInvalidaException {
		super(nome, descricao, classificacao);
		setDuracao(duracao);
	}

	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) throws CampoVazioException {
		if (duracao <= 0) {
			throw new CampoVazioException("A duração do filme deve ser maior que zero.");
		}
		this.duracao = duracao;
	}
	@Override
	public double getDuracaoTotal() {
		return this.duracao;
	}

	@Override
	public String getResumoDetalhes() {
		return "Filme • Duração: " + this.duracao + " min";
	}
}