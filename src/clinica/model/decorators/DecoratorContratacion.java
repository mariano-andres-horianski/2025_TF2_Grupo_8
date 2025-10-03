package clinica.model.decorators;

import clinica.model.IMedico;

public abstract class DecoratorContratacion extends DecoratorMedico {

	public DecoratorContratacion(IMedico encapsulado) {
		super(encapsulado);
	}
}
