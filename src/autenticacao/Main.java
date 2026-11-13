package autenticacao;

import javax.swing.JOptionPane;

import conta.Usuario;
import exceptions.CampoVazioException;
import exceptions.EmailInvalidoException;
import exceptions.IdadeInvalidaException;
import exceptions.TamanhoInvalidoException;

public class Main {

	public static void main(String[] args) throws CampoVazioException, EmailInvalidoException, TamanhoInvalidoException,
	IdadeInvalidaException {
		GerenciadorUsuarios manager  = new GerenciadorUsuarios();
		
		boolean opc2 = true;
		while (opc2) {
			String opc = JOptionPane.showInputDialog(null, "Escolha a opção: \n1: cadastro \n2: usuarios");
			if (opc.equals("1")) {
				String email = JOptionPane.showInputDialog(null, "Digite seu email: ");
				String senha = JOptionPane.showInputDialog(null, "Digite sua senha: ");
				String nome = JOptionPane.showInputDialog(null, "Digite seu nome: ");
				String idade = JOptionPane.showInputDialog(null, "Digite sua idade: ");
				int idd = Integer.parseInt(idade);
				
				JOptionPane.showMessageDialog(null, manager.verificarDuploEmail(email));
				manager.verificarDuploEmail(email);
				
				Usuario user = new Usuario(email,senha,nome,idd);
				manager.adicionarUsuario(user);
				
				String t = JOptionPane.showInputDialog(null, "continuar? ");
				if (t.equals("s")) {
					opc2 = true;
				}else if (t.equals("u")){
					JOptionPane.showMessageDialog(null, manager.listarQuantiUsuario());
				}else {
					opc2 = false;
				}
			}
				
		}

	} 
}