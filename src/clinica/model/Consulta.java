package clinica.model;

import java.time.LocalDate;

public class Consulta {
	private IMedico medico;
	private Paciente paciente;
	private double importe;
	private LocalDate fecha;

	public Consulta(IMedico medico, Paciente paciente, LocalDate fecha) {
		super();
		this.medico = medico;
		this.paciente = paciente;
		this.importe = medico.getHonorario() * 1.2; // Se le cobra un 20% arriba del honorario del médico
		this.fecha = fecha;
	}

	public Consulta(IMedico medico, Paciente paciente) {
		super();
		this.medico = medico;
		this.paciente = paciente;
		this.importe = medico.getHonorario() * 1.2;
		this.fecha = null; // La fecha se determina cuando se hace la facturación
	}

	public IMedico getMedico() {
		return medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public double getImporte() {
		return importe;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	
}
