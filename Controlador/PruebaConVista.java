import clinica.SingletonClinica;
import controlador.Asociados.ActionListenerAsociados;
import controlador.Simulacion.ActionListenerSimulacion;
import excepciones.AsociadoDuplicadoException;
import negocio.Asociado;
import negocio.RetornoAutomatico;
import vista.JframePrincipal.VentanaPrincipal;

public class PruebaConVista {
	public static void main(String[] args) {
		VentanaPrincipal vista = new VentanaPrincipal();
		SingletonClinica clinica = SingletonClinica.getInstance();

		ActionListenerAsociados controladorAsociados = new ActionListenerAsociados();
		controladorAsociados.setVentanaPrincipal(vista);

		ActionListenerSimulacion controladorSimulacion = new ActionListenerSimulacion();
		controladorSimulacion.setVentanaPrincipal(vista);

		// PRUEBAS ASOCIADO + AMBULANCIA
		Asociado a1 = clinica.crearAsociado("777", "David", "Miramar", "223333333", "Av. Libertador", 25);
		Asociado a2 = clinica.crearAsociado("888", "Martin", "Miramar", "223333333", "Av. Libertador", 25);
		Asociado a3 = clinica.crearAsociado("999", "Gian", "Miramar", "223333333", "Av. Libertador", 25);
		Asociado a4 = clinica.crearAsociado("101", "Kevin", "Miramar", "223333333", "Av. Libertador", 25);

		try {
			clinica.registrarAsociado(a1);
			clinica.registrarAsociado(a2);
			clinica.registrarAsociado(a3);
			clinica.registrarAsociado(a4);
		} catch (AsociadoDuplicadoException e) {
			e.printStackTrace();
		}

		System.out.println(clinica.mostrarAsociados());


		System.out.println("============= SIMULACION =============");

	}
}
