package Vista;

import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import DAO.ClienteDao;
import DAO.ClienteDaoImp;
import Modelo.Cliente;
import Reporte.Reportes;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class VCliente extends JPanel {
	private JTextField textDniCliente;
	private JTextField textNombreCliente;
	private JTextField textTelefonoCliente;
	private JTextField textDireccionCliente;
	private JLabel lblNewLabel_2;
	private JTable table;

	public VCliente() {
		setBounds(new Rectangle(0, 0, 512, 411));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 492, 102);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("DNI");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);

		textDniCliente = new JTextField();
		textDniCliente.setBounds(10, 36, 80, 20);
		panel.add(textDniCliente);
		textDniCliente.setColumns(10);

		textNombreCliente = new JTextField();
		textNombreCliente.setBounds(100, 36, 122, 20);
		panel.add(textNombreCliente);
		textNombreCliente.setColumns(10);

		textTelefonoCliente = new JTextField();
		textTelefonoCliente.setBounds(232, 36, 80, 20);
		panel.add(textTelefonoCliente);
		textTelefonoCliente.setColumns(10);

		textDireccionCliente = new JTextField();
		textDireccionCliente.setBounds(322, 36, 160, 20);
		panel.add(textDireccionCliente);
		textDireccionCliente.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(100, 11, 46, 14);
		panel.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Tel\u00E9fono");
		lblNewLabel_2.setBounds(232, 11, 46, 14);
		panel.add(lblNewLabel_2);

		JButton btnRegistrarCliente = new JButton("Registrar");
		btnRegistrarCliente.setBounds(10, 67, 80, 23);
		panel.add(btnRegistrarCliente);

		JButton btnActualizarCliente = new JButton("Actualizar");
		btnActualizarCliente.setBounds(190, 67, 80, 23);
		panel.add(btnActualizarCliente);

		JButton btnEliminarCliente = new JButton("Eliminar");
		btnEliminarCliente.setBounds(280, 67, 80, 23);
		panel.add(btnEliminarCliente);

		JLabel lblNewLabel_3 = new JLabel("Direcci\u00F3n");
		lblNewLabel_3.setBounds(322, 11, 46, 14);
		panel.add(lblNewLabel_3);

		JButton btnMostrarCliente = new JButton("Mostrar");
		btnMostrarCliente.setBounds(100, 67, 80, 23);
		panel.add(btnMostrarCliente);

		table = new JTable();
		table.setBounds(20, 124, 470, 242);
		add(table);

		JButton btnReporteCliente = new JButton("Reporte");
		btnReporteCliente.setBounds(401, 377, 89, 23);
		add(btnReporteCliente);

		// Botn para Registrar cliente
		btnRegistrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClienteDao cliente = new ClienteDaoImp();
				String dni = textDniCliente.getText();
				String nom = textNombreCliente.getText();
				String tel = textTelefonoCliente.getText();
				String dir = textDireccionCliente.getText();

				String val =cliente.BuscarCliente(dni).getDni();
				if (val == dni) {
					JOptionPane.showMessageDialog(null, "DNI:: Cliente ya existe");
				} else {
					Cliente datos = new Cliente(dni, nom, tel, dir);
					cliente.RegistrarCliente(datos);
					table.setModel(cliente.Mostrar());

					Limpiar();
				}
			}
		});

		btnMostrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClienteDao cliente = new ClienteDaoImp();
				table.setModel(cliente.Mostrar());

			}
		});

		btnActualizarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClienteDao cliente = new ClienteDaoImp();
				String dni = textDniCliente.getText();
				String nom = textNombreCliente.getText();
				String tel = textTelefonoCliente.getText();
				String dir = textDireccionCliente.getText();

				if (!"".equals(dni) || !"".equals(nom) || !"".equals(tel) || !"".equals(dir)) {

					Cliente datos = new Cliente(dni, nom, tel, dir);
					cliente.ModificarCliente(datos);
					JOptionPane.showMessageDialog(null, "Dato modificado");
					Limpiar();
					table.setModel(cliente.Mostrar());
				} else {
					JOptionPane.showMessageDialog(null, "Los campos estan vacios");
				}
			}
		});

		// Selecciona una fila de una tabla para modificar los datos de la fila
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int fila = table.rowAtPoint(e.getPoint());
				textDniCliente.setText(table.getValueAt(fila, 0).toString());
				textNombreCliente.setText(table.getValueAt(fila, 1).toString());
				textTelefonoCliente.setText(table.getValueAt(fila, 2).toString());
				textDireccionCliente.setText(table.getValueAt(fila, 3).toString());
			}
		});

		// Elimina al cliente ingresando el DNI
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClienteDao cliente = new ClienteDaoImp();
				if (textDniCliente.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecciona una fila o ingresa el Dni");
				} else {
					int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
					if (pregunta == 0) {

						cliente.EliminarCliente(textDniCliente.getText());
						Limpiar();
					}
				}
			}
		});

		// REPORTE
		btnReporteCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Reportes report = new Reportes();
				report.Reporte_cliente();
			}
		});
	}

	// Metodo para limpiar los campos
	public void Limpiar() {

		textDniCliente.setText("");
		textNombreCliente.setText("");
		textTelefonoCliente.setText("");
		textDireccionCliente.setText("");
	}
}
