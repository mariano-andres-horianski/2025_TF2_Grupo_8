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
import javax.swing.table.TableColumn;

import controlador.Asociados.ActionListenerAsociados;
import persistencia.DAOAsociadoYDTO.AsociadoDTO;
import vista.RendererAsociados.ButtonRenderer;
import vista.RendererAsociados.*;
import java.awt.Component;

/**
 * Panel (JPanel) que muestra la gestión de Asociados.
 * <p>
 * Contiene una tabla (JTable) con el listado de asociados y un botón para agregar nuevos.
 * Utiliza un ButtonRenderer y ButtonEditor para las acciones de "Editar" y "Eliminar" en la tabla.
 */
public class PanelAsociados extends JPanel{
	private ActionListenerAsociados controladorAsociados;
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JTable tabla;
	private JButton btnAgregar;
	/**
	 * Constructor del panel de gestión de asociados.
     *
     * <p><b>Precondición:</b> 
     * <pre>
     * El parámetro socios no debe ser null (aunque puede estar vacío).
     * El parámetro controladorAsociados no debe ser null.
     * </pre>
     * <p><b>Postcondición:</b> 
     * <pre>
     * El panel se inicializa con un BorderLayout.
     * El botón "Agregar asociado" se añade en la región Norte.
     * El modelo de la tabla modelo se crea y se puebla con los datos de socios.
     * La JTable tabla se inicializa con el modelo.
     * La columna "Editar" (índice 5) y la columna Eliminar (indice 6) se configura con ButtonRenderer y ButtonEditor.
     * <pre>
     * @param socios Un HashMap con los asociados a mostrar inicialmente.
     * @param controladorAsociados El controlador que gestionará los eventos de este panel.
	 */
	public PanelAsociados(HashMap<String, AsociadoDTO> socios, ActionListenerAsociados controladorAsociados) {
		this.controladorAsociados = controladorAsociados;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---- Botón superior
        btnAgregar = new JButton("Agregar asociado");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(btnAgregar);
        add(topPanel, BorderLayout.NORTH);

        // ---- Modelo de tabla
        modelo = new DefaultTableModel(new Object[]{"Nombre y Apellido", "DNI", "Ciudad", "Teléfono", "Domicilio", "Editar","Eliminar"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo las columna de botones son editables
                return column == 5 || column == 6;
            }
        };
        // ---- Cargar datos
        if (socios.isEmpty()) {
            modelo.addRow(new Object[]{"No hay socios registrados.", "", "", "", "", ""});
        } else {
            for (AsociadoDTO s : socios.values()) {
                modelo.addRow(new Object[]{
                    s.getNya(),
                    s.getDni(),
                    s.getCiudad(),
                    s.getTelefono(),
                    s.getDomicilioStr(),
                    null, // columna de boton editar
                    null // columna de boton eliminar
                });
                
            }
        }

        // ---- Crear tabla
        tabla = new JTable(modelo);
        
        
        tabla.getColumn("Editar").setCellRenderer(new ButtonRenderer("Editar"));
        tabla.getColumn("Editar").setCellEditor(new ButtonEditor(tabla, controladorAsociados, "SELECT_UPDATE"));
        tabla.getColumn("Eliminar").setCellRenderer(new ButtonRenderer("X"));
        tabla.getColumn("Eliminar").setCellEditor(new ButtonEditor(tabla, controladorAsociados, "SELECT_DELETE"));
        
        add(new JScrollPane(tabla), BorderLayout.CENTER);
	}
	/**
	 * 
	 * Agrega un MouseListener a la tabla.
	 * @param listener
	 */
	public void addMouseListenerTabla(MouseListener listener) {
        tabla.addMouseListener(listener);
    }
	/**
	 * Obtiene la JTable de asociados.
	 * @return la JTable.
	 */
    public JTable getTabla() {
        return tabla;
    }

    /**
     * Obtiene el modelo de datos de la tabla.
     * @return el DefaultTableModel.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }
    
    /**
     * Obtiene el botón "Agregar asociados".
     * @return el JButton.
     */
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	/**
	 * Refresca (limpia y vuelve a poblar) la JTable con una nueva lista de asociados.
	 *
	 * <p><b>Postcondición:</b>
	 * <pre>
	 * El modelo de la tabla se vacía (setRowCount(0)).
	 * La tabla se repobla con los datos del nuevo socios map.
	 * </pre>
	 * @param socios El HashMap actualizado de AsociadoDTO.
	 */
	public void refrescarTabla(HashMap<String,AsociadoDTO> socios) {
	    modelo.setRowCount(0); // borrar
	    socios.values().forEach(s ->
	        modelo.addRow(new Object[]{
	            s.getNya(), s.getDni(), s.getCiudad(),
	            s.getTelefono(), s.getDomicilioStr()
	        })
	    );
	}

}
