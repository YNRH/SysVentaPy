package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ConeccionBD.Conexion;
import Modelo.Usuario;

public class UsuarioDaoImp extends Conexion implements UsuarioDao{

	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public void RegistrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario BuscarUsuario(String user, String pass) {
		// TODO Auto-generated method stub

		Usuario usu = new Usuario();
	    try {
	    	Coneccion();
	        ps = con.prepareStatement("SELECT * FROM usuarios WHERE user =? AND clave =?");
	        ps.setString(1, user);
	        ps.setString(2, pass);
	        
	        rs = ps.executeQuery();
	        if (rs.next()) {
	        	usu.setId_usuario(rs.getInt("id_usuario"));
	        	usu.setNombre_usuario(rs.getString("nombre_usuario"));
	        	usu.setCorreo(rs.getString("correo"));
	        	usu.setUser(rs.getString("user"));
	        	usu.setClave(rs.getString("clave"));
	        	usu.setRol(rs.getString("rol"));
	        }
	     } catch (SQLException e) {
	    	 System.out.println(e.toString());
	   }
	    return usu;
	}
}
