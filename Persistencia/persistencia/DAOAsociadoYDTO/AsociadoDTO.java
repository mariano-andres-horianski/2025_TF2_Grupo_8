package persistencia.DAOAsociadoYDTO;

import java.io.Serializable;

/**
 * DTO que representa el registro en la tabla 'asociados'.
 * "desmenuza" los atributos de un Asociado para poder utilizarlos en el DAO
 */
public class AsociadoDTO implements Serializable{
    private String dni;
    private String nya;
    private String ciudad;
    private String telefono;
    private String domicilioStr;

    public String getDni() { 
    	return dni; 
    }
    public void setDni(String dni) {
    	this.dni = dni; 
    }
    public String getNya() { 
    	return nya; 
    }
    public void setNya(String nya) { 
    	this.nya = nya; 
    }
    public String getCiudad() { 
    	return ciudad; 
    }
    public void setCiudad(String ciudad) { 
    	this.ciudad = ciudad; 
    }
    public String getTelefono() { 
    	return telefono; 
    }
    public void setTelefono(String telefono) { 
    	this.telefono = telefono; 
    }
    public String getDomicilioStr() { 
    	return domicilioStr; 
    }
    public void setDomicilioStr(String domicilioStr) { 
    	this.domicilioStr = domicilioStr;
    }
}
