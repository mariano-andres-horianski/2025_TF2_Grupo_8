package vista.formularios;

import javax.swing.*;
import java.awt.*;
import persistencia.DAOAsociadoYDTO.AsociadoDTO;

public class FormularioUpdateAsociado extends JDialog {
    private JTextField txtDni, txtNya, txtCiudad, txtTelefono, txtDomicilio;
    private JButton btnGuardar, btnCancelar;

    public FormularioUpdateAsociado(AsociadoDTO socio) {
        setTitle("Editar Asociado");
        setModal(true); // Hace que bloquee la ventana principal
        setSize(350, 300);
        setLayout(new GridLayout(6, 2, 5, 5));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Crear campos
        txtDni = new JTextField(socio.getDni());
        txtDni.setEditable(false); // DNI inmutable
        txtNya = new JTextField(socio.getNya());
        txtCiudad = new JTextField(socio.getCiudad());
        txtTelefono = new JTextField(socio.getTelefono());
        txtDomicilio = new JTextField(socio.getDomicilioStr());

        // Botones
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        // Agregar al layout
        add(new JLabel("DNI:"));
        add(txtDni);
        add(new JLabel("Nombre y Apellido:"));
        add(txtNya);
        add(new JLabel("Ciudad:"));
        add(txtCiudad);
        add(new JLabel("Teléfono:"));
        add(txtTelefono);
        add(new JLabel("Domicilio:"));
        add(txtDomicilio);
        add(btnGuardar);
        add(btnCancelar);

        // Acción del botón guardar
        btnGuardar.addActionListener(e -> {
            socio.setNya(txtNya.getText());
            socio.setCiudad(txtCiudad.getText());
            socio.setTelefono(txtTelefono.getText());
            socio.setDomicilioStr(txtDomicilio.getText());

            // Podés agregar aquí una llamada a tu controlador o DAO:
            // controlador.actualizarAsociado(socio);

            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}
