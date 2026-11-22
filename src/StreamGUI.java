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
import catalogo.Conteudo;
import conta.Perfil;
import conta.Usuario;
import exceptions.CampoVazioException;
import exceptions.EmailExistenteException;
import exceptions.EmailInvalidoException;
import exceptions.IdadeInvalidaException;
import exceptions.IdadeStringException;
import exceptions.PerfilDuplicadoException;
import exceptions.TamanhoInvalidoException;

public class StreamGUI extends JFrame implements ActionListener {
	private Usuario usuario;
	private Perfil perfilatual;
	private static boolean STATUSCONTA;
	private GerenciadorUsuarios gerenciador;
	private GerenciadorConteudo gerenciadorConteudo;
	private Serie serie;
	ArrayList<Conteudo> series;

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

	public StreamGUI() {
		try {
			gerenciadorConteudo = new GerenciadorConteudo();
			series =gerenciadorConteudo.getConteudo("series");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			gerenciador = new GerenciadorUsuarios();
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
			menubar.add(menu);
			
			JMenu painelconteudo = new JMenu("Painel");
			item = new JMenuItem("Pagina Inicial");
			item.setActionCommand("paginainicial");
			item.addActionListener(this);
			painelconteudo.add(item);
			menubar.add(painelconteudo);
			
			// perfis
			item = new JMenuItem("Perfils");
			item.setActionCommand("menuperfils");
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
			item = new JMenuItem("AcessoAdmin");
			item.setActionCommand("admacess");
			item.addActionListener(this);
			menuadm.add(item);
			menubar.add(menuadm);

		}
		return menubar;
	}

	public void updateGUI() {
		if (center != null) {
			this.remove(center);
		}
		this.repaint();

	}

