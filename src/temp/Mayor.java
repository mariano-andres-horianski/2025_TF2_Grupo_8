package temp;

public class Mayor extends Paciente implements IPrioridad {

	public Mayor(String dni, String nya, String ciudad, String telefono, Domicilio domicilio, int nroHC, String rangoEtario) {
		super(dni, nya, ciudad, telefono, domicilio, nroHC, rangoEtario);
	}

	@Override
	public IPrioridad prioridadSala(IPrioridad paciente) {
		return paciente.compararConMayor(this);
	}

	@Override
	public IPrioridad compararConNino(IPrioridad nino) {
		return this;
	}

	@Override
	public IPrioridad compararConJoven(IPrioridad joven) {
		return joven;
	}

	@Override
	public IPrioridad compararConMayor(IPrioridad mayor) {
		return mayor;
	}

}
