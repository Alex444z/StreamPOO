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
import conta.Usuario;

public class StreamGUI extends JFrame implements ActionListener{
	private Usuario usuario;
	private GerenciadorUsuarios gerenciador;
	
	private JPanel center;
	private  JTextField nomeTxt;
	private  JTextField emailTxt;
	private  JTextField senhaTxt;
	private  JTextField idadeTxt;
		
	public StreamGUI() {
		gerenciador = new GerenciadorUsuarios();
	}
	
	private Component createMenu() {
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Conta");
		
		JMenuItem item = new JMenuItem("Registrar!");
		item.setActionCommand("menuRegistrar");
		item.addActionListener(this);
		menu.add(item);
		
		item = new JMenuItem("Login");
		item.setActionCommand("Logar");
		item.addActionListener(this);
		menu.add(item);
		
		menubar.add(menu);
		return menubar;
	}
	public void update() {
		
	}
	public void executar() {
		this.setTitle("StreamPOO");
		this.setSize(1280, 720);
		
		this.setLayout(new BorderLayout());
		this.add(createMenu(), BorderLayout.NORTH);
		
		this.cadastrar();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}	
	
	public void cadastrar() {
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Registrar")) {
			
		}else if (cmd.equals("Logar")) {		
			
		}else if (cmd.equals("Cadastrar")){
			String nome = nomeTxt.getText();
			String email = emailTxt.getText();
			String senha = senhaTxt.getText();
			int idade = Integer.parseInt(idadeTxt.getText());
			
			usuario = new Usuario(nome,email,senha,idade);
			
			gerenciador.addUsuario(usuario);
			
			JOptionPane.showMessageDialog(this, "Usuario criado com sucesso!");
		}
	}
	
	public static void main(String[] args) {
		StreamGUI streamgui = new StreamGUI();
		streamgui.executar();
	}

	
	
	

}
