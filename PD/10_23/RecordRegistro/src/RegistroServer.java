import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Logger;

public class RegistroServer {
    private static final Logger logger = Logger.getLogger("globlal");

    public static void main(String[] args) {
        HashMap<String, RecordRegistro> hash = new HashMap<>();
        Socket socket = null;

        System.out.println("In attesa...");

        try {
            ServerSocket serverSocket = new ServerSocket(7000);

            while (true) {
                socket = serverSocket.accept();
                System.out.println("Operazione effettuata dal client: " + socket.getInetAddress());

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                RecordRegistro record = (RecordRegistro) in.readObject();

                if (record.indirizzo() != null)
                    hash.put(record.nome(), record);
                else {
                    RecordRegistro res = hash.get(record.nome());
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                    out.writeObject(res);
                    out.flush();
                }

                socket.close();

                if (record.nome().equals("quit")) {
                    serverSocket.close();
                    System.exit(0);
                }
            }
        } catch (Throwable t) {
            logger.severe(t.getMessage());
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                System.exit(1);
            }
        }
    }
}
