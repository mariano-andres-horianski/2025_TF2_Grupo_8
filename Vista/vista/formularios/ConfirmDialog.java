package vista.formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import persistencia.DAOAsociadoYDTO.AsociadoDTO;
/**
 * Un diálogo modal de confirmación (Sí/No) genérico y reutilizable.
 * <p>
 * Esta vista está diseñada para ser invocada por un controlador cuando se requiere una confirmación del usuario 
 * (ej. para eliminar un registro).
 * <p>
 * Si el usuario presiona "Sí", esta clase re-envía un ActionEvent al controlador original (listener), usando el actionCommand
 * y el objeto socio (DTO) que le fueron provistos.
 */
public class ConfirmDialog extends JDialog {
	/**
	 * Construye el diálogo de confirmación.
	 * <p><b>Precondición:</b> parent, listener y socio} no deben ser nulos.
	 * <p><b>Postcondición:</b>
	 * <pre>
	 * El diálogo modal se muestra al usuario.
	 * Si se presiona "Sí", se dispara un ActionEvent al listener con el actionCommand y el socio provistos, y el diálogo se cierra.
	 * Si se presiona "No", el diálogo simplemente se cierra.
	 * </pre>
	 * @param parent El JFrame padre sobre el cual se centrará el diálogo.
	 * @param mensaje El texto de la pregunta a mostrar.
	 * @param actionCommand El comando de acción que se enviará al listener si el usuario confirma.
	 * @param listener El controlador (ActionListener) que recibirá el evento de confirmación.
	 * @param socio El DTO (AsociadoDTO) que contiene los datos a afectar, se pasará como source del nuevo ActionEvent.
	 * 
	 */
    public ConfirmDialog(JFrame parent, String mensaje, 
                         String actionCommand, ActionListener listener, AsociadoDTO socio) {

        super(parent, "Confirmación", true); // modal

        // Texto del mensaje
        JLabel lblMensaje = new JLabel(mensaje);
        lblMensaje.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblMensaje, BorderLayout.CENTER);

        // Panel con botones
        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btnSi = new JButton("Sí");
        JButton btnNo = new JButton("No");

        // Acción del botón Sí
        btnSi.addActionListener(e -> {
            // Disparamos el ActionEvent hacia el controlador
            listener.actionPerformed(
                new ActionEvent(socio, ActionEvent.ACTION_PERFORMED, actionCommand)
            );
            dispose(); // cerrar popup
        });

        // Acción del botón No
        btnNo.addActionListener(e -> dispose());

        panelBotones.add(btnSi);
        panelBotones.add(btnNo);

        add(panelBotones, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }
}
