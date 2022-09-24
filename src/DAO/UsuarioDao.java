package DAO;

import Modelo.Usuario;

public interface UsuarioDao {

	public void RegistrarUsuario(Usuario usuario);
	public Usuario BuscarUsuario(String user, String pass);
}
