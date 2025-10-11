package clinica.model;

/**
 * Interfaz que define el contrato para un médico dentro de la clínica.
 * <p>
 * Extiende IPersona y añade información específica de los médicos,
 * incluyendo número de matrícula profesional, especialidad y honorario por consulta.
 * </p>
 */
public interface IMedico extends IPersona {
	public int getNroMat();

	public Especialidad getEspecialidad();

	public double getHonorario();
}
