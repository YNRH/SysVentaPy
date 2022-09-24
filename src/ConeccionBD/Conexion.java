package ConeccionBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	protected Connection con;
	protected final String url = "jdbc:mysql://localhost/ventas0";
	protected final String usuario = "root";
	protected final String password = "";
	
	public void Coneccion() {	
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(url,usuario,password);
		} catch (Exception e) {
			System.out.println("Error. " + e);
			e.printStackTrace();
		}
	}
}
