package clinica;

import clinica.exceptions.*;
import clinica.d.dispatch.*;
import clinica.habitaciones.*;

import clinica.model.*;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;

public class SingletonClinica {
	private String nombre, direccion, telefono, ciudad;
	private static SingletonClinica instance;
	private HashMap<String, Paciente> pacientes;
	private HashMap<String, IMedico> medicos;
	private HashMap<Paciente, IHabitacion> internados;
	private ArrayList<Paciente> listaEspera;
	private ArrayList<Paciente> listaEnAtencion;
	
	private ArrayList<IPrioridad> patio;
	private SalaEspera salaEspera;

	private SingletonClinica() {
		ciudad = "Mar del Plata";
		direccion = "Avenida Siempreviva 123";
		telefono = "22300000";
		nombre = "Clinica Colon";
		pacientes = new HashMap<String, Paciente>();
		medicos = new HashMap<String, IMedico>();
		internados = new HashMap<>();

		listaEspera = new ArrayList<Paciente>();
		listaEnAtencion = new ArrayList<Paciente>();
		
		patio = new ArrayList<IPrioridad>();
		salaEspera= new SalaEspera();
		salaEspera.desocupar();
	}

	public static SingletonClinica getInstance() {
		if (instance == null) {
			instance = new SingletonClinica();
		}

		return instance;
	}

	public void registrarMedico(IMedico m) {
		this.medicos.put(m.getDni(), m);
	}

	public void registrarPaciente(Paciente p) {
		this.pacientes.put(p.getDni(), p);
	}

	public void addListaEspera(Paciente p) {
		this.listaEspera.add(p);
	}
	
	public void removeListaEspera(Paciente p) {
		this.listaEspera.remove(p);
	}
	
	public void addPatio(IPrioridad p) {
		this.patio.add(p);
	}
	
	public void removePatio(IPrioridad p) {
		this.patio.remove(p);
	}
	public void addListaEnAtencion(Paciente p) {
		this.listaEnAtencion.add(p);
	}

	public void removeListaEnAtencion(Paciente p) {
		this.listaEnAtencion.remove(p);
	}

	public void ingresaPaciente(Paciente p) throws PacienteNotFoundException {
		if (!pacientes.containsKey(p.getDni()))
			throw new PacienteNotFoundException("Paciente no registrado");

		addListaEspera(p);

		if (!(salaEspera.isOcupacion())) 
			salaEspera.ocuparSala(p);
		else
			if (!(p.prioridadSala(salaEspera.getPaciente()))) {
				addPatio(salaEspera.getPaciente());
				salaEspera.ocuparSala(p);
			}
			else
				addPatio(p);
	}
	public void atiendePaciente(IMedico m, Paciente p) {
		if (this.listaEspera.contains(p)) {
			removeListaEspera(p);
			if (this.patio.contains(p))
				removePatio(p);
			else {
				salaEspera.desocupar();
				if (!patio.isEmpty()) {
					salaEspera.ocuparSala(patio.get(0));
					removePatio(salaEspera.getPaciente());
				}
				
			}

			addListaEnAtencion(p);
			p.setFechaIngreso(LocalDate.now());
		}

		Consulta c = new Consulta(m, p);
		m.addConsulta(c);
		p.addConsulta(c);

	}

	public Factura egresaPaciente(Paciente p) {
		IHabitacion h = internados.get(p); //si no fue internado retorna null
        Factura f = new Factura(p, h);
        internados.remove(p);
        removeListaEnAtencion(p);
        return f;
	}
	
	public void internaPaciente(Paciente paciente,IHabitacion habitacion) throws PacienteNotFoundException{
		if (!pacientes.containsKey(paciente.getDni()))
			throw new PacienteNotFoundException("Paciente no registrado");
		internados.put(paciente, habitacion);
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

}
