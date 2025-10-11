package clinica.model;
/**
 * Clase abstracta que representa una persona dentro de la clínica.
 * <p>
 * Proporciona los atributos y métodos comunes a todas las personas del sistema,
 * incluyendo pacientes y médicos. Contiene información de identificación,
 * datos de contacto y domicilio.
 * </p>
 *
 */
public abstract class Persona implements IPersona {
	private String dni, nya, ciudad, telefono; // nya = Nombre y Apellido
	private Domicilio domicilio;
	/**
     * Crea una nueva persona con los datos básicos y domicilio.
     *
     * @param dni Documento Nacional de Identidad
     * @param nya Nombre y apellido
     * @param ciudad Ciudad de residencia
     * @param telefono Teléfono de contacto
     * @param domicilio Domicilio de la persona
     */
	public Persona(String dni, String nya, String ciudad, String telefono, Domicilio domicilio) {
		this.dni = dni;
		this.nya = nya;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.domicilio = domicilio;
	}
	
	@Override
	public String getDni() {
		return dni;
	}

	@Override
	public String getNya() {
		return nya;
	}

	@Override
	public String getCiudad() {
		return ciudad;
	}

	@Override
	public String getTelefono() {
		return telefono;
	}

	@Override
	public Domicilio getDomicilio() {
		return domicilio;
	}

}
