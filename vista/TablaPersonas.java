package vista;

import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TablaPersonas {
	private Object[] columnas;
	private List<Object[]> valores;
	private JTable tabla;
	private static DefaultTableModel modeloTabla;
	private JScrollPane scrollPanel;
	
	public TablaPersonas(Object[] columnas, List<Object[]> valores){
		this.columnas = columnas;
		this.valores = valores;
		
		crearTabla();
		agregarModelo();
		alinearValores();
		ajustarAnchoColumnas();
		
		scrollPanel = new JScrollPane(tabla);
	}

	private void crearTabla() {
		tabla = new JTable();
		tabla.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verificar si es un doble clic
                    int filaSeleccionada = tabla.getSelectedRow();
                    int columnaId = 0;

                    if (filaSeleccionada != -1 && columnaId != -1) {
                        Object valorCelda = tabla.getValueAt(filaSeleccionada, columnaId);
                        System.out.println("Valor de la celda seleccionada: " + valorCelda);
                    } else {
                        System.out.println("Ninguna celda seleccionada");
                    }
                }
            }
        });
	}
	
	private void agregarModelo() {
		modeloTabla = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
		modeloTabla.setColumnIdentifiers(columnas);
		
		for (Object[] fila : valores) {
			modeloTabla.addRow(fila);
		}
		
		tabla.setModel(modeloTabla);
	}
	
	private void alinearValores() {
		DefaultTableCellRenderer CellRender = new DefaultTableCellRenderer();
		CellRender.setHorizontalAlignment(SwingConstants.CENTER);
		for (int c = 0; c < columnas.length; c++) {
			tabla.getColumnModel().getColumn(c).setCellRenderer(CellRender);
		}
	}
	
	private void ajustarAnchoColumnas() {
		tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
	}
	
	public JScrollPane obtenerTablaPersona() {
		return scrollPanel;		 
	}
	
	public void actualizarValores(List<Object[]> valoresActualizados) {
		while (tabla.getRowCount() > 0) {
			modeloTabla.removeRow(0);
		}

		for (Object[] fila : valoresActualizados) {
			modeloTabla.addRow(fila);
		}

		tabla.setModel(modeloTabla);
	}
	
}