package negocio;

import clinica.SingletonClinica;

public class Operario implements Runnable {

    private SingletonClinica clinica;

    public Operario(SingletonClinica clinica) {
		this.clinica = clinica;
	}

	public void solicitarMantenimientoAmbulancia() {
        System.out.println("Operario solicita mantenimiento");
        clinica.getAmbulancia().solicitarMantenimiento();
    }

	@Override
	public void run() {
		solicitarMantenimientoAmbulancia();
	}
	
}