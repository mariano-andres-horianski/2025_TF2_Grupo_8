package persistencia.DAOAsociadoYDTO;

import java.util.HashMap;
import persistencia.Excepciones.*;

/**
 * Define la interfaz del Data Access Object (DAO) para AsociadoDTO.
 * 
 * <p>Esta interfaz abstrae las operaciones CRUD (Create, Read, Update, Delete)
 * sobre la entidad <b>Asociado</b> en el sistema de persistencia.
 */

public interface IAsociadoDAO {
    HashMap<String, AsociadoDTO> obtenerTodosMap();
    void agregar(AsociadoDTO a) throws DatoInvalidoException, AsociadoExistenteException;
    void eliminarPorDni(String dni) throws DatoInvalidoException, AsociadoNotFoundException;
    void guardarTodos(HashMap<String, AsociadoDTO> mapa);
    void vaciarTabla();
    void actualizar(AsociadoDTO a) throws AsociadoNotFoundException;
}
