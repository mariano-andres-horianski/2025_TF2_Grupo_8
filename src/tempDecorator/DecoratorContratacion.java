package tempDecorator;

import temp.*;

public abstract class DecoratorContratacion extends DecoratorMedico {

	public DecoratorContratacion(IMedico encapsulado) {
		super(encapsulado);
	}
}
