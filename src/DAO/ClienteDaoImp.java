package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ConeccionBD.Conexion;
import Modelo.Cliente;

public class ClienteDaoImp extends Conexion implements ClienteDao {

	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public void RegistrarCliente(Cliente cliente) {

		try {
			Coneccion();
			ps = con.prepareStatement("INSERT INTO clientes VALUES (null,?,?,?,?)");
			ps.setString(1, cliente.getDni());
			ps.setString(2, cliente.getNombre());
			ps.setString(3, cliente.getTelefono());
			ps.setString(4, cliente.getDireccion());
			ps.executeUpdate();
		}catch (Exception e) {
			System.out.println("Error al Registrar: "+e);
			e.getStackTrace();
		}
	}

	@Override
	public DefaultTableModel Mostrar() {

		String[] registros = new String[20];
		String[] encabezado = {"DNI","NOMBRE", "TELEFONO", "DIRECCION"};
		DefaultTableModel modelo = new DefaultTableModel(null,encabezado);
		modelo.addRow(encabezado);
		try {
			Coneccion();
			ps = con.prepareStatement("SELECT * FROM clientes");
			rs = ps.executeQuery();
			while(rs.next()) {
				registros[0] = rs.getString("dni");
				registros[1] = rs.getString("nombre_cliente");
				registros[2] = rs.getString("telefono");
				registros[3] = rs.getString("direccion");
				modelo.addRow(registros);
			}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return modelo;
	}

	@Override
	public void EliminarCliente(String dni) {
		
	       try {
	    	   Coneccion();
	           ps = con.prepareStatement("DELETE FROM clientes WHERE dni = ?");
	           ps.setString(1, dni);
	           ps.execute();
	           
	       } catch (SQLException e) {
	           System.out.println(e.toString());
	       }
	}

	@Override
	public void ModificarCliente(Cliente cliente) {

		try {
			Coneccion();
			String query = "UPDATE clientes SET nombre_cliente=?,telefono=?,direccion=? WHERE dni=?;";
			ps = con.prepareStatement(query);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getTelefono());
			ps.setString(3, cliente.getDireccion());
			ps.setString(4, cliente.getDni());
			int exito = ps.executeUpdate();
		}catch (Exception e) {
			System.out.println("Error al actualiar:: "+e);
			e.getStackTrace();
		}
	}

	@Override
	public Cliente BuscarCliente(String dni) {
		
		Cliente cl = new Cliente();
	    String sql = "SELECT * FROM clientes WHERE dni = ?";
	    
	    try {
	    	Coneccion();
	        ps = con.prepareStatement(sql);
	        ps.setString(1, dni);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	        	cl.setId_cliente(rs.getInt("id_cliente"));
	        	cl.setDni(rs.getString("dni"));
	            cl.setNombre(rs.getString("nombre_cliente"));
	            cl.setTelefono(rs.getString("telefono"));
	            cl.setDireccion(rs.getString("direccion"));
	        }
	     } catch (SQLException e) {
	    	 System.out.println(e.toString());
	   }
	    return cl;
	}
}
