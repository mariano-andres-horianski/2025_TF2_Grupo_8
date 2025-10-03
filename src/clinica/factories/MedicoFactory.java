package clinica.factories;

import clinica.exceptions.*;
import clinica.model.*;
import clinica.model.decorators.*;

public class MedicoFactory {

	// Médico base
	public IMedico crearMedico(String dni, String nya, String ciudad, String telefono, String calle, int altura,
			int nroMat, String especialidad) throws EspecialidadNotFoundException {

		Domicilio d = new Domicilio(calle, altura);

		Especialidad e = null;

		if (especialidad.equalsIgnoreCase("cirujano"))
			e = new MCirujano();
		else if (especialidad.equalsIgnoreCase("clinico"))
			e = new MClinico();
		else if (especialidad.equalsIgnoreCase("pediatra"))
			e = new MPediatra();
		else
			throw new EspecialidadNotFoundException("Especialidad no encotrada");

		return new Medico(dni, nya, ciudad, telefono, d, nroMat, e);
	}

	// Médico sin posgrado pero con contratación
	public IMedico crearMedico(String dni, String nya, String ciudad, String telefono, String calle, int altura,
			int nroMat, String especialidad, String contratacion)
			throws EspecialidadNotFoundException, ContratacionNotFoundException {

		IMedico medico = crearMedico(dni, nya, ciudad, telefono, calle, altura, nroMat, especialidad);

		// contratación
		if (contratacion.equalsIgnoreCase("residente")) {
			medico = new DecoratorContratacionResidente(medico);
		} else if (contratacion.equalsIgnoreCase("permanente")) {
			medico = new DecoratorContratacionPermanente(medico);
		} else
			throw new ContratacionNotFoundException("Contratación no encontrada");

		return medico;
	}

	// Médico con posgrado y contratación
	public IMedico crearMedico(String dni, String nya, String ciudad, String telefono, String calle, int altura,
			int nroMat, String especialidad, String contratacion, String posgrado)
			throws EspecialidadNotFoundException, ContratacionNotFoundException, PosgradoNotFoundException {

		IMedico medico = crearMedico(dni, nya, ciudad, telefono, calle, altura, nroMat, especialidad, contratacion);

		// posgrado
		if (posgrado.equalsIgnoreCase("magister")) {
			medico = new DecoratorPosgradoMagister(medico);
		} else if (posgrado.equalsIgnoreCase("doctorado")) {
			medico = new DecoratorPosgradoDoctorado(medico);
		} else
			throw new PosgradoNotFoundException("Posgrado no encontrado");

		return medico;
	}
}