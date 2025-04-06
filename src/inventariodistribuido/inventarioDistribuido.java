package inventariodistribuido;

public class inventarioDistribuido {
    public static void main(String[] args) {
        // Se llama a la función conectar() para realizar la conexión a la base de datos.
        ConexionDB.conectar(); // Solo prueba la conexión

        // Se cargan las sucursales desde la base de datos a la caché para su uso posterior.
        CacheBranch.cargarSucursales(); 
        
        // Se cargan los productos desde la base de datos a la caché.
        CacheProducto.cargarProductos(); 

        // Aquí mostramos el nombre del producto con ID 102 como prueba, obtenido de la caché.
        String producto = CacheProducto.obtenerNombreProducto(102);
        
        // Se imprime en consola el nombre del producto con ID 102
        System.out.println("Producto en caché con ID 102: " + producto);
    }
}
