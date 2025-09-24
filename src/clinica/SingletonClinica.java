package clinica;

public class SingletonClinica {
	public String nombre,direccion,telefono,ciudad;
	private SingletonClinica instance;
	private SingletonClinica(){
		ciudad = "Mar del Plata";
		direccion = "Avenida Siempreviva 123";
		telefono = "22300000";
		nombre = "Clínica Colón";
	}
	
	public SingletonClinica getInstance(){
		if (instance == null){
			this.instance = new SingletonClinica();
		}
		
		return this.instance;
	}
}
