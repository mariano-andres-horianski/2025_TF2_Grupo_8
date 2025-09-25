package tempDecorator;

import temp.*;

public class DecoratorPosgradoMagister extends DecoratorPosgrado {

	public DecoratorPosgradoMagister(IMedico encapsulado) {
		super(encapsulado);
	}
	
	public double getHonorario() {
		return this.encapsulado.getHonorario() * 1.05;
	}
}
