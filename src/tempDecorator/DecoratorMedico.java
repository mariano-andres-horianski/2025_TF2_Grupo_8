package tempDecorator;

import temp.Domicilio;
import temp.Especialidad;
import temp.IMedico;

public abstract class DecoratorMedico implements IMedico {
	protected IMedico encapsulado;

	public DecoratorMedico(IMedico encapsulado) {
		this.encapsulado = encapsulado;
	}

	@Override
	public String getDni() {
		return this.encapsulado.getDni();
	}

	@Override
	public String getNya() {
		return this.encapsulado.getNya();
	}

	@Override
	public String getCiudad() {
		return this.encapsulado.getCiudad();
	}

	@Override
	public String getTelefono() {
		return this.encapsulado.getTelefono();
	}

	@Override
	public Domicilio getDomicilio() {
		return this.encapsulado.getDomicilio();
	}

	public int getNroMat() {
		return this.encapsulado.getNroMat();
	}

	public Especialidad getEspecialidad() {
		return this.encapsulado.getEspecialidad();
	}
}
