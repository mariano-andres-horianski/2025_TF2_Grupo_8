package clinica.model.decorators;

import clinica.model.IMedico;

public class DecoratorContratacionPermanente extends DecoratorContratacion {

	public DecoratorContratacionPermanente(IMedico encapsulado) {
		super(encapsulado);
	}
	
	public double getHonorario() {
		return this.encapsulado.getHonorario() * 1.1;
	}
}
