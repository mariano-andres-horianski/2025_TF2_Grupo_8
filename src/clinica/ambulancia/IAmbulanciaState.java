package clinica.ambulancia;
/**
 * Interfaz que define el comportamiento de la Ambulancia mediante la aplicación del Patrón State.
 * <p>Define las acciones que pueden ser solicitadas a la ambulancia. 
 * <p>Cada implementación concreta de esta interfaz representará un estado específico
 * de la ambulancia y definirá cómo reacciona a cada evento (solicitud).
 * 
 */
public interface IAmbulanciaState {
	public void atencionADomicilio();
	public void trasladoAClinica();
	public void retornoAClinica();
	public void mantenimiento();

}
