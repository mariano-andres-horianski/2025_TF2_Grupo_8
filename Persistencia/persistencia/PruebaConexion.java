package persistencia;

import java.sql.SQLException;
import java.util.List;

public class PruebaConexion {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE CONEXIÓN Y DAO - INICIO ===");

        try {
            AsociadoDAOMySQL dao = new AsociadoDAOMySQL();

            System.out.println("\n1) Listado inicial de asociados:");
            List<AsociadoDTO> inicial = dao.obtenerTodos();
            System.out.println("   Total: " + inicial.size());
            for (AsociadoDTO a : inicial) {
                System.out.println("   - " + a.getDni() + " -> " + a.getNya());
            }

            // Preparar asociados de prueba
            AsociadoDTO a1 = new AsociadoDTO();
            a1.setDni("30111222");
            a1.setNya("Rodriguez, Mateo");
            a1.setCiudad("Mar del Plata");
            a1.setTelefono("223-1111222");
            a1.setDomicilioStr("Calle Uno 1");

            AsociadoDTO a2 = new AsociadoDTO();
            a2.setDni("30999888");
            a2.setNya("Martinez, Lucia");
            a2.setCiudad("Mar del Plata");
            a2.setTelefono("223-998877");
            a2.setDomicilioStr("Calle Dos 2");

            System.out.println("\n2) Insertando asociados de prueba:");
            try {
                dao.agregar(a1);
                System.out.println("   OK - Agregado: " + a1.getDni());
            } catch (AsociadoExistenteException ex) {
                System.out.println("   SKIP - Ya existe: " + ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("   ERROR SQL al agregar a1: " + ex.getMessage());
            }

            try {
                dao.agregar(a2);
                System.out.println("   OK - Agregado: " + a2.getDni());
            } catch (AsociadoExistenteException ex) {
                System.out.println("   SKIP - Ya existe: " + ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("   ERROR SQL al agregar a2: " + ex.getMessage());
            }

            System.out.println("\n3) Listado luego de inserts:");
            List<AsociadoDTO> luegoInsert = dao.obtenerTodos();
            System.out.println("   Total: " + luegoInsert.size());
            for (AsociadoDTO a : luegoInsert) {
                System.out.println("   - " + a.getDni() + " -> " + a.getNya());
            }

            System.out.println("\n4) Probando inserción duplicada (debe fallar):");
            AsociadoDTO dup = new AsociadoDTO();
            dup.setDni("30111222");
            dup.setNya("Rodriguez, Mateito");
            try {
                dao.agregar(dup);
                System.out.println("   ERROR - Se insertó duplicado (no debería).");
            } catch (AsociadoExistenteException ex) {
                System.out.println("   OK - Duplicado detectado: " + ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("   ERROR SQL al intentar duplicado: " + ex.getMessage());
            }

            System.out.println("\n5) Eliminando un asociado (dni: " + a1.getDni() + "):");
            try {
                dao.eliminarPorDni(a1.getDni());
                System.out.println("   OK - Eliminado: " + a1.getDni());
            } catch (AsociadoNotFoundException ex) {
                System.out.println("   SKIP - No encontrado: " + ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("   ERROR SQL al eliminar: " + ex.getMessage());
            }

            System.out.println("\n6) Intentando eliminar nuevamente (debe fallar):");
            try {
                dao.eliminarPorDni(a1.getDni());
                System.out.println("   ERROR - Se eliminó dos veces (no debería).");
            } catch (AsociadoNotFoundException ex) {
                System.out.println("   OK - Eliminación repetida detectada: " + ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("   ERROR SQL al eliminar 2: " + ex.getMessage());
            }

            System.out.println("\n7) Listado final de asociados:");
            List<AsociadoDTO> finalList = dao.obtenerTodos();
            System.out.println("   Total: " + finalList.size());
            for (AsociadoDTO a : finalList) {
                System.out.println("   - " + a.getDni() + " -> " + a.getNya());
            }

            System.out.println("\n=== PRUEBA DE CONEXIÓN Y DAO - FIN ===");

        } catch (SQLException e) {
            System.err.println("❌ Error general en pruebas DAO:");
            e.printStackTrace();
        } finally {
            try {
                BDConexion.getInstance().close();
                System.out.println("\nConexión cerrada correctamente (finally).");
            } catch (SQLException e) {
                // Si no se puede obtener instancia o cerrar, lo informamos por consola
                System.err.println("⚠️ No se pudo cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
