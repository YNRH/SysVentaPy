package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UsuarioDao;
import DAO.UsuarioDaoImp;
import Modelo.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passClave;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VLogin frame = new VLogin();
					frame.setVisible(true);
					  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VLogin() {
		setTitle("Inicio de Sesi\u00F3n - SISTEMA DE VENTAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 204));
		panel.setBounds(0, 0, 300, 361);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema de Ventas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Maiandra GD", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 11, 280, 72);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("@RHYN");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(76, 311, 54, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("@CCLV");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(76, 286, 54, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("@RFD");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(76, 336, 54, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("@HMSG");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3.setBounds(170, 287, 54, 14);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("@HAMV");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_4.setBounds(170, 312, 54, 14);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("@OTEF");
		lblNewLabel_1_4_1.setForeground(Color.WHITE);
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_4_1.setBounds(170, 336, 54, 14);
		panel.add(lblNewLabel_1_4_1);
		
		JLabel lblLogo = new JLabel(new ImageIcon("src/img/portada.png"));
		lblLogo.setBounds(20, 75, 270, 200);
		panel.add(lblLogo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(300, 0, 284, 361);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textUsuario = new JTextField();
		textUsuario.setToolTipText("");
		textUsuario.setForeground(new Color(100, 149, 237));
		textUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		textUsuario.setColumns(10);
		textUsuario.setBounds(94, 213, 150, 30);
		panel_1.add(textUsuario);
		
		JButton btnIniciaSesion = new JButton("Iniciar Sesión");
		btnIniciaSesion.setForeground(new Color(255, 255, 255));
		btnIniciaSesion.setBackground(new Color(102, 153, 255));
		btnIniciaSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIniciaSesion.setBounds(42, 295, 202, 30);
		panel_1.add(btnIniciaSesion);
		
		passClave = new JPasswordField();
		passClave.setForeground(new Color(100, 149, 237));
		passClave.setFont(new Font("Tahoma", Font.BOLD, 14));
		passClave.setBounds(94, 254, 150, 30);
		panel_1.add(passClave);
		
		JLabel lblIcono = new JLabel(new ImageIcon("src/img/empresa.png"));
		lblIcono.setBounds(10, 11, 264, 191);
		panel_1.add(lblIcono);
		
		JLabel lblNewLabel_2 = new JLabel("\u00BFOlvidaste tu clave?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(51, 153, 204));
		lblNewLabel_2.setBounds(52, 336, 192, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Usuario");
		lblNewLabel_3.setBounds(38, 213, 46, 30);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Clave");
		lblNewLabel_3_1.setBounds(42, 255, 46, 30);
		panel_1.add(lblNewLabel_3_1);
		
		// Con el VAñidamos la sesion
		btnIniciaSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Usuario usu = new Usuario();
			    UsuarioDao login = new UsuarioDaoImp();
			    
				String user = textUsuario.getText();
		        String pass = String.valueOf(passClave.getPassword());
		        if (!"".equals(user) || !"".equals(pass)) {
		            
		            usu = login.BuscarUsuario(user, pass);
		            if (usu.getUser()!= null && usu.getClave() != null) {
		            	
		            	int idusuario = usu.getId_usuario();
		            	String nomusuario =usu.getNombre_usuario();		            	
		            	
		            	SistemaVentas sv = new SistemaVentas(idusuario, nomusuario);
						sv.setVisible(true);
						dispose();
		            }else{
		                JOptionPane.showMessageDialog(null, "Usuario o la clave incorrecta");
		            }
		        }
			}
		});
	}
}
