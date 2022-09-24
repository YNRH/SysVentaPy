package DAO;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import Modelo.Producto;

public interface ProductoDao {

	public void RegistrarProducto(Producto producto);
	public DefaultTableModel MostrarProducto();
	public void EliminarProducto(String cod);
	public void ModificarProducto(Producto producto);
	public Producto BuscarProducto(String cod);
	
	public void CargarProveedor(JComboBox combo);
	
}
