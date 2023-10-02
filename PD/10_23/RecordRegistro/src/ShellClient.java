import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class ShellClient {
    private static final Logger logger = Logger.getLogger("globlal");
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final String ERRORMSG = "Cosa?";

    public static void main(String[] args) {
        String host = args[0];
        String cmd;

        try {
            while (!(cmd = ask(">> ")).equals("quit")) {
                if (cmd.equals("inserisci")) {
                    System.out.println("Inserire i dati: ");
                    String nome = ask("Nome: ");
                    String indirizzo = ask("Indirizzo: ");

                    RecordRegistro r = new RecordRegistro(nome ,indirizzo);
                    Socket socket = new Socket(host, 7000);

                    ObjectOutputStream sock_out = new ObjectOutputStream(socket.getOutputStream());

                    sock_out.writeObject(r);
                    sock_out.flush();

                    socket.close();
                } else if (cmd.equals("cerca")) {
                    System.out.println("Inserire il nome per la ricerca: ");
                    String nome = ask("Nome: ");
                    RecordRegistro r = new RecordRegistro(nome, null);
                    Socket socket = new Socket(host, 7000);


                    ObjectOutputStream sock_out = new ObjectOutputStream(socket.getOutputStream());
                    sock_out.writeObject(r);
                    sock_out.flush();

                    ObjectInputStream sock_in = new ObjectInputStream(socket.getInputStream());

                    RecordRegistro result = (RecordRegistro) sock_in.readObject();

                    if (result != null)
                        System.out.println("Indirizzo: " + result.indirizzo());
                    else
                        System.out.println("Record assente");

                    socket.close();
                }
                else
                    System.out.println(ERRORMSG);
            }
        } catch (Throwable t) {
            logger.severe("Lanciata Throwable: " + t.getMessage());
        }

        System.out.println("Ciaoo <3");
    }

    private static String ask(String prompt) throws IOException {
        System.out.print(prompt);
        return in.readLine();
    }
}
