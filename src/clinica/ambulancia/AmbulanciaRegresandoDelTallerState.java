
package clinica.ambulancia;
/**
 * Representa el estado "Regresando del Taller" (Estado 6) de la Ambulancia.
 * <p>
 * En este estado, la ambulancia no está disponible para solicitudes de
 * pacientes. Solo puede responder al evento de "retorno a clínica"
 * (cuando llega físicamente a la clínica).
 */
public class AmbulanciaRegresandoDelTallerState implements IAmbulanciaState{
	private Ambulancia ambulancia;

	public AmbulanciaRegresandoDelTallerState(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}
	/**
	 * Maneja la solicitud de "atención a domicilio" desde el estado "Regresando Taller".
     * No realiza ninguna acción (permanece en Estado 6).
     * <p><b>Postcondición:</b> el estado de la ambulancia no cambia.
	 */
	@Override
	public void atencionADomicilio() {

	}
	/**
	 * Maneja la solicitud de "traslado a clínica" desde el estado "Regresando Taller".
     * Informa que la ambulancia está ocupada.
     * <p><b>Postcondición:</b> el estado de la ambulancia no cambia.
	 */
	@Override
	public void trasladoAClinica() {
		System.out.println("AMBULANCIA OCUPADA: La ambulancia est� regresando del taller");
	}
	/**
	 * Maneja el evento de "retorno a clínica" desde el estado "Regresando Taller".
     * Este evento se dispara cuando la ambulancia llega a la clínica.
     * <p><b>Postcondición:</b> el estado de la ambulancia cambia a "Disponible"
	 */
	@Override
	public void retornoAClinica() {
		this.ambulancia.setEstado(new AmbulanciaDisponibleState(this.ambulancia));
	}
	/**
	 * Maneja la solicitud de "mantenimiento" desde el estado "Regresando Taller".
     * Informa que la ambulancia está ocupada.
     * <p><b>Postcondición:</b> el estado de la ambulancia no cambia.
	 */
	@Override
	public void mantenimiento() {
		System.out.println("AMBULANCIA OCUPADA: La ambulancia est� regresando del taller");
	}

	}
