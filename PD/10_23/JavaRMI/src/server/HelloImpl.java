package server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class HelloImpl extends UnicastRemoteObject implements Hello {
    private static final Logger logger = Logger.getLogger("global");

    protected HelloImpl() throws RemoteException {}

    @Override
    public String dimmiQualcosa(String daChi) throws RemoteException {
        logger.info("Sto salutando " + daChi);
        return "Ciao!";
    }

    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());

        try {
            logger.info("Creo l'oggetto remoto...");
            HelloImpl obj = new HelloImpl();
            logger.info("Ne effettuo il rebind...");
            Naming.rebind("JavaRMI", obj);
            logger.info("Pronto!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
