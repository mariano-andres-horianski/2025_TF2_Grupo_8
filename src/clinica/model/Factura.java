package clinica.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Factura {
    private static int contador = 1; // autoincremental

    private int numero;
	private LocalDate fechaEgreso;
    private Paciente paciente;
    // private Sala sala;                  // salas aún no implementadas
    private double total;               // importe total
    private long cantidadDias;

    public Factura(Paciente paciente) {
        this.numero = contador++;
        this.fechaEgreso = LocalDate.now();
        this.paciente = paciente;
        this.total = calcularTotal();
        this.cantidadDias = calcularDias();
        setFechaConsultas();
    }

    private double calcularTotal() {
        double suma = 0;
        for (Consulta c : paciente.getConsultas()) {
            suma += c.getImporte();
        }
        // Sólo suma las consultas, faltan salas
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
        sb.append("Cantidad de días: ").append(cantidadDias).append("\n");
        // sala aún no implementada
        // sb.append("Habitación tipo (si corresponde): XXXXXXXX  Costo: $0\n");
        sb.append("\nConsultas Médicas:\n");

        for (Consulta c : paciente.getConsultas()) {
            sb.append(c.getMedico()).append("   Subtotal: $").append(c.getImporte()).append("\n");
        }

        sb.append("\nTotal: $").append(total).append("\n");
        return sb.toString();
    }
    
    
}

