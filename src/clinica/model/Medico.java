package clinica.model;

import java.util.ArrayList;

public class Medico extends Persona implements IMedico {
	public static final double honorarioBase = 20000;
	private int nroMat;
	private Especialidad especialidad;
	private ArrayList<Consulta> consultas;

	public Medico(String dni, String nya, String ciudad, String telefono, Domicilio domicilio, int nroMat,
			Especialidad especialidad) {
		super(dni, nya, ciudad, telefono, domicilio);
		this.nroMat = nroMat;
		this.especialidad = especialidad;
		this.consultas = new ArrayList<>();
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

	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}
	
	public void addConsulta(Consulta c) {
		this.consultas.add(c);
	}

	@Override
	public String toString() {
		return "Nombre Médico: " + this.getNya() + "        Especialidad: " + this.especialidad;
	}

}