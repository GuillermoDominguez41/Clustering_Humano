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
public class VentanaEditarPersona extends JFrame {
	
	private Coordinador coordinador;
	private Integer rangoMinimo;
	private Integer rangoMaximo;
	private Integer idPersona;
	private JTextField txt_Id;
	private JTextField txt_Nombre;
	private JComboBox<Integer> cbx_Deporte;
	private JComboBox<Integer> cbx_Musica;
	private JComboBox<Integer> cbx_Espectaculo;
	private JComboBox<Integer> cbx_Ciencia;

	public VentanaEditarPersona(Coordinador coord) {	
		coordinador = coord;
		rangoMinimo = coordinador.obtenerRangoMinimo();
		rangoMaximo = coordinador.obtenerRangoMaximo();
		
		setSize(515, 300);
		setTitle("Editar Persona");
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/agregarPersona32.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		agregarComponentes();
	}

	public void mostrarVentana(Integer id, String nombre, Integer intDeporte, Integer intMusica, Integer intEspectaculo, Integer intCiencia) {
		this.idPersona = id;
		this.txt_Id.setText(id.toString());
		this.txt_Nombre.setText(nombre);
		this.cbx_Deporte.setSelectedItem(5);
		this.cbx_Musica.setSelectedItem(5);
		this.cbx_Espectaculo.setSelectedItem(5);
		this.cbx_Ciencia.setSelectedItem(5);
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

		JLabel lbl_Id = new JLabel("ID");
		lbl_Id.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_Id.setBounds(52, 39, 133, 22);
		pnl_AgregarPersona.add(lbl_Id);
		
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
		
		txt_Id= new JTextField();
		txt_Id.setBounds(184, 39, 282, 22);
		pnl_AgregarPersona.add(txt_Id);
		
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

		JButton btn_Actualizar = new JButton("Actualizar");
		btn_Actualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btn_Actualizar.setBounds(152, 179, 100, 30);
		btn_Actualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txt_Nombre.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "ERROR, CAMPOS VACIOS");
				}
				
				if(coordinador.actualizarPersona()) {
					limpiarCampos();
					JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE");
					coordinador.actualizarTodo();
					coordinador.cerrarVentanaEditarPersona();
				} else {
					JOptionPane.showMessageDialog(null, "ERROR, NO SE PUDO ACTUALIZAR");
				}
			}
		});
		pnl_AgregarPersona.add(btn_Actualizar);
		
		JButton btn_Eliminar = new JButton("Eliminar");
		btn_Eliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btn_Eliminar.setBounds(271, 179, 100, 30);
		btn_Eliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				coordinador.cerrarVentanaAgregarPersona();
			}
		});
		pnl_AgregarPersona.add(btn_Eliminar);
		
		
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
