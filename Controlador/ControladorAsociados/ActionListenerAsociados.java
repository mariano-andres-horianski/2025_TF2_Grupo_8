package ControladorAsociados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import persistencia.BasedeDatos.BDConexion;
import persistencia.DAOAsociadoYDTO.AsociadoDAOMySQL;
import persistencia.DAOAsociadoYDTO.AsociadoDTO;
import persistencia.Excepciones.AsociadoExistenteException;
import persistencia.Excepciones.AsociadoNotFoundException;
import persistencia.Excepciones.DatoInvalidoException;
import vista.JframePrincipal.VentanaPrincipal;
import vista.PanelCentral.PanelAsociados;
import vista.formularios.FormularioCreateAsociado;

public class ActionListenerAsociados implements ActionListener {
	private FormularioCreateAsociado formulario; //va a haber un formulario distinto para cada accion, por lo que se extraeran distintos datos de cada uno
	private VentanaPrincipal ventanaPrincipal;
	private AsociadoDAOMySQL BD;
	
	public ActionListenerAsociados() {
		try {
			this.BD = new AsociadoDAOMySQL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public FormularioCreateAsociado getFormulario() {
		return formulario;
	}

	
	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaPrincipal.setControladorAsociados(this);
	}

	public void setFormulario(FormularioCreateAsociado formulario) {
		this.formulario = formulario;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand().toUpperCase();
		
		switch(comando) {
			case "CREATE":
				AsociadoDTO nuevoSocio = new AsociadoDTO();
				
				nuevoSocio.setNya(this.formulario.getTextNYA().getText());
				nuevoSocio.setDni(this.formulario.getTextDNI().getText());
				nuevoSocio.setCiudad(this.formulario.getTextCiudad().getText());
				nuevoSocio.setTelefono(this.formulario.getTextTelefono().getText());
				nuevoSocio.setDomicilioStr(this.formulario.getTextDomicilio().getText());
				
				try {
					BD.agregar(nuevoSocio);
				} catch (AsociadoExistenteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "READ"://el usuario hizo click en el boton "asociados" para modificar el card layout, no hay pop up
				//Modificar el panel central de la ventana principal para que contenga una lista de asociados
				HashMap<String, AsociadoDTO> asociados= BD.obtenerTodosMap();
				PanelAsociados listado = new PanelAsociados(asociados,this);//me falta pasarle los listeners
				this.ventanaPrincipal.setPanel_Central(listado);
				break;
			case "UPDATE":
				break;
			case "DELETE":
				AsociadoDTO socioEliminado = (AsociadoDTO)e.getSource();
				String DNI = socioEliminado.getDni();
				
				try {
					BD.eliminarPorDni(DNI);
				} catch (AsociadoNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
		}
		
	}
	
}
