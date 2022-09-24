package DAO;

import javax.swing.table.DefaultTableModel;

import Modelo.Detalle;

public interface DetalleDao {
	
	public void RegistrarDetalle(Detalle detalle);
	public DefaultTableModel MostrarDetalle(int id_venta);
	public void EliminarDetalle(int id_detalle);
	public Detalle BuscarDetalle(int id);
}
