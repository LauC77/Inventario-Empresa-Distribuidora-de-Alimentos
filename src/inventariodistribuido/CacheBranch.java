package inventariodistribuido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class CacheBranch {

    // Usamos un HashMap para almacenar las sucursales en memoria (caché)
    private static HashMap<Integer, String> sucursales = new HashMap<>();

    // Este método carga las sucursales desde la base de datos y las guarda en el caché
    public static void cargarSucursales() {
        try {
            // Establecemos una conexión con la base de datos
            Connection conn = ConexionDB.conectar();

            // Creamos una declaración (Statement) para ejecutar las consultas SQL
            Statement stmt = conn.createStatement();

            // Realizamos una consulta SQL para obtener el id_sucursal y nombre de las sucursales
            ResultSet rs = stmt.executeQuery("SELECT id_sucursal, nombre FROM Sucursales");

            // Iteramos sobre los resultados de la consulta
            while (rs.next()) {
                // Recuperamos el id y el nombre de la sucursal
                int id = rs.getInt("id_sucursal"); // Obtenemos el id de la sucursal
                String nombre = rs.getString("nombre"); // Obtenemos el nombre de la sucursal

                // Guardamos la sucursal en el HashMap (caché) usando el id como clave y el nombre como valor
                sucursales.put(id, nombre);
            }

            // Mostramos un mensaje de éxito
            System.out.println("Caché de sucursales cargado correctamente.");
        } catch (Exception e) {
            // Si ocurre un error, lo mostramos en la consola
            System.out.println("Error al cargar sucursales en caché: " + e.getMessage());
        }
    }

    // Este método permite agregar manualmente una sucursal al caché
    public static void agregarSucursal(int id, String nombre) {
        // Agregamos la sucursal al HashMap con el id como clave y el nombre como valor
        sucursales.put(id, nombre);
    }
}
