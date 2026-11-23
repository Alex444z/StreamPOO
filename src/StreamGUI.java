import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import autenticacao.GerenciadorUsuarios;
import catalogo.GerenciadorConteudo;
import catalogo.Serie;
import catalogo.Temporada;
import catalogo.Filme;
import catalogo.Conteudo;
import catalogo.Episodio;
import conta.Perfil;
import conta.Usuario;
import exceptions.CampoVazioException;
import exceptions.EmailExistenteException;
import exceptions.EmailInvalidoException;
import exceptions.EmailNaoExisteException;
import exceptions.IdadeIgualException;
import exceptions.IdadeIncorretaException;
import exceptions.IdadeInvalidaException;
import exceptions.IdadeStringException;
import exceptions.NomeIgualException;
import exceptions.NomeIncorretoException;
import exceptions.PerfilDuplicadoException;
import exceptions.PerfilNaoExisteException;
import exceptions.PerfilPadraoException;
import exceptions.SenhaIgualException;
import exceptions.SenhaIncorretaException;
import exceptions.TamanhoInvalidoException;
import exceptions.ClassificacaoInvalidaException;
import exceptions.ConteudoExistenteException;
import exceptions.ConteudoNaoEcontradoException;
import exceptions.DuracaoInvalidaException;

public class StreamGUI extends JFrame implements ActionListener {
	private Usuario usuario;
	private Perfil perfilatual;
	private static boolean STATUSCONTA;
	private GerenciadorUsuarios gerenciador;
	private GerenciadorConteudo gerenciadorConteudo;
	private Serie serie;
	ArrayList<Conteudo> series;
	ArrayList<Conteudo> filmes;

	private JPanel center;
	JMenuBar menubar;
	private JTextField nomeTxt;
	private JTextField emailTxt;
	private JTextField senhaTxt;
	private JTextField idadeTxt;
	private JTextField nomeConteudoTxt;
	private JTextField classiTxt;
	private JTextField descricaoTxt;
	private JTextField duracaoTxt;
	private JTextField descricaotempTxt;
	private JTextField nomeepisodioTxt;
	private JTextField duracaoepisodioTxt;
	private JTextField novasenhaTxt;
	private JTextField antigasenhaTxt;
	private JTextField novoemailTxt;
	private JTextField antigoemailTxt;
	private JTextField antigonomeTxt;
	private JTextField novonomeTxt;
	private JTextField antigaidadeTxt;
	private JTextField novaidadeTxt;
	private JTextField apelidoTxt;
	private JTextField idadeperfilaTxt;
	private JCheckBox adultoCheck;
	private JTextField novoApelidoTxt;

