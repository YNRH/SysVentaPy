package Vista;

import javax.swing.JPanel;
import java.awt.Rectangle;

import javax.swing.border.TitledBorder;

import DAO.ProductoDao;
import DAO.ProductoDaoImp;
import Modelo.Producto;
import Reporte.Reportes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VProducto extends JPanel {
	private JTextField textCodigoProducto;
	private JTextField textDescripcionProducto;
	private JTextField textPrecioProducto;
	private JTable tableProducto;
	private JTextField textExistenciaProducto;
	
	private JComboBox comboBoxProveedor;

	public VProducto() {
		setBounds(new Rectangle(0, 0, 512, 411));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 492, 102);
		add(panel);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 11, 46, 14);
		panel.add(lblCodigo);
		
		textCodigoProducto = new JTextField();
		textCodigoProducto.setColumns(10);
		textCodigoProducto.setBounds(10, 36, 70, 20);
		panel.add(textCodigoProducto);
		
		textDescripcionProducto = new JTextField();
		textDescripcionProducto.setColumns(10);
		textDescripcionProducto.setBounds(90, 36, 132, 20);
		panel.add(textDescripcionProducto);
		
		textPrecioProducto = new JTextField();
		textPrecioProducto.setColumns(10);
		textPrecioProducto.setBounds(342, 36, 70, 20);
		panel.add(textPrecioProducto);
		
		JLabel lblNewLabel_1 = new JLabel("Descripci\u00F3n");
		lblNewLabel_1.setBounds(90, 11, 90, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Proveedor");
		lblNewLabel_2.setBounds(232, 11, 60, 14);
		panel.add(lblNewLabel_2);
		
		JButton btnRegistrarProducto = new JButton("Registrar");
		btnRegistrarProducto.setBounds(10, 67, 80, 23);
		panel.add(btnRegistrarProducto);
		
		JButton btnActualizarProducto = new JButton("Actualizar");
		btnActualizarProducto.setBounds(190, 67, 80, 23);
		panel.add(btnActualizarProducto);
		
		JButton btnEliminarProducto = new JButton("Eliminar");
		btnEliminarProducto.setBounds(280, 67, 80, 23);
		panel.add(btnEliminarProducto);
		
		JLabel lblNewLabel_3 = new JLabel("Precio");
		lblNewLabel_3.setBounds(342, 11, 46, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnMostrarProducto = new JButton("Mostrar");
		btnMostrarProducto.setBounds(100, 67, 80, 23);
		panel.add(btnMostrarProducto);
		
		textExistenciaProducto = new JTextField();
		textExistenciaProducto.setBounds(422, 36, 60, 20);
		panel.add(textExistenciaProducto);
		textExistenciaProducto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Stock");
		lblNewLabel.setBounds(422, 11, 60, 14);
		panel.add(lblNewLabel);
		
		comboBoxProveedor = new JComboBox();
		comboBoxProveedor.setBounds(232, 35, 100, 22);
		panel.add(comboBoxProveedor);
		
		ProductoDao producto = new ProductoDaoImp();
		producto.CargarProveedor(comboBoxProveedor);
		
		tableProducto = new JTable();
		tableProducto.setBounds(20, 124, 470, 242);
		add(tableProducto);
		
		JButton btnReporteProducto = new JButton("Reporte");
		btnReporteProducto.setBounds(401, 377, 89, 23);
		add(btnReporteProducto);
		
		btnRegistrarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProductoDao producto = new ProductoDaoImp();
				String cod = textCodigoProducto.getText();
				String nom = textDescripcionProducto.getText();
				int prov = comboBoxProveedor.getSelectedIndex();
				double pre = Double.parseDouble( textPrecioProducto.getText());
				int stock = Integer.parseInt(textExistenciaProducto.getText());
				
				Producto datos = new Producto(cod, nom, prov, pre, stock);
				producto.RegistrarProducto(datos);
				
				Limpiar();
				tableProducto.setModel(producto.MostrarProducto());
			}
		});
		
		btnMostrarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProductoDao producto = new ProductoDaoImp();
				tableProducto.setModel(producto.MostrarProducto());
			}
		});
		
		btnActualizarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProductoDao producto = new ProductoDaoImp();
				String cod = textCodigoProducto.getText();
				String nom = textDescripcionProducto.getText();
				int prov = comboBoxProveedor.getSelectedIndex();
				System.out.println(prov+" esesto..........");
				
				double pre = Double.parseDouble( textPrecioProducto.getText());
				int stock = Integer.parseInt(textExistenciaProducto.getText());
				
				//Verifica que los campos no esten vacios
				if (!"".equals(cod) || !"".equals(nom) || !"".equals(prov) || !"".equals(pre) || !"".equals(stock)) {
					
					Producto datos = new Producto(cod, nom, prov, pre, stock);
					producto.ModificarProducto(datos);
					JOptionPane.showMessageDialog(null, "Dato modificado");
					
					Limpiar();
					tableProducto.setModel(producto.MostrarProducto());
				} else {
					JOptionPane.showMessageDialog(null, "Los campos estan vacios");
				}
			}
		});
		
		// 
		tableProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int fila = tableProducto.rowAtPoint(e.getPoint());
				textCodigoProducto.setText(tableProducto.getValueAt(fila, 0).toString());
				textDescripcionProducto.setText(tableProducto.getValueAt(fila, 1).toString());
				comboBoxProveedor.setSelectedItem(tableProducto.getValueAt(fila, 2).toString());
				textPrecioProducto.setText(tableProducto.getValueAt(fila, 3).toString());
				textExistenciaProducto.setText(tableProducto.getValueAt(fila, 4).toString());
			}
		});

		btnEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProductoDao producto = new ProductoDaoImp();
				if (textCodigoProducto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecciona una fila o ingresa el codigo del producto");
				} else {
					int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
					if (pregunta == 0) {
						producto.EliminarProducto(textCodigoProducto.getText());
						
						Limpiar();
						tableProducto.setModel(producto.MostrarProducto());
					}
				}
			}
		});
		
		// Reporte de productos
		btnReporteProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Reportes report = new Reportes();
				report.Reporte_producto();
			}
		});
	}
	
	// Metodo para limpiar los campos
		public void Limpiar() {

			textCodigoProducto.setText("");
			textDescripcionProducto.setText("");
			comboBoxProveedor.setSelectedItem(null);
			textPrecioProducto.setText("");
			textExistenciaProducto.setText("");
		}
}
