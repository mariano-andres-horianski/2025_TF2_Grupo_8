package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton que provee conexiones JDBC.
 */
public class BDConexion {
    private static BDConexion instance;
    private Connection conn;

    private String baseDatos = "jdbc:mysql://localhost:3306/Grupo_8";
    private String usuario = "progra_c";
    private String password = "progra_c";

    private BDConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver JDBC de MySQL.", e);
        }

        this.conn = DriverManager.getConnection(baseDatos, usuario, password);
        this.conn.setAutoCommit(true);
    }


    public static synchronized BDConexion getInstance() throws SQLException {
        if (instance == null || instance.getConnection() == null || instance.getConnection().isClosed()) {
            instance = new BDConexion();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
