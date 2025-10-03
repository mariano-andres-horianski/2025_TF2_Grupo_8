package clinica.model;

public class MPediatra extends Especialidad {

	public MPediatra() {
		super("Pediatra");
	}

	@Override
	public double getHonorario(double base) {
		return base * 1.07;
	}

}
