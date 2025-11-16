package vista.RendererAsociados;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import controlador.Asociados.ActionListenerAsociados;
import persistencia.DAOAsociadoYDTO.AsociadoDTO;
import persistencia.DAOAsociadoYDTO.AsociadoDAOMySQL;
/**
 * Editor de celda (TableCellEditor) para los botones de acción en la JTable.
 * <p>
 * Esta clase es responsable de manejar la <b>interacción</b> (clics) con los botones.
 * Cuando un usuario hace clic en la celda, la JTable activa este editor.
 * <p>
 * Al recibir el clic, esta clase:
 * 	1. Identifica la fila seleccionada.
 * 	2. Extrae los datos de las celdas de esa fila (DNI, Nombre, etc.).
 * 	3. Construye un AsociadoDTO con esos datos.
 * 	4. Dispara un  ActionEvent hacia el controlador, pasando el DTO y el comando de acción.
 *	5. Detiene la edición de la celda.
 */
public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton btnEditar;
    private JTable tabla;
    private AsociadoDAOMySQL BD;
    
    /**
	 * Constructor del ButtonEditor.
	 * <p><b>Precondición:</b> tabla y controlador no deben ser nulos.
	 * <p><b>Postcondición:</b>
	 * <pre>
	 *  Se crea el panel y el botón. El texto del botón se
	 * define según el parámetro accion.
	 * Se registra un ActionListener en el botón para  capturar el clic, crear el DTO y notificar al controlador.
	 * </pre>
	 * @param tabla La JTable a la que este editor pertenece (para
	 * poder leer los datos de la fila).
	 * @param controlador El controlador que recibirá los eventos de acción.
	 * @param accion  El ActionCommand que se enviará al controlador. 
	 */
    public ButtonEditor(JTable tabla, ActionListenerAsociados controlador, String accion) {
        this.tabla = tabla;
        

        btnEditar = new JButton(accion.equals("SELECT_DELETE") ? "X" : "Editar");
        
        //para que no los redimensione al clickearlos
        btnEditar.setPreferredSize(new Dimension(60, 15));
        
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.add(btnEditar);
        btnEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                AsociadoDTO socio = new AsociadoDTO();
                socio.setNya((String) tabla.getValueAt(fila, 0));
                socio.setDni((String) tabla.getValueAt(fila, 1));
                socio.setCiudad((String)tabla.getValueAt(fila, 2));
                socio.setTelefono((String)tabla.getValueAt(fila, 3));
                socio.setDomicilioStr((String)tabla.getValueAt(fila, 4));
                
                controlador.actionPerformed(new ActionEvent(socio, ActionEvent.ACTION_PERFORMED, accion));
            }
            fireEditingStopped();
        });
        
    }
    /**
     * Devuelve el componente (el panel con el botón) que se mostrará cuando la celda entre en modo de edición. 
	 * @return El JPanel que contiene el botón interactivo.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return panel;
    }
    /**
     * Devuelve el valor de la celda después de la edición. 
	 * @return null siempre. Este editor no modifica el valor de la celda, sino que ejecuta una acción lateral (notificar al controlador).
     */
    @Override
    public Object getCellEditorValue() {
        return null;
    }
    
    public JTable getTabla() {
    	return this.tabla;
    }
}
