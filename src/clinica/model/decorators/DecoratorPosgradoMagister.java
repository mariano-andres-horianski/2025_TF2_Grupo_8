package clinica.model.decorators;

import clinica.model.IMedico;
/**
 * Decorator que añade le da un magister a un médico
 * <p>Añade 5% al honorario del médico.</p>
 */
public class DecoratorPosgradoMagister extends DecoratorPosgrado {

	public DecoratorPosgradoMagister(IMedico encapsulado) {
		super(encapsulado);
	}
	
	public double getHonorario() {
		return this.encapsulado.getHonorario() * 1.05;
	}
}
