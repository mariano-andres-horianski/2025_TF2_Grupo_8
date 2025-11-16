package clinica.ambulancia;
/**
 * Representa el estado "Atendiendo a Domicilio" (Estado 3) de la Ambulancia.
 * <p>En este estado, la ambulancia está ocupada y solo puede responder al
 * evento de "retorno a clínica" (cuando termina la atención).
 */
public class AmbulanciaAtendiendoADomicilioState implements IAmbulanciaState{
	private Ambulancia ambulancia;

	public AmbulanciaAtendiendoADomicilioState(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}
	/**
	 * Maneja la solicitud de "atención a domicilio" desde el estado "Atendiendo".
     * No realiza ninguna acción, ya que la ambulancia está ocupada
     * (permanece en Estado 3).
     * <p><b>Postcondición:</b> el estado de la ambulancia no cambia.
	 */
	@Override
	public void atencionADomicilio() {
		
	}
	/**
	 * Maneja la solicitud de "traslado a clínica" desde el estado "Atendiendo".
	 * <p>Informa que la ambulancia está ocupada.
	 * <p><b>Postcondición:</b> El estado de la ambulancia no cambia.
	 */
	@Override
	public void trasladoAClinica() {
		System.out.println("AMBULANCIA OCUPADA: La ambulancia est� atendiendo a domicilio");
	}
	/**
	 * Maneja el evento de "retorno a clínica" desde el estado "Atendiendo".
     * Este evento se dispara cuando la atención a domicilio finaliza.
     * <p><b>Postcondición:</b> El estado de la ambulancia cambia a "Regresando Sin Paciente"
     *
	 */
	@Override
	public void retornoAClinica() {
		this.ambulancia.setEstado(new AmbulanciaRegresandoSinPacienteState(this.ambulancia));
	}
	/**
	 * Maneja la solicitud de "mantenimiento" desde el estado "Atendiendo".
     * <p>Informa que la ambulancia está ocupada.
     * <p><b>Postcondición:</b> El estado de la ambulancia *no* cambia.
	 */
	@Override
	public void mantenimiento() {
		System.out.println("AMBULANCIA OCUPADA: La ambulancia est� atendiendo a domicilio");
	}

}