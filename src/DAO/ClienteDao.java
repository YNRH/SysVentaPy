package DAO;

import javax.swing.table.DefaultTableModel;

import Modelo.Cliente;

public interface ClienteDao {

	public void RegistrarCliente(Cliente cliente);
	public DefaultTableModel Mostrar();
	public void EliminarCliente(String dni);
	public void ModificarCliente(Cliente cliente);
	public Cliente BuscarCliente(String dni);
	
}
