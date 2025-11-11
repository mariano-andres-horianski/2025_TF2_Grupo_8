package persistencia.Excepciones;

/**
 * Excepción que indica que se ha proporcionado un dato inválido en una operación.
 */
public class DatoInvalidoException extends Exception {
    /**
     * Crea una nueva excepción con el mensaje especificado.
     * 
     * @param mensaje descripción del error.
     */
	public DatoInvalidoException(String mensaje) {
		super(mensaje); 
	}
	
}
