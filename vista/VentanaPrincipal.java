package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.Coordinador;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private Coordinador coordinador;
	private Color colorTextFont;
	private LineBorder lineBorder;
	private JTable tablaPersonas;
	private DefaultTableModel modelTP;
	private JTable tablaRelaciones;
	private DefaultTableModel modelTR;
	private JTextField txt_CantPersonas;
	private JTextField txt_CantRelaciones;
	private JTextField txt_PromInteresDeporte;
	private JTextField txt_PromInteresMusica;
	private JTextField txt_PromInteresEspectaculo;
	private JTextField txt_PromInteresCiencia;

	public VentanaPrincipal(Coordinador coord) {
		coordinador = coord;
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
		agregarPersonas(); // METODO DE PRUEBA
		crearGrillaPersonas();
		crearGrillaRelaciones();
		crearPanelEstadisticas();
		crearPanelGrupos();
		actualizarEstadisticas();
	}
	
	public void mostrarVentana() {
		setVisible(true);  
	}

	public void crearBarraHerramientas() {
		Color colorBackground = new Color(72, 95, 114);
		EmptyBorder emptyBorder = new EmptyBorder(2, 10, 2, 10);

		JToolBar toolBar = new JToolBar();
			toolBar.setBorder(new TitledBorder(lineBorder, "Barra de herramientas", TitledBorder.LEFT, TitledBorder.TOP,
					null, colorTextFont));
			toolBar.setToolTipText("Barra de herramientas");
			toolBar.setBounds(10, 10, 1246, 72);
		getContentPane().add(toolBar);

		toolBar.addSeparator();

		JButton btn_AgregarPersona = new JButton("Agregar Persona");
		btn_AgregarPersona.setHorizontalAlignment(SwingConstants.LEFT);
		btn_AgregarPersona.setBackground(colorBackground);
		btn_AgregarPersona.setBorder(emptyBorder);
		btn_AgregarPersona.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/agregarPersona32.png")));
		btn_AgregarPersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				coordinador.mostrarVentanaAgregarPersona();
			}
		});
		toolBar.add(btn_AgregarPersona);

		toolBar.addSeparator();

		JButton btn_CrearRelacion = new JButton("Crear Relacion");
		btn_CrearRelacion.setHorizontalAlignment(SwingConstants.LEFT);
		btn_CrearRelacion.setBackground(colorBackground);
		btn_CrearRelacion.setBorder(emptyBorder);
		btn_CrearRelacion.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/crearVinculo32.png")));
		btn_CrearRelacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				coordinador.mostrarVentanaAgregarRelacion(); 
			}
		});
		toolBar.add(btn_CrearRelacion);

		toolBar.addSeparator(new Dimension(5, 0));

		JButton btn_CrearGrupos = new JButton("Crear Grupos");
		btn_CrearGrupos.setHorizontalAlignment(SwingConstants.LEFT);
		btn_CrearGrupos.setBackground(colorBackground);
		btn_CrearGrupos.setBorder(emptyBorder);
		btn_CrearGrupos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/crearGrupo32.png")));
		btn_CrearGrupos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				lanzarVentanaCrearGrupos();
			}
		});
		toolBar.add(btn_CrearGrupos);
	}
	
	// ------------ Objetos utilizado para pruebas ---------------------------------
	public void agregarPersonas() {
		coordinador.agregarPersona("Matias", 4, 5, 4, 1); // ID 0
		coordinador.agregarPersona("Carlos", 5, 1, 3, 5); // ID 1
		coordinador.agregarPersona("Lionel", 5, 2, 3, 4); // ID 2
		coordinador.agregarPersona("Andres", 4, 4, 3, 4); // ID 3
	}
	
	// ------------ Añadir componentes a la interfaz -------------------------------
	public void crearGrillaPersonas() {
		String[] columns = { "ID", "Nombre", "Int. Deporte", "Int. Musica", "Int. Espectaculo", "Int. Ciencia" };

		JPanel pnl_Personas = new JPanel();
		pnl_Personas.setForeground(new Color(255, 255, 255));
		pnl_Personas.setBorder(new TitledBorder(lineBorder, "Listado Personas", TitledBorder.LEFT, TitledBorder.TOP, null, colorTextFont));
		pnl_Personas.setBounds(10, 92, 541, 263);
		pnl_Personas.setLayout(null);
		getContentPane().add(pnl_Personas);

		tablaPersonas = new JTable();
		modelTP = new DefaultTableModel(0, 0);
		modelTP.setColumnIdentifiers(columns);
		actualizarTablaPersonas();

		DefaultTableCellRenderer CellRender = new DefaultTableCellRenderer();
		CellRender.setHorizontalAlignment(SwingConstants.CENTER);
		for (int c = 0; c < columns.length; c++) {
			tablaPersonas.getColumnModel().getColumn(c).setCellRenderer(CellRender);
			if(c == 0)
				tablaPersonas.getColumnModel().getColumn(c).setPreferredWidth(10);
			if(c > 1) {
				tablaPersonas.getColumnModel().getColumn(c).setPreferredWidth(50);
			}
		}

		JScrollPane spl_TablaPersonas = new JScrollPane(tablaPersonas);
		spl_TablaPersonas.setBounds(10, 20, 521, 233);
		pnl_Personas.add(spl_TablaPersonas);		
	}
	
	public void crearGrillaRelaciones() {
		String[] columns = { "Persona 1", "Persona 2", "Indice" };

		JPanel pnl_Relaciones = new JPanel();
		pnl_Relaciones.setForeground(new Color(255, 255, 255));
		pnl_Relaciones.setBorder(new TitledBorder(lineBorder, "Listado Relaciones", TitledBorder.LEFT, TitledBorder.TOP, null, colorTextFont));
		pnl_Relaciones.setBounds(571, 92, 359, 263);
		pnl_Relaciones.setLayout(null);
		getContentPane().add(pnl_Relaciones);

		tablaRelaciones = new JTable();
		modelTR = new DefaultTableModel(0, 0);
		modelTR.setColumnIdentifiers(columns);
		actualizarTablaRelaciones();

		DefaultTableCellRenderer CellRender = new DefaultTableCellRenderer();
		CellRender.setHorizontalAlignment(SwingConstants.CENTER);
		for (int c = 0; c < columns.length; c++) {
			tablaRelaciones.getColumnModel().getColumn(c).setCellRenderer(CellRender);
		}

		JScrollPane spl_TablaRelaciones = new JScrollPane(tablaRelaciones);
		spl_TablaRelaciones.setBounds(10, 20, 339, 233);
		pnl_Relaciones.add(spl_TablaRelaciones);	
	}
	
	public void crearPanelEstadisticas() {
		JPanel pnl_Estadisticas = new JPanel();
		pnl_Estadisticas.setForeground(new Color(255, 255, 255));
		pnl_Estadisticas.setBorder(new TitledBorder(lineBorder, "Estadisticas Generales", TitledBorder.LEFT, TitledBorder.TOP, null, colorTextFont));
		pnl_Estadisticas.setBounds(952, 92, 304, 263);
		pnl_Estadisticas.setLayout(null);
		getContentPane().add(pnl_Estadisticas);
		
		JLabel lbl_CantPersonas = new JLabel("Personas registradas");
		lbl_CantPersonas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_CantPersonas.setBounds(10, 25, 155, 20);
		pnl_Estadisticas.add(lbl_CantPersonas);
		
		txt_CantPersonas = new JTextField();
		txt_CantPersonas.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_CantPersonas.setBounds(165, 25, 130, 20);
		txt_CantPersonas.setEditable(false);
		pnl_Estadisticas.add(txt_CantPersonas);
		
		JLabel lbl_CantRelaciones = new JLabel("Relaciones registradas");
		lbl_CantRelaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_CantRelaciones.setBounds(10, 50, 155, 20);
		pnl_Estadisticas.add(lbl_CantRelaciones);
		
		txt_CantRelaciones = new JTextField();
		txt_CantRelaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_CantRelaciones.setBounds(165, 50, 130, 20);
		txt_CantRelaciones.setEditable(false);
		pnl_Estadisticas.add(txt_CantRelaciones);
		
		JLabel lbl_PromInteresDeporte = new JLabel("Prom. Interes Deporte");
		lbl_PromInteresDeporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_PromInteresDeporte.setBounds(10, 75, 155, 20);
		pnl_Estadisticas.add(lbl_PromInteresDeporte);
		
		txt_PromInteresDeporte = new JTextField();
		txt_PromInteresDeporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_PromInteresDeporte.setBounds(165, 75, 130, 20);
		txt_PromInteresDeporte.setEditable(false);
		pnl_Estadisticas.add(txt_PromInteresDeporte);
		
		JLabel lbl_PromInteresMusica = new JLabel("Prom. Interes Musica");
		lbl_PromInteresMusica.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_PromInteresMusica.setBounds(10, 100, 155, 20);
		pnl_Estadisticas.add(lbl_PromInteresMusica);
		
		txt_PromInteresMusica = new JTextField();
		txt_PromInteresMusica.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_PromInteresMusica.setBounds(165, 100, 130, 20);
		txt_PromInteresMusica.setEditable(false);
		pnl_Estadisticas.add(txt_PromInteresMusica);
		
		JLabel lbl_PromInteresEspectaculo = new JLabel("Prom. Interes Espectaculo");
		lbl_PromInteresEspectaculo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_PromInteresEspectaculo.setBounds(10, 125, 155, 20);
		pnl_Estadisticas.add(lbl_PromInteresEspectaculo);
		
		txt_PromInteresEspectaculo = new JTextField();
		txt_PromInteresEspectaculo.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_PromInteresEspectaculo.setBounds(165, 125, 130, 20);
		txt_PromInteresEspectaculo.setEditable(false);
		pnl_Estadisticas.add(txt_PromInteresEspectaculo);
		
		JLabel lbl_PromInteresCiencia = new JLabel("Prom. Interes Ciencia");
		lbl_PromInteresCiencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_PromInteresCiencia.setBounds(10, 150, 155, 20);
		pnl_Estadisticas.add(lbl_PromInteresCiencia);

		txt_PromInteresCiencia = new JTextField();
		txt_PromInteresCiencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_PromInteresCiencia.setBounds(165, 150, 130, 20);
		txt_PromInteresCiencia.setEditable(false);
		pnl_Estadisticas.add(txt_PromInteresCiencia);		
	}

	public void crearPanelGrupos() {		
		JPanel pnl_Grupos = new JPanel();
		pnl_Grupos.setForeground(new Color(255, 255, 255));
		pnl_Grupos.setBorder(new TitledBorder(lineBorder, "Grupos", TitledBorder.LEFT, TitledBorder.TOP, null, colorTextFont));
		pnl_Grupos.setBounds(10, 365, 1246, 308);
		pnl_Grupos.setLayout(null);
		getContentPane().add(pnl_Grupos);

	}

	public void actualizarTodo() {
		actualizarTablaPersonas();
		actualizarTablaRelaciones();
		actualizarEstadisticas();
	}
	
	public void actualizarTablaPersonas() {
		actualizarTabla(tablaPersonas, modelTP, coordinador.obtenerPersonas());
	}
	
	public void actualizarTablaRelaciones() {
		actualizarTabla(tablaRelaciones, modelTR, coordinador.obtenerRelaciones());
	}
	
	public void actualizarTabla(JTable tabla, DefaultTableModel modeloTabla, List<Object[]> lista ) {
		while (tabla.getRowCount() > 0) {
			modeloTabla.removeRow(0);
		}
		for (Object[] fila : lista) {
			modeloTabla.addRow(fila);
		}
		
		tabla.setModel(modeloTabla);
	}
	
	public void actualizarEstadisticas() {
		txt_CantPersonas.setText(coordinador.cantPersonas().toString());
		txt_CantRelaciones.setText(coordinador.cantRelaciones().toString());
		txt_PromInteresDeporte.setText("");
		txt_PromInteresMusica.setText("");
		txt_PromInteresCiencia.setText("");
		txt_PromInteresEspectaculo.setText("");
	}
	
}
