import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	private Perfil perfilatual;
	private static boolean STATUSCONTA;
	private GerenciadorUsuarios gerenciador;
	
	private JPanel center;
	JMenuBar menubar;
	private  JTextField nomeTxt;
	private  JTextField emailTxt;
	private  JTextField senhaTxt;
	private  JTextField idadeTxt;
		
	public StreamGUI() {
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
			item = new JMenuItem("Segurança");
			item.setActionCommand("menuseguranca");
			item.addActionListener(this);
			menu.add(item);
			menubar.add(menu);
		}else {
			item = new JMenuItem("Registrar");
			item.setActionCommand("menuregistrar");
			item.addActionListener(this);
			menu.add(item);
			
			item = new JMenuItem("Login");
			item.setActionCommand("menulogar");
			item.addActionListener(this);
			menu.add(item);
			menubar.add(menu);
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
		
		int cont = 1;
		for (Perfil p : usuario.getPerfils()) {
			
			JButton perfil = new JButton(p.getApelido());
			perfil.setPreferredSize(new Dimension(100, 100));
			perfil.setActionCommand("perfil:" + p.getApelido());
			perfil.addActionListener(this);
			center.add(perfil);
			cont++;
		}
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
			try {
				gerenciador.salvarJSON();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
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
				exibirPerfis();
			}else {
				JOptionPane.showMessageDialog(this, "Usuario ou senha invalida!");
			}	
		}else if (cmd.startsWith("perfil:")) {
			String apelido = cmd.replace("perfil:", "");
			perfilatual = usuario.getPerfil(apelido);
			JOptionPane.showMessageDialog(this, "Voce está no perfil de "+ perfilatual.getApelido());
		}
	}
	
	public static void main(String[] args) {
		StreamGUI streamgui = new StreamGUI();
		streamgui.executar();
	}

	
	
	

}
