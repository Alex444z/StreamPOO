package autenticacao;

import java.util.ArrayList;

import conta.Usuario;

public class GerenciadorUsuarios {
	
	private ArrayList<Usuario> usuarios;
	
	public GerenciadorUsuarios(){
		usuarios = new ArrayList<Usuario>();
	}
	
	public boolean addUsuario(Usuario usuario) {
		return this.usuarios.add(usuario);
	}
	
	public int listarQuantiUsuario() {
		return this.usuarios.size();
		
	}
	
	public String verificarDuploEmail(String email) {
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(email)){
			    return "Email existente!";
      }
		}
		return "Email disponivel!";
	}
	
	public Usuario logarUsuario(String email, String senha) {
		for (Usuario u : usuarios) {
			if (u.autenticarEmail(email) && u.autenticarSenha(senha)) {
				return u;
			}
		}return null;

		
	}
	
}
