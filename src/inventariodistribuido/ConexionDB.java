package inventariodistribuido;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Definimos las constantes con los datos de conexión a la base de datos.
    // URL: Dirección de la base de datos y puerto, en este caso estamos usando el puerto 3308.
    private static final String URL = "jdbc:mysql://localhost:3308/inventario_db";
    // Usuario para la conexión, normalmente es "root" para bases de datos locales.
    private static final String USER = "root"; 
    // Contraseña de acceso a la base de datos.
    private static final String PASSWORD = "Laura307."; 

    // Método estático que se encarga de establecer la conexión con la base de datos.
    public static Connection conectar() {
        Connection conn = null; // Variable que almacenará la conexión a la base de datos.
        try {
            // Establecemos la conexión utilizando DriverManager con la URL, usuario y contraseña proporcionados.
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // Si la conexión es exitosa, mostramos un mensaje de confirmación.
            System.out.println("✅ Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            // Si ocurre un error, mostramos un mensaje de error con la descripción del problema.
            System.out.println("❌ Error al conectar: " + e.getMessage());
        }
        // Retornamos la conexión, que puede ser null si no se ha podido conectar.
        return conn;
    }
}
