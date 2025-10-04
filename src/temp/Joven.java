package temp;

public class Joven extends Paciente implements IPrioridad {

	public Joven(String dni, String nya, String ciudad, String telefono, Domicilio domicilio, int nroHC, String rangoEtario) {
		super(dni,nya,ciudad,telefono,domicilio,nroHC,rangoEtario);
	}

	@Override
	public IPrioridad prioridadSala(IPrioridad paciente) {
		return paciente.compararConJoven(this);
	}

	@Override
	public IPrioridad compararConNino(IPrioridad nino) {
		return nino;
	}

	@Override
	public IPrioridad compararConJoven(IPrioridad joven) {
		return joven;
	}

	@Override
	public IPrioridad compararConMayor(IPrioridad mayor) {
		return this;
	}

	

}
