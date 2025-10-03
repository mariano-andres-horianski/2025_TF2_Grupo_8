package clinica.model;

public class MCirujano extends Especialidad {

	public MCirujano() {
		super("Médico cirujano");
	}

	@Override
	public double getHonorario(double base) {
		return base * 1.1;
	}

}
