package persistencia.DAOAsociadoYDTO;

import java.sql.*;
import java.util.HashMap;
import persistencia.BasedeDatos.*;
import persistencia.Excepciones.*;
/**
 * Implementaci�n JDBC del DAO para AsociadoDTO.
 * Crea la tabla si no existe en el constructor.
 */
public class AsociadoDAOMySQL implements IAsociadoDAO {

    private BDConexion bd;
    
    /**
     * Constructor de clase con parametro "bd".
     *
     * <p>Precondiciones:
     * <ul>
     *   <li>bd != null</li>
     * </ul>
     *
     * <p>Postcondiciones:
     * <ul>
     *   <li>this.bd referenciar� al objeto BDConexion pasado.</li>
     *   <li>Se intentar� crear la tabla "asociados" si no existe.</li>
     * </ul>
     *
     * <p>Invariante tras la ejecuci�n: this.bd != null.
     *
     * @param bd instancia v�lida de BDConexion (no nula)
     */

    public AsociadoDAOMySQL(BDConexion bd) {
    	assert bd != null : "BDConexion pasado al constructor no debe ser null";
    	this.bd=bd;
    	crearTablaSiNoExiste();
    	assert this.bd != null : "Invariante:this.bd debe quedar no nulo tras la construcci�n";
    }
    
    /**
     * Constructor por defecto que obtiene la instancia singleton de BDConexion.
     *
     * <p>Precondiciones:
     * <ul>
     *   <li>El m�todo BDConexion.getInstance() puede lanzar SQLException si la conexi�n no est� disponible.</li>
     * </ul>
     *
     * <p>Postcondiciones:
     * <ul>
     *   <li>this.bd referenciar� a la instancia retornada por BDConexion.getInstance().</li>
     *   <li>Se intentar� crear la tabla "asociados" si no existe.</li>
     * </ul>
     *
     * <p>Invariante tras la ejecuci�n: this.bd != null (si no se lanz� SQLException).</p>
     *
     * @throws SQLException si no se puede obtener la conexi�n/instancia de BDConexion
     */
    
    public AsociadoDAOMySQL() throws SQLException {
        this.bd = BDConexion.getInstance();
        assert this.bd != null : "BDConexion.getInstance() devolvi� null";
        crearTablaSiNoExiste();
        assert this.bd != null : "Invariante: this.bd debe quedar no nulo tras la construcci�n por defecto";
    }

    /**
     * Crea la tabla 'asociados' si no existe.
     *
     * <p>Precondiciones:
     * <ul>
     *   <li>this.bd != null y bd.getSentencia() debe devolver una sentencia v�lida.</li>
     * </ul>
     *
     * <p>Postcondiciones:
     * <ul>
     *   <li>Se habr� ejecutado la sentencia CREATE TABLE IF NOT EXISTS; si ocurre SQLException se imprime el stacktrace.</li>
     * </ul>
     */
    
    private void crearTablaSiNoExiste() {
    	assert this.bd != null : "bd no debe ser null al crear la tabla";
    	try {
    		String sql = "CREATE TABLE IF NOT EXISTS asociados ("
                + "dni VARCHAR(20) PRIMARY KEY, "
                + "nya VARCHAR(200) NOT NULL, "
                + "ciudad VARCHAR(100), "
                + "telefono VARCHAR(50), "
                + "domicilioStr VARCHAR(255)"
                + ")";
        	this.bd.getSentencia().execute(sql);
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
        
    }
    
    /**
     * Recupera todos los asociados y los devuelve en un HashMap cuya clave es el DNI.
     *
     * <p>Precondiciones:
     * <ul>
     *   <li>this.bd != null</li>
     *   <li>La conexi�n de bd est� operativa para realizar consultas.</li>
     * </ul>
     *
     * <p>Postcondiciones:
     * <ul>
     *   <li>Devuelve un HashMap (puede estar vac�o) con una entrada por cada fila de la tabla 'asociados'.</li>
     *   <li>Cada AsociadoDTO disponible tendr� su dni, nya, ciudad, telefono y domicilioStr seteados a partir del ResultSet.</li>
     * </ul>
     *
     * <p>Invariante durante la ejecuci�n: no modifica la tabla 'asociados'.</p>
     *
     * @return HashMap<String, AsociadoDTO> con los asociados existentes (clave = dni). Nunca nulo.
     */

    @Override
    public HashMap<String, AsociadoDTO> obtenerTodosMap() {
    	assert this.bd != null : "bd no debe ser null al obtener todos";
    	HashMap<String, AsociadoDTO> mapa = new HashMap<>();
    	try {
	        String sql = "SELECT dni, nya, ciudad, telefono, domicilioStr FROM asociados";
	        PreparedStatement ps = bd.getConnection().prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AsociadoDTO a = new AsociadoDTO();
                a.setDni(rs.getString("dni"));
                a.setNya(rs.getString("nya"));
                a.setCiudad(rs.getString("ciudad"));
                a.setTelefono(rs.getString("telefono"));
                a.setDomicilioStr(rs.getString("domicilioStr"));
                assert a.getDni() != null : "DNI de un registro no deber�a ser null";
                mapa.put(a.getDni(), a);
            }
        } catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assert mapa != null : "El mapa retornado no debe ser null";
    	return mapa;
    }
    
