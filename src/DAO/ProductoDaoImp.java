package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections4.functors.CatchAndRethrowClosure;

import ConeccionBD.Conexion;
import Modelo.Producto;

public class ProductoDaoImp extends Conexion implements ProductoDao {

	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public void RegistrarProducto(Producto producto) {

		try {
			Coneccion();
			String query = "INSERT INTO productos VALUES (null,?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, producto.getCodproducto());
			ps.setString(2, producto.getDescripcion());
			ps.setInt(3, producto.getProveedor());
			ps.setDouble(4, producto.getPrecio());
			ps.setInt(5, producto.getStock());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error "+e);
			e.getStackTrace();
		}
	}

	@Override
	public DefaultTableModel MostrarProducto() {

		String[] registros = new String[20];
		String[] encabezado = { "CODIGO", "DESCRIPCIÓN", "PROVEEDOR", "PRECIO", "STOCK" };
		DefaultTableModel modelo = new DefaultTableModel(null, encabezado);
		modelo.addRow(encabezado);
		try {
			Coneccion();
			ps = con.prepareStatement("SELECT * FROM productos");
			rs = ps.executeQuery();
			while (rs.next()) {
				registros[0] = rs.getString("codigo_producto");
				registros[1] = rs.getString("descripcion");
				registros[2] = rs.getString("id_proveedor");
				registros[3] = rs.getString("precio");
				registros[4] = rs.getString("stock");
				modelo.addRow(registros);
			}
		} catch (Exception e) {
			System.out.println("Error al mostrar" + e);
			e.getStackTrace();
		}
		return modelo;
	}

	@Override
	public void EliminarProducto(String codigo) {

		try {
			Coneccion();
			ps = con.prepareStatement("DELETE FROM productos WHERE codigo_producto = ?");
			ps.setString(1, codigo);
			ps.execute();

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void ModificarProducto(Producto producto) {

		try {
			Coneccion();
			String query = "UPDATE productos SET descripcion=?, id_proveedor=?, precio=?, stock=? WHERE codigo_producto=?";
			ps = con.prepareStatement(query);

			ps.setString(1, producto.getDescripcion());
			ps.setInt(2, producto.getProveedor());
			ps.setDouble(3, producto.getPrecio());
			ps.setInt(4, producto.getStock());
			ps.setString(5, producto.getCodproducto());
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public Producto BuscarProducto(String cod) {
		// TODO Auto-generated method stub

		Producto pro = new Producto();
		String sql = "SELECT * FROM productos WHERE codigo_producto = ?";

		try {
			Coneccion();
			ps = con.prepareStatement(sql);
			ps.setString(1, cod);

			rs = ps.executeQuery();
			if (rs.next()) {
				pro.setId_producto(rs.getInt("id_producto"));
				pro.setCodproducto(rs.getString("codigo_producto"));
				pro.setDescripcion(rs.getString("descripcion"));
				pro.setProveedor(rs.getInt("id_proveedor"));
				pro.setPrecio(rs.getDouble("precio"));
				pro.setStock(rs.getInt("stock"));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return pro;
	}

	@Override
	public void CargarProveedor(JComboBox combo) {
		// TODO Auto-generated method stub
		combo.addItem("Seleccione una opcion");
		try {
			Coneccion();
			ps = con.prepareStatement("SELECT nombre_proveedor FROM proveedor ORDER BY id_proveedor");
			rs = ps.executeQuery();
			while (rs.next()) {
				combo.addItem(rs.getString("nombre_proveedor"));
				}
			}catch (SQLException e) {
				System.out.println(e.toString());
			}
	}


}
