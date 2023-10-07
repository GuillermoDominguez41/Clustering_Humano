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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controlador.Coordinador;

@SuppressWarnings("serial")
public class VentanaAgregarPersona extends JFrame {

	private Coordinador coordinador;
	private Integer rangoMinimo;
	private Integer rangoMaximo;
	private JTextField txt_Nombre;
	private JComboBox<Integer> cbx_Deporte;
	private JComboBox<Integer> cbx_Musica;
	private JComboBox<Integer> cbx_Espectaculo;
	private JComboBox<Integer> cbx_Ciencia;

	public VentanaAgregarPersona(Coordinador coord) {	
		coordinador = coord;
		rangoMinimo = coordinador.obtenerRangoMinimo();
		rangoMaximo = coordinador.obtenerRangoMaximo();
		
		setSize(515, 225);
		setTitle("Agregar Persona");
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/agregarPersona32.png")));
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
		txt_Nombre.setText("");
		cbx_Deporte.setSelectedIndex(0);
		cbx_Musica.setSelectedIndex(0);
		cbx_Espectaculo.setSelectedIndex(0);
		cbx_Ciencia.setSelectedIndex(0);
	}
	
	private void agregarComponentes() {			
		JPanel pnl_AgregarPersona = new JPanel();
		pnl_AgregarPersona.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_AgregarPersona.setLayout(null);
		getContentPane().add(pnl_AgregarPersona);

		JLabel lbl_Titulo = new JLabel("Agregar Persona");
		lbl_Titulo.setBounds(0, 7, 515, 22);
		lbl_Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Titulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl_AgregarPersona.add(lbl_Titulo);

		JLabel lbl_Nombre = new JLabel("Nombre");
		lbl_Nombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_Nombre.setBounds(52, 39, 133, 22);
		pnl_AgregarPersona.add(lbl_Nombre);

		JLabel lbl_IntDeporte = new JLabel("Interes Deporte");
		lbl_IntDeporte.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_IntDeporte.setBounds(52, 66, 133, 22);
		pnl_AgregarPersona.add(lbl_IntDeporte);

		JLabel lbl_IntMusica = new JLabel("Interes Musica");
		lbl_IntMusica.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_IntMusica.setBounds(52, 93, 133, 22);
		pnl_AgregarPersona.add(lbl_IntMusica);

		JLabel lbl_IntEspectaculo = new JLabel("Interes Espectaculo");
		lbl_IntEspectaculo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_IntEspectaculo.setBounds(52, 120, 133, 22);
		pnl_AgregarPersona.add(lbl_IntEspectaculo);

		JLabel lbl_IntCiencia = new JLabel("Interes Ciencia");
		lbl_IntCiencia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_IntCiencia.setBounds(52, 147, 133, 22);
		pnl_AgregarPersona.add(lbl_IntCiencia);
		
		txt_Nombre = new JTextField();
		txt_Nombre.setBounds(184, 39, 282, 22);
		pnl_AgregarPersona.add(txt_Nombre);
		
		cbx_Deporte = new JComboBox<Integer>();
		cbx_Deporte.setBounds(184, 66, 282, 22);
		pnl_AgregarPersona.add(cbx_Deporte);
		
		cbx_Musica = new JComboBox<Integer>();
		cbx_Musica.setBounds(184, 93, 282, 22);
		pnl_AgregarPersona.add(cbx_Musica);
		
		cbx_Espectaculo = new JComboBox<Integer>();
		cbx_Espectaculo.setBounds(184, 120, 282, 22);
		pnl_AgregarPersona.add(cbx_Espectaculo);

		cbx_Ciencia = new JComboBox<Integer>();
		cbx_Ciencia.setBounds(184, 147, 282, 22);
		pnl_AgregarPersona.add(cbx_Ciencia);
		
		for (int i = rangoMinimo; i <= rangoMaximo; i++) {
			cbx_Deporte.addItem(i);
			cbx_Musica.addItem(i);
			cbx_Espectaculo.addItem(i);
			cbx_Ciencia.addItem(i);
		}

		JButton btn_AgregarPersona = new JButton("Agregar");
		btn_AgregarPersona.setFont(new Font("Tahoma", Font.BOLD, 10));
		btn_AgregarPersona.setBounds(152, 179, 100, 30);
		btn_AgregarPersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombre = txt_Nombre.getText();
				Integer iDeporte = (Integer) cbx_Deporte.getSelectedItem();
				Integer iMusica = (Integer) cbx_Musica.getSelectedItem();
				Integer iEspectaculo = (Integer) cbx_Espectaculo.getSelectedItem();
				Integer iCiencia = (Integer) cbx_Ciencia.getSelectedItem();
				
				if (!nombre.isBlank() && iDeporte != null && iMusica != null && iEspectaculo != null && iCiencia != null) {
					coordinador.agregarPersona(nombre, iDeporte, iMusica, iEspectaculo, iCiencia);
					coordinador.actualizarDatosInterfaz();
					coordinador.cerrarVentanaAgregarPersona();
				} else {
					JOptionPane.showMessageDialog(null, "Error, se debe ingresar 'Nombre'");
				}
			}
		});
		pnl_AgregarPersona.add(btn_AgregarPersona);
		
		JButton btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btn_Cancelar.setBounds(271, 179, 100, 30);
		btn_Cancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				coordinador.cerrarVentanaAgregarPersona();
			}
		});
		pnl_AgregarPersona.add(btn_Cancelar);
	}	
	
}
