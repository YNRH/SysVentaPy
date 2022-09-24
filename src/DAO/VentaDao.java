package DAO;

import javax.swing.table.DefaultTableModel;

import Modelo.Venta;

public interface VentaDao {
	
	public void RegistrarVenta(Venta venta);
	public DefaultTableModel MostrarVenta();
	public void EliminarVenta(int id);
	public void ModificarVenta(Venta venta);
	public Venta BuscarVenta(int id);
	public int NumDeVentas();

}
