package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.ClienteDao;
import DAO.ClienteDaoImp;
import DAO.DetalleDao;
import DAO.DetalleDaoImp;
import DAO.ProductoDao;
import DAO.ProductoDaoImp;
import DAO.UsuarioDao;
import DAO.UsuarioDaoImp;
import DAO.VentaDao;
import DAO.VentaDaoImp;
import Modelo.Detalle;
import Modelo.Producto;
import Modelo.Venta;
import Reporte.Reportes;

import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.SwingConstants;

public class SistemaVentas extends JFrame implements ActionListener {

	private JTextField textCodProducto;
	private JTextField textDescripcion;
	private JTextField textCantidad;
	private JTextField textPrecio;
	private JTextField textDniCliente;
	private JTextField textNombreCliente;
	private JTextField textStockDisponible;
	private JTextField textTotalVenta;
	private JTextField textNumBoleta;
	private JTextField textFecha;

	private JPanel panelNewVenta;

	private JButton btnNewVenta;
	private JButton btnCliente;
	private JButton btnProducto;
	private JButton btnVenta;
	private JButton btnConfig;

	private JTable table;

	VCliente vcliente = new VCliente();
	VProducto vproducto = new VProducto();
	VVenta vventa = new VVenta();
	// VConfig vconfig = new VConfig();

	VentaDao venta = new VentaDaoImp();
	UsuarioDao usuario = new UsuarioDaoImp();
	ClienteDao cliente = new ClienteDaoImp();
	ProductoDao producto = new ProductoDaoImp();
	DetalleDao detalle = new DetalleDaoImp();

	private int idventa;
	private int stock;
	private int cantidad;
	private double totalventa;
	private double precio;
	private double subtotal;
	private String idDetalle;
	private String codproducto;
	private String descripcion;
	private int provee;

