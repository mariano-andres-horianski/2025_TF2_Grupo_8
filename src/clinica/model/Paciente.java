package clinica.model;

import java.time.LocalDate;
import clinica.d.dispatch.*;

/**
 * Clase abstracta que representa a un paciente dentro de la clínica.
 * <p>
 * Hereda de Persona y añade información específica del paciente,
 * como número de historia clínica, rango etario y fecha de ingreso.
 * Además, implementa IPrioridad para poder ser evaluado en listas
 * de espera o prioridad de atención.
 * </p>
 */
public abstract class Paciente extends Persona implements IPrioridad{
	/** Número de Historia Clínica del paciente. */
    private int nroHC;

    /** Rango etario del paciente ("Niño", "Joven" o "Mayor"). */
    private String rangoEtario;

    /** Fecha de ingreso del paciente a la clínica. */
    private LocalDate fechaIngreso;
    /**
     * Crea un nuevo paciente con los datos personales y clínicos básicos.
     *
     * @param dni Documento Nacional de Identidad
     * @param nya Nombre y apellido
     * @param ciudad Ciudad de residencia
     * @param telefono Teléfono de contacto
     * @param domicilio Domicilio del paciente
     * @param nroHC Número de Historia Clínica
     * @param rangoEtario Rango etario ("Niño", "Joven" o "Mayor")
     */
	public Paciente(String dni, String nya, String ciudad, String telefono, Domicilio domicilio, int nroHC,
			String rangoEtario) {
		super(dni, nya, ciudad, telefono, domicilio);
		this.nroHC = nroHC;
		this.rangoEtario = rangoEtario;
	}

	public int getNroHC() {
		return nroHC;
	}

	public String getRangoEtario() {
		return rangoEtario;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

}
