package temp;

import clinica.exceptions.*;
import clinica.factories.*;
import clinica.model.*;
import clinica.habitaciones.*;
import java.time.LocalDate;

import clinica.*;

public class Prueba {

	 public static void main(String[] args) {
	        try {
	        	
	        	HabitacionFactory habitacionFactory=new HabitacionFactory();
	        	IHabitacion habitacion1=habitacionFactory.crearHabitacion("Privada");
	            // ----------------- Inicializo clínica -----------------
	            SingletonClinica clinica = SingletonClinica.getInstance();

	            // ----------------- Creo médicos con Factory -----------------
	            MedicoFactory factory = new MedicoFactory();
	            PacienteFactory pacienteFactory = new PacienteFactory();
	            IMedico medico1 = factory.crearMedico("111", "Carlitos", "MDQ", "2231111111", "Av JBJ", 742, 1001, "clinico", "residente", "doctorado");
	            IMedico medico2 = factory.crearMedico("222", "Cacho", "MDQ", "2232222222", "Av Libertad", 123, 1002, "cirujano", "permanente");

	            clinica.registrarMedico(medico1);
	            clinica.registrarMedico(medico2);

	            // ----------------- Creo paciente -----------------
	            Domicilio domicilioPaciente = new Domicilio("Calle Falsa", 123);
	            Paciente paciente1 = pacienteFactory.crearPaciente("333", "Juan Perez", "MDQ", "2233333333", domicilioPaciente, 1, "Mayor");


	            clinica.registrarPaciente(paciente1);

	            // ----------------- Ingreso paciente -----------------
	            clinica.ingresaPaciente(paciente1);

	            // ----------------- Atiendo paciente varias veces -----------------
	            clinica.atiendePaciente(medico1, paciente1);
	            clinica.atiendePaciente(medico2, paciente1);
	            paciente1.setFechaIngreso(LocalDate.of(2025, 9, 03));  // Pruebo fecha de ingreso hace un mes
	            clinica.internaPaciente(paciente1,habitacion1);
	            // ----------------- Egreso paciente y genero factura -----------------
	            Factura factura = clinica.egresaPaciente(paciente1);

	            // ----------------- Muestro factura -----------------
	            System.out.println(factura);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
