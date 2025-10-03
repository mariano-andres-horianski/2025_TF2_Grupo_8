package clinica.model.decorators;

import clinica.model.IMedico;

public abstract class DecoratorPosgrado extends DecoratorMedico {

	public DecoratorPosgrado(IMedico encapsulado) {
		super(encapsulado);
	}
}
