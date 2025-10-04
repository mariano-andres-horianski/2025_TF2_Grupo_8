package temp;

public class Paciente extends Persona {
	private int nroHC;
	private String rangoEtario;
	private static int siguienteNro = 1;
	private int nroOrden;

	public Paciente(String dni, String nya, String ciudad, String telefono, Domicilio domicilio, int nroHC, String rangoEtario) {
		super(dni, nya, ciudad, telefono, domicilio);
		this.nroHC = nroHC;
		this.rangoEtario = rangoEtario;
		this.nroOrden = siguienteNro++;
	}

	public int getNroHC() {
		return nroHC;
	}

	public String getRangoEtario() {
		return rangoEtario;
	}
	
	

}
