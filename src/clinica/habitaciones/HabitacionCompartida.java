package clinica.habitaciones;

public class HabitacionCompartida implements IHabitacion {
    private static final double costoDia = 1000; 
    
    @Override
    public double calcularCosto(int dias) {
        return dias * costoDia;
    }

    @Override
    public String getTipo() {
        return "Habitación compartida";
    }
}
