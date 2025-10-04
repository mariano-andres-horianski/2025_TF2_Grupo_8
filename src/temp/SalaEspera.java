package temp;
	/**
	 * Representa la sala de espera de la clinica 
	 * Informa sobre la ocupacion de la sala y guarda la referencia del paciente que actualmente la ocupa
	 * 
	 */
public class SalaEspera {
	private boolean ocupacion;
	private IPrioridad paciente;
	
	public SalaEspera() {
		this.ocupacion = false;
		this.paciente = null;
	}
	public void ocuparSala(IPrioridad paciente) {
		this.paciente = paciente;
		this.ocupacion = true;
	}
	
	public void desocupar() {
		this.paciente = null;
		this.ocupacion = false;
	}
	
	public IPrioridad getPaciente() {
		return paciente;
	}
	
	public boolean isOcupacion() {
		return ocupacion;
	}
	
}
