package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ConeccionBD.Conexion;
import Modelo.Venta;

public class VentaDaoImp extends Conexion implements VentaDao {

	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public void RegistrarVenta(Venta venta) {
		// TODO Auto-generated method stub

		try {
			Coneccion();
			ps = con.prepareStatement("INSERT INTO ventas VALUES (?,?,?,?,?)");
			ps.setInt(1, venta.getId_venta());
			ps.setInt(2, venta.getId_cliente());
			ps.setInt(3, venta.getId_usuario());
			ps.setDouble(4, venta.getTotal());
			ps.setString(5, venta.getFecha());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al Registrar: " + e);
			e.getStackTrace();
		}
	}

	@Override
	public DefaultTableModel MostrarVenta() {
		// TODO Auto-generated method stub

		String[] registros = new String[20];
		String[] encabezado = { "ID_venta", "CLIENTE", "VENDEDOR", "TOTAL", "FECHA" };
		DefaultTableModel modelo = new DefaultTableModel(null, encabezado);
		modelo.addRow(encabezado);
		try {
			Coneccion();
			String query = "SELECT id_venta, nombre_cliente, nombre_usuario, total, fecha FROM ventas INNER JOIN clientes USING(id_cliente) INNER JOIN usuarios USING(id_usuario)";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				registros[0] = rs.getString("id_venta");
				registros[1] = rs.getString("nombre_cliente");
				registros[2] = rs.getString("nombre_usuario");
				registros[3] = rs.getString("total");
				registros[4] = rs.getString("fecha");
				modelo.addRow(registros);
			}
		} catch (Exception e) {
			System.out.println("Error al mostrar:" + e);
			e.getStackTrace();
		}
		return modelo;
	}

	@Override
	public void ModificarVenta(Venta venta) {
		// TODO Auto-generated method stub

		try {
			Coneccion();
			String query = "UPDATE ventas SET id_cliente=?, id_usuario=?,total=?, fecha=? WHERE id_venta=?;";
			ps = con.prepareStatement(query);
			ps.setInt(1, venta.getId_cliente());
			ps.setInt(2, venta.getId_usuario());
			ps.setDouble(3, venta.getTotal());
			ps.setString(4, venta.getFecha());
			ps.setInt(5, venta.getId_venta());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualiar:: " + e);
			e.getStackTrace();
		}
	}

	@Override
	public void EliminarVenta(int id) {
		// TODO Auto-generated method stub

		try {
			Coneccion();
			ps = con.prepareStatement("DELETE FROM ventas WHERE id_venta = ?");
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {
			System.out.println("Error al eliminar:" + e);
			e.getStackTrace();
		}
	}

	@Override
	public int NumDeVentas() {
		// TODO Auto-generated method stub
		int num = 0;
		try {
			Coneccion();
			ps = con.prepareStatement("SELECT MAX(id_venta) FROM ventas");
			rs = ps.executeQuery();
			if (rs.next()) {
				num = Integer.parseInt(rs.getString("MAX(id_venta)"));
			}

		} catch (Exception e) {
			System.out.println("Error:" + e);
			e.getStackTrace();
		}
		return num;
	}

	@Override
	public Venta BuscarVenta(int id) {
		// TODO Auto-generated method stub
		
		Venta venta = new Venta();
		try {

			Coneccion();
			ps = con.prepareStatement("SELECT * FROM ventas WHERE id_venta = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				venta.setId_venta(rs.getInt("id_venta"));
				venta.setId_cliente(rs.getInt("id_cliente"));
				venta.setId_usuario(rs.getInt("id_usuario"));
				venta.setTotal(rs.getDouble("total"));
				venta.setFecha(rs.getString("fecha"));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return venta;
	}

}
