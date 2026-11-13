import javax.swing.JOptionPane;

import autenticacao.GerenciadorUsuarios;
import conta.Usuario;

public class Main {

	public static void main(String[] args) {
		int opc;
		int opc1;
		GerenciadorUsuarios gerenciador = new GerenciadorUsuarios();
		Usuario adm = new Usuario("Admin","admin","ADM",20);
		gerenciador.adicionarUsuario(adm);
		do {
			//apagar depois
			boolean usr = true;
			if (usr) {
				Usuario usuario = new Usuario("Admin","admin","ADM",20);
				gerenciador.adicionarUsuario(usuario);
				usr = false;
			}
		opc = Integer.parseInt(JOptionPane.showInputDialog(null,"1- Registrar!\n2- Login!\n0- Sair!"));
		
		switch (opc){
			case 1:
				String email = JOptionPane.showInputDialog(null, "Digite o email: ");
				String senha = JOptionPane.showInputDialog(null, "Digite a senha: ");
				String nome = JOptionPane.showInputDialog(null, "Digite o nome: ");
				int idade = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite a idade"));
				
				Usuario usuario = new Usuario(email,senha,nome,idade);
				gerenciador.adicionarUsuario(usuario);
				
				JOptionPane.showMessageDialog(null, "Usuario Registrado com sucesso!");
				break;
			case 2:
				String email1 = JOptionPane.showInputDialog(null, "Digite o email: ");
				String senha1 = JOptionPane.showInputDialog(null, "Digite a senha: ");
				if (gerenciador.logarUsuario(email1, senha1) != null) {
					usuario = gerenciador.logarUsuario(email1, senha1);
					JOptionPane.showMessageDialog(null, "Usuario logado com sucesso!");
					do {
					opc1 = Integer.parseInt(JOptionPane.showInputDialog(null,"1- criar perfil! !\n2- entrar no perfil!\n0- Sair!"));
					
					switch(opc1){
						case 1:
							String apelido = JOptionPane.showInputDialog(null, "Digite seu apelido: ");
							int idd = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite sua idade: "));
							usuario.criarPerfil(apelido, idd);
							break;
						case 2:
							int opc2 = Integer.parseInt(JOptionPane.showInputDialog(null, usuario.listarPerfils()));
							switch (opc2) {
								case 1:
								break;
									
							}
							break;
						case 0:
							JOptionPane.showMessageDialog(null, "Saindo...");
							break;
					}
					}while(opc1 != 0);
				}else {
					JOptionPane.showMessageDialog(null, "Email ou senha invalidos!");
				}
				
				break;
			case 0:
				JOptionPane.showMessageDialog(null, "Saindo...");
				break;
		}
		
		}while(opc != 0);
	}

}
