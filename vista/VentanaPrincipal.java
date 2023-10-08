package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputAdapter;
import controlador.Coordinador;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private Coordinador coordinador;
	private AdmTablas admTabla;
	private Color colorTextFont;
	private LineBorder lineBorder;
	private JTable tablaPersonas, tablaGrupos;
	private JTextField txt_CantPersonas, txt_InteresDeporte, txt_InteresMusica, txt_InteresEspectaculo, txt_InteresCiencia;

	public VentanaPrincipal(Coordinador coord) {
		coordinador = coord;
		admTabla = new AdmTablas();
		colorTextFont = new Color(111, 145, 173);
		lineBorder = new LineBorder(new Color(39, 57, 88), 1, true);

		setSize(1280, 720);
		setTitle("Clustering Humano");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/appIcon_32.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		crearBarraHerramientas();
		crearGrillaPersonas();
		crearPanelEstadisticas();
		crearPanelGrupos();
		actualizarEstadisticas();
	}

	public void mostrarVentana() {
		setVisible(true);
	}
	
/* METODOS DEDICADOS A LA GENERACION DE LA INTERFAZ --------------------------------- */
	public void crearBarraHerramientas() {
		JToolBar toolBar = new JToolBar();
		toolBar.setBorder(new TitledBorder(lineBorder, "Barra de herramientas", TitledBorder.LEFT, TitledBorder.TOP, null, colorTextFont));
		toolBar.setBounds(10, 10, 1246, 72);
		getContentPane().add(toolBar);
		toolBar.addSeparator();

		JButton btn_AgregarPersona = new JButton("Agregar Persona");
		btn_AgregarPersona.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/agregarPersona32.png")));
		btn_AgregarPersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				coordinador.mostrarVentanaAgregarPersona();
			}
		});
		toolBar.add(btn_AgregarPersona);
		toolBar.addSeparator();

		JButton btn_CrearGrupos = new JButton("Crear Grupos");
		btn_CrearGrupos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/crearGrupo32.png")));
		btn_CrearGrupos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizarTablaGrupos();
			}
		});
		toolBar.add(btn_CrearGrupos);
		toolBar.addSeparator();	
		
		// Asignamos los atributos generales/compartidos a los componentes
		for(Component c : toolBar.getComponents()) {
			if(c instanceof JButton ) {
				((JButton) c).setHorizontalAlignment(SwingConstants.LEFT);
				((JButton) c).setBackground(new Color(72, 95, 114));
				((JButton) c).setBorder(new EmptyBorder(2, 10, 2, 10));
			}
		}	
	}

	public void crearGrillaPersonas() {
		String[] nombreColumnas = { "ID", "Nombre", "Int. Deporte", "Int. Musica", "Int. Espectaculo", "Int. Ciencia" };

		JPanel pnl_Personas = new JPanel();
		pnl_Personas.setForeground(new Color(255, 255, 255));
		pnl_Personas.setBorder(new TitledBorder(lineBorder, "Listado Personas", TitledBorder.LEFT, TitledBorder.TOP, null, colorTextFont));
		pnl_Personas.setBounds(10, 196, 541, 477);
		pnl_Personas.setLayout(null);
		getContentPane().add(pnl_Personas);

		tablaPersonas = new JTable();
		tablaPersonas.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tablaPersonas.getSelectedRow();
				// Verificar si se aplico doble clic sobre la tabla y si selecciono una fila
				if (e.getClickCount() == 2 && filaSeleccionada != -1) {
					String id = tablaPersonas.getValueAt(filaSeleccionada, 0).toString();
					String nombre = tablaPersonas.getValueAt(filaSeleccionada, 1).toString();
					String intDeporte = tablaPersonas.getValueAt(filaSeleccionada, 2).toString();
					String intMusica = tablaPersonas.getValueAt(filaSeleccionada, 3).toString();
					String intEspectaculo = tablaPersonas.getValueAt(filaSeleccionada, 4).toString();
					String intCiencia = tablaPersonas.getValueAt(filaSeleccionada, 5).toString();
					coordinador.mostrarVentanaEditarPersona(id, nombre, intDeporte, intMusica, intEspectaculo, intCiencia);
				}
			}
		});

		admTabla.establecerModeloNoEditable(tablaPersonas);
		admTabla.establecerTituloColumnas(tablaPersonas, nombreColumnas);
		admTabla.establecerValores(tablaPersonas, coordinador.obtenerPersonasEnListaObject());
		admTabla.centrarValoresEnCeldas(tablaPersonas);
		admTabla.ajustarAnchoColumna(tablaPersonas, 0, 10);

		JScrollPane spl_TablaPersonas = new JScrollPane(tablaPersonas);
		spl_TablaPersonas.setBounds(10, 20, 521, 447);
		pnl_Personas.add(spl_TablaPersonas);
	}

	public void crearPanelEstadisticas() {
		JPanel pnl_Estadisticas = new JPanel();
		pnl_Estadisticas.setBorder(new TitledBorder(lineBorder, "Estadisticas Generales", TitledBorder.LEFT, TitledBorder.TOP, null, colorTextFont));
		pnl_Estadisticas.setBounds(10, 92, 541, 94);
		pnl_Estadisticas.setLayout(null);
		getContentPane().add(pnl_Estadisticas);

		txt_CantPersonas = new JTextField();
		txt_CantPersonas.setBounds(10, 20, 90, 35);
		pnl_Estadisticas.add(txt_CantPersonas);
		
		JTextPane lbl_CantPersonas = new JTextPane();
		lbl_CantPersonas.setText("Total\nPersonas");
		lbl_CantPersonas.setBounds(10, 55, 90, 30);
		pnl_Estadisticas.add(lbl_CantPersonas);
		
		txt_InteresDeporte = new JTextField();
		txt_InteresDeporte.setBounds(120, 20, 90, 35);
		pnl_Estadisticas.add(txt_InteresDeporte);
		
		JTextPane lbl_InteresDeporte = new JTextPane();
		lbl_InteresDeporte.setText("Promedio\nInt. Deporte");
		lbl_InteresDeporte.setBounds(120, 55, 90, 30);
		pnl_Estadisticas.add(lbl_InteresDeporte);	
	
		txt_InteresMusica = new JTextField();
		txt_InteresMusica.setBounds(227, 20, 90, 35);
		pnl_Estadisticas.add(txt_InteresMusica);
		
		JTextPane lbl_InteresMusica = new JTextPane();
		lbl_InteresMusica.setText("Promedio\nInt. Musica");
		lbl_InteresMusica.setBounds(227, 55, 90, 30);
		pnl_Estadisticas.add(lbl_InteresMusica);

		txt_InteresEspectaculo = new JTextField();
		txt_InteresEspectaculo.setBounds(333, 20, 93, 35);
		pnl_Estadisticas.add(txt_InteresEspectaculo);
		
		JTextPane lbl_InteresEspectaculo = new JTextPane();
		lbl_InteresEspectaculo.setText("Promedio\nInt Espectaculo");
		lbl_InteresEspectaculo.setBounds(333, 55, 93, 30);
		pnl_Estadisticas.add(lbl_InteresEspectaculo);
		
		txt_InteresCiencia = new JTextField();
		txt_InteresCiencia.setBounds(441, 20, 90, 35);
		pnl_Estadisticas.add(txt_InteresCiencia);

		JTextPane lbl_InteresCiencia = new JTextPane();
		lbl_InteresCiencia.setText("Promedio\nInt. Ciencia");
		lbl_InteresCiencia.setBounds(441, 55, 90, 30);
		pnl_Estadisticas.add(lbl_InteresCiencia);
		
		// Asignamos los atributos generales/compartidos a los componentes
		for(Component c : pnl_Estadisticas.getComponents()) 
		{
			if(c instanceof JTextField ) {
				((JTextField) c).setHorizontalAlignment(SwingConstants.CENTER);
				((JTextField) c).setFont(new Font("Verdana", Font.BOLD, 20));
				((JTextField) c).setForeground(new Color(255, 128, 0));
				((JTextField) c).setEditable(false);
				((JTextField) c).setBackground(Color.WHITE);
			}
			
			if(c instanceof JTextPane ) {
				((JTextPane) c).setFont(new Font("Tahoma", Font.BOLD, 10));
			}
		}
	}

	public void crearPanelGrupos() {
		String[] nombreColumnas = new String[] { "Grupo", "Persona", "Promedio Interes" };
		
		JPanel pnl_Grupos = new JPanel();
		pnl_Grupos.setBorder(new TitledBorder(lineBorder, "Grupos", TitledBorder.LEFT, TitledBorder.TOP, null, colorTextFont));
		pnl_Grupos.setBounds(561, 92, 695, 581);
		pnl_Grupos.setLayout(null);
		getContentPane().add(pnl_Grupos);

		JPanel pnl_GruposTablas = new JPanel();
		pnl_GruposTablas.setBounds(10, 22, 675, 549);
		pnl_GruposTablas.setLayout(new GridLayout(1, 1, 0, 5));
		pnl_Grupos.add(pnl_GruposTablas);

		tablaGrupos = new JTable();
		admTabla.establecerModeloNoEditable(tablaGrupos);
		admTabla.establecerTituloColumnas(tablaGrupos, nombreColumnas);

		JScrollPane scrollPane = new JScrollPane(tablaGrupos);
		pnl_GruposTablas.add(scrollPane);
	}
	
	
/* METODOS DEDICADOS A LA ACTUALIZACION DE DATOS EN LA INTERFAZ --------------------- */
	public void actualizarDatosInterfaz() {
		actualizarTablaPersonas();
		actualizarTablaGrupos();
		actualizarEstadisticas();
	}
	
	private void actualizarTablaPersonas() {
		admTabla.establecerValores(tablaPersonas, coordinador.obtenerPersonasEnListaObject());
	}
	
	private void actualizarTablaGrupos() {
		admTabla.establecerValores(tablaGrupos, coordinador.obtenerGrupos());
	}

	public void actualizarEstadisticas() {
		txt_CantPersonas.setText(coordinador.cantPersonas());
		txt_InteresDeporte.setText(coordinador.promInteresDeportes());
		txt_InteresMusica.setText(coordinador.promInteresMusica());
		txt_InteresEspectaculo.setText(coordinador.promInteresEspectaculo());
		txt_InteresCiencia.setText(coordinador.promInteresCiencia());
	}
}