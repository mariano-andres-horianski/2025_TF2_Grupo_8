package vista.RendererAsociados;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import ControladorAsociados.ActionListenerAsociados;
import persistencia.DAOAsociadoYDTO.AsociadoDTO;
import persistencia.DAOAsociadoYDTO.AsociadoDAOMySQL;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton btnEditar;
    private JTable tabla;
    private AsociadoDAOMySQL BD;

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

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
    
    public JTable getTabla() {
    	return this.tabla;
    }
}
