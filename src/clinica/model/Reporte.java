package clinica.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Representa un reporte de actividad médica de un médico dentro de la clínica.
 * <p>
 * El reporte se genera para un período determinado, mostrando cronológicamente
 * todas las consultas realizadas por el médico en ese rango de fechas.
 * Para cada día, se enumeran los pacientes atendidos, junto con el honorario
 * de cada consulta. Al final se muestra la suma total de los honorarios.
 * </p>
 * <p>
 * La fecha de cada consulta se toma como la fecha de facturación asociada a la
 * misma. Las consultas fuera del período especificado son ignoradas.
 * </p>
 */
public class Reporte {
	private IMedico medico;
	/** Fecha de inicio del período del reporte (inclusive). */
	private LocalDate fechaInicio;
	/** Fecha de fin del período del reporte (inclusive). */
	private LocalDate fechaFin;
	private ArrayList<Consulta> consultasMedico;
	/**
     * Crea un reporte de actividad médica para un médico en un período dado.
     *
     * @param medico el médico al que corresponde el reporte
     * @param fechaInicio la fecha de inicio del período (inclusive)
     * @param fechaFin la fecha de fin del período (inclusive)
     * @param consultasMedico lista de consultas realizadas por el médico; si es null, se inicializa vacía
     */
	public Reporte(IMedico medico, LocalDate fechaInicio, LocalDate fechaFin, ArrayList<Consulta> consultasMedico) {
		this.medico = medico;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.consultasMedico = consultasMedico != null ? consultasMedico : new ArrayList<>();
	}
	/**
     * Genera una representación en texto del reporte.
     * <p>
     * Muestra la actividad del médico por día, enumerando los pacientes atendidos
     * y el honorario de cada consulta. Al final se muestra el total de honorarios
     * para el período.
     * </p>
     * <p>
     * Las consultas se muestran en orden cronológico y se ignoran aquellas que no
     * estén dentro del rango de fechas especificado.
     * </p>
     *
     * @return un String con el reporte de actividad médica, incluyendo pacientes, honorarios y total
     */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (consultasMedico.isEmpty()) {
			sb.append("MÃ©dico: ").append(medico.getNya()).append("\n");
			sb.append("No existen consultas registradas para este mÃ©dico.\n");
		} else {
			sb.append("REPORTE DE ACTIVIDAD MÃ‰DICA\n");
			sb.append("MÃ©dico: ").append(medico.getNya()).append("\n");
			sb.append("Periodo: ").append(fechaInicio).append(" a ").append(fechaFin).append("\n\n");

			// Ordenar por fecha (usa compareTo de Consulta)
			Collections.sort(consultasMedico);

			LocalDate fechaActual = null;
			double total = 0;

			for (Consulta c : consultasMedico) {
				LocalDate fechaConsulta = c.getFecha();
				if (fechaConsulta != null) // ignorar sin fecha
					if (!(fechaConsulta.isBefore(fechaInicio) || fechaConsulta.isAfter(fechaFin))) { // fuera del rango
																										// pedido
						if (!fechaConsulta.equals(fechaActual)) { // Mostrar la fecha como encabezado cuando cambia
							fechaActual = fechaConsulta;
							sb.append("Fecha: ").append(fechaConsulta).append("\n");
						}
						sb.append(String.format("  Paciente: %-25s  Importe: $%.2f\n", c.getPaciente().getNya(),
								c.getImporte()));

						total += c.getImporte();
					}
			}
			sb.append("\nTotal: $").append(String.format("%.2f", total)).append("\n");
		}
		return sb.toString();
	}
}
