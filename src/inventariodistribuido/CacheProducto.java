package inventariodistribuido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class CacheProducto {

    // Usamos un HashMap para almacenar los productos en memoria (caché)
    private static HashMap<Integer, String> productosCache = new HashMap<>();

    // Método para agregar productos manualmente (por si lo necesitas en otros casos)
    public static void agregarProducto(int id, String nombre) {
        // Se agrega un producto al caché utilizando el id como clave y el nombre como valor
        productosCache.put(id, nombre);
    }

    // Este método carga todos los productos desde la base de datos y los guarda en el caché
    public static void cargarProductos() {
        try {
            // Establecemos una conexión con la base de datos
            Connection conn = ConexionDB.conectar();

            // Creamos una declaración (Statement) para ejecutar la consulta SQL
            Statement stmt = conn.createStatement();

            // Realizamos la consulta para obtener el id_producto y nombre de los productos
            ResultSet rs = stmt.executeQuery("SELECT id_producto, nombre FROM productos");

            // Iteramos sobre los resultados de la consulta
            while (rs.next()) {
                // Recuperamos el id y el nombre de cada producto
                int id = rs.getInt("id_producto"); // Obtenemos el id del producto
                String nombre = rs.getString("nombre"); // Obtenemos el nombre del producto

                // Guardamos el producto en el HashMap (caché)
                productosCache.put(id, nombre);
            }

            // Mostramos un mensaje de éxito cuando los productos se cargan correctamente
            System.out.println("Productos cargados en caché correctamente.");
        } catch (Exception e) {
            // Si ocurre un error, lo capturamos y lo mostramos en consola
            System.out.println("Error al cargar productos en caché: " + e.getMessage());
        }
    }

    // Este método obtiene el nombre de un producto desde el caché dado su id
    public static String obtenerNombreProducto(int id) {
        // Devuelve el nombre del producto almacenado en el caché utilizando el id
        return productosCache.get(id);
    }

    // Puedes agregar otros métodos como eliminar, actualizar, listar, etc.
}
