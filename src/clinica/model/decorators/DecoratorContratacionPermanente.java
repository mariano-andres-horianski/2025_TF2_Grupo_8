package clinica.model.decorators;

import clinica.model.IMedico;
/**
 * Decorator que le asigna contratación permanente al médico
 * <p>Aumenta sus honorarios en 10%</p>
 */
public class DecoratorContratacionPermanente extends DecoratorContratacion {

	public DecoratorContratacionPermanente(IMedico encapsulado) {
		super(encapsulado);
	}
	
	public double getHonorario() {
		return this.encapsulado.getHonorario() * 1.1;
	}
}
