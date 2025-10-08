package clinica.model;
import java.util.ArrayList;
import clinica.habitaciones.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Factura {
	private static int contador = 1; // autoincremental
	private final static double costoPorInternacion= 500;

	private int numero;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;
    private Paciente paciente;
    private IHabitacion habitacion;
    private double total; 
    private long cantidadDias;
    private ArrayList<Consulta> consultas;             // importe total

    public Factura(Paciente paciente, IHabitacion habitacion) {
        this.numero = contador++;
        this.fechaEgreso = LocalDate.now();
        this.paciente = paciente;
        this.total = calcularTotal();
        this.cantidadDias = calcularDias();
        this.habitacion = habitacion;
        setFechaConsultas();
    }

    private double calcularTotal() {
        double suma = 0;
        for (Consulta c : paciente.getConsultas()) {
            suma += c.getImporte();
        }
        if (habitacion!=null)
        	suma+= habitacion.calcularCosto(cantidadDias)+costoPorInternacion;
        return suma;
    }
    
    private void setFechaConsultas() {
    	for (Consulta c : paciente.getConsultas())
    		c.setFecha(fechaEgreso);
    }
    
    private long calcularDias() {
    	long dias = ChronoUnit.DAYS.between(paciente.getFechaIngreso(), fechaEgreso);
        return dias;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("N° Factura: ").append(numero).append("\n");
        sb.append("Nombre Paciente: ").append(paciente.getNya()).append("\n");
        sb.append("Fecha Ingreso: ").append(paciente.getFechaIngreso()).append("\n");
        sb.append("Fecha Egreso: ").append(fechaEgreso).append("\n");
        sb.append("Cantidad de dias: ").append(cantidadDias).append("\n");
        
        if (habitacion != null) {
            sb.append("Habitación tipo: ").append(habitacion.getTipo())
              .append("                        Costo: $")
              .append(habitacion.calcularCosto(cantidadDias))
              .append("\n\n");
        }
        sb.append("\nConsultas Medicas:\n");

        for (Consulta c : paciente.getConsultas()) {
            sb.append(c.getMedico()).append("   Subtotal: $").append(c.getImporte()).append("\n");
        }

        sb.append("\nTotal: $").append(total).append("\n");
        return sb.toString();
    }
    
    
}

