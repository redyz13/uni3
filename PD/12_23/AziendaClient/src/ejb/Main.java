package ejb;

import java.util.List;
import java.util.Scanner;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {
    public static void main(String[] args) throws NamingException {
        Context ctx = new InitialContext();
        AziendaEJBRemote aziendaEJB = (AziendaEJBRemote) ctx.lookup("java:global/"
                + "AziendaBean/AziendaEJB!ejb.AziendaEJBRemote");
        List<Azienda> aziende = aziendaEJB.findAll();
        for (Azienda a : aziende)
            System.out.println("--- " + a);
        System.out.print("Inserire una tipologia: ");
        Scanner sc = new Scanner(System.in);
        aziende = aziendaEJB.searchByTipologia(sc.nextLine());
        for (Azienda a : aziende)
            System.out.println("--- " + a);
        
        // Punto b
        System.out.print("Inserire l'ID: ");
        Long id = sc.nextLong();
        System.out.print("Inserire le persone da assumere: ");
        int pers = sc.nextInt();
        AziendaDTO aziendaDTO = new AziendaDTO(id, pers);
        
        ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/javaee7/ConnectionFactory");
        Destination topic = (Destination) ctx.lookup("jms/javaee7/Topic");
        try (JMSContext jmsContext = cf.createContext()) {
            JMSProducer producer = jmsContext.createProducer();
            producer.send(topic, aziendaDTO);
        }
    }
}
