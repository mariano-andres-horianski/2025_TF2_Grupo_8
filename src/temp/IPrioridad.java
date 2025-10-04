package temp;
	/**
	 * Esta interfaz se encarga de asegurar la implementacion de los metodos para la solucion de prioridades en la sala de espera 
	 * @return paciente con mayor prioridad segun el siguiente criterio:
	 * 			Entre un Niño y un Joven, la Sala queda para el Niño
	 * 			Entre un Joven y un Mayor, la Sala queda para el Joven
	 * 			Entre un Mayor y un Niño, la Sala queda para el Mayor
	 * 
	 */

public interface IPrioridad {
	public boolean prioridadSala(IPrioridad paciente);
	public boolean compararConNino();
	public boolean compararConJoven();
	public boolean compararConMayor();
}

