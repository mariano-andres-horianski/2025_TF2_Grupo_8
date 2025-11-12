package vista.PanelCentral;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ControladorAsociados.ActionListenerAsociados;
import persistencia.DAOAsociadoYDTO.AsociadoDTO;
public class PanelAsociados extends JPanel{
	private ActionListenerAsociados controladorAsociados;
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JTable tabla;
	/**
	 * Create the panel.
	 */
	public PanelAsociados(HashMap<String, AsociadoDTO> socios, ActionListenerAsociados controladorAsociados) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Alinear verticalmente
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.controladorAsociados = controladorAsociados;
        this.modelo = new DefaultTableModel(new Object[]{"Nombre y Apellido", "DNI","Ciudad","Telefono","Domicilio"}, 0);
        ActionEvent agregar;
        JButton btnNewButton = new JButton("Agregar asociado");
        add(btnNewButton);

        if (socios.isEmpty()) {
            add(new JLabel("No hay socios registrados."));
            return;
        }
        socios.values().forEach(s -> 
        	modelo.addRow(new Object[]{s.getNya(), s.getDni(),s.getCiudad(),s.getTelefono(),s.getDomicilioStr()})
        );
        this.tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
	}
	
	public void addMouseListenerTabla(MouseListener listener) {
        tabla.addMouseListener(listener);
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }
}
