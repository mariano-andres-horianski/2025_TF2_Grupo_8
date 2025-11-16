package clinica.habitaciones;
/**
 * Define el comportamiento general de una habitación dentro de la clínica.
 * <p>
 * Las implementaciones de esta interfaz representan distintos tipos de habitaciones
 * (privadas, compartidas, de terapia intensiva) y deben proporcionar
 * la lógica necesaria para calcular su costo en función de la cantidad de días de internación.
 * </p>
 */
public interface IHabitacion {
	/**
     * Calcula el costo total de la habitación según la cantidad de días de internación.
    *
    * @param dias la cantidad de días que el paciente permaneció internado
    * @return el costo total de la habitación para el período indicado
    */
    double calcularCosto(long dias);
    /**
     * Devuelve el tipo de habitación.
     * <p>
     * Este valor identifica la categoría de la habitación (
     * "Privada", "Compartida" o "Terapia Intensiva").
     * </p>
     *
     * @return una cadena con la descripción del tipo de habitación
     */
    String getTipo();
}
