package clinica.model;
/**
 * Representa el domicilio de una persona.
 */
public class Domicilio {
	private String calle;
	private int altura;
	/**
	 * Crea un domicilio con su calle y altura
	 * @param calle La calle donde vive
	 * @param altura La altura sobre la calle en donde vive
	 */
	public Domicilio(String calle, int altura) {
		this.calle = calle;
		this.altura = altura;
	}

	public String getCalle() {
		return calle;
	}

	public int getAltura() {
		return altura;
	}
	
	@Override
	public String toString() {
		return "" + calle + " " + altura;
	}

}
