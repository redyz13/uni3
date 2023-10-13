package client;

import server.Hello;

import java.rmi.Naming;
import java.util.logging.Logger;

public class HelloClient {
    private static final Logger logger = Logger.getLogger("global");

    public static void main(String[] args) {
        try {
            logger.info("Creo l'oggetto remoto...");
            Hello obj = (Hello) Naming.lookup("rmi://localhost/JavaRMI");
            logger.info("Trovato! Invoco metodo...");
            String risultato = obj.dimmiQualcosa("Paolo");
            System.out.println("Ricevuto: " + risultato);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
