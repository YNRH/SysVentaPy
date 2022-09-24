package Modelo;

public class Usuario {

	private int id_usuario;
	private String nombre_usuario;
	private String correo;
	private String user;
	private String clave;
	private String rol;
	
	public Usuario() {

	}
	
	public Usuario(String nombre_usuario, String correo, String user, String clave, String rol) {
		this.nombre_usuario = nombre_usuario;
		this.correo = correo;
		this.user = user;
		this.clave = clave;
		this.rol = rol;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
