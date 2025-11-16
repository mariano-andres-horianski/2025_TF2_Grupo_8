package vista.JframePrincipal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JToggleButton;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;

import controlador.Asociados.ActionListenerAsociados;
import controlador.Simulacion.ActionListenerSimulacion;
import vista.PanelCentral.PanelSimulacion;
import vista.PanelCentral.Panel_Inicio;

import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
/**
 	* Ventana principal (JFrame) de la aplicación "Sistema de Gestión de Clínica".
 	* <p>
 	* Esta clase actúa como la Vista principal en el patrón MVC. Es responsable de
 	* contener y gestionar los paneles de navegación (Oeste) y el panel central
	* (Centro) que utiliza un CardLayout para intercambiar las diferentes vistas
	* <ul>
	* 	<li><code>Inicio<code></li>
	* 	<li><code>Asociados<code></li>
	* 	<li><code>Pacientes<code></li>
	* 	<li>
	* </ul>
 */
public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ActionListenerAsociados controladorAsociados;
	private ActionListenerSimulacion controladorSimulacion;
	private JButton boton_navegacionAsociados;
	private JButton boton_navegacionInicio;
	private JButton boton_navegacionSimulacion;
	private JPanel panel_Central;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 */
	/**
	 * Obtiene el panel central que contiene las vistas intercambiables.
	 * @return El JPanel que utiliza CardLayout.
	 */
	public JPanel getPanel_Central() {
		return panel_Central;
	}
	/**
	 * Establece o reemplaza el panel central.
	 * 
	 * <p><b>Precondición:</b> El panel central no debe ser null.
	 * <p><b>Postcondición:</b> El panel central de la ventana es actualizado.
	 * @param panel_Central El nuevo panel a establecer.
	 */
	public void setPanel_Central(JPanel nuevo) {
		panel_Central.removeAll();
	    panel_Central.add(nuevo, "ACTUAL");
	    panel_Central.revalidate();
	    panel_Central.repaint();
	}

	/**
	 * Create the frame.
	 */
	/**
	 * Constructor de la VentanaPrincipal.
     * <p><b>Postcondición:</b> La ventana principal se inicializa con sus componentes (menú,
     * toolbar, navegación y panel central).
     * <p><b>Postcondición:</b> El panel central se configura con un CardLayout.
     * <p><b>Postcondición:</b> El panel de inicio se muestra por defecto.
	 */
	public VentanaPrincipal() {
		setTitle("Sistema - Gestion de Clinica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem menu_Archivo = new JMenuItem("Archivo");
		menuBar.add(menu_Archivo);
		
		JMenuItem menu_Gestion = new JMenuItem("Gestion");
		menuBar.add(menu_Gestion);
		
		JMenuItem menu_Simulacion = new JMenuItem("Simulacion");
		menuBar.add(menu_Simulacion);
		
		JMenuItem menu_Herramientas = new JMenuItem("Herramientas");
		menuBar.add(menu_Herramientas);
		
		JMenuItem menu_Ayuda = new JMenuItem("Ayuda");
		menuBar.add(menu_Ayuda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_Central = new JPanel(new CardLayout());
		contentPane.add(panel_Central, BorderLayout.CENTER);
		panel_Central.setLayout(new CardLayout(0, 0));
		
		panel_Central.add(new PanelSimulacion(controladorSimulacion), "name_1387589521300");
		/*
		
		El código de debajo es el usado por PanelSimulacion para crearse, por lo que se simplifica a la línea de arriba
		Dejo el código para tener un backup por las dudas, debería borrarse
		
		Panel_Inicio panel_Inicio = new Panel_Inicio();
		panel_Inicio.setSize(new Dimension(0, 200));
		panel_Inicio.setBounds(new Rectangle(0, 0, 0, 200));
		panel_Inicio.setPreferredSize(new Dimension(10, 200));
		panel_Central.add(panel_Inicio, "name_1387589521300");
		panel_Inicio.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_info = new JPanel();
		panel_info.setPreferredSize(new Dimension(10, 80));
		panel_info.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_Inicio.add(panel_info, BorderLayout.NORTH);
		panel_info.setLayout(new GridLayout(1, 4, 15, 0));
		
		JPanel panel_AsocRegistrados = new JPanel();
		panel_AsocRegistrados.setPreferredSize(new Dimension(80, 30));
		panel_AsocRegistrados.setBackground(new Color(255, 255, 255));
		panel_info.add(panel_AsocRegistrados);
		panel_AsocRegistrados.setLayout(new BorderLayout(0, 0));
		
		JLabel label_numAsocRegistrados = new JLabel("  00");
		label_numAsocRegistrados.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_AsocRegistrados.add(label_numAsocRegistrados, BorderLayout.CENTER);
		
		JLabel lblAsociadosRegistrados = new JLabel("   Asociados Registrados");
		panel_AsocRegistrados.add(lblAsociadosRegistrados, BorderLayout.SOUTH);
		
		JPanel panel_EstadoDeAmbulancia = new JPanel();
		panel_EstadoDeAmbulancia.setPreferredSize(new Dimension(80, 30));
		panel_EstadoDeAmbulancia.setBackground(Color.WHITE);
		panel_info.add(panel_EstadoDeAmbulancia);
		panel_EstadoDeAmbulancia.setLayout(new BorderLayout(0, 0));
		
		JLabel label_numAmbulancia = new JLabel("  00");
		label_numAmbulancia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_EstadoDeAmbulancia.add(label_numAmbulancia, BorderLayout.WEST);
		
		JLabel label_Ambulancia = new JLabel("   Estado De Ambulancia");
		panel_EstadoDeAmbulancia.add(label_Ambulancia, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_Inicio.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("   Eventos Recientes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_Inicio.add(panel_2, BorderLayout.SOUTH);
		*/
		JToolBar toolBar = new JToolBar();
		toolBar.setPreferredSize(new Dimension(13, 50));
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JPanel panel_VistaActual = new JPanel();
		toolBar.add(panel_VistaActual);
		panel_VistaActual.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		panel_VistaActual.add(lblInicio, BorderLayout.CENTER);
		
		JPanel panel_Navegacion = new JPanel();
		panel_Navegacion.setSize(new Dimension(130, 0));
		panel_Navegacion.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel_Navegacion, BorderLayout.WEST);
		panel_Navegacion.setLayout(new GridLayout(0, 1, 0, 0));
		
		boton_navegacionInicio = new JButton("Inicio");
		panel_Navegacion.add(boton_navegacionInicio);
		
		
		boton_navegacionAsociados = new JButton("Asociados");
		boton_navegacionAsociados.addActionListener(this.controladorAsociados);
		panel_Navegacion.add(boton_navegacionAsociados);
		boton_navegacionInicio.setActionCommand("INICIO");

		boton_navegacionSimulacion = new JButton("Simulacion");
		panel_Navegacion.add(boton_navegacionSimulacion);
		boton_navegacionSimulacion.setActionCommand("SIMULACION");
		
		JPanel panel_aux1 = new JPanel();
		panel_Navegacion.add(panel_aux1);
		
		JPanel panel_aux2 = new JPanel();
		panel_Navegacion.add(panel_aux2);
		
		JPanel panel = new JPanel();
		panel_Navegacion.add(panel);

	}
	/**
	 * Asigna el controlador (ActionListener) a los componentes de la vista que gestionan Asociados.
	 * <p><b>Precondición:</b> El controladorAsociados no debe ser null.
     * <p><b>Postcondición:</b> El controlador se asigna al botón de navegación de asociados.
     *  @param controladorAsociados El controlador que manejará los eventos de asociados.
	 */
	public void setControladorAsociados(ActionListenerAsociados controladorAsociados) {
		this.controladorAsociados = controladorAsociados;
	}
	

	public void setControladorSimulacion(ActionListenerSimulacion controladorSimulacion) {
		this.controladorSimulacion = controladorSimulacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public ActionListenerAsociados getControladorAsociados() {
		return controladorAsociados;
	}
	

	public ActionListenerSimulacion getControladorSimulacion() {
		return controladorSimulacion;
	}

	public JButton getBoton_navegacionAsociados() {
		return boton_navegacionAsociados;
	}

	public JButton getBoton_navegacionInicio() {
		return boton_navegacionInicio;
	}

	public JButton getBoton_navegacionSimulacion() {
		return boton_navegacionSimulacion;
	}

	
}
