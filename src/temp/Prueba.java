package temp;

import tempDecorator.*;

public class Prueba {

	public static void main(String[] args) {
		IMedico m1 = new Medico("36798291", "Carlitos", "MDP", "344221123", new Domicilio("Alvear", 1555), 22, new MCirujano());
		
		System.out.println("Honorario = " + m1.getHonorario());
		
		IMedico m1Pos = new DecoratorPosgradoDoctorado(m1);
		
		System.out.println("Honorario = " + m1Pos.getHonorario());
		
		IMedico m1PosCont = new DecoratorContratacionPermanente(m1Pos);
		
		System.out.println("Honorario = " + m1PosCont.getHonorario());

	}

}
