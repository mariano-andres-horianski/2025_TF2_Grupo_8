package persistencia.Excepciones;

/**
 * Excepción que indica que no se encontró un asociado con el identificador especificado.
 */
public class AsociadoNotFoundException extends Exception{
    /**
     * Crea una nueva excepción con el mensaje especificado.
     * 
     * @param mensaje descripción del error.
     */
	public AsociadoNotFoundException(String mensaje) { 
		super(mensaje); 
	}
}
