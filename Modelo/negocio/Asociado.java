package negocio;

import java.util.Random;

import clinica.SingletonClinica;
import clinica.model.Domicilio;
import clinica.model.Persona;
/**
 * Representa a un Asociado de la clínica, capaz de solicitar servicios de ambulancia.
 * Esta clase implementa  Runnable para participar en la simulación concurrente, actuando como un hilo que genera solicitudes de forma periódica.
 *
 */
public class Asociado extends Persona implements Runnable {
	private SingletonClinica clinica;
	private Random random = new Random();
	/**
	 * Construye una nueva instancia de Asociado.
	 * <p><b>Precondición:</b>  dni, nya, ciudad, telefono y domicilio no deben ser nulos.
	 * @param dni DNI del asociado.
	 * @param nya Nombre y Apellido del asociado.
	 * @param ciudad Ciudad del asociado.
	 * @param telefono Teléfono del asociado.
	 * @param domicilio Objeto Domicilio del asociado. 
	 */
	public Asociado(String dni, String nya, String ciudad, String telefono, Domicilio domicilio) {
		super(dni, nya, ciudad, telefono, domicilio);
		this.clinica = SingletonClinica.getInstance();
	}
	/**
	 * Define el comportamiento del Asociado como hilo en la simulación.
	 * Mientras la simulación esté activa, el asociado solicita aleatoriamente atención a domicilio o traslado a la clínica, con una pausa entre solicitudes.
	 *
	 * <p><b>Precondición:</b> La simulación clinica.isSimulacionActiva() debe estar inicializada.
	 * <p><b>Postcondición:</b> El hilo terminará su ejecución (saldrá del bucle) cuando la simulación se marque como inactiva clinica.isSimulacionActiva() == false.
	 *
	 */
	@Override
    public void run() {

        while (clinica.isSimulacionActiva()) {
        	

            int accion = random.nextInt(2); 
            // 0 = atencion domicilio
            // 1 = traslado clinica
            
            switch (accion) {
                case 0:
                    System.out.println(getNya() + " -> Pide atención a domicilio"); // temporal
                    clinica.notificarEvento(getNya() + " pidió atención a domicilio");
                    clinica.getAmbulancia().solicitarAtencionDomicilio(getNya());
                    break;
                case 1:
                    System.out.println(getNya() + " -> Pide traslado a clínica"); // temporal
                    clinica.notificarEvento(getNya() + " pidió traslado a la clínica");
                    clinica.getAmbulancia().solicitarTrasladoClinica(getNya());
                    break;
            }

            try {
                Thread.sleep(5000 + random.nextInt(2000)); // espera entre trámites
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	/**
	 * Devuelve una representación en String del Asociado.
	 * @return Una cadena con el formato "Nombre y Apellido.
	 */
	@Override
	public String toString() {
		return this.getNya() + " (DNI: " + this.getDni() + ")";
	}
	
	

}
