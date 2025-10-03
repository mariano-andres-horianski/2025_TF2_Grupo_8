package clinica.model;

public class MClinico extends Especialidad {

	public MClinico() {
		super("Médico clínico");
	}

	@Override
	public double getHonorario(double base) {
		return base * 1.05;
	}

}
