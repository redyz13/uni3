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
        
        IstitutoEJBRemote istitutoEJB = (IstitutoEJBRemote) ctx.lookup("java:"
                + "global/PCTOBean/IstitutoEJB!ejb.IstitutoEJBRemote");
        
        System.out.println("Lista istituti:");
        List<Istituto> istituti = istitutoEJB.findIstituti();
        for (Istituto i : istituti)
            System.out.println("---" + i);
        
        System.out.println("\nLista istituti di tipologia Tipo1:");
        System.out.println("Risultato:");
        istituti = istitutoEJB.searchTipologia("Tipo1");
        for (Istituto i : istituti)
            System.out.println("---" + i);
        
        System.out.println("\nLista istituti della provincia di Provincia2:");
        istituti = istitutoEJB.searchProvincia("Provincia2");
        System.out.println("Risultato:");
        for (Istituto i : istituti)
            System.out.println("---" + i);
        
        System.out.println("\nIstituto con ID 1:");
        System.out.println("Risultato:");
        System.out.println("---" + istitutoEJB.searchID(1L));
        
        // Punto b)
        ConnectionFactory connectionFactory = (ConnectionFactory) ctx.
            lookup("jms/javaee7/ConnectionFactory");
        Destination topic = (Destination) ctx.lookup("jms/javaee7/Topic");   
        
        istituti = istitutoEJB.findIstituti();
        
        // Aggiornamenti numero studenti e stato gold core
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            JMSProducer producer = jmsContext.createProducer();
            for (Istituto i : istituti) {
                producer.send(topic, i);
            }
        }
    }
}
