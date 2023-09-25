package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controlador.Coordinador;

@SuppressWarnings("serial")
public class VentanaAgregarRelacion extends JFrame {

	private Coordinador coordinador;
	private Integer idMax;
	private JComboBox<Integer> cbx_Persona1;
	private JComboBox<Integer> cbx_Persona2;
	
	public VentanaAgregarRelacion(Coordinador coord) {
		coordinador = coord;
		idMax = coordinador.cantPersonas()-1;
		
		setSize(300, 128);
		setTitle("Crear Relacion");
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/crearVinculo32.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		agregarComponentes();
	}
	
	public void mostrarVentana() {
		setVisible(true);  
	}
	
	public void cerrarVentana() {
		limpiarCampos();
		setVisible(false);
	}
	
	private void limpiarCampos() {
		cbx_Persona1.setSelectedIndex(0);
		cbx_Persona2.setSelectedIndex(0);
	}

	private void agregarComponentes() {
		
		if(idMax < 1) {
			JOptionPane.showMessageDialog(null, "No hay suficientes items para generar relacion");
		} else {
			JPanel pnl_CrearRelacion = new JPanel();
			pnl_CrearRelacion.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnl_CrearRelacion.setLayout(null);
			getContentPane().add(pnl_CrearRelacion);
			
			JLabel lbl_Titulo = new JLabel("Crear Relacion");
			lbl_Titulo.setBounds(0, 7, 300, 22);
			lbl_Titulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Titulo.setFont(new Font("Tahoma", Font.BOLD, 13));
			pnl_CrearRelacion.add(lbl_Titulo);
			
			JLabel lbl_Persona1 = new JLabel("ID Persona 1");
			lbl_Persona1.setFont(new Font("Tahoma", Font.BOLD, 12));
			lbl_Persona1.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Persona1.setBounds(52, 35, 89, 22);
			pnl_CrearRelacion.add(lbl_Persona1);
			
			JLabel lbl_Persona2 = new JLabel("ID Persona 2");
			lbl_Persona2.setFont(new Font("Tahoma", Font.BOLD, 12));
			lbl_Persona2.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Persona2.setBounds(52, 62, 89, 22);
			pnl_CrearRelacion.add(lbl_Persona2);
			
			cbx_Persona1 = new JComboBox<Integer>();
			cbx_Persona1.setBounds(151, 35, 90, 22);
			pnl_CrearRelacion.add(cbx_Persona1);
			
			cbx_Persona2 = new JComboBox<Integer>();
			cbx_Persona2.setBounds(151, 62, 90, 22);
			pnl_CrearRelacion.add(cbx_Persona2);
			
			for (int i = 0; i <= idMax; i++) {
				cbx_Persona1.addItem(i);
				cbx_Persona2.addItem(i);
			}
			
			JButton btn_CrearRelacion = new JButton("Crear Relacion");
			btn_CrearRelacion.setBounds(26, 94, 120, 25);
			btn_CrearRelacion.setFont(new Font("Tahoma", Font.BOLD, 10));
			btn_CrearRelacion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Integer id_P1 = (Integer) cbx_Persona1.getSelectedItem();
					Integer id_P2 = (Integer) cbx_Persona2.getSelectedItem();
					
					if(id_P1 == id_P2) {
						JOptionPane.showMessageDialog(null, "No se puede generar relacion entre los items seleccionados");
					} else {
//						coordinador.agregarRelacion(id_P1, id_P2);
//						coordinador.actualizarTodo();
//						coordinador.cerrarVentanaAgregarRelacion();
					}
				}
			});
			pnl_CrearRelacion.add(btn_CrearRelacion);
			
			JButton btn_Cancelar = new JButton("Cancelar");
			btn_Cancelar.setBounds(156, 94, 120, 25);
			btn_Cancelar.setFont(new Font("Tahoma", Font.BOLD, 10));
			btn_Cancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					coordinador.cerrarVentanaAgregarRelacion();
				}
			});
			pnl_CrearRelacion.add(btn_Cancelar);
		}
	}	
}