    /**
     * Agrega un nuevo asociado en la tabla.
     *
      * <p>Precondiciones:
     * <ul>
     *   <li>a != null</li>
     *   <li>a.getDni() != null && !a.getDni().trim().isEmpty()</li>
     * </ul>
     * 
     * <p>Postcondiciones (en caso exitoso):
     * <ul>
     *   <li>Se inserta un nuevo registro en la tabla 'asociados' con los datos del DTO.</li>
     * </ul>
     *
     * @param a AsociadoDTO a insertar
     * @throws AsociadoExistenteException si ya existe un asociado con ese DNI
     */
    
    @Override
    public void agregar(AsociadoDTO a) throws AsociadoExistenteException {
    	assert a != null : "Se pide agregar asociado no nulo";
        assert a.getDni() != null && !a.getDni().trim().isEmpty() : "DNI no debe ser nulo o vac�o";
        try {
	        String sql = "INSERT INTO asociados (dni, nya, ciudad, telefono, domicilioStr) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement ps = bd.getConnection().prepareStatement(sql);
            ps.setString(1, a.getDni());
            ps.setString(2, a.getNya());
            ps.setString(3, a.getCiudad());
            ps.setString(4, a.getTelefono());
            ps.setString(5, a.getDomicilioStr());
            ps.executeUpdate();
        } catch (SQLException e) {
            String msg = e.getMessage() == null ? "" : e.getMessage().toLowerCase();
            String sqlState = e.getSQLState() == null ? "" : e.getSQLState();
            if (msg.contains("duplicate") || (sqlState != null && sqlState.startsWith("23"))) {
                // 23 indica un codigo de la excepcion que refiere a una violacion en la integridad de la base de datos
                // lanza excepci�n de dominio en lugar de SQLException para duplicados
                throw new AsociadoExistenteException("Ya existe un asociado con ese DNI.");
            }
            else
            	e.printStackTrace();
        }
    }

