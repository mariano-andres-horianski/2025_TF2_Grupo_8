package persistencia;

import java.sql.SQLException;
import java.util.HashMap;
import persistencia.Excepciones.*;
import persistencia.DAOAsociadoYDTO.*;
import persistencia.BasedeDatos.*;

public class PruebaActualizar {

    public static void main(String[] args) {
    	BDConexion bd;
        try {
        	// Obtener la conexión
        	bd = BDConexion.getInstance();

        	// Borra la tabla si existe (para comenzar limpio)
        	try {
        		bd.getSentencia().executeUpdate("DROP TABLE IF EXISTS asociados");
        		System.out.println("Tabla 'asociados' borrada (si existía).");
        	} catch (SQLException e) {
        		System.out.println("No se pudo borrar la tabla 'asociados' o no existía:");
        		e.printStackTrace();
        	}

            // Crear el DAO (el constructor recreará la tabla si no existe)
            IAsociadoDAO dao = new AsociadoDAOMySQL(bd);
            
            // Crear algunos asociados de ejemplo
            AsociadoDTO a1 = new AsociadoDTO();
            a1.setDni("111");
            a1.setNya("Ana López");
            a1.setCiudad("Mar del Plata");
            a1.setTelefono("2235551111");
            a1.setDomicilioStr("Av. Colón 123");

            AsociadoDTO a2 = new AsociadoDTO();
            a2.setDni("222");
            a2.setNya("Carlos Gómez");
            a2.setCiudad("Balcarce");
            a2.setTelefono("2265552222");
            a2.setDomicilioStr("San Martín 456");

            System.out.println("---- Agregando asociados ----");
            dao.agregar(a1);
            dao.agregar(a2);

            System.out.println("\n---- Listando todos ----");
            imprimirMapa(dao.obtenerTodosMap());

            // Modificar uno de los asociados (por ejemplo modificar ciudad y teléfono de 222)
            AsociadoDTO a2mod = new AsociadoDTO();
            a2mod.setDni("222"); // identificador
            a2mod.setNya("Carlos Gómez"); // puede mantenerse igual o cambiar
            a2mod.setCiudad("Mar del Plata"); // nueva ciudad
            a2mod.setTelefono("2269998888"); // nuevo teléfono
            a2mod.setDomicilioStr("San Martín 456"); // misma dirección

            System.out.println("\n---- Actualizando asociado DNI 222 ----");
            dao.actualizar(a2mod);

            System.out.println("\n---- Listando luego de la actualización ----");
            imprimirMapa(dao.obtenerTodosMap());

            bd.close();
        } catch (DatoInvalidoException | AsociadoExistenteException | 
                 AsociadoNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void imprimirMapa(HashMap<String, AsociadoDTO> mapa) {
        if (mapa.isEmpty()) {
            System.out.println("(sin registros)");
        } else {
            for (AsociadoDTO a : mapa.values()) {
                System.out.println(a.getDni() + " - " + a.getNya() + " - " + a.getCiudad() + " - " + a.getTelefono());
            }
        }
    }
}
