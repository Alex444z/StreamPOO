package catalogo;

import exceptions.CampoVazioException;
import exceptions.ClassificacaoInvalidaException;

public abstract class Conteudo {
	private static final int CLASSIFICACAO_MINIMA = 0;
    private static final int CLASSIFICACAO_MAXIMA = 18;
    
	private String nome;
	private String descricao;
	private int classificacao;
	
	public Conteudo(String nome, String descricao, int classificacao) throws CampoVazioException, ClassificacaoInvalidaException {
        if (nome == null || descricao == null || nome.trim().isEmpty() || descricao.trim().isEmpty()) {
            throw new CampoVazioException();
        }
        if (classificacao < CLASSIFICACAO_MINIMA || classificacao > CLASSIFICACAO_MAXIMA) {
            throw new ClassificacaoInvalidaException();
        }
		this.nome = nome;
		this.descricao = descricao;
		this.classificacao = classificacao;
	}
	
	public abstract double getDuracaoTotal();
	public abstract String getResumoDetalhes();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) throws CampoVazioException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new CampoVazioException();
        }
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) throws CampoVazioException {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new CampoVazioException();
        }
		this.descricao = descricao;
	}

	public int getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(int classificacao) throws ClassificacaoInvalidaException {
        if (classificacao < CLASSIFICACAO_MINIMA || classificacao > CLASSIFICACAO_MAXIMA) {
            throw new ClassificacaoInvalidaException();
        }
		this.classificacao = classificacao;
	}
	

}
