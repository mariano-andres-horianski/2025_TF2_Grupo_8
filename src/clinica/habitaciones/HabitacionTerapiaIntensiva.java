package clinica.habitaciones;

public class HabitacionTerapiaIntensiva implements IHabitacion {
    private static final double costoDia = 5000;

    @Override
    public double calcularCosto(int dias) {
        return Math.pow(costoDia, dias);
    }

    @Override
    public String getTipo() {
        return "Terapia intensiva";
    }
}
