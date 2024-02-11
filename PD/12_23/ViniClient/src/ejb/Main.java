package ejb;

import java.util.List;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Main {
    public static void main(String[] args) throws NamingException {
        // Punto a)
        Context ctx = new InitialContext();      
        
        VinoEJBRemote vinoEJB = (VinoEJBRemote) ctx.lookup("java:"
                + "global/ViniBean/VinoEJB!ejb.VinoEJBRemote");
        
        System.out.println("Lista vini:");
        List<Vino> vini = vinoEJB.findVini();
        for (Vino v : vini)
            System.out.println("---" + v);
        
        System.out.println("\nLista vini di Salerno:");
        vini = vinoEJB.searchProvenienza("Salerno");
        for (Vino v : vini)
            System.out.println("---" + v);
        
        System.out.println("\nLista vini del vitigno 1:");
        vini = vinoEJB.searchVitigno("Vitigno1");
        System.out.println("Risultato:");
        for (Vino v : vini)
            System.out.println("---" + v);
        
        System.out.println("\nLista vini dal prezzo di 30:");
        vini = vinoEJB.searchPrezzo(30f);
        System.out.println("Risultato:");
        for (Vino v : vini)
            System.out.println("---" + v);
        
        System.out.println("\nLista vini con prezzo minore di 40:");
        vini = vinoEJB.searchPrezzoMinoreDi(40f);
        System.out.println("Risultato:");
        for (Vino v : vini)
            System.out.println("---" + v);
        
        System.out.println("\nVino con ID 1:");
        System.out.println("Risultato:");
        System.out.println("---" + vinoEJB.searchId(1L));
        
        // Punto b)
        ConnectionFactory connectionFactory = (ConnectionFactory) ctx.
            lookup("jms/javaee7/ConnectionFactory");
        Destination topic = (Destination) ctx.lookup("jms/javaee7/Topic");   
        
        vini = vinoEJB.findVini();
        
        // Aggiornamenti numero bottiglie e stato acquisti
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            JMSProducer producer = jmsContext.createProducer();
            for (Vino v : vini) {
                producer.send(topic, v);
            }
        }        
    }   
}