	public SistemaVentas(int idusuario, String nomusuario) {

		setTitle("Sistema de Ventas");
		setBounds(100, 100, 680, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(102, 153, 204));
		panel1.setBounds(0, 0, 153, 411);
		getContentPane().add(panel1);
		panel1.setLayout(null);

		btnNewVenta = new JButton("Nueva Venta");
		btnNewVenta.setForeground(new Color(255, 255, 255));
		btnNewVenta.setBackground(new Color(0, 0, 51));
		btnNewVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewVenta.setBounds(0, 160, 153, 30);
		btnNewVenta.addActionListener(this);
		panel1.add(btnNewVenta);

		btnCliente = new JButton("Cliente");
		btnCliente.setForeground(new Color(255, 255, 255));
		btnCliente.setBackground(new Color(0, 0, 51));
		btnCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCliente.setBounds(0, 201, 153, 30);
		btnCliente.addActionListener(this);
		panel1.add(btnCliente);

		btnProducto = new JButton("Producto");
		btnProducto.setForeground(new Color(255, 255, 255));
		btnProducto.setBackground(new Color(0, 0, 51));
		btnProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnProducto.setBounds(0, 242, 153, 30);
		btnProducto.addActionListener(this);
		panel1.add(btnProducto);

		btnConfig = new JButton("Configuraci\u00F3n");
		btnConfig.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConfig.setBounds(0, 338, 153, 30);
		btnConfig.addActionListener(this);
		panel1.add(btnConfig);

		btnVenta = new JButton("Venta");
		btnVenta.setForeground(Color.WHITE);
		btnVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVenta.setBackground(new Color(0, 0, 51));
		btnVenta.setBounds(0, 283, 153, 30);
		btnVenta.addActionListener(this);
		panel1.add(btnVenta);

		JLabel lblNewLabel_9 = new JLabel("Usuario");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Swis721 BlkEx BT", Font.BOLD, 16));
		lblNewLabel_9.setBounds(10, 37, 133, 30);
		panel1.add(lblNewLabel_9);
		
		JLabel lblNomUsuario = new JLabel("New label");
		lblNomUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomUsuario.setFont(new Font("Snap ITC", Font.BOLD, 16));
		lblNomUsuario.setBounds(0, 78, 153, 30);
		lblNomUsuario.setText(nomusuario);
		panel1.add(lblNomUsuario);

		JPanel panel = new JPanel();
		panel.setBounds(152, 0, 512, 411);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));

		panelNewVenta = new JPanel();
		panel.add(panelNewVenta, "name_384817544293500");
		panel.add(vcliente);
		panel.add(vproducto);
		panel.add(vventa);
		// panel.add(vconfig);
		panelNewVenta.setLayout(null);

		JPanel panelGrupoProducto = new JPanel();
		panelGrupoProducto
				.setBorder(new TitledBorder(null, "Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrupoProducto.setBounds(10, 103, 481, 74);
		panelNewVenta.add(panelGrupoProducto);
		panelGrupoProducto.setLayout(null);

		textCodProducto = new JTextField();
		textCodProducto.setBounds(10, 42, 60, 20);
		panelGrupoProducto.add(textCodProducto);
		textCodProducto.setColumns(10);

		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(10, 17, 60, 14);
		panelGrupoProducto.add(lblNewLabel);

		textDescripcion = new JTextField();
		textDescripcion.setBounds(80, 42, 140, 20);
		panelGrupoProducto.add(textDescripcion);
		textDescripcion.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Producto");
		lblNewLabel_1.setBounds(80, 17, 82, 14);
		panelGrupoProducto.add(lblNewLabel_1);

		textCantidad = new JTextField();
		textCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		textCantidad.setBounds(230, 42, 70, 20);
		panelGrupoProducto.add(textCantidad);
		textCantidad.setColumns(10);

		textPrecio = new JTextField();
		textPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		textPrecio.setBounds(310, 42, 70, 20);
		panelGrupoProducto.add(textPrecio);
		textPrecio.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Cantidad");
		lblNewLabel_2.setBounds(230, 17, 70, 14);
		panelGrupoProducto.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Precio");
		lblNewLabel_3.setBounds(310, 17, 46, 14);
		panelGrupoProducto.add(lblNewLabel_3);

		textStockDisponible = new JTextField();
		textStockDisponible.setBounds(390, 41, 81, 20);
		panelGrupoProducto.add(textStockDisponible);
		textStockDisponible.setForeground(new Color(255, 255, 255));
		textStockDisponible.setFont(new Font("Tahoma", Font.BOLD, 12));
		textStockDisponible.setBackground(new Color(102, 153, 204));
		textStockDisponible.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Stock Disponible");
		lblNewLabel_6.setBounds(390, 17, 81, 14);
		panelGrupoProducto.add(lblNewLabel_6);

		table = new JTable();
		table.setBounds(20, 188, 432, 180);
		panelNewVenta.add(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 16, 300, 80);
		panelNewVenta.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("DNI");
		lblNewLabel_4.setBounds(10, 21, 46, 14);
		panel_1.add(lblNewLabel_4);

		textDniCliente = new JTextField();
		textDniCliente.setBounds(10, 46, 70, 20);
		panel_1.add(textDniCliente);
		textDniCliente.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Nombre");
		lblNewLabel_5.setBounds(90, 21, 110, 14);
		panel_1.add(lblNewLabel_5);

		textNombreCliente = new JTextField();
		textNombreCliente.setBounds(90, 46, 110, 20);
		panel_1.add(textNombreCliente);
		textNombreCliente.setColumns(10);

		JButton btnBuscaClientePorDNI = new JButton("Buscar");
		btnBuscaClientePorDNI.setBounds(208, 45, 82, 23);
		panel_1.add(btnBuscaClientePorDNI);

		JButton btnReporteBoleta = new JButton("Imprimir Boleta");
		btnReporteBoleta.setBounds(100, 379, 171, 23);
		panelNewVenta.add(btnReporteBoleta);

		textTotalVenta = new JTextField();
		textTotalVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		textTotalVenta.setForeground(new Color(51, 153, 204));
		textTotalVenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		textTotalVenta.setText(0+"");
		textTotalVenta.setBounds(378, 380, 74, 20);
		panelNewVenta.add(textTotalVenta);
		textTotalVenta.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Total de Venta");
		lblNewLabel_7.setBounds(281, 383, 87, 14);
		panelNewVenta.add(lblNewLabel_7);

		JButton btnAddCompra = new JButton("+");
		btnAddCompra.setBackground(new Color(0, 102, 102));
		btnAddCompra.setForeground(new Color(255, 255, 255));
		btnAddCompra.setFont(new Font("Britannic Bold", Font.BOLD, 12));
		btnAddCompra.setBounds(462, 188, 40, 40);
		panelNewVenta.add(btnAddCompra);

		JButton btnEliminarCompra = new JButton("-");
		btnEliminarCompra.setBackground(new Color(102, 0, 0));
		btnEliminarCompra.setForeground(new Color(255, 255, 255));
		btnEliminarCompra.setFont(new Font("Britannic Bold", Font.BOLD, 15));
		btnEliminarCompra.setBounds(462, 239, 40, 40);
		panelNewVenta.add(btnEliminarCompra);

		JLabel lblNewLabel1 = new JLabel("Nro.");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel1.setFont(new Font("Swis721 BlkOul BT", Font.BOLD, 20));
		lblNewLabel1.setBounds(345, 16, 62, 40);
		panelNewVenta.add(lblNewLabel1);

		textFecha = new JTextField();
		textFecha.setHorizontalAlignment(SwingConstants.CENTER);
		// Obtiene la fecha y hora actual, y lo devuelve con un formato
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		textFecha.setText(dtf.format(LocalDateTime.now()));
		textFecha.setBounds(405, 76, 86, 20);
		panelNewVenta.add(textFecha);
		textFecha.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Fecha");
		lblNewLabel_8.setBounds(349, 79, 46, 14);
		panelNewVenta.add(lblNewLabel_8);

		textNumBoleta = new JTextField();
		textNumBoleta.setHorizontalAlignment(SwingConstants.CENTER);
		textNumBoleta.setFont(new Font("Swis721 BlkOul BT", Font.PLAIN, 40));
		idventa = venta.NumDeVentas() + 1;
		textNumBoleta.setText(idventa + "");
		textNumBoleta.setBounds(429, 16, 62, 40);
		panelNewVenta.add(textNumBoleta);
		textNumBoleta.setColumns(10);

		// **
		//
		// Busca Nombre de cliente por su DNI
		btnBuscaClientePorDNI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textDniCliente.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Introdusca el DNI para Buscar");
				} else {
					String nom = cliente.BuscarCliente(textDniCliente.getText()).getNombre();
					textNombreCliente.setText(nom);

					// Genero una venta para el cliente
					int idcliente = cliente.BuscarCliente(textDniCliente.getText()).getId_cliente();
					totalventa = Double.parseDouble(textTotalVenta.getText()); // variable global
					String fecha = textFecha.getText();

					Venta v = new Venta(idventa, idcliente, idusuario, totalventa, fecha);
					venta.RegistrarVenta(v);
				}
			}
		});

		// Buscar datos del producto por COD
		textCodProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				codproducto = textCodProducto.getText();

				String descripcion = producto.BuscarProducto(codproducto).getDescripcion();
				precio = producto.BuscarProducto(codproducto).getPrecio();
				stock = producto.BuscarProducto(codproducto).getStock();

				textDescripcion.setText(descripcion);
				textPrecio.setText(precio + "");
				textStockDisponible.setText(stock + "");
			}
		});

		// ADD muestra detalles de la venta **** Muestra los productos en el carrito
		btnAddCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textCantidad.getText().equals("") || textCodProducto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Campos vacios");
				} else {

					idventa = Integer.parseInt(textNumBoleta.getText());
					int idproducto = producto.BuscarProducto(textCodProducto.getText()).getId_producto();

					cantidad = Integer.parseInt(textCantidad.getText());
					stock = Integer.parseInt(textStockDisponible.getText());

					precio = Double.parseDouble(textPrecio.getText());
					descripcion = textDescripcion.getText();
					provee = producto.BuscarProducto(codproducto).getProveedor();

					if (cantidad <= stock) {

						subtotal = cantidad * precio;
						totalventa = totalventa + subtotal;
						textTotalVenta.setText(totalventa + "");

						// Registra el detalle
						Detalle de = new Detalle(idventa, idproducto, cantidad, subtotal);
						detalle.RegistrarDetalle(de);

						// Muestra el detalle
						table.setModel(detalle.MostrarDetalle(idventa));

						// Actualiza las existencias
						stock = stock - cantidad;
						textStockDisponible.setText(stock + "");
						Producto datospro = new Producto(codproducto, descripcion, provee, precio, stock);
						producto.ModificarProducto(datospro);

					} else {
						JOptionPane.showMessageDialog(null, "stock no disponible");
					}
					Limpiarproductos();
				}
			}
		});

		// Elimina productos del carrito
		btnEliminarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				subtotal = detalle.BuscarDetalle(Integer.parseInt(idDetalle)).getSubtotal();
				cantidad = detalle.BuscarDetalle(Integer.parseInt(idDetalle)).getCantidad();
				stock = producto.BuscarProducto(textCodProducto.getText()).getStock();

				detalle.EliminarDetalle(Integer.parseInt(idDetalle));
				table.setModel(detalle.MostrarDetalle(Integer.parseInt(textNumBoleta.getText())));

				stock = stock + cantidad;
				textStockDisponible.setText(stock + "");
				Producto datospro = new Producto(codproducto, descripcion, provee, precio, stock);
				producto.ModificarProducto(datospro);

				totalventa = totalventa - subtotal;
				textTotalVenta.setText(totalventa + "");
			}
		});

		// Rellena los campos con los datos de la tabla seleccionada;
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int fila = table.rowAtPoint(e.getPoint());
				idDetalle = table.getValueAt(fila, 0).toString();
				textCodProducto.setText(table.getValueAt(fila, 1).toString());
				textDescripcion.setText(table.getValueAt(fila, 2).toString());
				textCantidad.setText(table.getValueAt(fila, 3).toString());
				textPrecio.setText(table.getValueAt(fila, 4).toString());
			}
		});

		// Genera Reporte y/o Boleta
		btnReporteBoleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int idcliente = cliente.BuscarCliente(textDniCliente.getText()).getId_cliente();
				String fecha = textFecha.getText();

				Venta datos = new Venta(idventa, idcliente, idusuario, totalventa, fecha);
				venta.ModificarVenta(datos);

				Limpiar();
				LimpiarTable();

				// Imprime una boleta o genera reporte de los detalles de la venta
				Reportes report = new Reportes();
				report.Reporte_boleta(idventa);
				
				idventa = venta.NumDeVentas() + 1;
				textNumBoleta.setText(idventa + "");
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnNewVenta) {
			panelNewVenta.setVisible(true);
			vcliente.setVisible(false);
			vproducto.setVisible(false);
			vventa.setVisible(false);

		} else if (e.getSource() == btnCliente) {
			panelNewVenta.setVisible(false);
			vcliente.setVisible(true);
			vproducto.setVisible(false);
			vventa.setVisible(false);
		} else if (e.getSource() == btnProducto) {
			panelNewVenta.setVisible(false);
			vcliente.setVisible(false);
			vproducto.setVisible(true);
			vventa.setVisible(false);
		} else if (e.getSource() == btnVenta) {
			panelNewVenta.setVisible(false);
			vcliente.setVisible(false);
			vproducto.setVisible(false);
			vventa.setVisible(true);
		}
	}

	// Limpia los campos
	public void Limpiar() {

		textDniCliente.setText("");
		textNombreCliente.setText("");
		textCodProducto.setText("");
		textDescripcion.setText("");
		textCantidad.setText("");
		textPrecio.setText("");
		textStockDisponible.setText("");
		textTotalVenta.setText("");
	}

	public void Limpiarproductos() {

		textCodProducto.setText("");
		textDescripcion.setText("");
		textCantidad.setText("");
		textPrecio.setText("");
		textStockDisponible.setText("");
	}

	private void LimpiarTable() {
		TableModel tmp = table.getModel();
		int fila = table.getRowCount();
		for (int i = 0; i < fila; i++) {
			((DefaultTableModel) tmp).removeRow(0);
		}
	}
}