	public StreamGUI() {
		try {
			gerenciadorConteudo = new GerenciadorConteudo();
			series =gerenciadorConteudo.getConteudo("series");
			filmes = gerenciadorConteudo.getConteudo("filmes");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			gerenciador = new GerenciadorUsuarios();
			if (gerenciador.getUsuario(gerenciador.recuperarSessaoJSON()) != null) { 
				usuario = gerenciador.getUsuario(gerenciador.recuperarSessaoJSON());
				STATUSCONTA = true;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Component createMenu() {
		menubar = new JMenuBar();
		JMenu menu = new JMenu("Conta");
		JMenuItem item;

		if (STATUSCONTA) {
			// seguranca
			JMenu menuseguranca = new JMenu("Segurança");
			item = new JMenuItem("Alterar senha");
			item.setActionCommand("menualterarsenha");
			item.addActionListener(this);
			menuseguranca.add(item);

			item = new JMenuItem("Alterar email");
			item.setActionCommand("menualteraremail");
			item.addActionListener(this);
			menuseguranca.add(item);

			item = new JMenuItem("Alterar nome");
			item.setActionCommand("menualterarnome");
			item.addActionListener(this);
			menuseguranca.add(item);

			item = new JMenuItem("Alterar idade");
			item.setActionCommand("menualteraridade");
			item.addActionListener(this);
			menuseguranca.add(item);
			menu.add(menuseguranca);
			
			item = new JMenuItem("Apagar conta");
			item.setActionCommand("menuconta");
			item.addActionListener(this);
			menuseguranca.add(item);
			menu.add(menuseguranca);
			
			menubar.add(menu);
			
			JMenu painelconteudo = new JMenu("Painel");
			item = new JMenuItem("Pagina Inicial");
			item.setActionCommand("paginainicial");
			item.addActionListener(this);
			painelconteudo.add(item);
			menubar.add(painelconteudo);
			
			// perfis
			JMenu perfilmenu = new JMenu("perfil");
			item = new JMenuItem("Perfis");
			item.setActionCommand("listaperfis");
			item.addActionListener(this);
			perfilmenu.add(item);
			menubar.add(perfilmenu);
			
			item = new JMenuItem("Remover perfil");
			item.setActionCommand("removerperfil");
			item.addActionListener(this);
			perfilmenu.add(item);
			menubar.add(perfilmenu);
			
			item = new JMenuItem("Adicionar perfil");
			item.setActionCommand("adicionarperfil");
			item.addActionListener(this);
			perfilmenu.add(item);
			menubar.add(perfilmenu);
			
			item = new JMenuItem("Mudar Apelido");
			item.setActionCommand("apelidoperfil");
			item.addActionListener(this);
			perfilmenu.add(item);
			menubar.add(perfilmenu);
			
			item = new JMenuItem("Sair");
			item.setActionCommand("sairconta");
			item.addActionListener(this);
			menu.add(item);
			
			menubar.add(menu);

		} else {
			item = new JMenuItem("Registrar");
			item.setActionCommand("menuregistrar");
			item.addActionListener(this);
			menu.add(item);

			item = new JMenuItem("Login");
			item.setActionCommand("menulogar");
			item.addActionListener(this);
			menu.add(item);
			menubar.add(menu);

			JMenu menuadm = new JMenu("ADMIN");
			JMenu menuserie = new JMenu("Gerenciar series");
			menuserie.setActionCommand("gerenciarseries");
			menuserie.addActionListener(this);
			menuadm.add(menuserie);
			
			item = new JMenuItem("Adicionar serie");
			item.setActionCommand("adicionarserie");
			item.addActionListener(this);
			menuserie.add(item);
			
			item = new JMenuItem("Apagar serie");
			item.setActionCommand("apagarserie");
			item.addActionListener(this);
			menuserie.add(item);
			
			item = new JMenuItem("Editar serie");
			item.setActionCommand("editarserie");
			item.addActionListener(this);
			menuserie.add(item);
			
			JMenu menufilme = new JMenu("Gerenciar filmes");
			menufilme.setActionCommand("gerenciarfilmes");
			menufilme.addActionListener(this);
			menuadm.add(menufilme);
			
			item = new JMenuItem("Adicionar filme");
			item.setActionCommand("adicionarfilme");
			item.addActionListener(this);
			menufilme.add(item);
			
			item = new JMenuItem("Apagar filme");
			item.setActionCommand("apagarfilme");
			item.addActionListener(this);
			menufilme.add(item);
			
			item = new JMenuItem("Editar filme");
			item.setActionCommand("editarfilme");
			item.addActionListener(this);
			menufilme.add(item);
			
			menubar.add(menuadm);

		}
		return menubar;
	}

	public void updateGUI() {
		if (center != null) {
			this.remove(center);
		}
		this.revalidate();
		this.repaint();

	}

	public void executar() {
		this.setTitle("StreamPOO");
		this.setSize(1280, 720);

		this.setLayout(new BorderLayout());
		this.add(createMenu(), BorderLayout.NORTH);

		if (STATUSCONTA && usuario != null) {
	        exibirPerfis();
	    } else {
	        login();
	    }
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public void cadastrar() {
		updateGUI();

		center = new JPanel();
		JLabel label;

		label = new JLabel("Nome:");
		nomeTxt = new JTextField(10);
		center.add(label);
		center.add(nomeTxt);

		label = new JLabel("Email: ");
		emailTxt = new JTextField(10);
		center.add(label);
		center.add(emailTxt);

		label = new JLabel("Senha: ");
		senhaTxt = new JTextField(10);
		center.add(label);
		center.add(senhaTxt);

		label = new JLabel("Idade: ");
		idadeTxt = new JTextField(10);
		center.add(label);
		center.add(idadeTxt);

		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.setActionCommand("Cadastrar");
		cadastrar.addActionListener(this);
		center.add(cadastrar);

		this.add(center);
		this.setVisible(true);

	}

	public void login() {
		updateGUI();
		center = new JPanel();

		JLabel label;

		label = new JLabel("Email:");
		emailTxt = new JTextField(10);
		center.add(label);
		center.add(emailTxt);

		label = new JLabel("Senha: ");
		senhaTxt = new JTextField(10);
		center.add(label);
		center.add(senhaTxt);

		JButton logar = new JButton("Entrar");
		logar.setActionCommand("logar");
		logar.addActionListener(this);
		center.add(logar);

		this.add(center);
		this.setVisible(true);

	}
	private void listarPerfils(String prefixoComando) {
		center = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		for (Perfil p : usuario.getPerfils()) {

			JButton perfil = new JButton(p.getApelido());
			perfil.setPreferredSize(new Dimension(100, 100));
			perfil.setActionCommand(prefixoComando + p.getApelido());
			perfil.addActionListener(this);
			center.add(perfil);
		}
	}
	public void exibirPerfis() {
		updateGUI();

		listarPerfils("perfil:");	
		
		JButton addperfil = new JButton("+");
		addperfil.setPreferredSize(new Dimension(100, 100));
		addperfil.setActionCommand("adicionarperfil");
		addperfil.addActionListener(this);
		center.add(addperfil);

		this.add(center);
		this.setVisible(true);
	}
	

	public void adicionarPerfil() {
		updateGUI();
		center = new JPanel();
		JLabel label;
		
		label = new JLabel("Apelido:");
		apelidoTxt = new JTextField(10);
		center.add(label);
		center.add(apelidoTxt);
		
		adultoCheck = new JCheckBox("Perfil Adulto");
		
		center.add(adultoCheck);
		
		JButton criar = new JButton("Criar");
		criar.setActionCommand("criarperfil");
		criar.addActionListener(this);
		center.add(criar);
		
		this.add(center);
		this.setVisible(true);
	}
	
	public void removerPerfil() {
		updateGUI();

		listarPerfils("removerperfil:");
		
		this.add(center);
		this.setVisible(true);
	}
	
	public void mudarApelido() {
		updateGUI();
		
		listarPerfils("mudarapelido:");
		this.add(center);
		this.setVisible(true);
		
	}
	
	public void mudarApelidoMenu(){
		updateGUI();
		center = new JPanel();
		JLabel label;
		
		label = new JLabel("Novo apelido:");
		novoApelidoTxt = new JTextField(10);
		center.add(label);
		center.add(novoApelidoTxt);
		
		this.add(center);
		this.setVisible(true);
	}

	public void senhaConfig() {
		updateGUI();
		center = new JPanel();
		JLabel label;

		label = new JLabel("Senha antiga:");
		antigasenhaTxt = new JTextField(10);
		center.add(label);
		center.add(antigasenhaTxt);

		label = new JLabel("Senha nova:");
		novasenhaTxt = new JTextField(10);
		center.add(label);
		center.add(novasenhaTxt);

		JButton alterarsenha = new JButton("Alterar");
		alterarsenha.setActionCommand("alterarsenha");
		alterarsenha.addActionListener(this);
		center.add(alterarsenha);

		this.add(center);
		this.setVisible(true);
	}

	public void emailConfig() {
		updateGUI();
		center = new JPanel();
		JLabel label;

		label = new JLabel("Email antigo:");
		antigoemailTxt = new JTextField(10);
		center.add(label);
		center.add(antigoemailTxt);

		label = new JLabel("Email novo:");
		novoemailTxt = new JTextField(10);
		center.add(label);
		center.add(novoemailTxt);

		JButton alteraremail = new JButton("Alterar");
		alteraremail.setActionCommand("alteraremail");
		alteraremail.addActionListener(this);
		center.add(alteraremail);

		this.add(center);
		this.setVisible(true);
	}

	public void nomeConfig() {
		updateGUI();
		center = new JPanel();
		JLabel label;

		label = new JLabel("Nome antigo:");
		antigonomeTxt = new JTextField(10);
		center.add(label);
		center.add(antigonomeTxt);

		label = new JLabel("Nome novo:");
		novonomeTxt = new JTextField(10);
		center.add(label);
		center.add(novonomeTxt);

		JButton alterarnome = new JButton("Alterar");
		alterarnome.setActionCommand("alterarnome");
		alterarnome.addActionListener(this);
		center.add(alterarnome);

		this.add(center);
		this.setVisible(true);
	}

	public void idadeConfig() {
		updateGUI();
		center = new JPanel();
		JLabel label;

		label = new JLabel("Idade antiga:");
		antigaidadeTxt = new JTextField(10);
		center.add(label);
		center.add(antigaidadeTxt);

		label = new JLabel("Idade nova:");
		novaidadeTxt = new JTextField(10);
		center.add(label);
		center.add(novaidadeTxt);

		JButton alteraridade = new JButton("AlterarIdade");
		alteraridade.setActionCommand("alteraridade");
		alteraridade.addActionListener(this);
		center.add(alteraridade);

		this.add(center);
		this.setVisible(true);
	}

	public void cadastrarSerie() {
		serie = null;
		updateGUI();
		center = new JPanel();
		JLabel label;

		label = new JLabel("Nome da serie:");
		nomeConteudoTxt = new JTextField(10);
		center.add(label);
		center.add(nomeConteudoTxt);

		label = new JLabel("Descricao:");
		descricaoTxt = new JTextField(10);
		center.add(label);
		center.add(descricaoTxt);

		label = new JLabel("Classificação:");
		classiTxt = new JTextField(10);
		center.add(label);
		center.add(classiTxt);

		label = new JLabel("Descricao da temporada:");
		descricaotempTxt = new JTextField(10);
		center.add(label);
		center.add(descricaotempTxt);

		label = new JLabel("Nome episodio:");
		nomeepisodioTxt = new JTextField(10);
		center.add(label);
		center.add(nomeepisodioTxt);

		label = new JLabel("Duracao episodio:");
		duracaoepisodioTxt = new JTextField(10);
		center.add(label);
		center.add(duracaoepisodioTxt);

		JButton cadastrarconteudo = new JButton("Cadastrar");
		cadastrarconteudo.setActionCommand("cadastrarconteudo");
		cadastrarconteudo.addActionListener(this);
		center.add(cadastrarconteudo);

		JButton adicionarep = new JButton("Addepisodio");
		adicionarep.setActionCommand("adicionarep");
		adicionarep.addActionListener(this);
		center.add(adicionarep);
		
		JButton adicionartemp = new JButton("Add temporada");
		adicionartemp.setActionCommand("adicionartemp");
		adicionartemp.addActionListener(this);
		center.add(adicionartemp);

		this.add(center);
		this.setVisible(true);
	}

	public void cadastrarFilme() {
		updateGUI();
		center = new JPanel();
		JLabel label;

		label = new JLabel("Nome do Filme:");
		nomeConteudoTxt = new JTextField(10);
		center.add(label);
		center.add(nomeConteudoTxt);

		label = new JLabel("Descricao:");
		descricaoTxt = new JTextField(10);
		center.add(label);
		center.add(descricaoTxt);

		label = new JLabel("Classificação:");
		classiTxt = new JTextField(10);
		center.add(label);
		center.add(classiTxt);

		label = new JLabel("Duração:");
		duracaoTxt = new JTextField(10);
		center.add(label);
		center.add(duracaoTxt);

		JButton cadastrarconteudo = new JButton("Cadastrar");
		cadastrarconteudo.setActionCommand("cadastrarfilme");
		cadastrarconteudo.addActionListener(this);
		center.add(cadastrarconteudo);;

		this.add(center);
		this.setVisible(true);
	}
	public void listarConteudos() {
		if (perfilatual == null) {
			JOptionPane.showMessageDialog(this, "Selecione um perfil primeiro!");
			exibirPerfis();
			return;
		}

		updateGUI();
		center = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

		for (Conteudo c : series) {
			if (!perfilatual.isAdulto() && c.getClassificacao() > 12) continue;
			JButton btn = new JButton(c.getNome());
			btn.setPreferredSize(new Dimension(120, 100));
			btn.setActionCommand("serie:" + c.getNome());
			btn.addActionListener(this);
			center.add(btn);
		}

		for (Conteudo c : filmes) {
			if (!perfilatual.isAdulto() && c.getClassificacao() > 12) continue;
			JButton btn = new JButton(c.getNome() + " (Filme)");
			btn.setPreferredSize(new Dimension(120, 100));
			btn.setActionCommand("filme:" + c.getNome());
			btn.addActionListener(this);
			center.add(btn);
		}

		this.add(center);
		this.revalidate();
		this.repaint();
	}
	public void paginaConteudo(String chave, String nome) {
		ArrayList<Conteudo> encontrados = gerenciadorConteudo.buscarConteudo(chave, nome);
		if (encontrados.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Conteúdo não encontrado!");
			return;
		}

		Conteudo conteudoatual = encontrados.get(0);

		updateGUI();
		center = new JPanel(new BorderLayout(15, 15));
		center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JPanel topo = new JPanel(new BorderLayout(15, 10));
		topo.setBackground(new Color(35, 35, 35));
		topo.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		JPanel infoEsquerda = new JPanel(new GridLayout(3, 1, 5, 5));
		infoEsquerda.setOpaque(false);

		JLabel tituloLabel = new JLabel(conteudoatual.getNome());
		tituloLabel.setForeground(Color.WHITE);
		tituloLabel.setFont(tituloLabel.getFont().deriveFont(22f));

		JLabel descLabel = new JLabel(conteudoatual.getDescricao());
		descLabel.setForeground(Color.LIGHT_GRAY);

		JLabel resumoLabel = new JLabel(conteudoatual.getResumoDetalhes());
		resumoLabel.setForeground(new Color(220, 50, 50));

		infoEsquerda.add(tituloLabel);
		infoEsquerda.add(descLabel);
		infoEsquerda.add(resumoLabel);

		JPanel infoDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		infoDireita.setOpaque(false);
		JLabel classiLabel = new JLabel("Classificação: " + conteudoatual.getClassificacao() + " anos");
		classiLabel.setForeground(Color.WHITE);
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setActionCommand("paginainicial");
		btnVoltar.addActionListener(this);

		infoDireita.add(classiLabel);
		infoDireita.add(btnVoltar);

		topo.add(infoEsquerda, BorderLayout.CENTER);
		topo.add(infoDireita, BorderLayout.EAST);
		center.add(topo, BorderLayout.NORTH);

		if (conteudoatual instanceof Serie) {
			Serie s = (Serie) conteudoatual;
			JPanel painelSerie = new JPanel(new BorderLayout(10, 10));

			JPanel barraTemporada = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
			barraTemporada.add(new JLabel("Selecione a Temporada:"));

			ArrayList<Temporada> temporadas = s.getTemporadas();
			String[] nomesTemporadas = new String[temporadas.size()];
			for (int i = 0; i < temporadas.size(); i++) {
				nomesTemporadas[i] = "Temporada " + (i + 1) + " - " + temporadas.get(i).getDescricao();
			}

			javax.swing.JComboBox<String> comboTemporadas = new javax.swing.JComboBox<>(nomesTemporadas);
			barraTemporada.add(comboTemporadas);
			painelSerie.add(barraTemporada, BorderLayout.NORTH);

			JPanel listaEpisodiosPanel = new JPanel(new GridLayout(0, 1, 8, 8));
			javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(listaEpisodiosPanel);
			scroll.setBorder(BorderFactory.createTitledBorder("Episódios"));
			painelSerie.add(scroll, BorderLayout.CENTER);

			Runnable atualizarListaEpisodios = () -> {
				listaEpisodiosPanel.removeAll();
				int idxTemp = comboTemporadas.getSelectedIndex();
				if (idxTemp >= 0 && idxTemp < temporadas.size()) {
					Temporada tempSelecionada = temporadas.get(idxTemp);
					ArrayList<Episodio> eps = tempSelecionada.getEpisodios();
					for (int i = 0; i < eps.size(); i++) {
						Episodio ep = eps.get(i);
						JPanel linhaEp = new JPanel(new BorderLayout(10, 5));
						linhaEp.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

						JLabel lblEp = new JLabel("  Ep. " + (i + 1) + " - " + ep.getNome() + " (" + ep.getDuracao() + " min)");
						JButton btnAssistir = new JButton("Assistir");
						int epIndex = i;
						btnAssistir.addActionListener(ev -> {
							JOptionPane.showMessageDialog(this, s.reproduzir(idxTemp, epIndex));
						});

						linhaEp.add(lblEp, BorderLayout.CENTER);
						linhaEp.add(btnAssistir, BorderLayout.EAST);
						listaEpisodiosPanel.add(linhaEp);
					}
				}
				listaEpisodiosPanel.revalidate();
				listaEpisodiosPanel.repaint();
			};

			comboTemporadas.addActionListener(ev -> atualizarListaEpisodios.run());
			if (!temporadas.isEmpty()) {
				atualizarListaEpisodios.run();
			}

			center.add(painelSerie, BorderLayout.CENTER);

		} else if (conteudoatual instanceof Filme) {
			Filme f = (Filme) conteudoatual;
			JPanel painelFilme = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
			JButton btnAssistirFilme = new JButton("Assistir Filme (" + f.getDuracao() + " min)");
			btnAssistirFilme.setPreferredSize(new Dimension(220, 45));
			btnAssistirFilme.addActionListener(ev -> {
				JOptionPane.showMessageDialog(this, "Você assistiu o filme: " + f.getNome());
			});
			painelFilme.add(btnAssistirFilme);
			center.add(painelFilme, BorderLayout.CENTER);
		}

		this.add(center);
		this.revalidate();
		this.repaint();
	}
	
	private int lerInteiro(String texto) throws CampoVazioException, IdadeStringException {
		if (texto == null || texto.isBlank()) {
			throw new CampoVazioException();
		}
		try {
			return Integer.parseInt(texto.trim());
		} catch (NumberFormatException nfe) {
			throw new IdadeStringException();
		}
	}
	
	private double lerDuracao(String texto) throws CampoVazioException, DuracaoInvalidaException {
		if (texto == null || texto.isBlank()) {
			throw new CampoVazioException();
		}
		try {
			return Double.parseDouble(texto.trim());
		} catch (NumberFormatException nfe) {
			throw new DuracaoInvalidaException();
		}
	}
	
	private void contaConfig() {
		updateGUI();
		center = new JPanel();
		JLabel label;
		
		label = new JLabel("Email: "+ usuario.getEmail());
		center.add(label);
		
		label = new JLabel("Nome: "+ usuario.getNome());
		center.add(label);
		label = new JLabel("Idade: "+ usuario.getIdade());
		center.add(label);
		
		JButton apagarconta = new JButton("Apagar conta");
		apagarconta.setActionCommand("apagarconta");
		apagarconta.addActionListener(this);
		center.add(apagarconta);
		
		this.add(center);
		this.setVisible(true);
	}
 
	public void sairConta() {
		usuario = null;
	    perfilatual = null;
	    STATUSCONTA = false;

	    this.remove(menubar);
	    this.add(createMenu(), BorderLayout.NORTH);
	    menubar.revalidate();
	    menubar.repaint();
	    login();
	}
	
	private void listarConteudos(ArrayList<Conteudo> lista, String prefixoComando) {
		updateGUI();
		center = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		for (Conteudo c : lista) {
			JButton btn = new JButton(c.getNome());
			btn.setPreferredSize(new Dimension(120, 100));
			btn.setActionCommand(prefixoComando + c.getNome());
			btn.addActionListener(this);
			center.add(btn);
		}
		this.add(center);
		this.revalidate();
		this.repaint();
	}
	
	private void editarConteudo(String chave, String nomeAtual) throws ClassificacaoInvalidaException, ConteudoNaoEcontradoException, CampoVazioException, IdadeStringException, IOException {
		String[] opcoes = {"Nome", "Descrição", "Classificação"};
		int escolha = JOptionPane.showOptionDialog(
				this,
				"O que deseja editar em " + nomeAtual + "?",
				"Editar Conteúdo",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				opcoes,
				opcoes[0]
		);

		if (escolha == 0) {
			String novoNome = JOptionPane.showInputDialog(this, "Novo nome:");
			if (novoNome != null) {
				gerenciadorConteudo.atualizarNomeConteudo(chave, nomeAtual, novoNome);
			}
		} else if (escolha == 1) {
			String novaDesc = JOptionPane.showInputDialog(this, "Nova descrição:");
			if (novaDesc != null) {
				gerenciadorConteudo.atualizarDescricaoConteudo(chave, nomeAtual, novaDesc);
			}
		} else if (escolha == 2) {
			String input = JOptionPane.showInputDialog(this, "Nova classificação (0 a 18):");
			if (input != null) {
				gerenciadorConteudo.atualizarClassificacaoConteudo(chave, nomeAtual, lerInteiro(input));
			}
		} else {
			return;
		}

		gerenciadorConteudo.salvarJSON();
		series = gerenciadorConteudo.getConteudo("series");
		filmes = gerenciadorConteudo.getConteudo("filmes");
		JOptionPane.showMessageDialog(this, "Conteúdo atualizado com sucesso!");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
 
		try {
			if (cmd.equals("menuregistrar")) {
				cadastrar();
 
			} else if (cmd.equals("menulogar")) {
				login();
 
			} else if (cmd.equals("Cadastrar")) {
				String nome = nomeTxt.getText().trim();
				String email = emailTxt.getText().trim();
				String senha = senhaTxt.getText();
				int idade = lerInteiro(idadeTxt.getText());
 
				Usuario novo = new Usuario(nome, email, senha, idade);
				gerenciador.addUsuario(novo);
				gerenciador.salvarJSON();
				JOptionPane.showMessageDialog(this, "Usuario criado com sucesso!");
 
			} else if (cmd.equals("logar")) {
				String email = emailTxt.getText().trim();
				String senha = senhaTxt.getText();
 
				Usuario logado;
				try {
					logado = gerenciador.logarUsuario(email, senha);
				} catch (SenhaIncorretaException | EmailNaoExisteException loginEx) {
					JOptionPane.showMessageDialog(this, "E-mail ou senha inválidos!");
					return;
				}
				
				usuario = logado;
				gerenciador.salvarSessaoJSON(usuario.getEmail());
				STATUSCONTA = true;
				this.remove(menubar);
				this.add(createMenu(), BorderLayout.NORTH);
				menubar.revalidate();
				menubar.repaint();
 
				JOptionPane.showMessageDialog(this, "Usuario logado com sucesso!");
				exibirPerfis();
 
			} else if (cmd.equals("listaperfis")) {
				exibirPerfis();
 
			} else if (cmd.startsWith("perfil:")) {
				String apelido = cmd.replace("perfil:", "");
				Perfil escolhido = usuario.getPerfil(apelido);
				if (escolhido == null) {
					JOptionPane.showMessageDialog(this, "Perfil não encontrado!");
					return;
				}
				perfilatual = escolhido;
				JOptionPane.showMessageDialog(this, "Voce está no perfil de " + perfilatual.getApelido());
				listarConteudos();
 
			} else if (cmd.equals("adicionarperfil")) {
				adicionarPerfil();
 
			} else if (cmd.equals("criarperfil")) {
				String apelido = apelidoTxt.getText().trim();
				boolean adulto = adultoCheck.isSelected();
 
				usuario.addPerfil(apelido, adulto);
				gerenciador.salvarJSON();
				JOptionPane.showMessageDialog(this, "Perfil criado com sucesso!");
				exibirPerfis();
 
			} else if (cmd.equals("adicionarserie")) {
				cadastrarSerie();
 
			} else if (cmd.equals("cadastrarconteudo")) {
				String nome = nomeConteudoTxt.getText().trim();
				String descricao = descricaoTxt.getText().trim();
				int classificacao = lerInteiro(classiTxt.getText());
				String descricaotemp = descricaotempTxt.getText().trim();
 
				Serie nova = new Serie(nome, descricao, classificacao);
				nova.adicionarTemporada(descricaotemp);
				gerenciadorConteudo.adicionarConteudo(nova);
				gerenciadorConteudo.salvarJSON();
 
				serie = nova;
				series = gerenciadorConteudo.getConteudo("series");
				JOptionPane.showMessageDialog(this, "Serie adicionada com sucesso!");
 
			} else if (cmd.equals("adicionarep")) {
				if (serie == null) {
					JOptionPane.showMessageDialog(this, "Cadastre uma série primeiro.");
					return;
				}
				String nomeep = nomeepisodioTxt.getText().trim();
				if (nomeep.isEmpty()) {
				    throw new CampoVazioException();
				}
				double duracaoep = lerDuracao(duracaoepisodioTxt.getText());

				serie.getTemporada().adicionarEpisodio(nomeep, duracaoep);
				gerenciadorConteudo.salvarJSON();
				JOptionPane.showMessageDialog(this, "Episódio adicionado!");
 
			} else if (cmd.equals("menualterarsenha")) {
				senhaConfig();
 
			} else if (cmd.equals("alterarsenha")) {
				String novasenha = novasenhaTxt.getText();
				String antigasenha = antigasenhaTxt.getText();
 
				usuario.alterarSenha(novasenha, antigasenha);
				gerenciador.salvarJSON();
				JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
 
			} else if (cmd.equals("menualteraremail")) {
				emailConfig();
 
			} else if (cmd.equals("alteraremail")) {
				String novoemail = novoemailTxt.getText().trim();
				String antigoemail = antigoemailTxt.getText().trim();
 
				gerenciador.alterarEmail(usuario,novoemail, antigoemail);
				gerenciador.salvarJSON();
				JOptionPane.showMessageDialog(this, "E-mail alterado com sucesso!");
 
			} else if (cmd.equals("menualterarnome")) {
				nomeConfig();
 
			} else if (cmd.equals("alterarnome")) {
				String novonome = novonomeTxt.getText().trim();
				String antigonome = antigonomeTxt.getText().trim();
 
				usuario.alterarNome(novonome, antigonome);
				gerenciador.salvarJSON();
				JOptionPane.showMessageDialog(this, "Nome alterado com sucesso!");
 
			} else if (cmd.equals("menualteraridade")) {
				idadeConfig();
 
			} else if (cmd.equals("alteraridade")) {
				int novaidade = lerInteiro(novaidadeTxt.getText());
				int antigaidade = lerInteiro(antigaidadeTxt.getText());
 
				usuario.alterarIdade(novaidade, antigaidade);
				gerenciador.salvarJSON();
				JOptionPane.showMessageDialog(this, "Idade alterada com sucesso!");
 
			} else if (cmd.equals("paginainicial")) {
				listarConteudos();
 
			} else if (cmd.startsWith("serie:")) {
				String nome = cmd.replace("serie:", "");
				paginaConteudo("series", nome);
			} else if(cmd .equals("menuconta")) {
				contaConfig();
			} else if (cmd.equals("apagarconta")) {
			    int confirmacao = JOptionPane.showConfirmDialog(
			            this,
			            "Tem certeza que deseja apagar sua conta?",
			            "Confirmar exclusão",
			            JOptionPane.YES_NO_OPTION
			        );
			        if (confirmacao == JOptionPane.YES_OPTION) {
			            gerenciador.apagarConta(usuario.getEmail());
			            gerenciador.salvarJSON();
			            gerenciador.salvarSessaoJSON("");
			            sairConta();
			            JOptionPane.showMessageDialog(this, "Conta apagada com sucesso!");
			        }

			    } else if (cmd.equals("sairconta")) {
			        gerenciador.salvarSessaoJSON("");
			        sairConta();

			    } else if (cmd.equals("removerperfil")) {
			        removerPerfil();

			    } else if (cmd.startsWith("removerperfil:")) {
			        String apelido = cmd.replace("removerperfil:", "");
			        usuario.removerPerfil(apelido);
			        if (perfilatual != null && perfilatual.getApelido().equalsIgnoreCase(apelido)) {
			            perfilatual = null;
			        }
			        gerenciador.salvarJSON();
			        JOptionPane.showMessageDialog(this, "Perfil removido!");
			        exibirPerfis();

			    } else if (cmd.equals("apelidoperfil")) {
			        mudarApelido();

			    } else if (cmd.startsWith("mudarapelido:")) {
			        String apelidoAntigo = cmd.replace("mudarapelido:", "");
			        String novoApelido = JOptionPane.showInputDialog(this, "Novo apelido para " + apelidoAntigo + ":");
			        
			        if (novoApelido != null) {
			            usuario.alterarApelidoPerfil(apelidoAntigo, novoApelido);
			            gerenciador.salvarJSON();
			            JOptionPane.showMessageDialog(this, "Apelido alterado com sucesso!");
			            exibirPerfis();
			        }
			    } else if (cmd.equals("apagarserie")) {
			    	listarConteudos(series, "apagarserie:");
			    } else if (cmd.equals("editarserie")) {
			    	listarConteudos(series, "editarserie:");
			    } else if (cmd.equals("adicionarfilme")) {
			    	cadastrarFilme();
			    } else if (cmd.equals("apagarfilme")) {
			    	listarConteudos(filmes, "apagarfilme:");
			    } else if (cmd.equals("editarfilme")) {
			    	listarConteudos(filmes, "editarfilme:");
			    }else if (cmd.startsWith("apagarserie:")) {
			    	String nome = cmd.replace("apagarserie:", "");
			    	gerenciadorConteudo.removerConteudo("series", nome);
			    	gerenciadorConteudo.salvarJSON();
			    	series = gerenciadorConteudo.getConteudo("series");
			    	JOptionPane.showMessageDialog(this, "Série removida com sucesso!");
			    	listarConteudos(series, "apagarserie:");

			    } else if (cmd.startsWith("apagarfilme:")) {
			    	String nome = cmd.replace("apagarfilme:", "");
			    	gerenciadorConteudo.removerConteudo("filmes", nome);
			    	gerenciadorConteudo.salvarJSON();
			    	filmes = gerenciadorConteudo.getConteudo("filmes");
			    	JOptionPane.showMessageDialog(this, "Filme removido com sucesso!");
			    	listarConteudos(filmes, "apagarfilme:");
			    } else if (cmd.startsWith("filme:")) {
			    	String nome = cmd.replace("filme:", "");
			    	paginaConteudo("filmes", nome);
			    }else if (cmd.startsWith("editarserie:")) {
			    	String nomeAtual = cmd.replace("editarserie:", "");
			    	editarConteudo("series", nomeAtual);
			    	listarConteudos(series, "editarserie:");

			    }else if (cmd.equals("cadastrarfilme")) {
			    	String nome = nomeConteudoTxt.getText().trim();
			    	String descricao = descricaoTxt.getText().trim();
			    	int classificacao = lerInteiro(classiTxt.getText());
			    	double duracao = lerDuracao(duracaoTxt.getText());

			    	Filme novoFilme = new Filme(nome, descricao, classificacao, duracao);
			    	gerenciadorConteudo.adicionarConteudo(novoFilme);
			    	gerenciadorConteudo.salvarJSON();
			    	filmes = gerenciadorConteudo.getConteudo("filmes");
			    	JOptionPane.showMessageDialog(this, "Filme adicionado com sucesso!");
			    }else if (cmd.startsWith("editarfilme:")) {
			    	String nomeAtual = cmd.replace("editarfilme:", "");
			    	editarConteudo("filmes", nomeAtual);
			    	listarConteudos(filmes, "editarfilme:");
			    } else if (cmd.equals("adicionartemp")) {
					if (serie == null) {
						JOptionPane.showMessageDialog(this, "Cadastre uma série primeiro.");
						return;
					}
					String descricaotemp = descricaotempTxt.getText().trim();
					serie.adicionarTemporada(descricaotemp);
					gerenciadorConteudo.salvarJSON();
					descricaotempTxt.setText("");
					JOptionPane.showMessageDialog(this, "Nova temporada adicionada!");
			    }
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Digite apenas números nos campos numéricos.");
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this, "Não foi possível salvar os dados.");
		} catch (CampoVazioException | EmailExistenteException | EmailInvalidoException
				| EmailNaoExisteException | IdadeInvalidaException | IdadeStringException
				| PerfilDuplicadoException | SenhaIncorretaException | TamanhoInvalidoException
				| ClassificacaoInvalidaException | ConteudoExistenteException
				| ConteudoNaoEcontradoException | SenhaIgualException | NomeIncorretoException
				| NomeIgualException | IdadeIncorretaException | IdadeIgualException
				| PerfilNaoExisteException | PerfilPadraoException | DuracaoInvalidaException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} catch (RuntimeException re) {
			re.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro inesperado: " + re);
		}

	
	}

	public static void main(String[] args) {
		StreamGUI streamgui = new StreamGUI();
		
		streamgui.executar();
	}
}
