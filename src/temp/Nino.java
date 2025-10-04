package temp;

public class Nino extends Paciente implements IPrioridad{

	public Nino(String dni, String nya, String ciudad, String telefono, Domicilio domicilio, int nroHC, String rangoEtario) {
		super(dni,nya,ciudad,telefono,domicilio,nroHC,rangoEtario);
	}

	@Override
	public IPrioridad prioridadSala(IPrioridad paciente) {
		return paciente.compararConNino(this);
	}

	@Override
	public IPrioridad compararConNino(IPrioridad nino) {
		return nino;
	}

	@Override
	public IPrioridad compararConJoven(IPrioridad joven) {
		return this;
	}

	@Override
	public IPrioridad compararConMayor(IPrioridad mayor) {
		return mayor;
	}

	
}
