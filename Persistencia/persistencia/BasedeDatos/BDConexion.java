package persistencia.BasedeDatos;

import java.sql.*;

/**
 * Singleton que provee conexiones JDBC a la base de datos MySQL del sistema.
 * 
 */
public class BDConexion {
    private static BDConexion instance;
    private Connection conn;
    private Statement sentencia;

    private String baseDatos = "jdbc:mysql://localhost:3306/Grupo_8";
    private String usuario = "progra_c";
    private String password = "progra_c";
    
    /**
     * Constructor privado que inicializa la conexión y la sentencia.
     * 
     * <p><b>Precondición:</b> El driver JDBC de MySQL debe estar disponible en el classpath. </p>
     * <p><b>Postcondición:</b> Si no ocurre una excepción, conn y sentencia se inicializan correctamente 
     * y la conexión queda en modo auto-commit. </p>
     * 
     */
    
    private BDConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("No se pudo cargar el puente JDBC-ODBC");
            return;
        }
        try {
        	this.conn = DriverManager.getConnection(baseDatos, usuario, password);
        	this.sentencia=conn.createStatement();
        	this.conn.setAutoCommit(true);
        	
        	assert conn != null : "La conexión no debería ser nula tras inicializar.";
            assert sentencia != null : "La sentencia no debería ser nula tras inicializar.";
            assert conn.getAutoCommit() : "La conexión debería estar en auto-commit.";
        }
        catch (Exception e) {
            System.out.println("No se pudo iniciar la conexion");
            return;
        }
        
    }

    /**
     * Devuelve la instancia única del singleton, garantizando que la conexión sea válida.
     *
     * <p><b>Precondición:</b> Ninguna.</p>
     * <p><b>Postcondición:</b> Se devuelve una instancia (no-nula) de BDConexion. Si existía una instancia previa con conexión cerrada,
     * se intenta crear una nueva instancia con conexión abierta.</p>
     *
     * @return instancia única de BDConexion
     * @throws SQLException si ocurre un error al verificar o crear la conexión
     */
    public static synchronized BDConexion getInstance() throws SQLException {
        if (instance == null || instance.getConnection() == null || instance.getConnection().isClosed()) {
            instance = new BDConexion();
        }
        
        assert instance != null : "La instancia BDConexion no debería ser nula.";
        assert instance.getConnection() != null : "La conexión no debería ser nula en una instancia válida.";

        return instance;
    }

    /**
     * Devuelve la conexión JDBC actual.
     *
     * <p><b>Precondición:</b> La instancia debería haber sido inicializada mediante getInstance() para garantizar
     * que la conexión esté disponible.</p>
     *
     * <p><b>Postcondición:</b> Se devuelve la referencia a conn (puede ser null si la inicialización falló).</p>
     */
    public Connection getConnection() {
        return conn;
    }
    
    /**
     * Devuelve el Statement asociado a la conexión.
     *
     * <p><b>Precondición:</b> La conexión debe estar activa para que la sentencia sea usable.</p>
     *
     * <p><b>Postcondición:</b> Se devuelve sentencia o null si no se creó.</p>
     */
    public Statement getSentencia() {
    	try {
			assert conn != null && !conn.isClosed() : "No se puede obtener la sentencia si la conexión está cerrada.";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sentencia;
	}

    /**
     * Cierra la conexión si está abierta.
     *
     * <p><b>Precondición:</b> La instancia debe haber sido inicializada</p>
     *
     * <p><b>Postcondición:</b>
     * <ul>
     *   <li>Si conn no era nula ni estaba cerrada, queda cerrada tras ejecutar este método.</li>
     *   <li>Tras cerrar la conexión, la invariante de estado que exige que sentencia sea usable ya no se cumple
     *       (es decir, sentencia deja de ser utilizable mientras la conexión esté cerrada).</li>
     * </ul>
     * </p>
     */
	public void close() {
        try {
            if (conn != null && !conn.isClosed()) 
            	conn.close();
            assert conn == null || conn.isClosed() : "La conexión debería estar cerrada tras close().";
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
