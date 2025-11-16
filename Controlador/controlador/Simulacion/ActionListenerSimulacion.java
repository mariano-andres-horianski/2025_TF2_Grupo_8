package controlador.Simulacion;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import clinica.*;
import controlador.Asociados.ReadListenerAsociados;
import vista.JframePrincipal.VentanaPrincipal;
import vista.PanelCentral.PanelSimulacion;
/**
 * Controlador para el módulo de simulación, siguiendo el patrón MVC.
 *
 * Esta clase actúa como el nexo entre la Vista (VentanaPrincipal y
 * PanelSimulacion) y el Modelo (SingletonClinica).
 *
 * Implementa {@link ActionListener} para reaccionar a los eventos de la GUI (clics en botones).
 * Implementa {@link Observer} para recibir notificaciones del Modelo  y actualizar la Vista en consecuencia.
 *
 */
public class ActionListenerSimulacion implements ActionListener, Observer {

	private VentanaPrincipal ventanaPrincipal;
	private PanelSimulacion panelSimulacion;
	private SingletonClinica clinica;
	/**
	 * Construye el controlador de simulación.
	 * <p>Obtiene la instancia del modelo (SingletonClinica) y se registra a sí mismo como Observador (Observer) para recibir actualizaciones.
	 * <p><b>Postcondición:</b> this se añade a la lista de observadores de SingletonClinica.
	 */
	public ActionListenerSimulacion() {
		this.clinica = SingletonClinica.getInstance();
		clinica.addObserver(this);
	}
	/**
	 * Establece y configura la vista principal (JFrame) para este controlador.
	 * Este método es crucial para enlazar el Modelo, la Vista y el Controlador (MVC).
	 * <p><b>Precondición:</b> ventanaPrincipal no debe ser nula.
	 * <p><b>Postcondición:</b> 
	 * <pre>
	 * Este controlador se asigna a la ventanaPrincipal.
	 * Se registra como ActionListener para el botón de navegación de simulación.
	 * Se inicializa el PanelSimulacion, pasándole este controlador.
	 * </pre>
	 * @param ventanaPrincipal La instancia de la VentanaPrincipal (Vista).
	 */
	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaPrincipal.setControladorSimulacion(this);
		this.ventanaPrincipal.getBoton_navegacionSimulacion().addActionListener(this);
		this.panelSimulacion = new PanelSimulacion(ventanaPrincipal.getControladorSimulacion());
	}
	/**
	 * Maneja los eventos de acción (clics en botones) provenientes de la vista.
	 * Delega las acciones al modelo (Clinica) o gestiona la navegación de la interfaz.
	 * <p><b>Precondición:</b> e no debe ser nulo.
	 * <p><b>Postcondición:</b> Se ejecuta la lógica correspondiente al comando del botón.
	 * @param e El evento de acción que contiene el comando del botón. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand().toUpperCase();

		switch (comando) {

			case "SIMULACION":
				ventanaPrincipal.setPanel_Central(panelSimulacion);
				panelSimulacion.actualizarNumAsociados(String.valueOf(clinica.getAsociados().size()));
				break;
				
			case "INICIAR_SIM":
				if (!clinica.isSimulacionActiva())
					comenzarSimulacion();
				break;

			case "MANTENIMIENTO":
				if (clinica.isSimulacionActiva()) {
					Thread operario = new Thread(clinica.getOperario());
				    operario.start();
					panelSimulacion.agregarEvento("OPERARIO: Solicitud de mantenimiento");
				}
				break;

			case "FINALIZAR_SIMULACION":
				if (clinica.isSimulacionActiva())
					finalizarSimulacion();
				break;
		}
	}

	/**
	 * Inicia la simulación.
	 * Delega el inicio de los hilos al modelo (Clinica) y actualiza la vista (PanelSimulacion) con un mensaje. 
	 * <p><b>Postcondición:</b>
	 * <pre>
	 * La simulación en SingletonClinica se marca como activa.
	 * Los hilos de Asociados y RetornoAutomático son iniciados por la clínica.
	 * Se añade "Simulación iniciada." al log de eventos de la vista.
	 * </pre>
	 */
	public void comenzarSimulacion() {
		panelSimulacion.agregarEvento("Simulación iniciada.\n");
		clinica.lanzarSimulacion();
	}
	/**
	 * Finaliza la simulación.
	 * Delega la detención de los hilos al modelo (Clinica) y resetea la vista.
	 * <p><b>Postcondición:</b>
	 * <pre>
	 * La simulación en SingletonClinica se marca como inactiva.
	 * El estado de la ambulancia en la vista se resetea a "--".
	 * Se añade "Simulación finalizada." al log de eventos de la vista.
	 * </pre>
	 */
	public void finalizarSimulacion() {
		panelSimulacion.agregarEvento("Simulación finalizada.\n");
		panelSimulacion.actualizarEstadoAmbulancia("  --");
		clinica.finalizarSimulacion();
	}
	/**
	 * Método invocado por el Observable (SingletonClinica) cuando hay un cambio en el Modelo.
	 * Actualiza la vista (PanelSimulacion) con la información recibida.
	 * <p><b>Precondición:</b>
	 * o debe ser la instancia de SingletonClinica y arg debe ser una instancia de String.
	 * <p><b>Postcondición:</b> 
	 * Si arg es un estado, actualiza el label de estado de la ambulancia.
	 * Si arg es un evento (otro String), lo añade al log de eventos de la vista.
	 * @param o   El objeto Observable que notificó (debería ser SingletonClinica).
	 * @param arg El argumento/mensaje pasado por el Observable (se espera un String).
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		if((SingletonClinica)o == clinica) {
			String msg = (String) arg;
			
			if(msg.startsWith("ESTADO:")) {
				String estado = msg.substring("ESTADO:".length());
				panelSimulacion.actualizarEstadoAmbulancia(estado);
			} else {
			panelSimulacion.agregarEvento(msg);
			}
		}
		
	}
	
}
