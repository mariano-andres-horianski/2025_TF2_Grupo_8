package clinica.ambulancia;
/**
 * Clase principal que representa la Ambulancia 
 * <p>
 * Esta clase mantiene una referencia al estado actual ({@link IAmbulanciaState})
 * y delega todas las solicitudes de acción a dicho estado.
 * También provee el método para cambiar su propio estado ({@link #setEstado(IAmbulanciaState)}).
 * 
 */
public class Ambulancia {
	/**
	 * Referencia al estado actual de la ambulancia
	 */
	private IAmbulanciaState estado;
	/**
	 * Constructor de la ambulancia.
	 * <p>
	 * <ul>
	 * La ambulancia se inicializa en el estado "Disponible"
	 * </ul>
	 */
	public Ambulancia() {
		this.estado = new AmbulanciaDisponibleState(this);
	}
	
	public void atencionADomicilio() {
		this.estado.atencionADomicilio();
	}
	public void trasladoAClinica() {
		this.estado.trasladoAClinica();
	}
	public void retornoAClinica() {
		this.estado.retornoAClinica();
	}
	public void mantenimiento() {
		this.estado.mantenimiento();
	}
	/**
	 * Establece un nuevo estado para la ambulancia.
	 * Este método es llamado por los objetos de estado concretos para realizar las transiciones
	 *<p><b>Precondición:</b> el parametro estado no debe ser null.
	 *<p><b>Postcondición:</b> el estado interno de la ambulancia se actualiza al nuevo estado proporcionado.
	 * 
	 * @param estado El nuevo estado en el que entrará la ambulancia.
	 */
	public void setEstado(IAmbulanciaState estado) {
		this.estado = estado;
	}
	
}
