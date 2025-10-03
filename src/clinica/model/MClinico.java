package clinica.model;

public class MClinico extends Especialidad {

	public MClinico() {
		super("Clínico");
	}

	@Override
	public double getHonorario(double base) {
		return base * 1.05;
	}

}
