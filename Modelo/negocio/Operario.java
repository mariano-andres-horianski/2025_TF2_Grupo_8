package negocio;

import clinica.SingletonClinica;
/**
 * Representa al Operario de la clínica, cuya función es solicitar el mantenimiento de la ambulancia.
 *
 * Esta clase implementa Runnable para poder ser ejecutada en un hilo independiente.
 * A diferencia del Asociado (que se ejecuta en un bucle), este hilo tiene una vida corta:
 * se inicia, ejecuta su única tarea run(), y termina.
 *
 */
public class Operario implements Runnable {

    private SingletonClinica clinica;

    /**
     * Construye un Operario asociado a la instancia de la Clínica. 
	 * @param clinica Instancia única (Singleton) de la clínica. 
     */
    public Operario(SingletonClinica clinica) {
		this.clinica = clinica;
	}

    /**
	 * Realiza la acción de solicitar mantenimiento.
	 * Delega la llamada al método sincronizado de la Ambulancia. 
	 * 
	 * <p>El hilo actual (el del Operario) puede bloquearse si la ambulancia está ocupada y no puede ir a mantenimiento.
	 */
	public void solicitarMantenimientoAmbulancia() {
        clinica.getAmbulancia().solicitarMantenimiento();
    }

	/**
	 * Punto de entrada para el hilo del Operario.
	 * La única acción de este hilo es invocar.
	 *
	 * <p>La solicitud de mantenimiento es enviada a la ambulancia y el hilo finaliza su ejecución una vez que el método retorna.
	 */
	@Override
	public void run() {
		solicitarMantenimientoAmbulancia();
	}
	
}