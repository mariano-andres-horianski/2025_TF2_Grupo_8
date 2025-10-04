package clinica.model;

import java.time.LocalDate;
import clinica.d.dispatch.*;
import java.util.ArrayList;


public abstract class Paciente extends Persona implements IPrioridad{
	private int nroHC;
	private String rangoEtario;
	private LocalDate fechaIngreso;
	private ArrayList<Consulta> consultas;

	public Paciente(String dni, String nya, String ciudad, String telefono, Domicilio domicilio, int nroHC,
			String rangoEtario) {
		super(dni, nya, ciudad, telefono, domicilio);
		this.nroHC = nroHC;
		this.rangoEtario = rangoEtario;
		this.consultas = new ArrayList<>();
	}

	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}

	public void addConsulta(Consulta c) {
		this.consultas.add(c);
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
