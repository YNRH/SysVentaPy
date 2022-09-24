package Vista;

import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import DAO.VentaDao;
import DAO.VentaDaoImp;
import Reporte.Reportes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VVenta extends JPanel {
	private JTable tableVenta;
	private JTextField textIdVenta;
	private JTextField textFechaVenta;

	public VVenta() {
		setBounds(new Rectangle(0, 0, 512, 411));
		setLayout(null);
		
		tableVenta = new JTable();
		tableVenta.setBounds(20, 124, 470, 242);
		add(tableVenta);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 492, 102);
		add(panel);
		
		JLabel lblId = new JLabel("ID_Venta");
		lblId.setBounds(10, 11, 46, 14);
		panel.add(lblId);
		
		textIdVenta = new JTextField();
		textIdVenta.setBackground(new Color(255, 204, 153));
		textIdVenta.setColumns(10);
		textIdVenta.setBounds(10, 36, 50, 20);
		panel.add(textIdVenta);
		
		JButton btnBuscarVenta = new JButton("Buscar");
		btnBuscarVenta.setBounds(402, 67, 80, 23);
		panel.add(btnBuscarVenta);
		
		JButton btnEliminarVenta = new JButton("Eliminar");
		btnEliminarVenta.setBounds(10, 67, 80, 23);
		panel.add(btnEliminarVenta);
		
		JButton btnMostrarVenta = new JButton("Mostrar");
		btnMostrarVenta.setBounds(100, 67, 80, 23);
		panel.add(btnMostrarVenta);
		
		textFechaVenta = new JTextField();
		textFechaVenta.setColumns(10);
		textFechaVenta.setBounds(400, 36, 82, 20);
		panel.add(textFechaVenta);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(400, 11, 60, 14);
		panel.add(lblFecha);
		
		JButton btnReporteVenta = new JButton("Reporte");
		btnReporteVenta.setBounds(401, 377, 89, 23);
		add(btnReporteVenta);
		
		btnMostrarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentaDao venta = new VentaDaoImp();
				tableVenta.setModel(venta.MostrarVenta());
			}
		});
		
		btnEliminarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentaDao venta = new VentaDaoImp();
				if (textIdVenta.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Ingresa id de Venta");
				} else {
					int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
					if (pregunta == 0) {

						int id = Integer.parseInt(textIdVenta.getText());
						venta.EliminarVenta(id);
						Limpiar();
						tableVenta.setModel(venta.MostrarVenta());
					}
				}
			}
		});
		
		btnBuscarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String fecha = dtf.format(LocalDateTime.now());
				VentaDao venta = new VentaDaoImp();
				//tableVenta.setModel(venta.BuscarVenta(Integer.parseInt(textIdVenta.getText())));
			}
		});
		
		btnReporteVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Reportes report = new Reportes();
				report.Reporte_venta();
			}
		});
		
		tableVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tableVenta.rowAtPoint(e.getPoint());
				textIdVenta.setText(tableVenta.getValueAt(fila, 0).toString());	
			}
		});
	}
	
	public void Limpiar() {
		textIdVenta.setText("");
		textFechaVenta.setText("");
	}
}
