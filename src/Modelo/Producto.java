package Modelo;

public class Producto {

	private int id_producto;
	private String codproducto;
	private String descripcion;
	private int proveedor;
	private double precio;
	private int stock;
	
	public Producto() {
		
	}
	
	public Producto(String codproducto, String descripcion, int proveedor, double precio, int stock) {
	
		this.codproducto = codproducto;
		this.descripcion = descripcion;
		this.proveedor = proveedor;
		this.precio = precio;
		this.stock = stock;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getCodproducto() {
		return codproducto;
	}

	public void setCodproducto(String codproducto) {
		this.codproducto = codproducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getProveedor() {
		return proveedor;
	}

	public void setProveedor(int proveedor) {
		this.proveedor = proveedor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
