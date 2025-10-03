package temp;

import clinica.exceptions.*;
import clinica.factories.*;
import clinica.model.*;

public class Prueba {

	public static void main(String[] args) {
		MedicoFactory MF = new MedicoFactory();

		IMedico m1;
		try {
			m1 = MF.crearMedico("33333333", "Carlitos", "MDP", "344212432", "Alvear", 1350, 55, "Pediatra",
					"permanente", "magister");
			
			System.out.println(m1);
			System.out.println("Honorario = " + m1.getHonorario());
			
		} catch (EspecialidadNotFoundException | ContratacionNotFoundException | PosgradoNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
