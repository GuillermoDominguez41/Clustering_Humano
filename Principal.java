
import modelo.Logica;
import vista.VentanaAgregarPersona;
import vista.VentanaEditarPersona;
import vista.VentanaPrincipal;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import bd.AdmBaseDatos;
import javax.swing.*;
import controlador.Coordinador;

public class Principal {

	private Coordinador coordinador;
	private Logica logica;
	private AdmBaseDatos admBD;
	private VentanaPrincipal vPrincipal;
	private VentanaAgregarPersona vAgregarPersona;
	private VentanaEditarPersona vEditarPersona;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatGradiantoDeepOceanIJTheme());
			Principal miPrincipal = new Principal();
			miPrincipal.iniciar();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	private void iniciar() {
		// Se instancian las clases y se relacionan con el coordinador.
		coordinador = new Coordinador();
		admBD = new AdmBaseDatos();
		coordinador.setAdministradorBD(admBD);
		logica = new Logica(coordinador);
		coordinador.setLogica(logica);
		vPrincipal = new VentanaPrincipal(coordinador);
		coordinador.setVentanaPrincipal(vPrincipal);
		vAgregarPersona = new VentanaAgregarPersona(coordinador);
		coordinador.setVentanaAgregarPersona(vAgregarPersona);
		vEditarPersona = new VentanaEditarPersona(coordinador);
		coordinador.setVentanaEditarPersona(vEditarPersona);

		// Iniciamos la interfaz principal
		vPrincipal.mostrarVentana();
	}
}
