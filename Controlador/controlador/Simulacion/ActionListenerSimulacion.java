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

public class ActionListenerSimulacion implements ActionListener, Observer {

	private VentanaPrincipal ventanaPrincipal;
	private PanelSimulacion panelSimulacion;
	private SingletonClinica clinica;

	public ActionListenerSimulacion() {
		this.clinica = SingletonClinica.getInstance();
		clinica.addObserver(this);
	}

	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaPrincipal.setControladorSimulacion(this);
		this.ventanaPrincipal.getBoton_navegacionSimulacion().addActionListener(this);
		this.panelSimulacion = new PanelSimulacion(ventanaPrincipal.getControladorSimulacion());
	}

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

	
	public void comenzarSimulacion() {
		panelSimulacion.agregarEvento("Simulación iniciada.\n");
		clinica.lanzarSimulacion();
	}
	
	public void finalizarSimulacion() {
		panelSimulacion.agregarEvento("Simulación finalizada.\n");
		panelSimulacion.actualizarEstadoAmbulancia("  --");
		clinica.finalizarSimulacion();
	}

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
