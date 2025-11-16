package vista.formularios;

import javax.swing.*;

import controlador.Asociados.ActionListenerAsociados;

import java.awt.*;
import java.awt.event.ActionEvent;

import persistencia.DAOAsociadoYDTO.AsociadoDTO;
/**
 * Formulario (JDialog) modal para la actualización de un Asociado existente.
 * <p>
 * Esta vista recibe un AsociadoDTO para pre-cargar los datos en los campos del formulario.
 * El campo DNI no es editable respetando la regla de negocio de que el DNI es la clave inmutable.
 * <p>
 * Al presionar "Guardar", esta vista modifica el objeto DTO original y luego lo envía al controlador (ActionListenerAsociados)
 * dentro de un ActionEvent con el comando "UPDATE".
 */
public class FormularioUpdateAsociado extends JDialog {
    private JTextField txtDni, txtNya, txtCiudad, txtTelefono, txtDomicilio;
    private JButton btnGuardar, btnCancelar;
    private ActionListenerAsociados controlador;
    /**
     * Constructor del formulario de edición.
	 * <p><b>Precondición:</b> socio y controlador no deben ser nulos.
	 * <p><b>Postcondición:</b>
	 * <pre>
	 *  El JDialog se inicializa como modal y los campos de texto se rellenan con los datos del socio.
	 * El campo txtDni se deshabilita para edición.
	 * El botón "Guardar" está configurado para:
	 * 	1. Actualizar el objeto socio original con los nuevos datos de los campos.
	 * 	2. Crear un ActionEvent con el socio (ya modificado) como source y el comando "UPDATE".
	 * 	3. Enviar el evento al controlador.
	 * 	4. Cerrar el diálogo (dispose()).
	 * El botón "Cancelar" está configurado para cerrar el diálogo (dispose()).
	 * </pre>
	 * @param socio  El AsociadoDTO} que contiene los datos a editar. 
	 * @param controlador El controlador ( ActionListenerAsociados ) que gestionará el evento "UPDATE". 
     */
    public FormularioUpdateAsociado(AsociadoDTO socio,ActionListenerAsociados controlador) {
        setTitle("Editar Asociado");
        this.controlador = controlador;
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
        add(new JLabel("Tel�fono:"));
        add(txtTelefono);
        add(new JLabel("Domicilio:"));
        add(txtDomicilio);
        add(btnGuardar);
        add(btnCancelar);

        // Accion del boton guardar
        btnGuardar.addActionListener(e -> {
            socio.setNya(txtNya.getText());
            socio.setCiudad(txtCiudad.getText());
            socio.setTelefono(txtTelefono.getText());
            socio.setDomicilioStr(txtDomicilio.getText());
            controlador.actionPerformed(new ActionEvent(socio,0,"UPDATE"));
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());
    }

	public JTextField getTxtDni() {
		return txtDni;
	}

	public JTextField getTxtNya() {
		return txtNya;
	}

	public JTextField getTxtCiudad() {
		return txtCiudad;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JTextField getTxtDomicilio() {
		return txtDomicilio;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
    
    
}