    /**
     * Elimina un asociado por su DNI.
     *
     * <p>Precondiciones:
     * <ul>
     *   <li>dni != null && !dni.trim().isEmpty()</li>
     * </ul>
     *
     * <p>Postcondiciones:
     * <ul>
     *   <li>Si exist�a un asociado con el DNI, queda eliminado de la tabla.</li>
     *   <li>Si no exist�a, se lanza AsociadoNotFoundException.</li>
     * </ul>
     *
     * @param dni DNI del asociado a eliminar
     * @throws AsociadoNotFoundException si no existe asociado con ese DNI
     */
    @Override
    public void eliminarPorDni(String dni) throws AsociadoNotFoundException {
    	assert dni != null && !dni.trim().isEmpty() : "DNI para eliminar no debe ser nulo o vac�o";
        try {
	        String sql = "DELETE FROM asociados WHERE dni = ?";
	        PreparedStatement ps = bd.getConnection().prepareStatement(sql);
	        ps.setString(1, dni);
	        int afectados = ps.executeUpdate();
	        if (afectados == 0) {
	            // lanzar excepcion si no exist�a
	            throw new AsociadoNotFoundException("No existe un asociado con ese DNI.");
	        }
        } catch(SQLException e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Guarda todos los asociados del mapa en la tabla.
     *
     * <p>Precondiciones:
     * <ul>
     *   <li>mapa puede ser nulo; si no es nulo, sus valores (AsociadoDTO) pueden contener entradas nulas que se ignoran.</li>
     *   <li>this.bd != null y la conexi�n es usable para transacciones.</li>
     * </ul>
     *
     * <p>Postcondiciones (si mapa != null):
     * <ul>
     *   <li>La tabla 'asociados' primero se vac�a y despues se actualiza con los nuevos datos.</li>
     *   <li>En caso de error durante la operaci�n por lotes, se intenta rollback y la tabla permanece en el estado anterior al commit (salvo que el rollback falle).</li>
     * </ul>
     *
     * Se preserva el autoCommit original de la conexi�n tras finalizar (finally).
     * 
     * @param mapa Mapa con los AsociadoDTO a persistir (clave = dni). Puede ser null, en cuyo caso no hace nada.
     */
    public void guardarTodos(HashMap<String, AsociadoDTO> mapa) {
    	assert this.bd != null : "bd no debe ser null al guardarTodos";
        if (mapa!= null) {
        	Connection conn = bd.getConnection();
        	assert conn != null : "conn no debe ser null";
            boolean origAuto=true;
            try {
            	origAuto = conn.getAutoCommit();
                conn.setAutoCommit(false);
                assert !conn.getAutoCommit() : "Se espera autoCommit == false despu�s de setAutoCommit(false)";
                this.bd.getSentencia().executeUpdate("DELETE FROM asociados"); // elimina el contenido de la tabla
                String sql = "INSERT INTO asociados (dni, nya, ciudad, telefono, domicilioStr) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                for (AsociadoDTO a : mapa.values()) {
                    if (!(a == null || a.getDni() == null)) {
                    	assert a.getDni() != null : "DNI de DTO dentro de mapa no debe ser null";
                        ps.setString(1, a.getDni());
                        ps.setString(2, a.getNya());
                        ps.setString(3, a.getCiudad());
                        ps.setString(4, a.getTelefono());
                        ps.setString(5, a.getDomicilioStr());
                        ps.addBatch();
                    }
                }
                ps.executeBatch();
                conn.commit();
            } catch (SQLException e) {
                try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
            } finally {
                try {
					conn.setAutoCommit(origAuto);
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        }
    }

    /**
     * Elimina todos los registros de la tabla 'asociados'.
     *
     * <p>Precondiciones:
     * <ul>
     *   <li>this.bd != null</li>
     * </ul>
     *
     * <p>Postcondiciones:
     * <ul>
     *   <li>La tabla 'asociados' queda vac�a si la operaci�n es exitosa; si ocurre SQLException se imprime el stacktrace.</li>
     * </ul>
     */
    
    @Override
    public void vaciarTabla() {
    	assert this.bd != null : "bd no debe ser null al vaciar la tabla";
    	try {
    		this.bd.getSentencia().executeUpdate("DELETE FROM asociados");
    	}catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Actualiza un asociado existente identificado por su DNI.
     *
     * <p>Precondiciones:
     * <ul>
     *   <li>a != null</li>
     *   <li>a.getDni() != null && !a.getDni().trim().isEmpty()</li>
     * </ul>
     *
     * <p>Postcondiciones:
     * <ul>
     *   <li>Si existía un asociado con ese DNI, sus campos nya, ciudad, telefono y domicilioStr quedan actualizados.</li>
     *   <li>Si no existía un asociado con ese DNI, se lanza AsociadoNotFoundException.</li>
     * </ul>
     *
     * <p>Invariante durante/tras la ejecución: this.bd != null.</p>
     *
     * @param a AsociadoDTO con los datos a actualizar (identificado por dni)
     * @throws AsociadoNotFoundException si no existe asociado con ese DNI
     */
    @Override
    public void actualizar(AsociadoDTO a) throws AsociadoNotFoundException {
        assert a != null : "Asociado a actualizar no debe ser null";
        assert a.getDni() != null && !a.getDni().trim().isEmpty() : "DNI no debe ser nulo o vacío";
        assert this.bd != null : "bd no debe ser null al actualizar";
        try {
            String sql = "UPDATE asociados SET nya = ?, ciudad = ?, telefono = ?, domicilioStr = ? WHERE dni = ?";
            PreparedStatement ps = bd.getConnection().prepareStatement(sql);
            ps.setString(1, a.getNya());
            ps.setString(2, a.getCiudad());
            ps.setString(3, a.getTelefono());
            ps.setString(4, a.getDomicilioStr());
            ps.setString(5, a.getDni());
            int afectados = ps.executeUpdate();
            if (afectados == 0) {
                // no existía el registro a actualizar
                throw new AsociadoNotFoundException("No existe un asociado con ese DNI.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        
}
