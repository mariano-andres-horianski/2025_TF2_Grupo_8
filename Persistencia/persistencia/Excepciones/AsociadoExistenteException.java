package persistencia.Excepciones;

/**
 * Excepción que indica que se intentó agregar un asociado que ya existe en la base de datos.
 */
public class AsociadoExistenteException extends Exception {
	/**
     * Crea una nueva excepción con el mensaje especificado.
     * 
     * @param mensaje descripción del error.
     */
	public AsociadoExistenteException(String mensaje) {
		super(mensaje); 
	}
	
}
