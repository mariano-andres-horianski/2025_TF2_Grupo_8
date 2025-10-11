package clinica.model;

/**
 * Representa un médico dentro de la clínica.
 * <p>
 * Hereda de Persona y implementa IMedico, proporcionando
 * información adicional como número de matrícula, especialidad y cálculo
 * de honorario según la especialidad.
 * </p>
 *
 * <p>
 * El honorario del médico se calcula tomando la base honorarioBase
 * y aplicando el factor correspondiente de su Especialidad.
 * </p>
 */
public class Medico extends Persona implements IMedico {
	public static final double honorarioBase = 20000;
	private int nroMat;
	private Especialidad especialidad;
	/**
     * Crea un nuevo médico con sus datos personales, matrícula y especialidad.
     *
     * @param dni Documento Nacional de Identidad
     * @param nya Nombre y apellido
     * @param ciudad Ciudad de residencia
     * @param telefono Teléfono de contacto
     * @param domicilio Domicilio del médico
     * @param nroMat Número de matrícula profesional
     * @param especialidad Especialidad médica
     */
	public Medico(String dni, String nya, String ciudad, String telefono, Domicilio domicilio, int nroMat,
			Especialidad especialidad) {
		super(dni, nya, ciudad, telefono, domicilio);
		this.nroMat = nroMat;
		this.especialidad = especialidad;
	}

	public int getNroMat() {
		return nroMat;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public double getHonorario() {
		return this.especialidad.getHonorario(honorarioBase);
	}

	@Override
	public String toString() {
	    return String.format("Nombre Medico: %-20s  Especialidad: %-15s",
	            this.getNya(),
	            this.especialidad.toString());
	}


}