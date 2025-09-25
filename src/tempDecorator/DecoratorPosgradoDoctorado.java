package tempDecorator;

import temp.*;

public class DecoratorPosgradoDoctorado extends DecoratorPosgrado {

	public DecoratorPosgradoDoctorado(IMedico encapsulado) {
		super(encapsulado);
	}
	
	public double getHonorario() {
		return this.encapsulado.getHonorario() * 1.1;
	}
}
