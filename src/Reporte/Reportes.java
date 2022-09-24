package Reporte;


import java.util.HashMap;
import java.util.Map;

import ConeccionBD.Conexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Reportes extends Conexion {
	
	
	public void Reporte_cliente() {
		try {
			Coneccion();
			JasperReport jr = (JasperReport)JRLoader.loadObject(getClass().getResource("/Reporte/ReporteCliente.jasper"));
			JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
			JasperViewer jv = new JasperViewer(jp, false);
			jv.setVisible(true);
		
		}catch (Exception ex) {
			System.out.println("Erorr:: "+ex);
			ex.printStackTrace();
		}
	}
	
	public void Reporte_producto() {
		try {
			Coneccion();
			JasperReport jr = (JasperReport)JRLoader.loadObject(getClass().getResource("/Reporte/ReporteProducto.jasper"));
			JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
			JasperViewer jv = new JasperViewer(jp, false);
			jv.setVisible(true);
		
		}catch (Exception ex) {
			System.out.println("Erorr:: "+ex);
			ex.printStackTrace();
		}
	}
	
	public void Reporte_venta() {
		try {
			Coneccion();
			JasperReport jr = (JasperReport)JRLoader.loadObject(getClass().getResource("/Reporte/ReporteVenta.jasper"));
			JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
			JasperViewer jv = new JasperViewer(jp, false);
			jv.setVisible(true);
		
		}catch (Exception ex) {
			System.out.println("Erorr:: "+ex);
			ex.printStackTrace();
		}
	}
	
	public void Reporte_boleta(int id) {
		try {
			Coneccion();
			Map parametro = new HashMap();
			parametro.put("Pidventa", id);
			JasperReport jr = (JasperReport)JRLoader.loadObject(getClass().getResource("/Reporte/ReporteBoleta.jasper"));
			JasperPrint jp = JasperFillManager.fillReport(jr, parametro, con);
			JasperViewer jv = new JasperViewer(jp, false);
			jv.setVisible(true);
		
		}catch (Exception ex) {
			System.out.println("Erorr:: "+ex);
			ex.printStackTrace();
		}
	}
	
}