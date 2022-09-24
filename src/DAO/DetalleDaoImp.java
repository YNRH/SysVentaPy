package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ConeccionBD.Conexion;
import Modelo.Detalle;

public class DetalleDaoImp extends Conexion implements DetalleDao {

	private PreparedStatement ps;
	private ResultSet rs;
	

	@Override
	public void RegistrarDetalle(Detalle detalle) {
		// TODO Auto-generated method stub
		
		try {
			Coneccion();
			ps = con.prepareStatement("INSERT INTO detalle VALUES (null,?,?,?,?)");
			ps.setInt(1, detalle.getId_venta());
			ps.setInt(2, detalle.getId_producto());
			ps.setInt(3, detalle.getCantidad());
			ps.setDouble(4, detalle.getSubtotal());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al Registrar: " + e);
			e.getStackTrace();
		}		
	}
	
	@Override
	public DefaultTableModel MostrarDetalle(int idVenta) {
		// TODO Auto-generated method stub
		
		String[] registros = new String[20];
		String[] encabezado = { " ","CODIGO", "PRODUCTO", "CANTIDAD", "P.U", "P.T" };
		DefaultTableModel modelo = new DefaultTableModel(null, encabezado);
		modelo.addRow(encabezado);
		try {
			Coneccion();
			String query = "SELECT id_detalle, codigo_producto, descripcion, cantidad, precio, subtotal FROM detalle INNER JOIN productos USING(id_producto) WHERE id_venta=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, idVenta);
			rs = ps.executeQuery();
			while (rs.next()) {
				registros[0] = rs.getString("id_detalle");
				registros[1] = rs.getString("codigo_producto");
				registros[2] = rs.getString("descripcion");
				registros[3] = rs.getString("cantidad");
				registros[4] = rs.getString("precio");
				registros[5] = rs.getString("subtotal");
				modelo.addRow(registros);
			}
		} catch (Exception e) {
			System.out.println("Error al mostrar:" + e);
			e.getStackTrace();
		}
		return modelo;
	}

	@Override
	public void EliminarDetalle(int id_detalle) {
		// TODO Auto-generated method stub
		
		try {
			Coneccion();
			ps = con.prepareStatement("DELETE FROM detalle WHERE id_detalle = ?");
			ps.setInt(1, id_detalle);
			ps.execute();

		} catch (SQLException e) {
			System.out.println("Error al eliminar:" + e);
			e.getStackTrace();
		}
		
	}

	@Override
	public Detalle BuscarDetalle(int id) {
		// TODO Auto-generated method stub
		
		Detalle detalle = new Detalle();
		try {

			Coneccion();
			ps = con.prepareStatement("SELECT * FROM detalle WHERE id_detalle = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				detalle.setId_venta(rs.getInt("id_detalle"));
				detalle.setId_venta(rs.getInt("id_venta"));
				detalle.setId_producto(rs.getInt("id_producto"));
				detalle.setCantidad(rs.getInt("cantidad"));
				detalle.setSubtotal(rs.getDouble("subtotal"));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return detalle;
	}
}
