package clinica.model.decorators;

import clinica.model.IMedico;

public class DecoratorContratacionResidente extends DecoratorContratacion {

	public DecoratorContratacionResidente(IMedico encapsulado) {
		super(encapsulado);
	}
	
	public double getHonorario() {
		return this.encapsulado.getHonorario() * 1.05;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Contratación = residente";
	}
}
