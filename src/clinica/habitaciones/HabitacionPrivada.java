package clinica.habitaciones;

public class HabitacionPrivada implements IHabitacion {
    private static final double costoDia = 2000; // podés ajustar

    @Override
    public double calcularCosto(int dias) {
    	double costo=0;
        if (dias == 1) {
            costo=costoDia;
        } 
        else 
        	if (dias <= 5) {
        		costo= dias * costoDia * 1.3;
        	} 
        	else {
        		costo=  dias * costoDia * 2;
        	}
        return costo;
    }

    @Override
    public String getTipo() {
        return "Habitación privada";
    }
}
