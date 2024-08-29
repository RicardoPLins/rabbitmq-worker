import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class NewTask {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try (
            Connection connection = connectionFactory.newConnection();
            Channel canal = connection.createChannel();
        ) {
            String NOME_FILA = "plica";
            String mensagem = String.join(" ", args);

            canal.queueDeclare(NOME_FILA, false, false, false, null);

            canal.basicPublish("", NOME_FILA, null, mensagem.getBytes("UTF-8"));
            System.out.println("[x] Enviado '" + mensagem + "'");
        }
    }
}
