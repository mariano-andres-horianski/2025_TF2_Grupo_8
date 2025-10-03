package clinica.model.decorators;

import clinica.model.IMedico;

public class DecoratorPosgradoMagister extends DecoratorPosgrado {

	public DecoratorPosgradoMagister(IMedico encapsulado) {
		super(encapsulado);
	}
	
	public double getHonorario() {
		return this.encapsulado.getHonorario() * 1.05;
	}
}