	public void executar() {
		this.setTitle("StreamPOO");
		this.setSize(1280, 720);

		this.setLayout(new BorderLayout());
		this.add(createMenu(), BorderLayout.NORTH);

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

	public void exibirPerfis() {
		updateGUI();
		center = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

		for (Perfil p : usuario.getPerfils()) {

			JButton perfil = new JButton(p.getApelido());
			perfil.setPreferredSize(new Dimension(100, 100));
			perfil.setActionCommand("perfil:" + p.getApelido());
			perfil.addActionListener(this);
			center.add(perfil);
		}
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
		JCheckBox adultoCheck;
		
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
		updateGUI();
		center = new JPanel();
		JLabel label;

		label = new JLabel("Nome da serie/Filme:");
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
		cadastrarconteudo.setActionCommand("cadastrarconteudo");
		cadastrarconteudo.addActionListener(this);
		center.add(cadastrarconteudo);

		this.add(center);
		this.setVisible(true);
	}
	public void listarSeries() {
		updateGUI();
		center = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		for (Conteudo c : series) {
			JButton serie = new JButton(c.getNome());
			serie.setPreferredSize(new Dimension(100, 100));
			serie.setActionCommand("serie:" + c.getNome());
			serie.addActionListener(this);
			center.add(serie);
		}

		this.add(center);
		this.setVisible(true);
	}
	public void paginaSerie(String nome) {
		updateGUI();
		center = new JPanel( new BorderLayout(0,15));
		center.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		JPanel topo = new JPanel(new GridLayout(1, 2, 15, 0));
		topo.setBackground(new Color(255,0,0));
		topo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		JPanel primeiro = new JPanel(new BorderLayout(0,10));
		JLabel label;
		Conteudo serieatual = null;
		for (Conteudo c : series) {
			if (c.getNome().equals(nome)) {
				serieatual = c;
				break;
			}
		}

		label = new JLabel(serieatual.getNome());		
		primeiro.add(label, BorderLayout.NORTH);
		
		
		
		
		label = new JLabel(serieatual.getDescricao());
		primeiro.add(label, BorderLayout.CENTER);
		
		center.add(topo, BorderLayout.NORTH);
		
		label = new JLabel(Integer.toString(serieatual.getClassificacao()));		
		primeiro.add(label, BorderLayout.EAST);
		topo.add(primeiro);
	
		this.add(center);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		if (cmd.equals("menuregistrar")) {
			cadastrar();
		} else if (cmd.equals("menulogar")) {
			login();
		} else if (cmd.equals("Cadastrar")) {
				
			try {
				String nome = nomeTxt.getText();
				String email = emailTxt.getText();
				String senha = senhaTxt.getText();
				String idadeTexto = idadeTxt.getText();
				
				if (idadeTexto.isEmpty()) {
		            throw new CampoVazioException();
		        }
				
				int idade;
				try {
		            idade = Integer.parseInt(idadeTexto);
		        } catch (NumberFormatException nfe) {
		            throw new IdadeStringException();
		        }
				
				usuario = new Usuario(nome, email, senha, idade);
				gerenciador.addUsuario(usuario);
				try {
					gerenciador.salvarJSON();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Usuario criado com sucesso!");
			} catch (CampoVazioException cve) {
				JOptionPane.showMessageDialog(this, cve.getMessage());
			} catch (IdadeStringException ise) {
				JOptionPane.showMessageDialog(this, ise.getMessage());
			} catch (TamanhoInvalidoException tie) {
				JOptionPane.showMessageDialog(this, tie.getMessage());
			} catch (IdadeInvalidaException iie) {
				JOptionPane.showMessageDialog(this, iie.getMessage());
			} catch (EmailInvalidoException eie) {
				JOptionPane.showMessageDialog(this, eie.getMessage());
			} catch (PerfilDuplicadoException pde) {
				JOptionPane.showMessageDialog(this, pde.getMessage());
			} catch (EmailExistenteException eee) {
				JOptionPane.showMessageDialog(this, eee.getMessage());
			}
			

			
		} else if (cmd.equals("logar")) {
			try {
			String email = emailTxt.getText();
			String senha = senhaTxt.getText();
			usuario = gerenciador.logarUsuario(email, senha);
				if (usuario != null) {
					STATUSCONTA = true;
					this.remove(menubar);
	
					this.add(createMenu(), BorderLayout.NORTH);
					menubar.revalidate();
					menubar.repaint();
	
					JOptionPane.showMessageDialog(this, "Usuario logado com sucesso!");
					exibirPerfis();
				} else {
					JOptionPane.showMessageDialog(this, "Usuario ou senha invalida!");
				}
			}catch(EmailExistenteException eee) {
				JOptionPane.showMessageDialog(this, eee.getMessage());
			}
		} else if (cmd.equals("menuperfils")) {
			exibirPerfis();
		} else if (cmd.startsWith("perfil:")) {
			String apelido = cmd.replace("perfil:", "");
			perfilatual = usuario.getPerfil(apelido);
			JOptionPane.showMessageDialog(this, "Voce está no perfil de " + perfilatual.getApelido());
			listarSeries();
		} else if (cmd.equals("adicionarperfil")) {
			adicionarPerfil();
		} else if (cmd.equals("criarperfil")) {
			try {
				String apelido = apelidoTxt.getText();
				boolean adulto = adultoCheck.isSelected();


				usuario.addPerfil(apelido, adulto);
				gerenciador.salvarJSON();

			} catch (PerfilDuplicadoException pde) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, pde.getMessage());
			} catch (IdadeStringException ise) {
				JOptionPane.showMessageDialog(this, ise.getMessage());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		}else if (cmd.equals("admacess")) {
			cadastrarSerie();
		} else if (cmd.equals("cadastrarconteudo")) {
			String nome = nomeConteudoTxt.getText();
			String descricao = descricaoTxt.getText();
			int classificacao = Integer.parseInt(classiTxt.getText());
			String descricaotemp = descricaotempTxt.getText();

			serie = new Serie(nome, descricao, classificacao);
			serie.adicionarTemporada(descricaotemp);
			gerenciadorConteudo.adicionarConteudo(serie);
			try {
				gerenciadorConteudo.salvarJSON();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Serie adicionada com sucesso!");

			}
			JOptionPane.showMessageDialog(this, gerenciadorConteudo.getConteudos());

		} else if (cmd.equals("adicionarep")) {
			String nomeep = nomeepisodioTxt.getText();
			double duracaoep = Double.parseDouble(duracaoepisodioTxt.getText());
			serie.getTemporada().adicionarEpisodio(nomeep, duracaoep);
			try {
				gerenciadorConteudo.salvarJSON();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Serie adicionada com sucesso!");

			}
			JOptionPane.showMessageDialog(this, "cmd ativado");
		} else if (cmd.equals("menualterarsenha")) {
			senhaConfig();
		} else if (cmd.equals("alterarsenha")) {
			String novasenha = novasenhaTxt.getText();
			String antigasenha = antigasenhaTxt.getText();
			usuario.alterarSenha(novasenha, antigasenha);
			JOptionPane.showMessageDialog(this, usuario.getSenha());
			try {
				gerenciador.salvarJSON();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (cmd.equals("menualteraremail")) {
			emailConfig();

		} else if (cmd.equals("alteraremail")) {
			String novoemail = novoemailTxt.getText();
			String antigoemail = antigoemailTxt.getText();
			usuario.alterarEmail(novoemail, antigoemail);
			JOptionPane.showMessageDialog(this, usuario.getEmail());
			try {
				gerenciador.salvarJSON();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (cmd.equals("menualterarnome")) {
			nomeConfig();
		} else if (cmd.equals("alterarnome")) {
			String novonome = novonomeTxt.getText();
			String antigonome = antigonomeTxt.getText();
			usuario.alterarNome(novonome, antigonome);
			JOptionPane.showMessageDialog(this, usuario.getNome());
			try {
				gerenciador.salvarJSON();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (cmd.equals("menualteraridade")) {
			idadeConfig();
		} else if (cmd.equals("alteraridade")) {
			int novaidade = Integer.parseInt(novaidadeTxt.getText());
			int antigaidade = Integer.parseInt(antigaidadeTxt.getText());
			usuario.alterarIdade(novaidade, antigaidade);
			JOptionPane.showMessageDialog(this, usuario.getIdade());
			try {
				gerenciador.salvarJSON();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(cmd.equals("paginainicial")) {
			listarSeries();
		}else if(cmd.startsWith("serie:")) {
			String nome = cmd.replace("serie:", "");
			paginaSerie(nome);	
		}
	}

	public static void main(String[] args) {
		StreamGUI streamgui = new StreamGUI();
		streamgui.executar();
	}
}
