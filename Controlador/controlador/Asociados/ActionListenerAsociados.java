package controlador.Asociados;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import persistencia.BasedeDatos.BDConexion;
import persistencia.DAOAsociadoYDTO.AsociadoDAOMySQL;
import persistencia.DAOAsociadoYDTO.AsociadoDTO;
import persistencia.Excepciones.AsociadoExistenteException;
import persistencia.Excepciones.AsociadoNotFoundException;
import persistencia.Excepciones.DatoInvalidoException;
import vista.JframePrincipal.VentanaPrincipal;
import vista.PanelCentral.PanelAsociados;
import vista.PanelCentral.PanelSimulacion;
import vista.formularios.ConfirmDialog;
import vista.formularios.FormularioCreateAsociado;
import vista.formularios.FormularioUpdateAsociado;

public class ActionListenerAsociados implements ActionListener {
	private VentanaPrincipal ventanaPrincipal;
	private AsociadoDAOMySQL BD;
	
	public ActionListenerAsociados() {
		try {
			this.BD = new AsociadoDAOMySQL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaPrincipal.setControladorAsociados(this);
		this.ventanaPrincipal.setVisible(true);
		this.ventanaPrincipal.getBoton_navegacionAsociados().addMouseListener(new ReadListenerAsociados(this));
		this.ventanaPrincipal.getBoton_navegacionInicio().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand().toUpperCase();
		HashMap<String, AsociadoDTO> asociados;
		PanelAsociados listado;
		JPanel panelCentral;
		CardLayout cl;
		switch(comando) {
			case "INICIO":
				JPanel panelInicio = new JPanel();
			    panelInicio.add(new JLabel("Bienvenido al sistema"));

			    panelCentral = ventanaPrincipal.getPanel_Central();

			    panelCentral.add(panelInicio, "PANEL_INICIO");

			    cl = (CardLayout) panelCentral.getLayout();
			    cl.show(panelCentral, "PANEL_INICIO");

			    panelCentral.revalidate();
			    panelCentral.repaint();
			    break;

			case "CREATE"://el usuario hizo click en "guardar" en el formulario de Agregar asociado
				AsociadoDTO nuevoSocio = (AsociadoDTO)e.getSource();
				
				try {
					BD.agregar(nuevoSocio);
				} catch (AsociadoExistenteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				asociados = BD.obtenerTodosMap();
				listado = (PanelAsociados)this.ventanaPrincipal.getPanel_Central();
				listado.refrescarTabla(asociados);
				break;
			case "READ"://el usuario hizo click en el boton "asociados" para modificar el card layout, no hay pop up
				//Modificar el panel central de la ventana principal para que contenga una lista de asociados
				asociados= BD.obtenerTodosMap();
				panelCentral = this.ventanaPrincipal.getPanel_Central();
				listado = new PanelAsociados(asociados,this);//me falta pasarle los listeners
				this.ventanaPrincipal.setPanel_Central(listado);
				listado.getBtnAgregar().addMouseListener(new CreateListenerAsociados(this));
				
				String nombrePanel = "PANEL_ASOCIADOS";
	            panelCentral.add(listado, nombrePanel);
	            ///Creo un panel de cero y lo guardo en el panel central
	            cl = (CardLayout) (panelCentral.getLayout());
	            cl.show(panelCentral, nombrePanel);
	            panelCentral.revalidate();
	            panelCentral.repaint();
				break;
			case "UPDATE":
				AsociadoDTO socio = (AsociadoDTO) e.getSource();
				
				try {
					BD.actualizar(socio);
				} catch (AsociadoNotFoundException e1) {
					e1.printStackTrace();
				}
				asociados = BD.obtenerTodosMap();
				listado = (PanelAsociados)this.ventanaPrincipal.getPanel_Central();
				listado.refrescarTabla(asociados);
				
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
				asociados = BD.obtenerTodosMap();
				listado = (PanelAsociados)this.ventanaPrincipal.getPanel_Central();
				listado.refrescarTabla(asociados);
				break;
			case "SELECT_UPDATE":
				FormularioUpdateAsociado form = new FormularioUpdateAsociado((AsociadoDTO)e.getSource(),this);
				form.setLocationRelativeTo(null);
	            form.setVisible(true);
	            
				break;
			case "SELECT_DELETE":
				ConfirmDialog popUpEliminar = new ConfirmDialog(null,
					    "¿Está seguro que desea eliminar este asociado?",
					    "DELETE",
					    this,
					    (AsociadoDTO)e.getSource()
					);
				popUpEliminar.setVisible(true);
				break;
		}
		
	}


	
}
