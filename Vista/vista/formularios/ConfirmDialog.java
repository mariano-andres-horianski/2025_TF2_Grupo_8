package vista.formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import persistencia.DAOAsociadoYDTO.AsociadoDTO;

public class ConfirmDialog extends JDialog {

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
