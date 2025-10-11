package clinica.model.decorators;

import clinica.model.IMedico;
/**
 * Decorator que añade la contratación del médico
 */
public abstract class DecoratorContratacion extends DecoratorMedico {

	public DecoratorContratacion(IMedico encapsulado) {
		super(encapsulado);
	}
}
