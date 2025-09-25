package temp;

public class Paciente extends Persona {
	private int nroHC;
	private String rangoEtario;

	public Paciente(String dni, String nya, String ciudad, String telefono, Domicilio domicilio, int nroHC, String rangoEtario) {
		super(dni, nya, ciudad, telefono, domicilio);
		this.nroHC = nroHC;
		this.rangoEtario = rangoEtario;
	}

	public int getNroHC() {
		return nroHC;
	}

	public String getRangoEtario() {
		return rangoEtario;
	}

}
