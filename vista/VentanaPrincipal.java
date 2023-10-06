package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import controlador.Coordinador;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private Coordinador coordinador;
	private Color colorTextFont;
	private LineBorder lineBorder;
	private JTable tablaPersonas;
	private DefaultTableModel modelTablaPersonas, modelTableGrupos;
	private JPanel pnl_GruposTablas;
	private JTextField textField;

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
		crearGrillaPersonas();
		crearPanelEstadisticas();
		crearPanelGrupos();
		actualizarEstadisticas();
	}

	public void mostrarVentana() {
		setVisible(true);
	}
	
	// ------------ Generacion de la barra de herramientas superior ----------------
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

		JButton btn_CrearGrupos = new JButton("Crear Grupos");
		btn_CrearGrupos.setHorizontalAlignment(SwingConstants.LEFT);
		btn_CrearGrupos.setBackground(colorBackground);
		btn_CrearGrupos.setBorder(emptyBorder);
		btn_CrearGrupos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/crearGrupo32.png")));
		btn_CrearGrupos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarGrupos();
			}
		});
		toolBar.add(btn_CrearGrupos);
	}

	public void agregarGrupos() {
		List<String> grupos = coordinador.obtenerGrupos();

		while (modelTableGrupos.getRowCount() > 0) {
			modelTableGrupos.removeRow(0);
		}

		for (int i = 0; i < grupos.size(); i++) {
			modelTableGrupos.addRow(new String[] { "Grupo " + (i + 1), grupos.get(i) });
		}
	}

	// ------------ Añadir componentes a la interfaz -------------------------------
	public void crearGrillaPersonas() {
		String[] columns = { "ID", "Nombre", "Int. Deporte", "Int. Musica", "Int. Espectaculo", "Int. Ciencia" };

		JPanel pnl_Personas = new JPanel();
		pnl_Personas.setForeground(new Color(255, 255, 255));
		pnl_Personas.setBorder(new TitledBorder(lineBorder, "Listado Personas", TitledBorder.LEFT, TitledBorder.TOP,
				null, colorTextFont));
		pnl_Personas.setBounds(10, 196, 541, 477);
		pnl_Personas.setLayout(null);
		getContentPane().add(pnl_Personas);

		tablaPersonas = new JTable();
		tablaPersonas.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Verificar si es un doble clic
					int filaSeleccionada = tablaPersonas.getSelectedRow();

					if (filaSeleccionada != -1) {
						String id = tablaPersonas.getValueAt(filaSeleccionada, 0).toString();
						String nombre = tablaPersonas.getValueAt(filaSeleccionada, 1).toString();
						String intDeporte = tablaPersonas.getValueAt(filaSeleccionada, 2).toString();
						String intMusica = tablaPersonas.getValueAt(filaSeleccionada, 3).toString();
						String intEspectaculo = tablaPersonas.getValueAt(filaSeleccionada, 4).toString();
						String intCiencia = tablaPersonas.getValueAt(filaSeleccionada, 5).toString();

						coordinador.mostrarVentanaEditarPersona( id, nombre, intDeporte, intMusica, intEspectaculo, intCiencia );
					} else {
						System.out.println("Ninguna celda seleccionada");
					}
				}
			}
		});
		modelTablaPersonas = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelTablaPersonas.setColumnIdentifiers(columns);

		actualizarTablaPersonas();

		DefaultTableCellRenderer CellRender = new DefaultTableCellRenderer();
		CellRender.setHorizontalAlignment(SwingConstants.CENTER);
		for (int c = 0; c < columns.length; c++) {
			tablaPersonas.getColumnModel().getColumn(c).setCellRenderer(CellRender);
		}

		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(10);

		JScrollPane spl_TablaPersonas = new JScrollPane(tablaPersonas);
		spl_TablaPersonas.setBounds(10, 20, 521, 447);

		pnl_Personas.add(spl_TablaPersonas);
	}

	public void actualizarTablaPersonas() {
		actualizarTabla(tablaPersonas, modelTablaPersonas, coordinador.obtenerPersonasEnListaObject());
	}

	public void actualizarTabla(JTable tabla, DefaultTableModel modeloTabla, List<Object[]> lista) {
		while (tabla.getRowCount() > 0) {
			modeloTabla.removeRow(0);
		}

		for (Object[] fila : lista) {
			modeloTabla.addRow(fila);
		}

		tabla.setModel(modeloTabla);
	}

	// ------------ Crear panel de estadisticas y avance visual --------------------
	public void crearPanelEstadisticas() {
		JPanel pnl_Estadisticas = new JPanel();
		pnl_Estadisticas.setForeground(new Color(255, 255, 255));
		pnl_Estadisticas.setBorder(new TitledBorder(lineBorder, "Estadisticas Generales", TitledBorder.LEFT,
				TitledBorder.TOP, null, colorTextFont));
		pnl_Estadisticas.setBounds(10, 92, 541, 94);
		pnl_Estadisticas.setLayout(null);
		getContentPane().add(pnl_Estadisticas);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 20, 101, 65);
		pnl_Estadisticas.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setForeground(new Color(255, 128, 0));
		textField.setBounds(0, 0, 101, 35);
		panel.add(textField);
		textField.setText("25");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Verdana", Font.BOLD, 20));
		textField.setEditable(false);
		
		JLabel lbl_CantPersonas_2 = new JLabel("Promedio");
		lbl_CantPersonas_2.setBounds(0, 35, 101, 15);
		panel.add(lbl_CantPersonas_2);
		lbl_CantPersonas_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_CantPersonas_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lbl_CantPersonas_1_1 = new JLabel("Int. Espectaculo");
		lbl_CantPersonas_1_1.setBounds(0, 50, 101, 15);
		panel.add(lbl_CantPersonas_1_1);
		lbl_CantPersonas_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_CantPersonas_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
	}

	public void crearPanelGrupos() {
		JPanel pnl_Grupos = new JPanel();
		pnl_Grupos.setForeground(new Color(255, 255, 255));
		pnl_Grupos.setBorder(
				new TitledBorder(lineBorder, "Grupos", TitledBorder.LEFT, TitledBorder.TOP, null, colorTextFont));
		pnl_Grupos.setBounds(561, 92, 695, 581);
		pnl_Grupos.setLayout(null);
		getContentPane().add(pnl_Grupos);

		pnl_GruposTablas = new JPanel();
		pnl_GruposTablas.setBounds(10, 22, 675, 549);
		pnl_GruposTablas.setLayout(new GridLayout(1, 1, 0, 5));
		pnl_Grupos.add(pnl_GruposTablas);

		modelTableGrupos = new DefaultTableModel();
		modelTableGrupos.setColumnIdentifiers(new String[] { "Grupo", "Persona", "Promedio Interes" });
		JTable table = new JTable(modelTableGrupos);
		JScrollPane scrollPane = new JScrollPane(table);
		pnl_GruposTablas.add(scrollPane);
	}

	public void actualizarTodo() {
		actualizarTablaPersonas();
		actualizarEstadisticas();
	}

	public void actualizarEstadisticas() {
	}
}