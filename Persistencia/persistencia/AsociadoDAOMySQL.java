package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO para AsociadoDTO.
 * Crea la tabla si no existe en el constructor.
 */
public class AsociadoDAOMySQL implements IAsociadoDAO {

    private final BDConexion bd;

    public AsociadoDAOMySQL() throws SQLException {
        this.bd = BDConexion.getInstance();
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS asociados ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "dni VARCHAR(20) NOT NULL UNIQUE, "
                + "nya VARCHAR(200) NOT NULL, "
                + "ciudad VARCHAR(100), "
                + "telefono VARCHAR(50), "
                + "domicilioStr VARCHAR(255)"
                + ")";
        try (Statement st = bd.getConnection().createStatement()) {
            st.execute(sql);
        }
    }

    @Override
    public List<AsociadoDTO> obtenerTodos() throws SQLException {
        List<AsociadoDTO> lista = new ArrayList<>();
        String sql = "SELECT id, dni, nya, ciudad, telefono, domicilioStr FROM asociados";
        try (PreparedStatement ps = bd.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AsociadoDTO a = new AsociadoDTO();
                a.setId(rs.getInt("id"));
                a.setDni(rs.getString("dni"));
                a.setNya(rs.getString("nya"));
                a.setCiudad(rs.getString("ciudad"));
                a.setTelefono(rs.getString("telefono"));
                a.setDomicilioStr(rs.getString("domicilioStr"));
                lista.add(a);
            }
        }
        return lista;
    }

    @Override
    public void agregar(AsociadoDTO a) throws SQLException, AsociadoExistenteException {
        if (a == null) throw new SQLException("Datos de asociado inválidos.");
        if (a.getDni() == null || a.getDni().trim().isEmpty()) throw new SQLException("DNI inválido.");

        String sql = "INSERT INTO asociados (dni, nya, ciudad, telefono, domicilioStr) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = bd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getDni());
            ps.setString(2, a.getNya());
            ps.setString(3, a.getCiudad());
            ps.setString(4, a.getTelefono());
            ps.setString(5, a.getDomicilioStr());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    a.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            String msg = e.getMessage() == null ? "" : e.getMessage().toLowerCase();
            String sqlState = e.getSQLState() == null ? "" : e.getSQLState();
            if (msg.contains("duplicate") || sqlState.startsWith("23")) {
                // lanzar excepción de dominio en lugar de SQLException para duplicados
                throw new AsociadoExistenteException("Ya existe un asociado con ese DNI.");
            }
            throw e;
        }
    }

    @Override
    public void eliminarPorDni(String dni) throws SQLException, AsociadoNotFoundException {
        if (dni == null || dni.trim().isEmpty()) throw new SQLException("DNI inválido.");

        String sql = "DELETE FROM asociados WHERE dni = ?";
        try (PreparedStatement ps = bd.getConnection().prepareStatement(sql)) {
            ps.setString(1, dni);
            int afectados = ps.executeUpdate();
            if (afectados == 0) {
                // lanzar excepción de dominio si no existía
                throw new AsociadoNotFoundException("No existe un asociado con ese DNI.");
            }
        }
    }

    @Override
    public boolean existeDni(String dni) throws SQLException {
        if (dni == null) return false;
        String sql = "SELECT COUNT(*) FROM asociados WHERE dni = ?";
        try (PreparedStatement ps = bd.getConnection().prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    @Override
    public void guardarTodos(List<AsociadoDTO> lista) throws SQLException {
        if (lista == null) return;
        Connection conn = bd.getConnection();
        boolean origAuto = conn.getAutoCommit();
        try {
            conn.setAutoCommit(false);
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("DELETE FROM asociados");
            }
            String sql = "INSERT INTO asociados (dni, nya, ciudad, telefono, domicilioStr) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (AsociadoDTO a : lista) {
                    ps.setString(1, a.getDni());
                    ps.setString(2, a.getNya());
                    ps.setString(3, a.getCiudad());
                    ps.setString(4, a.getTelefono());
                    ps.setString(5, a.getDomicilioStr());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(origAuto);
        }
    }

    @Override
    public void inicializarTablaConDatosIniciales() throws SQLException {
        Connection conn = bd.getConnection();
        boolean origAuto = conn.getAutoCommit();
        try {
            conn.setAutoCommit(false);
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("DROP TABLE IF EXISTS asociados");
            }
            crearTablaSiNoExiste();
            String sql = "INSERT INTO asociados (dni, nya, ciudad, telefono, domicilioStr) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "12345678");
                ps.setString(2, "Perez, Juan");
                ps.setString(3, "Mar del Plata");
                ps.setString(4, "223-1234567");
                ps.setString(5, "Calle Falsa 123");
                ps.executeUpdate();

                ps.setString(1, "87654321");
                ps.setString(2, "Gomez, Ana");
                ps.setString(3, "Mar del Plata");
                ps.setString(4, "223-7654321");
                ps.setString(5, "Av. Siempreviva 742");
                ps.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(origAuto);
        }
    }
}
