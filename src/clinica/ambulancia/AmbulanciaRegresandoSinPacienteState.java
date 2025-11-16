package clinica.ambulancia;
/**
 * Representa el estado "Regresando a Clínica Sin Paciente" (Estado 4) de la Ambulancia.
 * <p>
 * Este estado ocurre después de una atención a domicilio donde no se requirió traslado.
 * En este estado, la ambulancia puede ser interrumpida para una nueva atención o traslado, o puede finalizar su retorno.
 */
public class AmbulanciaRegresandoSinPacienteState implements IAmbulanciaState{
	private Ambulancia ambulancia;

	public AmbulanciaRegresandoSinPacienteState(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}
	/**
	 * Maneja la solicitud de "atención a domicilio" desde el estado "Regresando".
     * Interrumpe el regreso para atender la nueva solicitud. 
     * <p><b>Postcondición:</b> el estado de la ambulancia cambia a "Atendiendo a Domicilio" 
	 */
	@Override
	public void atencionADomicilio() {
		this.ambulancia.setEstado(new AmbulanciaAtendiendoADomicilioState(this.ambulancia));
	}
	/**
	 * Maneja la solicitud de "traslado a clínica" desde el estado "Regresando".
     * Interrumpe el regreso para atender la nueva solicitud. 
     * <p><b>Postcondición:</b> el estado de la ambulancia cambia a "Trasladando Paciente" 
	 */
	@Override
	public void trasladoAClinica() {
		this.ambulancia.setEstado(new AmbulanciaTrasladandoPacienteState(this.ambulancia));
	}
	/**
	 * Maneja el evento de "retorno a clínica" desde el estado "Regresando".
     * Este evento se dispara cuando la ambulancia finalmente llega a la clínica. 
     * <p><b>Postcondición:</b> el estado de la ambulancia cambia a "Disponible" 
	 */
	@Override
	public void retornoAClinica() {
		this.ambulancia.setEstado(new AmbulanciaDisponibleState(this.ambulancia));
	}
	/**
	 * Maneja la solicitud de "mantenimiento" desde el estado "Regresando".
     * Informa que la ambulancia está ocupada (no puede ir a taller si no está físicamente en la clínica). 
     *<p><b>Postcondición:</b> el estado de la ambulancia *no* cambia.
	 */
	@Override
	public void mantenimiento() {
		System.out.println("AMBULANCIA OCUPADA: La ambulancia est� regresando sin paciente");
	}

	}
