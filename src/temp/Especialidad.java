package temp;

public abstract class Especialidad {
	private String nombre; // de la especialidad

	public Especialidad(String nombre) {
		this.nombre = nombre;
	}

	public abstract double getHonorario(double base);

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "" + nombre;
	}

	
}
