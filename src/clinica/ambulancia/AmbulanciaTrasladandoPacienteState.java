package clinica.ambulancia;
/**
 * Representa el estado "Trasladando Paciente a Clínica" (Estado 2) de la Ambulancia.
 * <p>
 * En este estado, la ambulancia está ocupada y solo puede responder al
 * evento de "retorno a clínica" (cuando llega a la clínica y deja al paciente).
 */
public class AmbulanciaTrasladandoPacienteState implements IAmbulanciaState{
	private Ambulancia ambulancia;

	public AmbulanciaTrasladandoPacienteState(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}
	/**
	 * Maneja la solicitud de "atención a domicilio" desde el estado "Trasladando".
     * <p>
     * No realiza ninguna acción, ya que la ambulancia está ocupada
     * (permanece en Estado 2).
     * <p><b>Postcondición:</b> el estado de la ambulancia no cambia.
	 */
	@Override
	public void atencionADomicilio() {
		
	}
	/**
	 * Maneja la solicitud de "traslado a clínica" desde el estado "Trasladando".
     * <p>Informa que la ambulancia está ocupada (ya está realizando esta acción).
     * <p><b>Postcondición:</b> el estado de la ambulancia no cambia.
	 */
	@Override
	public void trasladoAClinica() {
		System.out.println("AMBULANCIA OCUPADA: La ambulancia est� trasladando un paciente");
	}
	/**
	 * Maneja el evento de "retorno a clínica" desde el estado "Trasladando".
     * <p>Este evento se dispara cuando la ambulancia llega a la clínica.
     * <p><b>Postcondición:</b> el estado de la ambulancia cambia a "Disponible"
	 */
	@Override
	public void retornoAClinica() {
		this.ambulancia.setEstado(new AmbulanciaDisponibleState(this.ambulancia));
	}
	/**
	 * Maneja la solicitud de "mantenimiento" desde el estado "Trasladando".
     * <p>Informa que la ambulancia está ocupada.
     *<p><b>Postcondición:</b> el estado de la ambulancia no cambia.
	 */
	@Override
	public void mantenimiento() {
		System.out.println("AMBULANCIA OCUPADA: La ambulancia est� trasladando un paciente");
	}

	}