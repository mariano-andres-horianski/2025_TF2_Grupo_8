package clinica.model;

import java.util.ArrayList;

public interface IMedico extends IPersona {
	public int getNroMat();

	public Especialidad getEspecialidad();

	public double getHonorario();
	
	public ArrayList<Consulta> getConsultas();
	
	public void addConsulta(Consulta c);
}
