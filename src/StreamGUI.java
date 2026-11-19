import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import autenticacao.GerenciadorUsuarios;
import conta.Perfil;
import conta.Usuario;

public class StreamGUI extends JFrame implements ActionListener{
	private Usuario usuario;
	private static boolean STATUSCONTA;
	private GerenciadorUsuarios gerenciador;
	
	private JPanel center;
	JMenuBar menubar;
	private  JTextField nomeTxt;
	private  JTextField emailTxt;
	private  JTextField senhaTxt;
	private  JTextField idadeTxt;
		
	public StreamGUI() {
		gerenciador = new GerenciadorUsuarios();

		usuario = new Usuario("Jose","jose@","1234",20);
		usuario.criarPerfil("night", 20);
		usuario.criarPerfil("duda", 20);
		usuario.criarPerfil("dnilso", 20);
		gerenciador.addUsuario(usuario);
	}
	
	private Component createMenu() {
		menubar = new JMenuBar();
		JMenu menu = new JMenu("Conta");
		
		JMenuItem item = new JMenuItem("Registrar");
		item.setActionCommand("menuregistrar");
		item.addActionListener(this);
		menu.add(item);
		
		item = new JMenuItem("Login");
		item.setActionCommand("menulogar");
		item.addActionListener(this);
		menu.add(item);
		
		menubar.add(menu);
		
		if (STATUSCONTA) {
			JMenu menuprin = new JMenu("Perfis");
			for (Perfil p : usuario.getPerfils()) {
				item = new JMenuItem(p.getApelido());
				item.setActionCommand("perfil");
				item.addActionListener(this);
				menuprin.add(item);
			}
		menubar.add(menuprin);
		}
		
		return menubar;
		
	}
	
	public void updateGUI() {
		if (center != null) {
			this.remove(center);
		}this.repaint();
		
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
		updateGUI()
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("menuregistrar")) {
			cadastrar();
		}else if (cmd.equals("menulogar")) {		
			login();
		}else if (cmd.equals("Cadastrar")){
			String nome = nomeTxt.getText();
			String email = emailTxt.getText();
			String senha = senhaTxt.getText();
			int idade = Integer.parseInt(idadeTxt.getText());
			
			usuario = new Usuario(nome,email,senha,idade);
			
			gerenciador.addUsuario(usuario);
			
			JOptionPane.showMessageDialog(this, "Usuario criado com sucesso!");
		}else if(cmd.equals("logar")) {
			
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
			}else {
				JOptionPane.showMessageDialog(this, "Usuario ou senha invalida!");
			}
			
			
		}
	}
	
	public static void main(String[] args) {
		StreamGUI streamgui = new StreamGUI();
		streamgui.executar();
	}

	
	
	

}
