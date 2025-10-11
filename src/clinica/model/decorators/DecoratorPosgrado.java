package clinica.model.decorators;

import clinica.model.IMedico;
/**
 * Decorator que añade el posgrado del médico
 */
public abstract class DecoratorPosgrado extends DecoratorMedico {

	public DecoratorPosgrado(IMedico encapsulado) {
		super(encapsulado);
	}
}
