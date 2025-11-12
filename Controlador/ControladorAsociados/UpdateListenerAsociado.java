package ControladorAsociados;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import persistencia.DAOAsociadoYDTO.AsociadoDTO;
import vista.formularios.FormularioUpdateAsociado;

public class UpdateListenerAsociado extends MouseAdapter {
	private AsociadoDTO socio;
	
	@Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            // Crear el formulario de edición con el socio actual
            FormularioUpdateAsociado form = new FormularioUpdateAsociado(socio);

            // Mostrar el formulario como diálogo modal
            form.setLocationRelativeTo(null); // Centrado
            form.setVisible(true);
        }
    }
}
