package inventariodistribuido;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.MessageConsumer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.jms.Message;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumidor {

    // Método que se encarga de recibir los mensajes de la cola JMS (Java Message Service)
    public static void recibirMensajes() throws Exception {
        // Creamos una fábrica de conexiones para ActiveMQ, indicando la URL del servidor
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        
        // Establecemos la conexión con ActiveMQ
        Connection connection = factory.createConnection();
        connection.start(); // Iniciamos la conexión

        // Creamos una sesión en modo transaccional desactivado y con reconocimiento automático de mensajes
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Definimos el destino, en este caso una cola llamada "transacciones"
        Destination destino = session.createQueue("transacciones");

        // Creamos un consumidor para leer los mensajes de la cola
        MessageConsumer consumer = session.createConsumer(destino);
        
        // El consumidor espera a recibir un mensaje de la cola
        Message mensaje = consumer.receive(); // Espera hasta recibir un mensaje

        // Si el mensaje es del tipo TextMessage, lo procesamos
        if (mensaje instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) mensaje;  // Convertimos el mensaje a tipo TextMessage
            String texto = textMessage.getText();  // Obtenemos el contenido del mensaje como texto
            System.out.println("✅ Mensaje recibido: " + texto);

            // Separamos el mensaje en el tipo de transacción (Compra o Venta) y los datos de la transacción
            String tipo = texto.split(":")[0].trim(); // Compra o Venta
            String datos = texto.split(":")[1];

            // Separamos los datos de la transacción por comas y extraemos la información relevante
            String[] partes = datos.split(",");
            int idProducto = Integer.parseInt(partes[0].split("=")[1].trim()); // Extraemos el ID del producto
            int cantidad = Integer.parseInt(partes[1].split("=")[1].trim());    // Extraemos la cantidad
            double precio = Double.parseDouble(partes[2].split("=")[1].trim());  // Extraemos el precio

            int idSucursal = 1; // Asignamos un ID de sucursal fijo (esto puede ser dinámico si es necesario)

            // Creamos un objeto Transaccion para almacenar la información de la transacción
            Transaccion transaccion = new Transaccion();
            transaccion.setTipo(tipo);  // Establecemos el tipo de transacción (Compra/Venta)
            transaccion.setIdSucursal(idSucursal);  // Establecemos el ID de la sucursal
            transaccion.setIdProducto(idProducto);  // Establecemos el ID del producto
            transaccion.setCantidad(cantidad);  // Establecemos la cantidad del producto
            transaccion.setPrecioUnitario(precio);  // Establecemos el precio del producto

            // Llamamos al método para guardar la transacción en la base de datos
            guardarEnBD(transaccion);
        }

        // Cerramos la sesión y la conexión después de procesar el mensaje
        session.close();
        connection.close();
    }

    // 👉 Método main para correr el código directamente, en espera de mensajes
    public static void main(String[] args) {
        try {
            System.out.println("⏳ Esperando mensaje...");
            recibirMensajes();  // Llamamos al método para recibir mensajes
        } catch (Exception e) {
            e.printStackTrace();  // Si ocurre un error, lo mostramos en la consola
        }
    }

    // Método para guardar la transacción en la base de datos
    public static void guardarEnBD(Transaccion transaccion) {
        try {
            // Establecemos la conexión con la base de datos
            java.sql.Connection conexion = ConexionDB.conectar();

            // Preparamos la consulta SQL para insertar la transacción en la tabla Transacciones
            String sql = "INSERT INTO Transacciones (tipo, id_sucursal, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?, ?)";
            java.sql.PreparedStatement stmt = conexion.prepareStatement(sql);

            // Asignamos los valores de la transacción a los parámetros de la consulta SQL
            stmt.setString(1, transaccion.getTipo());
            stmt.setInt(2, transaccion.getIdSucursal());
            stmt.setInt(3, transaccion.getIdProducto());
            stmt.setInt(4, transaccion.getCantidad());
            stmt.setDouble(5, transaccion.getPrecioUnitario());

            // Ejecutamos la consulta para guardar la transacción en la base de datos
            stmt.executeUpdate();
            System.out.println("✅ Transacción guardada en la base de datos.");
        } catch (Exception e) {
            // Si ocurre un error al guardar en la base de datos, mostramos el error
            System.out.println("❌ Error al guardar en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
