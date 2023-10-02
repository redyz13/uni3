import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger("global");

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            logger.info("Socket istanziato, attesa di connessioni...");

            Socket socket = serverSocket.accept();
            logger.info("Accettata una connessione...");

            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

            String nome = (String) is.readObject();

            logger.info("Ricevuto <" + nome + ">");

            os.writeObject("Hello " + nome);

            socket.close();
        } catch (EOFException e) {
            logger.severe("Problemi con la connessione: " + e.getMessage());
        } catch (Throwable t) {
            logger.severe("Lanciata Throwable: " + t.getMessage());
        }
    }
}
