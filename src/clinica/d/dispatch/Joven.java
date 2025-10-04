package clinica.d.dispatch;
import clinica.model.*;

public class Joven extends Paciente implements IPrioridad {

	public Joven(String dni, String nya, String ciudad, String telefono, Domicilio domicilio, int nroHC, String rangoEtario) {
		super(dni,nya,ciudad,telefono,domicilio,nroHC,rangoEtario);
	}

	@Override
	public boolean prioridadSala(IPrioridad paciente) {
		return paciente.compararConJoven();
	}

	@Override
	public boolean compararConNino() {
		return false;
		}

	@Override
	public boolean compararConJoven() {
		return false;
	}

	@Override
	public boolean compararConMayor() {
		return true;
	}

	

}

