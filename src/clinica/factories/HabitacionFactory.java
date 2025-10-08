package clinica.factories;

import clinica.habitaciones.*;

public class HabitacionFactory {

    public IHabitacion crearHabitacion(String tipo) {
        if (tipo.equalsIgnoreCase("Compartida")) {
            return new HabitacionCompartida();
        }
        if (tipo.equalsIgnoreCase("Privada")) {
            return new HabitacionPrivada();
        }
        if (tipo.equalsIgnoreCase("TerapiaIntensiva")) {
            return new HabitacionTerapiaIntensiva();
        }
        return null;
    }
}
