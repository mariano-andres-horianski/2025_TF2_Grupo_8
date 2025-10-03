package clinica.model.decorators;

import clinica.model.IMedico;

public class DecoratorPosgradoDoctorado extends DecoratorPosgrado {

	public DecoratorPosgradoDoctorado(IMedico encapsulado) {
		super(encapsulado);
	}
	
	public double getHonorario() {
		return this.encapsulado.getHonorario() * 1.1;
	}
	
}
