package clinica.ambulancia;
/**
 * Representa el estado "En Taller" (Estado 5) de la Ambulancia.
 * <p> En este estado, la ambulancia está fuera de servicio y no puede responder a ninguna solicitud de pacientes. 
 * Solo puede responderal evento de "mantenimiento" (que en este contexto significa "finalizar mantenimiento").
 */
public class AmbulanciaEnTallerState implements IAmbulanciaState{
	private Ambulancia ambulancia;

	public AmbulanciaEnTallerState(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}
	/**
	 * Maneja la solicitud de "atención a domicilio" desde el estado "En Taller".
     * No realiza ninguna acción (permanece en Estado 5).
     * <p><b>Postcondición:</b> el estado de la ambulancia no cambia.
	 */
	@Override
	public void atencionADomicilio() {

	}
	/**
	 * Maneja la solicitud de "traslado a clínica" desde el estado "En Taller".
     * Informa que la ambulancia está ocupada.
     *  <p><b>Postcondición:</b> el estado de la ambulancia no cambia.
	 */
	@Override
	public void trasladoAClinica() {
		System.out.println("AMBULANCIA OCUPADA: La ambulancia est� en el taller");
	}
	/**
	 * Maneja el evento de "retorno a clínica" desde el estado "En Taller".
     * No realiza ninguna acción (permanece en Estado 5).
     *  <p><b>Postcondición:</b> el estado de la ambulancia no cambia.
	 */
	@Override
	public void retornoAClinica() {

	}
	/**
	 * Maneja la solicitud de "mantenimiento" desde el estado "En Taller".
     * Este evento se dispara cuando el mantenimiento finaliza.
     * <p><b>Postcondición:</b> el estado de la ambulancia cambia a "Regresando Del Taller"
	 */
	@Override
	public void mantenimiento() {
		this.ambulancia.setEstado(new AmbulanciaRegresandoDelTallerState(this.ambulancia));
	}

	}
