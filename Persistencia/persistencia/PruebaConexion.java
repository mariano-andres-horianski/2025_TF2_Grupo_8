package persistencia;

import java.sql.SQLException;
import java.util.HashMap;
import persistencia.Excepciones.*;
import persistencia.DAOAsociadoYDTO.*;
import persistencia.BasedeDatos.*;

public class PruebaConexion {

    public static void main(String[] args) {
    	BDConexion bd;
        try {
        	bd = BDConexion.getInstance();
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

            System.out.println("\n---- Eliminando el DNI 111 ----");
            dao.eliminarPorDni("111");

            System.out.println("\n---- Listando nuevamente ----");
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
                System.out.println(a.getDni() + " - " + a.getNya() + " - " + a.getCiudad());
            }
        }
    }
}
