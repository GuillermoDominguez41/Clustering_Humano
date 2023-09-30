

import modelo.Logica;
import vista.VentanaAgregarPersona;
import vista.VentanaAgregarRelacion;
import vista.VentanaPrincipal;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;

import javax.swing.*;
import controlador.Coordinador;

public class Principal {
	
	private Logica logica;
	private Coordinador coordinador;
	private VentanaPrincipal vPrincipal;
	private VentanaAgregarPersona vAgregarPersona;
	private VentanaAgregarRelacion vAgregarRelacion;

	public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatGradiantoDeepOceanIJTheme());
            Principal miPrincipal=new Principal();
            miPrincipal.iniciar();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
	}

	private void iniciar() {		
	 // Se instancian las clases y se relacionan con el coordinador.
		coordinador = new Coordinador();
		logica = new Logica(coordinador);
		coordinador.setLogica(logica);
		vPrincipal = new VentanaPrincipal(coordinador);
		coordinador.setVentanaPrincipal(vPrincipal);
		vAgregarPersona = new VentanaAgregarPersona(coordinador);
		coordinador.setVentanaAgregarPersona(vAgregarPersona);
		vAgregarRelacion = new VentanaAgregarRelacion(coordinador);
		coordinador.setVentanaAgregarRelacion(vAgregarRelacion);
		
	 // Iniciamos la interfaz principal
		vPrincipal.mostrarVentana();
	}	
}
