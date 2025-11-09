package persistencia;

import java.sql.SQLException;
import java.util.List;

public interface IAsociadoDAO {
    List<AsociadoDTO> obtenerTodos() throws SQLException;
    void agregar(AsociadoDTO a) throws SQLException, AsociadoExistenteException;
    void eliminarPorDni(String dni) throws SQLException, AsociadoNotFoundException;
    boolean existeDni(String dni) throws SQLException;
    void guardarTodos(List<AsociadoDTO> lista) throws SQLException;
    void inicializarTablaConDatosIniciales() throws SQLException;
}
