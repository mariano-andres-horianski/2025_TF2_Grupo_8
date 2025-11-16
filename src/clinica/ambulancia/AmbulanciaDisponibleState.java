package clinica.ambulancia;
/**
 * Representa el estado "Disponible" (Estado 1) de la Ambulancia.
 * <p>
 * En este estado, la ambulancia puede responder a solicitudes de atención,
 * traslado o mantenimiento.
 */
public class AmbulanciaDisponibleState implements IAmbulanciaState{
	private Ambulancia ambulancia;

	public AmbulanciaDisponibleState(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}
	/**
	 * Maneja la solicitud de "atención a domicilio" desde el estado "Disponible".
     * <p><b>Postcondición:</b> el estado de la ambulancia cambia a "Atendiendo a Domicilio"
	 */
	@Override
	public void atencionADomicilio() {
		this.ambulancia.setEstado(new AmbulanciaAtendiendoADomicilioState(this.ambulancia));
	}
	/**
	 * Maneja la solicitud de "traslado a clínica" desde el estado "Disponible".
     *<p><b>Postcondición:</b> el estado de la ambulancia cambia a "Trasladando Paciente"
	 */
	@Override
	public void trasladoAClinica() {
		this.ambulancia.setEstado(new AmbulanciaTrasladandoPacienteState(this.ambulancia));
	}
	/**
	 * Maneja el evento de "retorno a clínica" desde el estado "Disponible".
     * <p>No realiza ninguna acción, ya que la ambulancia ya está en la clínica y disponible (permanece en Estado 1).
     * <p><b>Postcondición:</b> el estado de la ambulancia no cambia.
	 */
	@Override
	public void retornoAClinica() {
		
	}
	/**
	 * Maneja la solicitud de "mantenimiento" desde el estado "Disponible".
     *<p><b>Postcondición:</b> el estado de la ambulancia cambia a "En Taller"
	 */
	@Override
	public void mantenimiento() {
		this.ambulancia.setEstado(new AmbulanciaEnTallerState(this.ambulancia));
	}

}
