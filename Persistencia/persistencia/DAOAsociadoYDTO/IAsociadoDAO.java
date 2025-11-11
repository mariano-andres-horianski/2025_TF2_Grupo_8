package persistencia.DAOAsociadoYDTO;

import java.util.HashMap;
import persistencia.Excepciones.*;

public interface IAsociadoDAO {
    HashMap<String, AsociadoDTO> obtenerTodosMap();
    void agregar(AsociadoDTO a) throws DatoInvalidoException, AsociadoExistenteException;
    void eliminarPorDni(String dni) throws DatoInvalidoException, AsociadoNotFoundException;
    void guardarTodos(HashMap<String, AsociadoDTO> mapa);
    void vaciarTabla();
}
