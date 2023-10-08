package vista;

import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AdmTablas {

	public void establecerValores(JTable tabla, List<Object[]> valoresFilas) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		limpiarTabla(modelo);
		agregarFilas(modelo, valoresFilas);
	}

	private void agregarFilas(DefaultTableModel modelo, List<Object[]> valoresFilas) {
		for (Object[] fila : valoresFilas) {
			modelo.addRow(fila);
		}
	}

	private void limpiarTabla(DefaultTableModel modelo) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
	}

	public void establecerTituloColumnas(JTable tabla, String[] nombreColumnas) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		modelo.setColumnIdentifiers(nombreColumnas);
	}

	public void ajustarAnchoColumna(JTable tabla, Integer numeroColumna, Integer anchoColumna) {
		tabla.getColumnModel().getColumn(numeroColumna).setPreferredWidth(anchoColumna);
	}

	public void establecerModeloNoEditable(JTable tabla) {
		@SuppressWarnings("serial")
		DefaultTableModel modeloConCeldasNoEditables = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tabla.setModel(modeloConCeldasNoEditables);
	}

	public void centrarValoresEnCeldas(JTable tabla) {
		Integer cantColumnas = tabla.getColumnCount();

		DefaultTableCellRenderer CellRender = new DefaultTableCellRenderer();
		CellRender.setHorizontalAlignment(SwingConstants.CENTER);

		for (int c = 0; c < cantColumnas; c++) {
			tabla.getColumnModel().getColumn(c).setCellRenderer(CellRender);
		}
	}

}
