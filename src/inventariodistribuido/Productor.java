package inventariodistribuido;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Productor {

    // Método para enviar el mensaje con la transacción
    public static void enviarMensaje(String mensaje) throws Exception {
        // Crear la conexión al broker ActiveMQ
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try (Connection connection = factory.createConnection()) {
            connection.start();

            // Crear sesión sin transacciones, con auto acknowledge
            try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {

                // Crear una cola llamada "transacciones"
                Destination destino = session.createQueue("transacciones");

                // Crear el productor de mensajes
                MessageProducer producer = session.createProducer(destino);

                // Crear el mensaje y asignarlo a la variable
                TextMessage message = session.createTextMessage(mensaje);

                // Enviar el mensaje a la cola
                producer.send(message);

                System.out.println("✅ Mensaje enviado desde Productor: " + mensaje);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al enviar el mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método main para enviar un mensaje de ejemplo
    public static void main(String[] args) {
        try {
            // Crear un mensaje con los datos de la transacción (ejemplo de compra)
            String mensaje = "Compra: producto=102, cantidad=30, precio=500";

            // Llamar al método para enviar el mensaje
            enviarMensaje(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

